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
@Table(name = "property")
@NoArgsConstructor
public class PropertyProductEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "unit")
    String unit;

    @Column(name = "description")
    String description;


    @OneToMany(mappedBy = "propertyProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductPropertyEntity> productPropertyList = new ArrayList<>();
}
