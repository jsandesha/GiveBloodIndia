package com.highpeak.gbi.datastore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.highpeak.gbi.datastore.model.EmailPhone;
import com.highpeak.gbi.datastore.model.UserModel;

public interface UserModelRepository extends CrudRepository<UserModel, Integer> {

    @Override
    UserModel findOne( Integer userId );

    Optional<UserModel> findByEmailIdAndMobileNumberAndIsActiveTrue( String emailId, String mobileNumber );

    Optional<UserModel> findByMobileNumberAndIsActiveTrue( String mobileNumber );

    Optional<UserModel> findByFirstNameAndIsActiveTrue( String name );

}
