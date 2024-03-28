package com.tiernebre.util.error;

import io.javalin.http.HttpStatus;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import java.util.stream.Collectors;

public class ZoneBlitzValidationError extends ZoneBlitzClientError {

  public ZoneBlitzValidationError(String error) {
    super(error, HttpStatus.BAD_REQUEST);
    new ZoneBlitzValidationError(Vector.of(error));
  }

  public ZoneBlitzValidationError(Seq<String> errors) {
    super(errors.collect(Collectors.joining(", ")), HttpStatus.BAD_REQUEST);
  }
}
