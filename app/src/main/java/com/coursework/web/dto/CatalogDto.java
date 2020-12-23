package com.coursework.web.dto;

import com.coursework.web.dto.type.CatalogType;
import com.coursework.web.dto.type.StatusActiveType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(name = "Дерево каталога", description = "")
public class CatalogDto {
    private Long id;
    private List<CatalogDto> children = new ArrayList<>();
    private String value;
    private CatalogType type;
    private StatusActiveType status;
}
