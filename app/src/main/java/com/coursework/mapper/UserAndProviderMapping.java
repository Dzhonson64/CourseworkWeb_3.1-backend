package com.coursework.mapper;

import com.coursework.db.model.ProviderEntity;
import com.coursework.db.model.UserEntity;
import com.coursework.web.dto.UserAndProvidersDto;
import com.coursework.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAndProviderMapping {
    UserAndProviderMapping MAPPER = Mappers.getMapper(UserAndProviderMapping.class);


    UserAndProvidersDto UserToEntity(UserEntity source);

    List<UserAndProvidersDto> UserTotListEntity(List<UserEntity> source);

    @Mapping(target = "username", source = "name")
    UserAndProvidersDto ProviderToEntity(ProviderEntity source);

    List<UserAndProvidersDto> ProviderTotListEntity(List<ProviderEntity> source);
}
