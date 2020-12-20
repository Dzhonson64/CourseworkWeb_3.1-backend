package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Компания", description = "Объект пользователя")
public class ProviderDto {
    Long id;
    String name;
    String password;
    AddressDto address;
}
