package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.web.dto.CatalogDto;
import com.coursework.web.dto.type.CatalogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "type_product")
@NoArgsConstructor
public class TypeProductEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CatalogType type;

    @OneToMany(mappedBy = "typeProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductEntity> productList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    TypeProductEntity parentTypeProduct;

    @OneToMany(mappedBy = "parentTypeProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TypeProductEntity> typeProductList = new ArrayList<>();

    @OneToMany(mappedBy = "typeProductProperty", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TypePropertyEntity> typePropertyList = new ArrayList<>();

}
