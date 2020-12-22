package com.coursework.db.repository;

import com.coursework.db.model.ProviderEntity;
import com.coursework.web.dto.ProviderDto;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepo extends JpaRepository<ProviderEntity, Long> {

    Optional<ProviderEntity> findByNameAndPassword(String name, String password);

    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM ProviderEntity p WHERE p.id = :id")
    int delete(@Param("id") Long id);
}
