package scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.highpeak.gbi.datastore.repository.BloodRequestRepository;

/**
 * @author sandesha, Created on 24/08/17
 */
@Component
public class DailyMessage {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Scheduled( cron = "0 1 6 * * ?" )
    public void sendDailyAlerts()
    {
        System.out.println("executing....cron");
        /* try { Calendar today = DateUtil.getCalendarWithTimeAtStartOfDay(Calendar.getInstance());
         * 
         * } */
    }
}
