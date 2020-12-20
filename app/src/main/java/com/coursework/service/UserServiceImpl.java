package com.coursework.service;

import com.coursework.db.model.AddressEntity;
import com.coursework.db.model.RoleEntity;
import com.coursework.db.model.UserEntity;
import com.coursework.db.model.type.Status;
import com.coursework.db.repository.AddressRepo;
import com.coursework.db.repository.RoleRepo;
import com.coursework.db.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AddressRepo addressRepo;


    @Override
    public UserEntity register(UserEntity user) {
        RoleEntity roleUser = roleRepo.findByName("ROLE_USER");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        AddressEntity addressEntity = addressRepo.save(user.getAddress());
        user.setAddress(addressEntity);
        UserEntity registeredUser = userRepo.save(user);


        return registeredUser;
    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> result = userRepo.findAll();
        return result;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity result = userRepo.findByUsername(username);
        return result;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity result = userRepo.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
