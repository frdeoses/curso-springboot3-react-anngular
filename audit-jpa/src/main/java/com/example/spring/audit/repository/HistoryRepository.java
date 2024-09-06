package com.example.spring.audit.repository;

import com.example.spring.audit.entity.HistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Integer> {
}
