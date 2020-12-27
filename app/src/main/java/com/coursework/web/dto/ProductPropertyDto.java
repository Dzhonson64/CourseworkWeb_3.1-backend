package com.coursework.web.dto;

import com.coursework.db.model.ProductPropertyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
@Getter
@Setter
@Schema(name = "Значение свойств товаров", description = "")
public class ProductPropertyDto {
    Long id;
    String value;
    Long productId;
    Long propertyId;
    Long productPropertyId;
}
