package com.highpeak.gbi.datastore.model;

import javax.persistence.*;

/**
 * @author sandesha, Created on 22/08/17
 */
@Entity
@Table( name = "gbi_reachable_address" )
public class AddressModel {

    private Long id;

    private String latitude;

    private String longitude;

    private String city;

    private Integer zip;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ra_id" )
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @Column( name = "ra_lat" )
    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude( String latitude )
    {
        this.latitude = latitude;
    }

    @Column( name = "ra_lon" )
    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude( String longitude )
    {
        this.longitude = longitude;
    }

    @Column( name = "ra_city" )
    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    @Column( name = "ra_zip" )
    public Integer getZip()
    {
        return zip;
    }

    public void setZip( Integer zip )
    {
        this.zip = zip;
    }
}
