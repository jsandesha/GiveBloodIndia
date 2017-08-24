package com.highpeak.gbi.webservices.utils.mail;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.highpeak.gbi.webservices.entities.MailBean;
import com.highpeak.gbi.webservices.uiresponse.DataException;
import com.highpeak.gbi.webservices.utils.constant.Constant;

/**
 * Util class to send e-mail
 * 
 * @author sandesha, Created on 20/08/17
 */
@Component
public class GmailSenderUtil {

    private static final Logger logger = LoggerFactory.getLogger(GmailSenderUtil.class);

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

            logger.info("Email sent");
        }
        catch( Exception e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException("Exception", "Error while sending email address", HttpStatus.BAD_GATEWAY);
        }
    }
}
