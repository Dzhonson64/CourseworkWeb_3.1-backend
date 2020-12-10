package com.coursework.db.repository;

import com.coursework.db.model.ProductPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyRepo extends JpaRepository<ProductPropertyEntity, Long> {
}
