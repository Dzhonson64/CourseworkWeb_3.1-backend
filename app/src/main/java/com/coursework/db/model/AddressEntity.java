package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.db.model.type.TypeAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor
public class AddressEntity extends BaseEntity {

    @Column(name = "city")
    String city;

    @Column(name = "country")
    String country;

    @Column(name = "street")
    String street;


    @Column(name = "home")
    String home;

    @Column(name = "apartment")
    Integer apartment;

    @Column(name = "type")
    TypeAddress type;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<UserEntity> userList = new ArrayList<>();

}
