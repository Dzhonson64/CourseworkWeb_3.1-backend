package com.coursework.exceptions;

import com.coursework.exceptions.helper.ErrorCode;

import java.util.Objects;

public class ObjectNotExistsException extends com.coursework.exceptions.LogicException {

  private final ObjectType objectType;
  private final String message;
  private final String fieldValue;

  public ObjectNotExistsException(com.coursework.exceptions.ObjectNotExistsException.ObjectType type, Long id) {
    super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, id);
    this.objectType = type;
    this.message = "id";
    this.fieldValue = id.toString();
  }

  public ObjectNotExistsException(com.coursework.exceptions.ObjectNotExistsException.ObjectType type, String message, Object id) {
    super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, Objects.toString(id));
    this.objectType = type;
    this.message = message;
    this.fieldValue = Objects.toString(id);
  }

  public enum ObjectType {
    museum,
    tur,
    exhibit
  }

  public String toString() {
    return String.format("ObjectNotExistsException{type=%s, by %s=%s}", this.objectType, this.message, this.fieldValue);
  }
}

