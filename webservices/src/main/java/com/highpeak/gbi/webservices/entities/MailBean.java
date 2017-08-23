package com.highpeak.gbi.webservices.entities;

import java.util.List;
import java.util.Set;

/**
 * @author sandesha, Created on 20/08/17
 */
public class MailBean {

    private Set<String> to;

    private String subject;

    private String body;

    public Set<String> getTo()
    {
        return to;
    }

    public void setTo( Set<String> to )
    {
        this.to = to;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject( String subject )
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody( String body )
    {
        this.body = body;
    }
}
