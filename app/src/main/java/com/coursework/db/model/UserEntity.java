package com.coursework.db.model;


import com.coursework.db.model.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(name = "username")
    String username;

    @Column(name = "enabled")
    Boolean enabled;

    @Column(name = "status")
    String status;

    @Column(name = "snils")
    String snils;

    @Column(name = "surname")
    String surname;

    @Column(name = "name")
    String name;

    @Column(name = "patronymic")
    String patronymic;

    @Column(name = "phone")
    String phone;

    @Column(name = "postcode")
    String postcode;


    @Column(name = "money")
    BigDecimal money;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "address_id")
    AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "password_id", nullable = false)
    PasswordEntity password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<OrderEntity> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<FeedbackEntity> feedbackList = new ArrayList<>();
}
