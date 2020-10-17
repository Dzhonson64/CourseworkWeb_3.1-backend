package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "image")
    byte[] image;

    @Column(name = "description")
    String description;


    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<OrderProductEntity> userList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<FeedbackEntity> feedbackList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<WaybillEntity> waybillList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductPropertyEntity> productPropertyList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "type_id")
    TypeProductEntity typeProduct;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    DiscountEntity discount;

    @ManyToOne
    @JoinColumn(name = "bonus_id")
    BonusEntity bonus;
}
