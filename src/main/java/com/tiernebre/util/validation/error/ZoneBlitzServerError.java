package com.tiernebre.util.validation.error;

public class ZoneBlitzServerError
  extends RuntimeException
  implements ZoneBlitzError {

  public ZoneBlitzServerError(String message) {
    super(message);
  }

  @Override
  public String publicMessage() {
    return "An unexpected server error occurred on our end. Please try again or reach out if this error still happens!";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ZoneBlitzServerError otherError) {
      return getMessage().equals(otherError.getMessage());
    } else {
      return false;
    }
  }
}
