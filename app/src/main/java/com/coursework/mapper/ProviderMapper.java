package com.coursework.mapper;

import com.coursework.db.model.ProviderEntity;
import com.coursework.web.dto.ProviderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProviderMapper {

    ProviderMapper MAPPER = Mappers.getMapper(ProviderMapper.class);

    @Mapping(target = "address.region", source = "source.address.region.name")
    @Mapping(target = "address.city", source = "source.address.city.name")
    @Mapping(target = "address.district", source = "source.address.district.name")
    @Mapping(target = "address.street", source = "source.address.street.name")
    @Mapping(target = "address.building", source = "source.address.building.name")
    @Mapping(target = "address.country", source = "source.address.country.name")
    @Mapping(target = "address.type", source = "source.address.type")
    ProviderEntity toEntity(ProviderDto source);

    List<ProviderEntity> totListEntity(List<ProviderDto> source);

    @Mapping(target = "address.region.name", source = "source.address.region")
    @Mapping(target = "address.city.name", source = "source.address.city")
    @Mapping(target = "address.district.name", source = "source.address.district")
    @Mapping(target = "address.street.name", source = "source.address.street")
    @Mapping(target = "address.building.name", source = "source.address.building")
    @Mapping(target = "address.country.name", source = "source.address.country")
    @Mapping(target = "address.type", source = "source.address.type")
    @Mapping(target = "address.id", source = "source.address.id")
    ProviderDto toDto(ProviderEntity source);

    List<ProviderDto> toListDto(List<ProviderEntity> source);
}
