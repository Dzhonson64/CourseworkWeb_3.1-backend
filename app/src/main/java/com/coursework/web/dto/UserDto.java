package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "Пользователь", description = "Объект пользователя")
public class UserDto {
    private String googleId;
    private String nickName;
    private String status;
    private String snils;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String postcode;
    private BigDecimal money;
    private String password;
    private AddressDto address;
}
