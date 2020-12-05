package com.coursework.service;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.db.repository.ProductRepo;
import com.coursework.db.repository.TypeProductRepo;
import com.coursework.mapper.ProductMapper;
import com.coursework.web.dto.CatalogDto;
import com.coursework.web.dto.type.CatalogType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final TypeProductRepo typeProductRepo;

    public boolean saveCatalogList(List<TypeProductEntity> entityList) {
        if (Objects.isNull(entityList) || entityList.isEmpty()) {
            return false;
        }

        return saveChildren(null, entityList);
    }

    public List<TypeProductEntity> getCatalogList() {
        return typeProductRepo.findAllByType(CatalogType.ROOT_NODE);
    }

    private boolean saveChildren(TypeProductEntity parent, List<TypeProductEntity> childList) {
        for (int i = 0; i < childList.size(); i++) {
            TypeProductEntity entity = childList.get(i);
            if (Objects.nonNull(entity.getId()) && typeProductRepo.findById(entity.getId()).isPresent()) {
                TypeProductEntity entityFromDB = typeProductRepo.findById(entity.getId()).get();
                entity = ProductMapper.MAPPER.typeProductEntityToTypeProductEntity(entityFromDB);
            }
            entity.setParentTypeProduct(parent);
            try {
                typeProductRepo.save(entity);
            } catch (Exception e) {
                return false;
            }

            saveChildren(entity, entity.getTypeProductList());
        }
        return true;
    }

    private boolean getChildren(TypeProductEntity parent, List<TypeProductEntity> childList) {
        for (int i = 0; i < childList.size(); i++) {
            childList.get(i).setParentTypeProduct(parent);
            try {
                TypeProductEntity ty = typeProductRepo.save(childList.get(i));
            } catch (Exception e) {
                return false;
            }

            saveChildren(childList.get(i), childList.get(i).getTypeProductList());
        }
        return true;
    }


}
