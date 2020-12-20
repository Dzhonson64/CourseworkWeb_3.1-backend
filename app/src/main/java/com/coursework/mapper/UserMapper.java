package com.coursework.mapper;

import com.coursework.db.model.UserEntity;
import com.coursework.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "address.region", source = "source.address.region.name")
    @Mapping(target = "address.city", source = "source.address.city.name")
    @Mapping(target = "address.district", source = "source.address.district.name")
    @Mapping(target = "address.street", source = "source.address.street.name")
    @Mapping(target = "address.building", source = "source.address.building.name")
    @Mapping(target = "address.country", source = "source.address.country.name")
    @Mapping(target = "address.type", source = "source.address.type")
    @Mapping(target = "status", expression = "java(com.coursework.db.model.type.Status.ACTIVE)")
    @Mapping(target = "username", source = "source.nickName")
    UserEntity toEntity(UserDto source);

    List<UserEntity> totListEntity(List<UserDto> source);

    @Mapping(target = "address.region.name", source = "source.address.region")
    @Mapping(target = "address.city.name", source = "source.address.city")
    @Mapping(target = "address.district.name", source = "source.address.district")
    @Mapping(target = "address.street.name", source = "source.address.street")
    @Mapping(target = "address.building.name", source = "source.address.building")
    @Mapping(target = "address.country.name", source = "source.address.country")
    @Mapping(target = "address.type", source = "source.address.type")
    @Mapping(target = "address.id", source = "source.address.id")
    @Mapping(target = "nickName", source = "source.username")
    UserDto toDto(UserEntity source);

    List<UserDto> toListDto(List<UserEntity> source);
}
