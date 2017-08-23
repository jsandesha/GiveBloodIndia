package com.highpeak.gbi.webservices.utils.distance;

import java.util.ArrayList;
import java.util.List;

import com.highpeak.gbi.datastore.repository.EmailPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.highpeak.gbi.datastore.model.EmailPhone;
import com.highpeak.gbi.datastore.repository.UserModelRepository;
import com.highpeak.gbi.webservices.UIResponse.DataException;
import com.highpeak.gbi.webservices.entities.Address;

/**
 * @author sandesha, Created on 23/08/17
 */
@Component
public class DistanceUtil {

    @Autowired
    private EmailPhoneRepository userModelRepository;

    public List<EmailPhone> fetchUsersWithinRadius( Address address, Integer radius, List<String> bloodGroups,
            Boolean matchingGroup ) throws DataException
    {
        try
        {
            Double oldLatitude = address.getLatitude();
            Double oldLongitude = address.getLongitude();

            Double coeffLat = radius * 0.0000089;
            Double maxLat = oldLatitude + coeffLat;
            Double minLat = oldLatitude - coeffLat;

            Double coeffLon = coeffLat / (Math.cos((oldLatitude * 0.018)));
            Double maxLon = oldLongitude + coeffLon;
            Double minLon = oldLongitude - coeffLon;

            List<EmailPhone> emailPhoneList = new ArrayList<>();
            if( matchingGroup )
            {
                emailPhoneList = userModelRepository.getUsersInTheRangeWithMatchingGroup(maxLat, minLat, maxLon, minLon,
                        bloodGroups);
            }
            else
            {
                emailPhoneList = userModelRepository.getAllUsersInTheRange(maxLat, minLat, maxLon, minLon);
            }
            return emailPhoneList;
        }

        catch( Exception e )
        {
            e.printStackTrace();
            throw new DataException("Exception", "Error while computing distance", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
