package com.coursework.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "User", description = "Объект пользователя")
public class UserDto {
    String googleId;
    String username;
    String status;
    String snils;
    String surname;
    String name;
    String patronymic;
    String phone;
    String postcode;
    BigDecimal money;
}
