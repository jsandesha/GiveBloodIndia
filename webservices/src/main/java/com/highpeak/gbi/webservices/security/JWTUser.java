/*
package com.highpeak.gbi.webservices.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

*/
/**
 * @author sandesha, Created on 22/08/17
 *//*

@Component
public class JWTUser implements UserDetails {

    private Integer id;
    private String name;
    private String emailId;
    private String mobileNumber;
    private String gender;
    private String bloodGroup;
    private Boolean isActive;
    private Calendar lastPasswordResetDate;
    private Calendar createdAt;

    private JWTUser()
    {
        */
/**
         *
         *//*

    }

    public JWTUser( Integer id, String name, String emailId, String mobileNumber, String gender, String bloodGroup,
            Boolean isActive, Calendar lastPasswordResetDate, Calendar createdAt )
    {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.isActive = isActive;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.createdAt=createdAt;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    public String getBloodGroup()
    {
        return bloodGroup;
    }

    public void setBloodGroup( String bloodGroup )
    {
        this.bloodGroup = bloodGroup;
    }

    public Boolean getActive()
    {
        return isActive;
    }

    public void setActive( Boolean active )
    {
        isActive = active;
    }

    public Calendar getLastPasswordResetDate()
    {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate( Calendar lastPasswordResetDate )
    {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.emptyList();
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        return name;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
*/
