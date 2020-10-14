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
@Table(name = "password")
@NoArgsConstructor
public class PasswordEntity extends BaseEntity {
    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "password", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserEntity> userList = new ArrayList<>();
}
