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
@Table(name = "type_property")
@NoArgsConstructor
public class TypeProductEntity extends BaseEntity {

    @Column(name = "type_id")
    String description;

    @OneToMany(mappedBy = "typeProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductEntity> productList = new ArrayList<>();


    TypeProductEntity typeProduct;
}
