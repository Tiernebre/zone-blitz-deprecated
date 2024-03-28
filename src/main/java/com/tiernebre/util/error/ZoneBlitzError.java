package com.tiernebre.util.error;

import io.javalin.http.HttpStatus;

public interface ZoneBlitzError {
  /**
   * Message that should be rendered and viewed by the "public" (any user of the Zone Blitz application).
   *
   * This method does not return any internal implementation details if an error was caused by something
   * on the server's end.
   *
   * If the user provided bad data, we explicitly and clearly communicate they need to fix it within this message.
   * @return A public friendly obfuscated version of the error.
   */
  public String publicMessage();

  /**
   * @return An associated HTTP status code with the error. Likely going to be 4xx+.
   */
  public HttpStatus httpStatus();

  /**
   * Narrows a specific ZoneBlitzError implementation into a ZoneBlitzError. Useful for generic
   * type mapping casting within functional chain methods.
   */
  static <T extends ZoneBlitzError> ZoneBlitzError narrow(T error) {
    return (ZoneBlitzError) error;
  }
}
