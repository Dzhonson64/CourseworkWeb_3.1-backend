package com.coursework.web.controller;

import com.coursework.mapper.ProductMapper;
import com.coursework.mapper.ProductPropertyMapper;
import com.coursework.service.ProductPropertyService;
import com.coursework.service.ProductService;
import com.coursework.web.dto.CatalogDto;
import com.coursework.web.dto.PropertyProductsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Product", description = "")
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductPropertyService productPropertyService;

    @Operation(summary = "Сохранение дерева каталога")
    @PostMapping("/catalog")
    @ResponseBody
    public boolean saveCatalog(@RequestBody List<CatalogDto> catalogDto){
        return productService.saveCatalogList(ProductMapper.MAPPER.toTypeProductListEntity(catalogDto));
    }

    @Operation(summary = "Получения дерева каталога")
    @GetMapping("/catalog")
    @ResponseBody
    public List<CatalogDto> getCatalog(){
        return ProductMapper.MAPPER.toTypeProductListDto(productService.getCatalogList());
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
}
