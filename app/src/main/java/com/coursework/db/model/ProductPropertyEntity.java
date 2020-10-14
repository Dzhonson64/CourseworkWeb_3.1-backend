package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product_property")
@NoArgsConstructor
public class ProductPropertyEntity extends BaseEntity {

    @Column(name = "value")
    Double value;

    ProductEntity product;
    PropertyProductEntity propertyProduct;
}
