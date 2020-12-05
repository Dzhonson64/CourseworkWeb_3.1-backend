package com.coursework.mapper;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.web.dto.CatalogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "source.value")
    ProductEntity toProductEntity(CatalogDto source);

    List<ProductEntity> toProductListEntity(List<CatalogDto> source);

    @Mapping(target = "typeProductList", source = "source.children")
    @Mapping(target = "name", source = "source.value")
    @Mapping(target = "type", source = "source.status")
    TypeProductEntity toTypeProductEntity(CatalogDto source);

    List<TypeProductEntity> toTypeProductListEntity(List<CatalogDto> source);

    @Mapping(target = "children", source = "source.typeProductList")
    @Mapping(target = "value", source = "source.name")
    @Mapping(target = "status", source = "source.type")
    CatalogDto toTypeProductDto(TypeProductEntity source);

    List<CatalogDto> toTypeProductListDto(List<TypeProductEntity> source);


    TypeProductEntity typeProductEntityToTypeProductEntity(TypeProductEntity source);
}
