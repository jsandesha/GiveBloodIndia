package com.highpeak.gbi.webservices.entities;

/**
 * @author sandesha, Created on 22/08/17
 */
public class Address {

    private Double latitude;

    private Double longitude;

    private String city;

    private Integer zip;

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude( Double latitude )
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude( Double longitude )
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
