package com.coursework.service;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.db.repository.ProductRepo;
import com.coursework.db.repository.TypeProductRepo;
import com.coursework.mapper.ProductMapper;
import com.coursework.web.dto.CatalogDto;
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
@Transactional
    public boolean saveCatalogList(List<TypeProductEntity> entityList) {
        if (Objects.isNull(entityList) || entityList.isEmpty()) {
            return false;
        }
        boolean b = saveChildren(entityList);
        typeProductRepo.deleteAll();
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




}
