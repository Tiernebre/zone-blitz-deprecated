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
    String fieldName,
    int length
  ) {
    return validation(
      value -> value.length() <= length,
      String.format(
        "%s cannot be greater than %s characters long.",
        fieldName,
        length
      )
    );
  }

  public static Function<String, Validation<String, String>> minimumLength(
    String fieldName,
    int length
  ) {
    return validation(
      value -> value.length() >= length,
      String.format(
        "%s cannot be lesser than %s characters long.",
        fieldName,
        length
      )
    );
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
