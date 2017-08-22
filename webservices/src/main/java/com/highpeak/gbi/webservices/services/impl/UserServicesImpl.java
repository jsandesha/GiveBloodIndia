package com.highpeak.gbi.webservices.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.highpeak.gbi.datastore.model.AddressModel;
import com.highpeak.gbi.datastore.model.UserModel;
import com.highpeak.gbi.datastore.model.UserToAddress;
import com.highpeak.gbi.datastore.repository.AddressRepository;
import com.highpeak.gbi.datastore.repository.UserModelRepository;
import com.highpeak.gbi.datastore.repository.UserToAddressRepository;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.entities.Address;
import com.highpeak.gbi.webservices.entities.MailBean;
import com.highpeak.gbi.webservices.entities.SMSBean;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.services.UserServices;
import com.highpeak.gbi.webservices.utils.Date.DateUtil;
import com.highpeak.gbi.webservices.utils.mail.GmailSenderUtil;
import com.highpeak.gbi.webservices.utils.sms.SMSSenderUtil;

@Service
@Transactional( rollbackFor = Exception.class )
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserToAddressRepository userToAddressRepository;

    @Autowired
    private GmailSenderUtil gmailSenderUtil;

    @Autowired
    private SMSSenderUtil smsSenderUtil;

    /**
     * Method to perform null check on the input and it's contents
     * 
     * @param userDetailsBean
     * @throws DataException
     */
    private void validateRegistrationDetails( UserDetailsBean userDetailsBean ) throws DataException
    {
        if( null == userDetailsBean )
        {
            throw new DataException("Exception", "Invalid input", HttpStatus.BAD_REQUEST);
        }
        if( null == userDetailsBean.getFirstName() || null == userDetailsBean.getMobileNumber()
                || null == userDetailsBean.getGender() || null == userDetailsBean.getAge() )
        {
            throw new DataException("Exception", "All Mandatory fields are required", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userDetailsBean
     * @return
     * @throws DataException
     */
    @Override
    public UserModel registerUser( UserDetailsBean userDetailsBean ) throws DataException
    {
        try
        {
            // validate input
            validateRegistrationDetails(userDetailsBean);

            // Check already active user present in the database
            Optional<UserModel> userModelOptional = userModelRepository
                    .findByMobileNumberAndIsActiveTrue(userDetailsBean.getMobileNumber());
            if( userModelOptional.isPresent() )
            {
                throw new DataException("Exception", "User already registered as donar", HttpStatus.BAD_REQUEST);
            }

            // Save user to db
            UserModel savedUser = saveUser(userDetailsBean);
            List<Address> addressModelList = userDetailsBean.getAdresses();
            // Save addresses to database
            if( !addressModelList.isEmpty() )
            {
                saveAddress(addressModelList, savedUser);
            }

            // send confirmation email on seperate thread
            new Thread(() -> {
                try
                {
                    SMSBean smsBean = new SMSBean();
                    smsSenderUtil.sendSms(smsBean);
                    System.out.println("Sending mail.......");
                    MailBean mailBean = new MailBean();
                    mailBean.setTo(userDetailsBean.getEmailId());
                    mailBean.setSubject("Gratitude from givebloodindia.org");
                    mailBean.setBody("You have been registered as a blood donar. Thank you for the support");
                    sendConfirmationEmail(mailBean);
                }
                catch( DataException e )
                {
                    e.printStackTrace();
                }
            }).start();
            return savedUser;
        }
        catch( DataException e )
        {
            e.printStackTrace();
            throw e;
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new DataException("Exception", "Something went wrong while saving user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * To send confirmation e-mail
     * 
     * @param mailBean
     */
    private void sendConfirmationEmail( MailBean mailBean ) throws DataException
    {
        gmailSenderUtil.sendMail(mailBean);
    }

    /**
     * @param userDetailsBean
     * @return
     * @throws DataException
     */
    private UserModel saveUser( UserDetailsBean userDetailsBean ) throws DataException
    {
        try
        {
            Calendar lastBloodDonationDate = null;
            UserModel userModel = new UserModel();
            userModel.setAge(userDetailsBean.getAge());
            userModel.setBloodGroup(userDetailsBean.getBloodGroup());
            userModel.setEmailId(userDetailsBean.getEmailId());
            userModel.setMobileNumber(userDetailsBean.getMobileNumber());
            userModel.setFirstName(userDetailsBean.getFirstName());
            userModel.setLastName(userDetailsBean.getLastName());
            userModel.setGender(userDetailsBean.getGender());
            userModel.setIsActive(true);
            userModel.setCreatedAt(DateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
            if( null != userDetailsBean.getLastBloodDonationDate() )
            {
                lastBloodDonationDate = DateUtil.getUTCCalenderInstance(userDetailsBean.getLastBloodDonationDate());
            }
            userModel.setLastDonationDate(lastBloodDonationDate);
            return userModelRepository.save(userModel);
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new DataException("Exception", "Something went wrong while saving user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param addressList
     * @param user
     * @throws DataException
     */
    private void saveAddress( List<Address> addressList, UserModel user ) throws DataException
    {
        try
        {
            for( Address address : addressList )
            {
                AddressModel addressModel = new AddressModel();
                addressModel.setCity(address.getCity());
                addressModel.setLatitude(address.getLatitude());
                addressModel.setLongitude(address.getLongitude());
                addressModel.setZip(address.getZip());

                AddressModel savedAddress = addressRepository.save(addressModel);

                UserToAddress userToAddress = new UserToAddress();
                userToAddress.setUser(user);
                userToAddress.setAddress(savedAddress);
                userToAddressRepository.save(userToAddress);

            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new DataException("Exception", "Something went wrong while saving user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
