package com.coursework.service;

import com.coursework.db.model.AddressEntity;
import com.coursework.db.model.ProviderEntity;
import com.coursework.db.model.RoleEntity;
import com.coursework.db.model.UserEntity;
import com.coursework.db.model.type.Status;
import com.coursework.db.repository.AddressRepo;
import com.coursework.db.repository.ProviderRepo;
import com.coursework.db.repository.RoleRepo;
import com.coursework.db.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProviderService {
    private final RoleRepo roleRepo;
    private final ProviderRepo providerRepo;
    private final AddressRepo addressRepo;

    public ProviderEntity register(ProviderEntity provider) {

        AddressEntity addressEntity = addressRepo.save(provider.getAddress());
        provider.setAddress(addressEntity);
        ProviderEntity registeredUser = providerRepo.save(provider);


        return registeredUser;
    }

}
