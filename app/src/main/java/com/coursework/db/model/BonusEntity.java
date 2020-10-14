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
@Table(name = "bonus")
@NoArgsConstructor
public class BonusEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "points")
    Integer points;

    @OneToMany(mappedBy = "bounus", cascade = CascadeType.REFRESH, orphanRemoval = true)
    List<ProductEntity> productList = new ArrayList<>();
}
