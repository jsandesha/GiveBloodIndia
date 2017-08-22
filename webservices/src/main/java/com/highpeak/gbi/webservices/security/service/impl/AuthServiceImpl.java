/*
package com.highpeak.gbi.webservices.security.service.impl;

import com.highpeak.gbi.datastore.model.User;
import com.highpeak.gbi.datastore.repository.UserRepository;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.security.AccountCredentials;
import com.highpeak.gbi.webservices.security.entity.AuthenticationRequest;
import com.highpeak.gbi.webservices.security.service.AuthService;
import com.highpeak.gbi.webservices.security.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Optional;

*/
/**
 * @author sandesha, Created on 22/08/17
 *//*

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private UserModelRepository userRepository;

    @Override
    public Integer login( AuthenticationRequest authenticationRequest ) throws DataException
    {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getMobileNumber(),
                        authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMobileNumber());
        String token = jwtTokenUtil.generateToken(userDetails);

        Optional<User> userOptional = userRepository
                .findByMobileNumberAndIsActiveTrue(authenticationRequest.getMobileNumber());

        return userOptional.get().getId();
    }
}
*/
