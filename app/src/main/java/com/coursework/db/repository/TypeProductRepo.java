package com.coursework.db.repository;

import com.coursework.db.model.TypeProductEntity;
import com.coursework.web.dto.type.CatalogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProductEntity, Long> {
    List<TypeProductEntity> findAllByType(CatalogType type);
}
