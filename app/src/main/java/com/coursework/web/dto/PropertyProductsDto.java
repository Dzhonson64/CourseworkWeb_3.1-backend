package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Свойства товаров каталога", description = "")
public class PropertyProductsDto {
    Long id;
    String name;
    String unit;
    Long catalogId;
}
