package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "waybill")
@NoArgsConstructor
public class WaybillEntity extends BaseEntity {

    @Column(name = "name")
    Integer amount;

    @Column(name = "date_arrive")
    LocalDate dateArrive;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    ProviderEntity provider;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;
}
