package com.coursework.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "property")
@NoArgsConstructor
public class PropertyProductEntity {

    @Column(name = "name")
    String name;

    @Column(name = "unit")
    String unit;

    @Column(name = "description")
    String description;
}
