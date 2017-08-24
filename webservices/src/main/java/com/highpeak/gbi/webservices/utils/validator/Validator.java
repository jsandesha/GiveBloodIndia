package com.highpeak.gbi.webservices.utils.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.uiresponse.DataException;
import com.highpeak.gbi.webservices.utils.constant.Constant;

/**
 * @author sandesha, Created on 24/08/17
 */
@Component
public class Validator {

    /**
     * Method to perform null check on the input and it's contents
     *
     * @param userDetailsBean
     * @throws DataException
     */
    public void validateRegistrationDetails( UserDetailsBean userDetailsBean ) throws DataException
    {
        if( null == userDetailsBean )
        {
            throw new DataException(Constant.EXCEPTION, Constant.INVALID_INPUT, HttpStatus.BAD_REQUEST);
        }
        if( null == userDetailsBean.getFirstName() || null == userDetailsBean.getMobileNumber()
                || null == userDetailsBean.getGender() || null == userDetailsBean.getAge()
                || null == userDetailsBean.getAdresses() )
        {
            throw new DataException(Constant.EXCEPTION, Constant.MANDATORY_PARAM_ERROR, HttpStatus.BAD_REQUEST);
        }
        if( userDetailsBean.getAdresses().isEmpty() )
        {
            throw new DataException(Constant.EXCEPTION, Constant.MANDATORY_PARAM_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Validates requester details
     * 
     * @param userDetailsBean
     * @throws DataException
     */
    public void validateRequesterDetails( UserDetailsBean userDetailsBean ) throws DataException
    {

        if( null == userDetailsBean )
        {
            throw new DataException(Constant.EXCEPTION, Constant.INVALID_INPUT, HttpStatus.BAD_REQUEST);
        }
        if( null == userDetailsBean.getFirstName() || null == userDetailsBean.getMobileNumber()
                || null == userDetailsBean.getBloodGroup() || null == userDetailsBean.getBloodRequiredDate()
                || null == userDetailsBean.getHospital() )
        {
            throw new DataException(Constant.EXCEPTION, Constant.MANDATORY_PARAM_ERROR, HttpStatus.BAD_REQUEST);
        }

    }
}
