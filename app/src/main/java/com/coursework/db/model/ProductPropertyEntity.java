package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_property")
@NoArgsConstructor
public class ProductPropertyEntity extends BaseEntity {

    @Column(name = "value")
    String value;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    PropertyProductEntity propertyProduct;
}
