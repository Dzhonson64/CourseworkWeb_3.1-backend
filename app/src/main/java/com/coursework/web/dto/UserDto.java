package com.coursework.web.dto;

import com.coursework.db.model.type.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Schema(name = "Пользователь", description = "Объект пользователя")
public class UserDto {
    private String googleId;
    private String username;
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
    private LocalDate birthday;
    private GenderType male;
    private String email;
    private Long id;
}
