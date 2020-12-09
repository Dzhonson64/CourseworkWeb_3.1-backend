package com.coursework.db.repository;

import com.coursework.db.model.PropertyProductEntity;
import com.coursework.db.model.TypeProductEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyProductRepo extends JpaRepository<PropertyProductEntity, Long> {
    @Query(value = " SELECT p2.type_id " +
            " FROM property p " +
            " INNER JOIN  type_property p2 ON " +
            " p.id = p2.property_id " +
            " WHERE p.id  = :id ", nativeQuery = true)
    Long getCatalogId(@Param("id") Long id);
}
