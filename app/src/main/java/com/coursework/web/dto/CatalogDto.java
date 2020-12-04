package com.coursework.web.dto;

import com.coursework.web.dto.type.CatalogType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Дерево каталога", description = "")
public class CatalogDto {
    private Long id;
    private CatalogDto[] children;
    private String value;
    private CatalogType status;
}
