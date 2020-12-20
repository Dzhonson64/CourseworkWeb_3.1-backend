package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.db.model.type.TypeAddress;
import com.coursework.web.dto.AddressOptional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Bean;

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
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "street")
    private String street;


    @Column(name = "apartment")
    private Integer apartment;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeAddress type;
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @OneToMany(mappedBy = "address", orphanRemoval = true)
    private List<UserEntity> userList = new ArrayList<>();

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProviderEntity> addressList = new ArrayList<>();


    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @Column(name = "building")
    private String building;

}
