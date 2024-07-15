package com.app.spring.security.persistence.repository;

import com.app.spring.security.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

//    @Query("select u from UserEntity u where u.username = ?")
//    Optional<UserEntity> findUser(String username);

}
