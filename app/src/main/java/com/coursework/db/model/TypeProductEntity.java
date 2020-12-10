package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Transient;

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
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CatalogType type;

    @OneToMany(mappedBy = "typeProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<ProductEntity> productList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private TypeProductEntity parentTypeProduct;
@Cascade({
        org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.REFRESH,
        org.hibernate.annotations.CascadeType.REPLICATE,
        org.hibernate.annotations.CascadeType.REMOVE,
        org.hibernate.annotations.CascadeType.DETACH
})
    @OneToMany(mappedBy = "parentTypeProduct", fetch=FetchType.EAGER, orphanRemoval = true)
    private List<TypeProductEntity> typeProductList = new ArrayList<>();

    @OneToMany(mappedBy = "typeProductProperty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TypePropertyEntity> typePropertyList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusActiveType status;

}
