package com.coursework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FillProperty {
    Long propertyId;
    Long productPropertyId;
    String value;
    String unit;
    String name;
}
