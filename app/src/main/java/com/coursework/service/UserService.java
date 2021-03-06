package com.coursework.service;

import com.coursework.db.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user);

    List<UserEntity> getAll();

    UserEntity findByUsername (String username);

    UserEntity findById(Long id);

    void delete(Long id);
}
