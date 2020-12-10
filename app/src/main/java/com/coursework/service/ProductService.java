package com.coursework.service;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.ProductPropertyEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.db.repository.ProductPropertyRepo;
import com.coursework.db.repository.ProductRepo;
import com.coursework.db.repository.PropertyProductRepo;
import com.coursework.db.repository.TypeProductRepo;
import com.coursework.mapper.ProductMapper;
import com.coursework.web.dto.CatalogDto;
import com.coursework.web.dto.ProductDto;
import com.coursework.web.dto.ProductPropertyDto;
import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final TypeProductRepo typeProductRepo;
    private final ProductPropertyRepo productPropertyRepo;
    private final PropertyProductRepo propertyProductRepo;
@Transactional
    public boolean saveCatalogList(List<TypeProductEntity> entityList) {
        if (Objects.isNull(entityList) || entityList.isEmpty()) {
            return false;
        }
        boolean b = saveChildren(entityList);
        //typeProductRepo.deleteAll();
        typeProductRepo.saveAll(entityList);

        return b;
    }

    public List<TypeProductEntity> getCatalogList() {
        return typeProductRepo.findAllByType(CatalogType.ROOT_NODE);
    }

    private boolean saveChildren(List<TypeProductEntity> childList) {
        List<TypeProductEntity> childFilteredList = childList.stream()
                .filter(typeProductEntity -> typeProductEntity.getStatusActiveType() == StatusActiveType.ENABLE &&  !typeProductEntity.getName().isEmpty()).collect(Collectors.toList());
        childList.clear();
        childList.addAll(childFilteredList);
        for (int i = 0; i < childList.size(); i++) {
            setParent(childList.get(i));
            saveChildren(childList.get(i).getTypeProductList());
        }
        return true;
    }

    private TypeProductEntity setParent(TypeProductEntity  source) {
        for (TypeProductEntity entity : source.getTypeProductList()) {
            entity.setParentTypeProduct(source);
            setParent(entity);
        }
        return source;
    }
    public List<TypeProductEntity> getCatalogLast(){
    List<TypeProductEntity> list = typeProductRepo.findAll();

        return list.stream().filter(typeProductEntity -> typeProductEntity.getTypeProductList().size() == 0).collect(Collectors.toList());
    }

    public ProductDto saveProduct(ProductDto productDto){
        ProductEntity productEntity = ProductMapper.MAPPER.toProductEntity(productDto);
        return  ProductMapper.MAPPER.toProductDto(productRepo.save(productEntity));
    }

    public List<ProductDto> getProductList(){
        return ProductMapper.MAPPER.toProductListDto(productRepo.findAll());
    }

    public boolean saveProductProperty(List<ProductPropertyDto> productPropertyDto){

        for (ProductPropertyDto propertyDto: productPropertyDto){
            ProductPropertyEntity productPropertyEntity;
            if (Objects.isNull(propertyDto.getId())) {
                productPropertyEntity = ProductMapper.MAPPER.toProductPropertyEntity(propertyDto);
                productPropertyEntity.setProduct(productRepo.findById(propertyDto.getProductId()).get());
                productPropertyEntity.setPropertyProduct(propertyProductRepo.findById(propertyDto.getPropertyId()).get());
            }else{
                productPropertyEntity = productPropertyRepo.findById(propertyDto.getId()).get();
                BeanUtils.copyProperties(propertyDto, productPropertyEntity, "id");
            }

            productPropertyRepo.save(productPropertyEntity);
        }

        return true;
    }




}
