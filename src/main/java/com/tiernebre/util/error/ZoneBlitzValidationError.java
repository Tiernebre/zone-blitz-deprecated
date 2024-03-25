package com.tiernebre.util.error;

import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import java.util.stream.Collectors;

public class ZoneBlitzValidationError extends ZoneBlitzClientError {

  public ZoneBlitzValidationError(String error) {
    super(error);
    new ZoneBlitzValidationError(Vector.of(error));
  }

  public ZoneBlitzValidationError(Seq<String> errors) {
    super(errors.collect(Collectors.joining(", ")));
  }
}
