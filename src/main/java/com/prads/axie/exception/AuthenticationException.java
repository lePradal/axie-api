package com.prads.axie.exception;

public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
