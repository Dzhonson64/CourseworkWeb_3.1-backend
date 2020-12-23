package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.db.model.type.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class OrderEntity extends BaseEntity {

    @Column(name = "date_time")
    LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Column(name = "amount")
    Integer amount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<OrderProductEntity> orderProductList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;


}
