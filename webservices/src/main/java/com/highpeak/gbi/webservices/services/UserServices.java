package com.highpeak.gbi.webservices.services;

import com.highpeak.gbi.datastore.model.UserModel;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;

public interface UserServices {

    UserModel registerUser(UserDetailsBean userDetailsBean) throws DataException;
}
