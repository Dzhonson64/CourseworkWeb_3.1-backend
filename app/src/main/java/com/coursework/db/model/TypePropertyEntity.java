package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "type_property")
@NoArgsConstructor
public class TypePropertyEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    TypeProductEntity typeProductProperty;

    @ManyToOne
    @JoinColumn(name = "type_property", nullable = false)
    PropertyProductEntity propertyProduct;

    @Column(name = "description")
    String description;
}
