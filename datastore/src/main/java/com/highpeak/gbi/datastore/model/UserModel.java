package com.highpeak.gbi.datastore.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author sandesha, Created on 22/08/17
 */
@Entity
@Table( name = "gbi_user" )
public class UserModel {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String emailId;

    private String mobileNumber;

    private String bloodGroup;

    private String gender;

    private Calendar lastDonationDate;

    private Calendar createdAt;

    private Boolean isActive;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "u_id" )
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @Column( name = "u_fname" )
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    @Column( name = "u_lname" )
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    @Column( name = "u_age" )
    public Integer getAge()
    {
        return age;
    }

    public void setAge( Integer age )
    {
        this.age = age;
    }

    @Column( name = "u_email_id" )
    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    @Column( name = "u_mobile_number" )
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    @Column( name = "u_blood_group" )
    public String getBloodGroup()
    {
        return bloodGroup;
    }

    public void setBloodGroup( String bloodGroup )
    {
        this.bloodGroup = bloodGroup;
    }

    @Column( name = "u_gender" )
    public String getGender()
    {
        return gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    @Column( name = "u_last_don_date" )
    public Calendar getLastDonationDate()
    {
        return lastDonationDate;
    }

    public void setLastDonationDate( Calendar lastDonationDate )
    {
        this.lastDonationDate = lastDonationDate;
    }

    @Column( name = "u_created_at" )
    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt( Calendar createdAt )
    {
        this.createdAt = createdAt;
    }

    @Column( name = "u_is_active" )
    public Boolean getIsActive()
    {
        return isActive;
    }

    public void setIsActive( Boolean active )
    {
        isActive = active;
    }
}
