package com.coursework.db.model.type;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum TypeAddress {
    User ("User"),
    Provider ("Provider");

    @NonNull
    private final String value;
}
