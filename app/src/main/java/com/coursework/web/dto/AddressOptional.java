package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "Дополнительная информация о адреса", description = "Дополнительная информация о каждом элеменете адреса")
public class AddressOptional {
    String name;
    String id;
    String type;
    String typeShort;
}
