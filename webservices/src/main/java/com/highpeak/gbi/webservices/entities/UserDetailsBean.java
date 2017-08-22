package com.highpeak.gbi.webservices.entities;

import java.util.List;

/**
 * @author sandesha, Created on 19/08/17
 *
 *         Bean contains registration details of the user to be registered
 */
public class UserDetailsBean {

    private String firstName;

    private String lastName;

    private Integer age;

    private String mobileNumber;

    private String emailId;

    private String bloodGroup;

    private String gender;

    private List<Address> adresses;

    private Long lastBloodDonationDate;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge( Integer age )
    {
        this.age = age;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public String getBloodGroup()
    {
        return bloodGroup;
    }

    public void setBloodGroup( String bloodGroup )
    {
        this.bloodGroup = bloodGroup;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    public List<Address> getAdresses()
    {
        return adresses;
    }

    public void setAdresses( List<Address> adresses )
    {
        this.adresses = adresses;
    }

    public Long getLastBloodDonationDate()
    {
        return lastBloodDonationDate;
    }

    public void setLastBloodDonationDate( Long lastBloodDonationDate )
    {
        this.lastBloodDonationDate = lastBloodDonationDate;
    }
}
