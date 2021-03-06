package com.highpeak.gbi.datastore.repository;

import com.highpeak.gbi.datastore.model.AddressModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author sandesha, Created on 21/08/17
 */
public interface AddressRepository extends CrudRepository<AddressModel, Integer>{

    Optional<AddressModel> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
