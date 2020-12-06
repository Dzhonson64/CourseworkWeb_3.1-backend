package com.coursework.web.controller;

import com.coursework.mapper.ProductMapper;
import com.coursework.service.ProductService;
import com.coursework.web.dto.CatalogDto;
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
}
