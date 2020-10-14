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
@Table(name = "user_roles")
@NoArgsConstructor
public class RoleEntity extends BaseEntity {
    @Column(name = "name")
    String name;

    @Column(name = "role")
    String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<UserEntity> userList;
}
