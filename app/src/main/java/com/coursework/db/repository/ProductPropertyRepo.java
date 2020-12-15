package com.coursework.db.repository;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.ProductPropertyEntity;
import com.coursework.db.model.PropertyProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyRepo extends JpaRepository<ProductPropertyEntity, Long> {
    ProductPropertyEntity findByValueAndProductAndPropertyProduct(Double value, ProductEntity product, PropertyProductEntity propductProperty);
}
