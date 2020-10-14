package com.coursework.exceptions;

import com.coursework.exceptions.helper.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EntityException extends com.coursework.exceptions.LogicException {
    private final String message;
    private final Object fieldValue;

    public EntityException(String message, Object id) {
        super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, Objects.toString(id));
        this.message = message;
        this.fieldValue = Objects.toString(id);
    }
    public String toString() {
        return String.format("SaveException{message=%s, objectId=%s}", this.message, this.fieldValue);
    }
}
