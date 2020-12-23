package com.coursework.db.model;

import com.coursework.db.model.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
@NoArgsConstructor
public class RoleEntity extends BaseEntity {
    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    List<UserEntity> userList;
}
