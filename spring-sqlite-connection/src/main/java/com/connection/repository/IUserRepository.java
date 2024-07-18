package com.connection.repository;

import com.connection.entities.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends ListCrudRepository<UserEntity, Long> {
}
