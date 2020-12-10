package com.coursework.service;

import com.coursework.db.model.ProductPropertyEntity;
import com.coursework.db.model.PropertyProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.db.model.TypePropertyEntity;
import com.coursework.db.repository.PropertyProductRepo;
import com.coursework.db.repository.TypeProductRepo;
import com.coursework.db.repository.TypePropertyRepo;
import com.coursework.mapper.ProductPropertyMapper;
import com.coursework.web.dto.PropertyProductsDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductPropertyService {
    private final PropertyProductRepo propertyProductRepo;
    private final TypeProductRepo typeProductRepo;
    private final TypePropertyRepo typePropertyRepo;

    public boolean savePropertyProduct(List<PropertyProductsDto> propertyProductsList) {
        for (PropertyProductsDto propertyProducts : propertyProductsList) {
            if (Objects.isNull(propertyProducts.getId())) {
                typeProductRepo.findAll();
                TypeProductEntity typeProductEntity = typeProductRepo.findById(propertyProducts.getCatalogId()).get();
                TypePropertyEntity typePropertyEntity = new TypePropertyEntity();
                typePropertyEntity.setTypeProductProperty(typeProductEntity);
                PropertyProductEntity propertyProductEntity = ProductPropertyMapper.MAPPER.toEntity(propertyProducts);
                PropertyProductEntity savedPropertyProductEntity = propertyProductRepo.save(propertyProductEntity);
                typePropertyEntity.setPropertyProduct(savedPropertyProductEntity);
                typePropertyRepo.save(typePropertyEntity);
            }else{
                PropertyProductEntity propertyProductEntity = propertyProductRepo.findById(propertyProducts.getId()).get();
                BeanUtils.copyProperties(propertyProducts, propertyProductEntity, "id");
                propertyProductRepo.save(propertyProductEntity);
            }


        }
        return true;
    }

    public List<PropertyProductsDto> getPropertyProduct(){
        List<PropertyProductEntity> entities = propertyProductRepo.findAll();
        List<PropertyProductsDto> productsDtos = ProductPropertyMapper.MAPPER.toListDto(entities);
        for (PropertyProductsDto dto:  productsDtos){
            dto.setCatalogId(propertyProductRepo.getCatalogId(dto.getId()));
        }
        return productsDtos;
    }

    public List<PropertyProductsDto> getPropertyProductByCatalog(Long catalogId){
        List<PropertyProductEntity> propertyList = propertyProductRepo.getPropertyListByCatalog(catalogId);
       return ProductPropertyMapper.MAPPER.toListDto(propertyList);
    }

    public Boolean deleteProduct(Long id) {
        propertyProductRepo.deleteById(id);
        return true;
    }
}
