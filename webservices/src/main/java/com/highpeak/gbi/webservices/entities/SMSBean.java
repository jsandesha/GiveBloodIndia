package com.highpeak.gbi.webservices.entities;

import java.util.List;

/**
 * @author sandesha, Created on 22/08/17
 */
public class SMSBean {

    private List<String> tos;

    private String smsBody;

    public List<String> getTos()
    {
        return tos;
    }

    public void setTos( List<String> tos )
    {
        this.tos = tos;
    }

    public String getSmsBody()
    {
        return smsBody;
    }

    public void setSmsBody( String smsBody )
    {
        this.smsBody = smsBody;
    }
}
