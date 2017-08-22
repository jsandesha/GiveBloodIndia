/*
package com.highpeak.gbi.webservices.security;

import com.highpeak.gbi.datastore.model.User;
import com.highpeak.gbi.datastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

*/
/**
 * @author sandesha, Created on 22/08/17
 *//*

@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserModelRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByMobileNumberAndIsActiveTrue(s);
        if(userOptional.isPresent())
        {
            JWTUserFactory.create(userOptional.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
*/
