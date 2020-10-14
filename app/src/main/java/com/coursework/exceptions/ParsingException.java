package com.coursework.exceptions;

import com.coursework.exceptions.helper.ErrorCode;

import java.util.Objects;

public class ParsingException extends com.coursework.exceptions.LogicException {

  private final com.coursework.exceptions.ParsingException.ObjectType objectType;
  private final String message;
  private final String fieldValue;

  public ParsingException(com.coursework.exceptions.ParsingException.ObjectType type, Long id) {
    super(ErrorCode.COMMON_OBJECT_NOT_EXISTS, id);
    this.objectType = type;
    this.message = "id";
    if (id != null) {
      this.fieldValue = id.toString();
    } else {
      this.fieldValue = null;
    }
  }

  public ParsingException(com.coursework.exceptions.ParsingException.ObjectType type, String message, Object id) {
    super(ErrorCode.JAVA_ERROR, Objects.toString(id));
    this.objectType = type;
    this.message = message;
    this.fieldValue = Objects.toString(id);
  }

  public enum ObjectType {
    City
  }

  public String toString() {
    return String.format("ParsingException{type=%s, by %s=%s}", this.objectType, this.message, this.fieldValue);
  }
}