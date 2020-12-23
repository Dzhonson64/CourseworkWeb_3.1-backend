package com.coursework.web.controller;

import com.coursework.mapper.ProductMapper;
import com.coursework.service.ProductPropertyService;
import com.coursework.service.ProductService;
import com.coursework.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Product", description = "")
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductPropertyService productPropertyService;

    @Operation(summary = "Сохранение дерева каталога")
    @PostMapping("")
    @ResponseBody
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        if (Objects.isNull(productDto.getId()) &&
         Objects.isNull(productDto.getPrice()) &&
         Objects.isNull(productDto.getCatalogId()) &&
         Objects.isNull(productDto.getImage()) &&
        productDto.getDescription().isEmpty() &&
        productDto.getName().isEmpty()
        ) {
            return productDto;
        }
        return productService.saveProduct(productDto);
    }


    @Operation(summary = "Сохранение дерева каталога")
    @GetMapping("")
    @ResponseBody
    public List<ProductDto> getProducts(){
        return productService.getProductList();
    }

    @Operation(summary = "Сохранение значений свойтств")
    @PostMapping("/product-property")
    @ResponseBody
    public boolean saveProductProperty(@RequestBody List<ProductPropertyDto> productPropertyList){
        return productService.saveProductProperty(productPropertyList);
    }

    @Operation(summary = "Сохранение дерева каталога")
    @PostMapping("/catalog")
    @ResponseBody
    public boolean saveCatalog(@RequestBody List<CatalogDto> catalogDto){
        return productService.saveCatalogList(catalogDto);
    }

    @Operation(summary = "Получения дерева каталога")
    @GetMapping("/catalog")
    @ResponseBody
    public List<CatalogDto> getCatalog(){
        return ProductMapper.MAPPER.toTypeProductListDto(productService.getCatalogList());
    }

    @Operation(summary = "Получения дерева каталога")
    @GetMapping("{id}/catalog")
    @ResponseBody
    public CatalogDto getCatalog(@PathVariable("id") Long id){
        return productService.getCatalogByProductId(id);
    }

    @Operation(summary = "Получения дерева каталога")
    @GetMapping("/catalog/last")
    @ResponseBody
    public List<CatalogDto> getCatalogLast(){
        return ProductMapper.MAPPER.toTypeProductListDto(productService.getCatalogLast());
    }



    @Operation(summary = "Сохранение свойств товаров")
    @PostMapping("/properties")
    @ResponseBody
    public boolean saveProperties(@RequestBody  List<PropertyProductsDto> list){
        return productPropertyService.savePropertyProduct(list);
    }

    @Operation(summary = "Получения свойств товаров")
    @GetMapping("/properties")
    @ResponseBody
    public List<PropertyProductsDto> getProperties(){
        return productPropertyService.getPropertyProduct();
    }

    @Operation(summary = "Удаление свойств товаров")
    @DeleteMapping("/properties/{id}")
    @ResponseBody
    public Boolean deleteProperties(@PathVariable("id") Long id){
        return productPropertyService.deleteProduct(id);
    }

    @Operation(summary = "Удаление свойств товаров")
    @GetMapping("/properties/catalog/{id}")
    @ResponseBody
    public List<PropertyProductsDto> getPropertyProductByCatalog(@PathVariable("id") Long id){
        return productPropertyService.getPropertyProductByCatalog(id);
    }

    @Operation(summary = "Удаление свойств товаров")
    @DeleteMapping("{id}")
    @ResponseBody
    public boolean getDeleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @Operation(summary = "Получение всех товаров по id")
    @GetMapping("{id}/properties")
    @ResponseBody
    public List<FillProperty>  getAllPropertyByProduct(@PathVariable("id") Long id){
        return productService.getAllPropertyProductByProduct(id);
    }

    @Operation(summary = "Получение товара по id товаров")
    @GetMapping("{id}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @Operation(summary = "Получение всех продуктов каталога")
    @GetMapping("catalog/{id}")
    @ResponseBody
    public List<ProductDto> getAllProductsByCatalog(@PathVariable("id") Long id){
        return productService.getAllProductsByCatalog(id);
    }

    @PostMapping("{id}/image")
    @ResponseBody
    public ProductDto changeImageProject(@PathVariable("id") Long id, @RequestParam("image") MultipartFile form) {
        return productService.changePhotoProject(id, form);
    }

    @GetMapping("/{id}/image")
    @ResponseBody
    public Resource getImgProject(@PathVariable("id") Long projectId) {
        return productService.getImg(projectId);
    }
}
