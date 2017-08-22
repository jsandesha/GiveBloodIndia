package com.highpeak.gbi.webservices.utils.sms;

import org.springframework.stereotype.Component;
import com.highpeak.gbi.webservices.entities.SMSBean;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * @author sandesha, Created on 20/08/17
 */
@Component
public class SMSSenderUtil {

    public static final String ACCOUNT_SID = "ACebcec631ba69c4f42125e4d86347db43";
    public static final String AUTH_TOKEN = "664b64c5bd984beff1e3145cbc69bd07";

    public void sendSms(SMSBean smsBean)
    {
        try
        {
            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber("+918660273618"), new PhoneNumber("+16193322312"),"Sample Test")
                    .create();
            System.out.println("sid: "+message.getSid());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
