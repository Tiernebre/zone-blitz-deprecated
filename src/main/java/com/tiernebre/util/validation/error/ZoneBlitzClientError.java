package com.tiernebre.util.validation.error;

public final class ZoneBlitzClientError
  extends RuntimeException
  implements ZoneBlitzError {

  @Override
  public String publicMessage() {
    return this.getMessage();
  }
}
