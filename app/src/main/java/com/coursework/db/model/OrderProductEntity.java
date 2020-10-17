package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "order_product")
@NoArgsConstructor
public class OrderProductEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;
}
