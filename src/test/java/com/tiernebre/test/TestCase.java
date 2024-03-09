package com.tiernebre.test;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record TestCase<I, O>(
  String name,
  I input,
  Function<I, O> output,
  BiConsumer<I, O> mock,
  BiConsumer<I, O> verification
) {
  public TestCase(String name, I input, Function<I, O> output) {
    this(name, input, output, null, null);
  }

  public TestCase(
    String name,
    I input,
    Function<I, O> output,
    BiConsumer<I, O> mock
  ) {
    this(name, input, output, mock, null);
  }
}
