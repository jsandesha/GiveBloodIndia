package com.highpeak.gbi.webservices.utils.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.entities.MailBean;

import java.util.List;
import java.util.Set;

/**
 * Util class to send e-mail
 * 
 * @author sandesha, Created on 20/08/17
 */
@Component
public class GmailSenderUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail( MailBean mailBean ) throws DataException
    {
        try
        {
            Set<String> to = mailBean.getTo();
            String[] tos = to.toArray(new String[to.size()]);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(tos);
            mailMessage.setText(mailBean.getBody());
            mailMessage.setSubject(mailBean.getSubject());

            javaMailSender.send(mailMessage);

            System.out.println("Mail sent!");
        }
        catch( Exception e )
        {
            throw new DataException("Exception", "Error while sending email address", HttpStatus.BAD_GATEWAY);
        }
    }
}
