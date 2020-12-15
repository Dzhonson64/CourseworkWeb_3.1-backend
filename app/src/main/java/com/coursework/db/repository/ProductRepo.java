package com.coursework.db.repository;

import com.coursework.db.model.ProductEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM ProductEntity p WHERE p.id = :id")
    int delete(@Param("id") Long id);
}
