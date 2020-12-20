package com.coursework.db.repository;

import com.coursework.db.model.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<ProviderEntity, Long> {
}
