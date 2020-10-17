package com.coursework.db.model;


import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_roles")
@NoArgsConstructor
public class ProviderEntity extends BaseEntity {
    @Column(name = "name")
    String name;

    @Column(name = "phone")
    String phone;

    @Column(name = "start_date")
    String startDate;

    @Column(name = "image_logo")
    byte[] imageLogo;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<WaybillEntity> waybillList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    AddressEntity address;
}
