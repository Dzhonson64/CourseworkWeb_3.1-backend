package com.coursework.web.dto;

import com.coursework.db.model.type.TypeUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Пользователь и Поставщик")
public class UserAndProvidersDto {
    Long id;
    String username;
    String password;
    TypeUser type;
    boolean isAdmin;
}
