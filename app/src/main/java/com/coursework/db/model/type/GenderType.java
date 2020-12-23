package com.coursework.db.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum GenderType {
    MALE ("Женский"),
    FEMALE ("Мужской");
    private final String gender;
}
