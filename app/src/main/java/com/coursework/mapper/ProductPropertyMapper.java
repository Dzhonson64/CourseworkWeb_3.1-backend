package com.coursework.mapper;

import com.coursework.db.model.PropertyProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.web.dto.CatalogDto;
import com.coursework.web.dto.PropertyProductsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductPropertyMapper {
    ProductPropertyMapper MAPPER = Mappers.getMapper(ProductPropertyMapper.class);


    PropertyProductEntity toEntity(PropertyProductsDto source);
    List<PropertyProductEntity> toListEntity(List<PropertyProductsDto> source);

    PropertyProductsDto toDto(PropertyProductEntity source);
    List<PropertyProductsDto> toListDto(List<PropertyProductEntity> source);
}
