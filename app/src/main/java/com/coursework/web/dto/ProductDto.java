package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Товары", description = "")
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private Long catalogId;
    private String image;
}
