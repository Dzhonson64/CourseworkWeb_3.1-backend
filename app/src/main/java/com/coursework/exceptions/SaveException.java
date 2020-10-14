package com.coursework.exceptions;

import com.coursework.exceptions.helper.ErrorCode;

import java.util.Objects;

public class SaveException extends com.coursework.exceptions.LogicException {

    private final ObjectType objectType;
    private final String message;
    private final Object fieldValue;

    public SaveException(com.coursework.exceptions.SaveException.ObjectType type, String message, Object id) {
        super(ErrorCode.JAVA_ERROR, Objects.toString(id));
        this.objectType = type;
        this.message = message;
        this.fieldValue = Objects.toString(id);
    }
    public enum ObjectType {
        SibelService
    }
    public String toString() {
        return String.format("SaveException{type=%s, message=%s, objectId=%s}", this.objectType, this.message, this.fieldValue);
    }

}
