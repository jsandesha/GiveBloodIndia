package com.highpeak.gbi.datastore.model;

import java.util.Calendar;
import javax.persistence.*;

/**
 * @author sandesha, Created on 23/08/17
 */
@Entity
@Table( name = "gbi_blood_request" )
public class BloodRequestModel {

    private Long id;

    private String firstName;

    private String lastName;

    private String emailId;

    private String mobileNumber;

    private String bloodGroup;

    private Calendar requiredDate;

    private Integer units;

    private String component;

    private String hospital;

    private AddressModel addressModel;

    private Calendar requestedAt;

    private Boolean isActive;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "br_id" )
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @Column( name = "br_fname" )
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    @Column( name = "br_lname" )
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    @Column( name = "br_email_id" )
    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    @Column( name = "br_mobile_number" )
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    @Column( name = "br_blood_group" )
    public String getBloodGroup()
    {
        return bloodGroup;
    }

    public void setBloodGroup( String bloodGroup )
    {
        this.bloodGroup = bloodGroup;
    }

    @Column( name = "br_required_date" )
    public Calendar getRequiredDate()
    {
        return requiredDate;
    }

    public void setRequiredDate( Calendar requiredDate )
    {
        this.requiredDate = requiredDate;
    }

    @Column( name = "br_units" )
    public Integer getUnits()
    {
        return units;
    }

    public void setUnits( Integer units )
    {
        this.units = units;
    }

    @Column( name = "br_component" )
    public String getComponent()
    {
        return component;
    }

    public void setComponent( String component )
    {
        this.component = component;
    }

    @Column( name = "br_hospital_name" )
    public String getHospital()
    {
        return hospital;
    }

    public void setHospital( String hospital )
    {
        this.hospital = hospital;
    }

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "br_frn_add_id", referencedColumnName = "ra_id" )
    public AddressModel getAddressModel()
    {
        return addressModel;
    }

    public void setAddressModel( AddressModel addressModel )
    {
        this.addressModel = addressModel;
    }

    @Column( name = "br_requested_at" )
    public Calendar getRequestedAt()
    {
        return requestedAt;
    }

    public void setRequestedAt( Calendar requestedAt )
    {
        this.requestedAt = requestedAt;
    }

    @Column( name = "br_is_active" )
    public Boolean getIsActive()
    {
        return isActive;
    }

    public void setIsActive( Boolean active )
    {
        isActive = active;
    }
}
