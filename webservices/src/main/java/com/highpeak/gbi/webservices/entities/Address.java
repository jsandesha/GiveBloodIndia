package com.highpeak.gbi.webservices.entities;

/**
 * @author sandesha, Created on 22/08/17
 */
public class Address {

    private String latitude;

    private String longitude;

    private String city;

    private Integer zip;

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude( String latitude )
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude( String longitude )
    {
        this.longitude = longitude;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public Integer getZip()
    {
        return zip;
    }

    public void setZip( Integer zip )
    {
        this.zip = zip;
    }
}
