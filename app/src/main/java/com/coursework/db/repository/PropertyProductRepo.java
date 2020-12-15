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

    @Query(value = "SELECT * " +
            " FROM public.property " +
            " WHERE id in (SELECT p2.property_id " +
            "            FROM type_product p " +
            "            INNER JOIN  type_property p2 ON " +
            "             p.id = p2.type_id " +
            "             WHERE p.id  = :id) ", nativeQuery = true)
    List<PropertyProductEntity> getPropertyListByCatalog(@Param("id") Long id);

    @Query(value = " SELECT * FROM property WHERE id In\n" +
            "(SELECT p.property_id " +
            "            FROM type_property p " +
            "            INNER JOIN  type_product p2 ON " +
            "            p2.id = p.type_id " +
            "            WHERE p2.id  = :id)", nativeQuery = true)
    List<PropertyProductEntity> getAllPropertyProductByCatalog(@Param("id") Long id);
}
