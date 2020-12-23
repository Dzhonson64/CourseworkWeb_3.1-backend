package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_product")
@NoArgsConstructor
public class OrderProductEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @Column(name = "amount")
    Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;
}
