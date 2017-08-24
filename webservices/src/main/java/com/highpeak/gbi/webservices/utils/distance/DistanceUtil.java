package com.highpeak.gbi.webservices.utils.distance;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.highpeak.gbi.datastore.model.EmailPhone;
import com.highpeak.gbi.datastore.repository.EmailPhoneRepository;
import com.highpeak.gbi.webservices.entities.Address;
import com.highpeak.gbi.webservices.uiresponse.DataException;
import com.highpeak.gbi.webservices.utils.constant.Constant;

/**
 * Utility class which computes radius
 * 
 * @author sandesha, Created on 23/08/17
 */
@Component
public class DistanceUtil {

    private static final Logger logger = LoggerFactory.getLogger(DistanceUtil.class);

    @Autowired
    private EmailPhoneRepository userModelRepository;

    public List<EmailPhone> fetchUsersWithinRadius( Address address, Integer radius, List<String> bloodGroups,
            Boolean matchingGroup ) throws DataException
    {
        try
        {
            Calendar threeMonthsBack = Calendar.getInstance();
            threeMonthsBack.add(Calendar.DATE, -3);

            Double oldLatitude = address.getLatitude();
            Double oldLongitude = address.getLongitude();

            Double coeffLat = radius * 0.0000089;
            Double maxLat = oldLatitude + coeffLat;
            Double minLat = oldLatitude - coeffLat;

            Double coeffLon = coeffLat / (Math.cos((oldLatitude * 0.018)));
            Double maxLon = oldLongitude + coeffLon;
            Double minLon = oldLongitude - coeffLon;

            List<EmailPhone> emailPhoneList;
            if( matchingGroup )
            {
                emailPhoneList = userModelRepository.getUsersInTheRangeWithMatchingGroup(maxLat, minLat, maxLon, minLon,
                        bloodGroups, threeMonthsBack);
            }
            else
            {
                emailPhoneList = userModelRepository.getAllUsersInTheRange(maxLat, minLat, maxLon, minLon,
                        threeMonthsBack);
            }
            return emailPhoneList;
        }

        catch( Exception e )
        {
            logger.error(Constant.ERROR, e);
            throw new DataException("Exception", "Error while computing distance", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
