package com.highpeak.gbi.webservices.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.highpeak.gbi.webservices.UIResponse.AbstractController;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.services.UserServices;

@RestController
@EnableAutoConfiguration
@RequestMapping( "/rest" )
public class UserController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServices userService;

    /* @RequestMapping( value = "/user", method = RequestMethod.GET ) public ResponseEntity<?>
     * getUser( @RequestParam Integer id ) { LOGGER.debug("In Controller to fetch user"); UserModel user
     * = homeService.getUserById(id); return buildResponse(user); } */

    @RequestMapping( value = "/registerUser", method = RequestMethod.POST )
    public ResponseEntity<?> registerUser( @RequestBody UserDetailsBean userDetailsBean )
    {
        LOGGER.debug("In Controller to register user {}", userDetailsBean.getEmailId());
        try
        {
            return buildResponse(userService.registerUser(userDetailsBean));
        }
        catch( DataException e )
        {
            return buildError(e);
        }
    }

    @RequestMapping(value = "/requestBlood", method = RequestMethod.POST)
    public ResponseEntity<?> requestForBlood(@RequestBody UserDetailsBean userDetailsBean)
    {
        try
        {
            return buildResponse(userService.requestBlood(userDetailsBean));
        }
        catch( DataException e )
        {
            return buildError(e);
        }
    }

}
