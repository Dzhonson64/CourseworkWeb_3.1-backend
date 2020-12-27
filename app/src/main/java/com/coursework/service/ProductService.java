package com.coursework.service;

import com.coursework.db.model.*;
import com.coursework.db.repository.*;
import com.coursework.exceptions.EntityException;
import com.coursework.exceptions.helper.ErrorCode;
import com.coursework.mapper.ProductMapper;
import com.coursework.mapper.ProductPropertyMapper;
import com.coursework.storage.service.StorageException;
import com.coursework.storage.service.StorageFileNotFoundException;
import com.coursework.storage.service.StorageService;
import com.coursework.web.dto.*;
import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final TypeProductRepo typeProductRepo;
    private final ProductPropertyRepo productPropertyRepo;
    private final ProviderRepo providerRepo;
    private final PropertyProductRepo propertyProductRepo;
    private final StorageService storageService;
    private final WayBillRepo wayBillRepo;

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
            if (entity.getStatus() == StatusActiveType.UNABLE) {
                entity.setParentTypeProduct(null);
            }
            entity.setParentTypeProduct(parent);

            saveChildren(entity, entity.getTypeProductList());
            //typeProductRepo.save(entity);
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
        ProviderEntity providerEntity = providerRepo.findById(productDto.getProviderId()).orElse(null);
        WaybillEntity waybillEntity = new WaybillEntity();

        ProductEntity productEntity;
        if (Objects.isNull(productDto.getId())) {
            productEntity = ProductMapper.MAPPER.toProductEntity(productDto);
            productEntity.setTypeProduct(typeProductRepo.findById(productDto.getCatalogId()).get());
        }else{
            productEntity = productRepo.findById(productDto.getId()).get();
        }
        waybillEntity.setProduct(productEntity);
        waybillEntity.setProvider(providerEntity);
        waybillEntity.setDateArrive(LocalDate.now());
        providerRepo.save(providerEntity);
        ProductDto productDto2 = ProductMapper.MAPPER.toProductDto(productRepo.save(productEntity));
        wayBillRepo.save(waybillEntity);
        return productDto2;
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
@Transactional
    private void delete() {
//        List<TypeProductEntity> typeProductEntities = typeProductRepo.findAllByStatus(StatusActiveType.UNABLE);
//        for (TypeProductEntity typeProductEntity : typeProductEntities) {
//            propertyProductRepo.deleteAll(propertyProductRepo.getPropertyListByCatalog(typeProductEntity.getId()));
//        }
//        typeProductRepo.deleteAll(typeProductEntities);
        typeProductRepo.deleteUnable();
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

    public List<ProductDto> getAllProductsByCatalog(Long catalogId) {
        typeProductRepo.findAll();
        TypeProductEntity typeProductEntity = typeProductRepo.findById(catalogId).get();
        return ProductMapper.MAPPER.toProductListDto(typeProductEntity.getProductList());

    }


    public ProductDto changePhotoProject(Long id, MultipartFile file) {
        ProductEntity projectEntity = productRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS.toString(), "not found entity"));
        if (projectEntity.getImage() != null) {
            deleteImg(projectEntity.getImage());
        }


        String uuidOriginalFilename = getUUIDOriginalFilename(file);
        storageService.store(file, uuidOriginalFilename);
        projectEntity.setImage(uuidOriginalFilename);

        return ProductMapper.MAPPER.toProductDto(productRepo.save(projectEntity));
    }

    public Resource getImg(Long id) {
        ProductEntity productEntity = productRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS.toString(), "not found entity"));
        Resource resource = null;
        try {

        } catch (StorageException e) {
            throw new StorageFileNotFoundException("file not found");
        }
        return storageService.loadAsResource(productEntity.getImage());
    }

    private void deleteImg(String imageFileName) {
        Resource oldImageProject = storageService.loadAsResource(imageFileName);
        try {
            oldImageProject.getFile().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUUIDOriginalFilename(MultipartFile file) {
        int beginIndExpansion = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String fileNewName = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename().substring(0, beginIndExpansion);
        return file.getOriginalFilename().replace(fileName, fileNewName);
    }



    public List<ProductEntity> getLastProducts(){
       List<WaybillEntity> waybillEntities = wayBillRepo.findAll().stream()
               .sorted((o1, o2) -> o2.getDateArrive().compareTo(o1.getDateArrive()))
               .limit(5)
               .collect(Collectors.toList());
        return waybillEntities.stream()
                .map(WaybillEntity::getProduct)
                .collect(Collectors.toList());
    }



}
