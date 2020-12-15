package com.coursework.service;

import com.coursework.db.model.ProductEntity;
import com.coursework.db.model.ProductPropertyEntity;
import com.coursework.db.model.PropertyProductEntity;
import com.coursework.db.model.TypeProductEntity;
import com.coursework.db.repository.ProductPropertyRepo;
import com.coursework.db.repository.ProductRepo;
import com.coursework.db.repository.PropertyProductRepo;
import com.coursework.db.repository.TypeProductRepo;
import com.coursework.mapper.ProductMapper;
import com.coursework.mapper.ProductPropertyMapper;
import com.coursework.web.dto.*;
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
    public boolean saveCatalogList(List<CatalogDto> catalogDto) {
        if (Objects.isNull(catalogDto) || catalogDto.isEmpty()) {
            return false;
        }
        List<TypeProductEntity> productEntityList = ProductMapper.MAPPER.toTypeProductListEntity(catalogDto);
        for (TypeProductEntity productEntity : productEntityList) {
            saveChildren(productEntity, productEntity.getTypeProductList());


        }
        typeProductRepo.saveAll(productEntityList);
        delete();
        return true;
    }

    public List<TypeProductEntity> getCatalogList() {
        return typeProductRepo.findAllByTypeAndStatus(CatalogType.ROOT_NODE, StatusActiveType.ENABLE);
    }

    private boolean saveChildren(TypeProductEntity parent, List<TypeProductEntity> catalogChildrenDto) {
//
        for (int i = 0; i < catalogChildrenDto.size(); i++) {
            TypeProductEntity entity = parent.getTypeProductList().get(i);
            entity.setParentTypeProduct(parent);
            saveChildren(entity, entity.getTypeProductList());

        }
        return true;
    }

    private TypeProductEntity setParent(TypeProductEntity source) {
        for (TypeProductEntity entity : source.getTypeProductList()) {
            entity.setParentTypeProduct(source);
            setParent(entity);
        }
        return source;
    }

    public List<TypeProductEntity> getCatalogLast() {
        List<TypeProductEntity> list = typeProductRepo.findAll();

        return list.stream().filter(typeProductEntity -> typeProductEntity.getTypeProductList().size() == 0).collect(Collectors.toList());
    }

    public ProductDto saveProduct(ProductDto productDto) {
        typeProductRepo.findAll();
        ProductEntity productEntity;
        if (Objects.isNull(productDto.getId())) {
            productEntity = ProductMapper.MAPPER.toProductEntity(productDto);
            productEntity.setTypeProduct(typeProductRepo.findById(productDto.getCatalogId()).get());
        }else{
            productEntity = productRepo.findById(productDto.getId()).get();
        }

        return ProductMapper.MAPPER.toProductDto(productRepo.save(productEntity));
    }

    public List<ProductDto> getProductList() {
        typeProductRepo.findAll();
        return ProductMapper.MAPPER.toProductListDto(productRepo.findAll());
    }

    public boolean saveProductProperty(List<ProductPropertyDto> productPropertyDto) {
typeProductRepo.findAll();
        for (ProductPropertyDto propertyDto : productPropertyDto) {
            ProductPropertyEntity productPropertyEntity;
            if (Objects.isNull(propertyDto.getProductPropertyId())) {
                productPropertyEntity = ProductMapper.MAPPER.toProductPropertyEntity(propertyDto);
                productPropertyEntity.setProduct(productRepo.findById(propertyDto.getProductId()).get());
                productPropertyEntity.setPropertyProduct(propertyProductRepo.findById(propertyDto.getPropertyId()).get());
            } else {
                productPropertyEntity = productPropertyRepo.findById(propertyDto.getProductPropertyId()).get();
                productPropertyEntity.setValue( propertyDto.getValue());
            }

            productPropertyRepo.save(productPropertyEntity);
        }

        return true;
    }

    private List<TypeProductEntity> removeUnable(List<TypeProductEntity> typeProductList) {
        for (int i = 0; i < typeProductList.size(); i++) {
            if (typeProductList.get(i).getStatus() == StatusActiveType.UNABLE) {
                typeProductList.remove(typeProductList.get(i));
            } else {
                removeUnable(typeProductList.get(i).getTypeProductList());
            }

        }
        return typeProductList;
    }

    private TypeProductEntity removeUnable(TypeProductEntity typeProductList) {
        if (typeProductList.getStatus() == StatusActiveType.UNABLE) {
            return null;
        }
        for (int i = 0; i < typeProductList.getTypeProductList().size(); i++) {
            if (typeProductList.getTypeProductList().get(i).getStatus() == StatusActiveType.UNABLE) {
                typeProductList.getTypeProductList().remove(typeProductList.getTypeProductList().get(i));
            } else {
                removeUnable(typeProductList.getTypeProductList().get(i).getTypeProductList());
            }

        }
        return typeProductList;
    }

    private void delete() {
        List<TypeProductEntity> typeProductEntities = typeProductRepo.findAllByStatus(StatusActiveType.UNABLE);
        for (TypeProductEntity typeProductEntity : typeProductEntities) {
            propertyProductRepo.deleteAll(propertyProductRepo.getPropertyListByCatalog(typeProductEntity.getId()));
        }
        typeProductRepo.deleteAll(typeProductEntities);

    }

    @Transactional
    public boolean deleteProduct(Long productDtoId) {
        productRepo.delete(productDtoId);

        return true;
    }

    public CatalogDto getCatalogByProductId(Long id) {
        typeProductRepo.findAll();
        ProductEntity productEntity = productRepo.findById(id).get();
        return ProductMapper.MAPPER.toTypeProductDto(productEntity.getTypeProduct());
    }

    public ProductDto getProductById(Long id) {
        typeProductRepo.findAll();
        return ProductMapper.MAPPER.toProductDto(productRepo.findById(id).get());
    }

    public List<FillProperty> getAllPropertyProductByProduct(Long productId) {
        typeProductRepo.findAll();
        ProductEntity productEntity = productRepo.findById(productId).get();
        List<FillProperty> fillProperties = new ArrayList<>();
        for (ProductPropertyEntity p:  productEntity.getProductPropertyList()
             ) {
            FillProperty fillProperty = new FillProperty();
            fillProperty.setValue(p.getValue());
            fillProperty.setProductPropertyId(p.getId());
            fillProperty.setName( p.getPropertyProduct().getName());
            fillProperty.setUnit( p.getPropertyProduct().getUnit());
            fillProperty.setPropertyId( p.getPropertyProduct().getId());
            fillProperties.add(fillProperty);
        }
        return fillProperties;
    }


}
