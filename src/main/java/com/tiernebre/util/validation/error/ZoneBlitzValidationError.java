package com.tiernebre.util.validation.error;

import io.vavr.collection.Seq;
import java.util.stream.Collectors;

public class ZoneBlitzValidationError extends ZoneBlitzClientError {

  public ZoneBlitzValidationError(Seq<String> errors) {
    super(errors.collect(Collectors.joining(", ")));
  }
}
