package com.coursework.db.model;


import com.coursework.db.model.base.BaseEntity;
import com.coursework.db.model.type.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usr")
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Column(name = "google_id")
    String googleId;

    @Column(name = "username")
    String username;

    @Column(name = "enabled")
    Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )

    private List<RoleEntity> roles;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "address_id")
    AddressEntity address;



    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<OrderEntity> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<FeedbackEntity> feedbackList = new ArrayList<>();
}
