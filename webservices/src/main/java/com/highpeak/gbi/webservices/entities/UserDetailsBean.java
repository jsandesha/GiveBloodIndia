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

    /* this is used to capture both donar's address and requester's requested address */
    private List<Address> adresses;

    private Long lastBloodDonationDate;

    private Long bloodRequiredDate;

    private Integer numberOfUnits;

    private Boolean termsAndRequestAcceptance;

    private String hospital;

    private String component;

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

    public Long getBloodRequiredDate()
    {
        return bloodRequiredDate;
    }

    public void setBloodRequiredDate( Long bloodRequiredDate )
    {
        this.bloodRequiredDate = bloodRequiredDate;
    }

    public Integer getNumberOfUnits()
    {
        return numberOfUnits;
    }

    public void setNumberOfUnits( Integer numberOfUnits )
    {
        this.numberOfUnits = numberOfUnits;
    }

    public Boolean getTermsAndRequestAcceptance()
    {
        return termsAndRequestAcceptance;
    }

    public void setTermsAndRequestAcceptance( Boolean termsAndRequestAcceptance )
    {
        this.termsAndRequestAcceptance = termsAndRequestAcceptance;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
