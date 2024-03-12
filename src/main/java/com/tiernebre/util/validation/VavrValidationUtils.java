package com.tiernebre.util.validation;

import io.vavr.control.Validation;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public final class VavrValidationUtils {

  public static Function<String, Validation<String, String>> required(
    String fieldName
  ) {
    return validation(
      value -> StringUtils.isNotBlank(value),
      String.format("%s is a required field.", fieldName)
    );
  }

  public static Function<String, Validation<String, String>> maximumLength(
    int length,
    String errorMessage
  ) {
    return validation(value -> value.length() <= length, errorMessage);
  }

  public static Function<String, Validation<String, String>> minimumLength(
    int length,
    String errorMessage
  ) {
    return validation(value -> value.length() >= length, errorMessage);
  }

  private static Function<String, Validation<String, String>> validation(
    Function<String, Boolean> predicate,
    String errorMessage
  ) {
    return value ->
      predicate.apply(value)
        ? Validation.valid(value)
        : Validation.invalid(errorMessage);
  }
}
