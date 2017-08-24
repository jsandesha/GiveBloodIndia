package com.highpeak.gbi.datastore.repository;

import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.highpeak.gbi.datastore.model.EmailPhone;

/**
 * @author sandesha, Created on 23/08/17
 */
public interface EmailPhoneRepository extends CrudRepository<EmailPhone,Integer>{
    @Query( nativeQuery = true, value = "select u.u_id, u.u_email_id,u.u_mobile_number "
            + "from gbi_user u inner join gbi_user_to_address uta on u.u_id = uta.uta_frn_user_id "
            + "inner join gbi_reachable_address ra on uta.uta_frn_add_id = ra.ra_id "
            + "where u.u_is_active is true and u.u_blood_group in (:bloodGroups) and ra.ra_lat<:maxLat and ra.ra_lat>:minLat and ra.ra_lon<:maxLon and ra.ra_lon>:minLon "
            + "and u.u_last_don_date<=:threeMonthsBack" )
    List<EmailPhone> getUsersInTheRangeWithMatchingGroup(@Param( "maxLat" ) Double maxLat,
                                                         @Param( "minLat" ) Double minLat, @Param( "maxLon" ) Double maxLon, @Param( "minLon" ) Double minLon,
            @Param( "bloodGroups" ) List<String> bloodGroups, @Param( "threeMonthsBack" ) Calendar threeMonthsBack );

    @Query( nativeQuery = true, value = "select u.u_id, u.u_email_id,u.u_mobile_number "
            + "from gbi_user u inner join gbi_user_to_address uta on u.u_id = uta.uta_frn_user_id "
            + "inner join gbi_reachable_address ra on uta.uta_frn_add_id = ra.ra_id "
            + "where u.u_is_active is true and ra.ra_lat<:maxLat and ra.ra_lat>:minLat and ra.ra_lon<:maxLon and ra.ra_lon>:minLon "
            + "and u.u_last_don_date<=:threeMonthsBack" )
    List<EmailPhone> getAllUsersInTheRange( @Param( "maxLat" ) Double maxLat, @Param( "minLat" ) Double minLat,
            @Param( "maxLon" ) Double maxLon, @Param( "minLon" ) Double minLon,
            @Param( "threeMonthsBack" ) Calendar threeMonthsBack );
}
