package com.highpeak.gbi.webservices.services.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highpeak.gbi.datastore.model.*;
import com.highpeak.gbi.datastore.repository.AddressRepository;
import com.highpeak.gbi.datastore.repository.BloodRequestRepository;
import com.highpeak.gbi.datastore.repository.UserModelRepository;
import com.highpeak.gbi.datastore.repository.UserToAddressRepository;
import com.highpeak.gbi.webservices.entities.Address;
import com.highpeak.gbi.webservices.entities.MailBean;
import com.highpeak.gbi.webservices.entities.SMSBean;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.services.UserServices;
import com.highpeak.gbi.webservices.uiresponse.DataException;
import com.highpeak.gbi.webservices.utils.constant.Constant;
import com.highpeak.gbi.webservices.utils.date.DateUtil;
import com.highpeak.gbi.webservices.utils.distance.DistanceUtil;
import com.highpeak.gbi.webservices.utils.mail.GmailSenderUtil;
import com.highpeak.gbi.webservices.utils.sms.SMSSenderUtil;
import com.highpeak.gbi.webservices.utils.validator.Validator;

@Service
@Transactional( rollbackFor = Exception.class )
public class UserServicesImpl implements UserServices {

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

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

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Autowired
    private DistanceUtil distanceUtil;

    @Autowired
    private Validator validator;

    @Autowired
    private DateUtil dateUtil;

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
            // validate userDetails
            validator.validateRegistrationDetails(userDetailsBean);

            // Check already active user present in the database
            Optional<UserModel> userModelOptional = userModelRepository
                    .findByMobileNumberAndIsActiveTrue(userDetailsBean.getMobileNumber());
            if( userModelOptional.isPresent() )
            {
                throw new DataException(Constant.EXCEPTION, Constant.DUPLICATE_DONAR, HttpStatus.BAD_REQUEST);
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
            sendAlertInAThread(savedUser);

            return savedUser;
        }

