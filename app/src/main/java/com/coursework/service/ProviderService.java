package com.coursework.service;

import com.coursework.db.model.AddressEntity;
import com.coursework.db.model.ProviderEntity;
import com.coursework.db.model.RoleEntity;
import com.coursework.db.model.UserEntity;
import com.coursework.db.model.type.Status;
import com.coursework.db.model.type.TypeUser;
import com.coursework.db.repository.AddressRepo;
import com.coursework.db.repository.ProviderRepo;
import com.coursework.db.repository.RoleRepo;
import com.coursework.db.repository.UserRepo;
import com.coursework.exceptions.EntityException;
import com.coursework.mapper.UserAndProviderMapping;
import com.coursework.web.dto.ProviderDto;
import com.coursework.web.dto.UserAndProvidersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final RoleRepo roleRepo;
    private final ProviderRepo providerRepo;
    private final AddressRepo addressRepo;
    private final UserRepo userRepo;

    public ProviderEntity register(ProviderEntity provider) throws Exception {
        if (providerRepo.findByNameAndPassword(provider.getName(), provider.getPassword()).isPresent()) {
            throw new Exception("Такой пользователь уже существует");
        }
        AddressEntity addressEntity = addressRepo.save(provider.getAddress());

        provider.setAddress(addressEntity);
        ProviderEntity registeredUser = providerRepo.save(provider);


        return registeredUser;
    }

    public ProviderEntity login(ProviderEntity provider) {


        return providerRepo.findByNameAndPassword(provider.getName(), provider.getPassword())
                .orElseThrow(() -> new EntityException("User not be found", provider.getId()));
    }

    public List<UserAndProvidersDto> getAllUsers() {
        List<UserEntity> userList = userRepo.findAll();
        List<ProviderEntity> providerList = providerRepo.findAll();
        List<UserAndProvidersDto> userAndProvidersDtos = userList.stream().map(userAndProviders -> {
            UserAndProvidersDto userAndProvidersDto = UserAndProviderMapping.MAPPER.UserToEntity(userAndProviders);
            userAndProvidersDto.setType(TypeUser.User);
            if (isAdmin(userAndProviders.getRoles())) {
                userAndProvidersDto.setAdmin(true);
            }
            return userAndProvidersDto;
        }).collect(Collectors.toList());
        List<UserAndProvidersDto> userAndProvidersDtos2 = UserAndProviderMapping.MAPPER.ProviderTotListEntity(providerList);
        userAndProvidersDtos2.forEach(userAndProvidersDto -> userAndProvidersDto.setType(TypeUser.Provider));
        userAndProvidersDtos.addAll(userAndProvidersDtos2);
        return userAndProvidersDtos;
    }

    @Transactional
    public void delete(Long id) {
        UserEntity userEntity = userRepo.findById(id).orElse(null);
        ProviderEntity providerEntity = providerRepo.findById(id).orElse(null);

        if (userEntity != null) {
            userRepo.delete(id);
        } else if (providerEntity != null) {
            providerRepo.delete(id);
        }
    }

    public boolean isAdmin(List<RoleEntity> roleEntities) {
        for(RoleEntity roleEntity: roleEntities){
            if (roleEntity.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

}
