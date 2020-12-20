package com.coursework.db.model;


import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "provider")
@NoArgsConstructor
public class ProviderEntity extends BaseEntity {
    @Column(name = "name")
    String name;

    @Column(name = "phone")
    String phone;

    @Column(name = "password")
    String password;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "image_logo")
    byte[] imageLogo;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WaybillEntity> waybillList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    AddressEntity address;
}