        catch( DataException e )
        {
            logger.error(Constant.ERROR, e);
            throw e;
        }
        catch( Exception e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException(Constant.EXCEPTION, Constant.ERROR_SAVING_USER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendAlertInAThread( UserModel savedUser )
    {
        new Thread(() -> {
            try
            {
                SMSBean smsBean = new SMSBean();
                smsBean.setTos(Arrays.asList(savedUser.getMobileNumber()));
                smsBean.setSmsBody("Registration Successfull");
                smsSenderUtil.sendSms(smsBean);

                logger.info("Sending registration success email to {}", smsBean.getTos().get(0));
                sendConfirmationEmail(savedUser.getEmailId());
            }
            catch( DataException e )
            {
                logger.error(Constant.ERROR, e);
            }
        }).start();

    }

    /**
     * To send confirmation e-mail
     * 
     * @param toAddress
     */
    private void sendConfirmationEmail( String toAddress ) throws DataException
    {
        MailBean mailBean = new MailBean();
        mailBean.setTo(new HashSet<>(Arrays.asList(toAddress)));
        mailBean.setSubject("Gratitude from givebloodindia.org");
        mailBean.setBody("You have been registered as a blood donar. Thank you for the support");
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
            userModel.setBloodGroup(userDetailsBean.getBloodGroup().trim());
            userModel.setEmailId(userDetailsBean.getEmailId().trim());
            userModel.setMobileNumber(userDetailsBean.getMobileNumber().trim());
            userModel.setFirstName(userDetailsBean.getFirstName().trim());
            userModel.setLastName(userDetailsBean.getLastName().trim());
            userModel.setGender(userDetailsBean.getGender().trim());
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
            logger.error(Constant.ERROR, e);
            throw new DataException(Constant.EXCEPTION, Constant.ERROR_SAVING_USER, HttpStatus.INTERNAL_SERVER_ERROR);
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
                AddressModel savedAddress;
                // Check whether the same address is already present in database
                Optional<AddressModel> addressModelOptional = addressRepository
                        .findByLatitudeAndLongitude(address.getLatitude(), address.getLongitude());
                if( addressModelOptional.isPresent() )
                {
                    savedAddress = addressModelOptional.get();
                }
                else
                {
                    //Create and save new address
                    AddressModel addressModel = new AddressModel();
                    addressModel.setCity(address.getCity());
                    addressModel.setLatitude(address.getLatitude());
                    addressModel.setLongitude(address.getLongitude());
                    addressModel.setZip(address.getZip());

                    savedAddress = addressRepository.save(addressModel);
                }
                // Map User to Address
                UserToAddress userToAddress = new UserToAddress();
                userToAddress.setUser(user);
                userToAddress.setAddress(savedAddress);
                userToAddressRepository.save(userToAddress);

            }
        }
        catch( Exception e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException(Constant.EXCEPTION, Constant.ERROR_SAVING_USER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * To save requester address
     * 
     * @param addressList
     * @return
     */
    private AddressModel saveAddress( List<Address> addressList )
    {
        Address address = addressList.get(0);// Only one requesting address is possible
        AddressModel addressModel = new AddressModel();
        addressModel.setZip(address.getZip());
        addressModel.setLongitude(address.getLongitude());
        addressModel.setLatitude(address.getLatitude());
        addressModel.setCity(address.getCity());

        return addressRepository.save(addressModel);
    }

    /**
     * Save requester's details
     * 
     * @param userDetailsBean
     * @param savedAddress
     * @return
     */
    private BloodRequestModel saveRequesterData( UserDetailsBean userDetailsBean, AddressModel savedAddress )
    {
        BloodRequestModel bloodRequestModel = new BloodRequestModel();
        bloodRequestModel.setFirstName(userDetailsBean.getFirstName());
        bloodRequestModel.setLastName(userDetailsBean.getLastName());
        bloodRequestModel.setEmailId(userDetailsBean.getEmailId());
        bloodRequestModel.setMobileNumber(userDetailsBean.getMobileNumber());
        bloodRequestModel.setBloodGroup(userDetailsBean.getBloodGroup());
        Calendar requiredDate = DateUtil.getUTCCalenderInstance(userDetailsBean.getBloodRequiredDate());
        bloodRequestModel.setRequiredDate(requiredDate);
        bloodRequestModel.setUnits(userDetailsBean.getNumberOfUnits());
        bloodRequestModel.setHospital(userDetailsBean.getHospital());
        bloodRequestModel.setComponent(userDetailsBean.getComponent());
        bloodRequestModel.setAddressModel(savedAddress);
        return bloodRequestRepository.save(bloodRequestModel);
    }

    /**
     * @param userDetailsBean
     * @return
     * @throws DataException
     */
    @Override
    public Integer requestBlood( UserDetailsBean userDetailsBean ) throws DataException
    {
        try
        {
            validator.validateRequesterDetails(userDetailsBean);
            //Save the address from where user is requesting for blood
            AddressModel savedAddress = saveAddress(userDetailsBean.getAdresses());
            //Save user's data
            saveRequesterData(userDetailsBean, savedAddress);

            return sendRequestMessage(userDetailsBean);// send message to donars within the range

        }
        catch( DataException e )
        {
            logger.error(Constant.ERROR, e);
            throw e;
        }
        catch( Exception e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException(Constant.EXCEPTION, Constant.SOMETHING_WENT_WRONG,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find blood group matching users
     * 
     * @param requestingAddress
     * @param radius
     * @param bloodGroups
     * @return
     * @throws DataException
     */
    private List<EmailPhone> getPotentialDonarsWithMatch( Address requestingAddress, Integer radius,
            List<String> bloodGroups ) throws DataException
    {
        try
        {
            return distanceUtil.fetchUsersWithinRadius(requestingAddress, radius, bloodGroups, true);
        }
        catch( DataException e )
        {
            logger.error(Constant.ERROR, e);
            throw e;
        }
    }

    /**
     * Get all reachable donars
     * 
     * @param requestingAddress
     * @param radius
     * @return
     * @throws DataException
     */
    private List<EmailPhone> getAllPotentialDonars( Address requestingAddress, Integer radius ) throws DataException
    {
        try
        {
            return distanceUtil.fetchUsersWithinRadius(requestingAddress, radius, Collections.emptyList(), false);
        }
        catch( DataException e )
        {
            logger.error(Constant.ERROR, e);
            throw e;
        }
    }

    /**
     * Choose users according to blood group matching criteria and send request alert
     * 
     * @param userDetailsBean
     * @return
     * @throws DataException
     */
    private Integer sendRequestMessage( UserDetailsBean userDetailsBean ) throws DataException
    {
        // User can request from one address at a time
        Address requestingAddress = userDetailsBean.getAdresses().get(0);
        List<EmailPhone> emailPhoneList = new ArrayList<>();
        try
        {
            switch ( userDetailsBean.getBloodGroup() )
            {
                case "AB+" :
                    // check donars within primary radius
                    emailPhoneList = getAllPotentialDonars(requestingAddress, Constant.PRIMARY_RADIUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        //increase the radius and search again
                        emailPhoneList = getAllPotentialDonars(requestingAddress, Constant.SECONDARY_RADIUS);
                    }

                    break;
                case "AB-" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_ABMINUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_ABMINUS);
                    }

                    break;
                case "B+" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_BPLUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_BPLUS);
                    }

                    break;
                case "B-" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_BMINUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_BMINUS);
                    }

                    break;
                case "A+" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_APLUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_APLUS);
                    }

                    break;
                case "A-" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_AMINUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_AMINUS);
                    }

                    break;
                case "O+" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_OPLUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_OPLUS);
                    }
                    break;
                case "O-" :
                    emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.PRIMARY_RADIUS,
                            Constant.BG_MATCH_OMINUS);
                    if( emailPhoneList.isEmpty() )
                    {
                        logger.info(Constant.NO_DONAR_FOUND);
                        emailPhoneList = getPotentialDonarsWithMatch(requestingAddress, Constant.SECONDARY_RADIUS,
                                Constant.BG_MATCH_OMINUS);
                    }

                    break;
                default :
                    break;

            }
            Set<String> toAdds = new HashSet<>();
            Set<String> mobileNumbers = new HashSet<>();

            SMSBean smsBean = new SMSBean();
            if( emailPhoneList.isEmpty() )
            {
                //No donar's found
                smsBean.setTos(Arrays.asList(userDetailsBean.getMobileNumber()));
                smsBean.setSmsBody(
                        Constant.NO_DONAR_FOUND + dateUtil.getStringDate(userDetailsBean.getBloodRequiredDate()));
                smsSenderUtil.sendSms(smsBean);
                return 0;
            }
            else
            {
                for( EmailPhone emailPhone : emailPhoneList )
                {
                    //removes duplicate emailIds
                    toAdds.add(emailPhone.getEmailId());
                    mobileNumbers.add(emailPhone.getMobileNumber());
                }
                smsBean.setTos(new ArrayList<>(mobileNumbers));
                smsBean.setSmsBody(
                        "Urgent blood needed on " + dateUtil.getStringDate(userDetailsBean.getBloodRequiredDate()));
                smsSenderUtil.sendSms(smsBean);
                //send e-mail
                MailBean mailBean = new MailBean();
                mailBean.setTo(toAdds);
                mailBean.setBody("Blood needed");
                mailBean.setSubject("Urgent blood needed");
                gmailSenderUtil.sendMail(mailBean);
                return mobileNumbers.size();
            }

        }
        catch( DataException e )
        {
            logger.error(Constant.ERROR, e);
            throw e;
        }
    }

}
