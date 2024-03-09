package com.tiernebre.test;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record TestCase<I, O>(
  String name,
  I input,
  Function<I, O> output,
  BiConsumer<I, O> mock
) {
  public TestCase(String name, I input, Function<I, O> output) {
    this(name, input, output, null);
  }
}
