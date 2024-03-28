package com.tiernebre.util.error;

import io.javalin.http.HttpStatus;

public class ZoneBlitzClientError
  extends RuntimeException
  implements ZoneBlitzError {

  private final HttpStatus httpStatus;

  public ZoneBlitzClientError(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ZoneBlitzClientError otherError) {
      return (
        publicMessage().equals(otherError.publicMessage()) &&
        httpStatus().equals(otherError.httpStatus())
      );
    } else {
      return false;
    }
  }

  @Override
  public String publicMessage() {
    return this.getMessage();
  }

  @Override
  public HttpStatus httpStatus() {
    return httpStatus;
  }
}
