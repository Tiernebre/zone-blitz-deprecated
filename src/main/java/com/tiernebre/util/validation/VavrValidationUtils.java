package com.tiernebre.util.validation;

import io.vavr.Tuple2;
import io.vavr.control.Validation;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public final class VavrValidationUtils {

  public static Validation<String, String> required(
    String value,
    String fieldName
  ) {
    return required(fieldName).apply(value);
  }

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

  public static <T> Function<
    Tuple2<T, T>,
    Validation<String, Tuple2<T, T>>
  > matches(String valueFieldName, String otherValueFieldName) {
    return validation(
      value -> value._1.equals(value._2),
      String.format("%s must match %s.", valueFieldName, otherValueFieldName)
    );
  }

  private static <T> Function<T, Validation<String, T>> validation(
    Function<T, Boolean> predicate,
    String errorMessage
  ) {
    return value ->
      predicate.apply(value)
        ? Validation.valid(value)
        : Validation.invalid(errorMessage);
  }
}
