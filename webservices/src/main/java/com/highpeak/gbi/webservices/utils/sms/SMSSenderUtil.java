package com.highpeak.gbi.webservices.utils.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.highpeak.gbi.webservices.entities.SMSBean;
import com.highpeak.gbi.webservices.uiresponse.DataException;
import com.highpeak.gbi.webservices.utils.constant.Constant;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * @author sandesha, Created on 20/08/17
 */
@Component
public class SMSSenderUtil {

    private static final String ACCOUNT_SID = "AC4e05a68037f6ca70b8640620e1c4f678";
    private static final String AUTH_TOKEN = "18578a350f31a9847f5d37414364d2cf";

    private static final Logger logger = LoggerFactory.getLogger(SMSSenderUtil.class);

    public void sendSms( SMSBean smsBean ) throws DataException
    {
        try
        {
            logger.info("SMS Body: {}", smsBean.getSmsBody());
            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            for( String to : smsBean.getTos() )
            {
                Message message = Message
                        .creator(new PhoneNumber(to), new PhoneNumber("+18312641181"), smsBean.getSmsBody()).create();
                logger.info("SID: {}", message.getSid());
            }
        }
        catch( ApiException e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException(Constant.EXCEPTION, Constant.ERROR_SENDING_SMS, HttpStatus.BAD_GATEWAY);
        }
    }
}
