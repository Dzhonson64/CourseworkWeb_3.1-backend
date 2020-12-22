package com.coursework.web.dto;

import com.coursework.db.model.type.TypeUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Дополнительная информация о адреса", description = "Дополнительная информация о каждом элеменете адреса")
public class AddressDto {
    private Long id;
    private AddressOptional country;
    private AddressOptional region;
    private AddressOptional city;
    private AddressOptional district;
    private AddressOptional street;
    private AddressOptional building;
    private TypeUser type;
}
