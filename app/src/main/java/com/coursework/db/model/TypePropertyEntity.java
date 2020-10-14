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
@Table(name = "type_property")
@NoArgsConstructor
public class TypePropertyEntity extends BaseEntity {

    TypeProductEntity typeProductProperty;
    PropertyProductEntity propertyProduct;

    @Column(name = "description")
    String description;
}
