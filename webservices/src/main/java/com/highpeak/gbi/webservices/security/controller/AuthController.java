/*
package com.highpeak.gbi.webservices.security.controller;

import com.highpeak.gbi.webservices.UIResponse.AbstractController;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.security.entity.AuthenticationRequest;
import com.highpeak.gbi.webservices.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @author sandesha, Created on 22/08/17
 *//*

@RestController
@RequestMapping("/")
public class AuthController extends AbstractController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> brainbloxAccountLogin(@RequestBody AuthenticationRequest authenticationRequest)
    {
        try
        {
            return buildResponse(authService.login(authenticationRequest));
        }
        catch( DataException e )
        {
            return buildError(e);
        }
    }
}
*/
