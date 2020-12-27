package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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


    @OneToMany(mappedBy = "typeProduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductEntity> productList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TypeProductEntity parentTypeProduct;

    @OneToMany(mappedBy = "parentTypeProduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TypeProductEntity> typeProductList = new ArrayList<>();

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "typeProductProperty", cascade = CascadeType.ALL)
    private List<TypePropertyEntity> typePropertyList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusActiveType status;

}
