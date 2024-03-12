package com.tiernebre.util.validation.error;

public class ZoneBlitzServerError
  extends RuntimeException
  implements ZoneBlitzError {

  @Override
  public String publicMessage() {
    return "An unexpected server error occurred on our end. Please try again or reach out if this error still happens!";
  }
}
