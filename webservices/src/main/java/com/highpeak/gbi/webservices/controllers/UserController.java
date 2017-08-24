package com.highpeak.gbi.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highpeak.gbi.webservices.entities.UserDetailsBean;
import com.highpeak.gbi.webservices.services.UserServices;
import com.highpeak.gbi.webservices.uiresponse.AbstractController;
import com.highpeak.gbi.webservices.uiresponse.DataException;

@RestController
@EnableAutoConfiguration
@RequestMapping( "/rest" )
public class UserController extends AbstractController {

    @Autowired
    private UserServices userService;

    @RequestMapping( value = "/registerUser", method = RequestMethod.POST )
    public ResponseEntity<?> registerUser( @RequestBody UserDetailsBean userDetailsBean )
    {
        try
        {
            return buildResponse(userService.registerUser(userDetailsBean));
        }
        catch( DataException e )
        {
            return buildError(e);
        }
    }

    @RequestMapping( value = "/requestBlood", method = RequestMethod.POST )
    public ResponseEntity<?> requestForBlood( @RequestBody UserDetailsBean userDetailsBean )
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
