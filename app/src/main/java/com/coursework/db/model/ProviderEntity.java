package com.coursework.db.model;


import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    String phone;

    @Column(name = "start_date")
    byte[] image_logo;

    AddressEntity address;
}
