package com.highpeak.gbi.webservices.services;

import com.highpeak.gbi.datastore.model.UserModel;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.uiresponse.DataException;

public interface UserServices {

    UserModel registerUser( UserDetailsBean userDetailsBean ) throws DataException;

    Integer requestBlood( UserDetailsBean userDetailsBean ) throws DataException;
}
