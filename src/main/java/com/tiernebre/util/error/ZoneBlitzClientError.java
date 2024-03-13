package com.tiernebre.util.error;

public class ZoneBlitzClientError
  extends RuntimeException
  implements ZoneBlitzError {

  public ZoneBlitzClientError(String message) {
    super(message);
  }

  @Override
  public String publicMessage() {
    return this.getMessage();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ZoneBlitzClientError otherError) {
      return publicMessage().equals(otherError.publicMessage());
    } else {
      return false;
    }
  }
}
