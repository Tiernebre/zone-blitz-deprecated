package com.tiernebre.test;

import static org.junit.Assert.assertEquals;

import io.vavr.collection.Seq;
import java.util.function.Function;
import org.slf4j.LoggerFactory;

public final class TestCaseRunner {

  public static <I, O> void run(
    @SuppressWarnings("rawtypes") Class suite,
    Seq<TestCase<I, O>> cases,
    Function<I, O> toTest
  ) {
    cases.forEach(caze -> {
      var LOG = LoggerFactory.getLogger(suite);
      var name = caze.name();
      LOG.debug(String.format("Running Test Case \"%s\"", name));
      var output = caze.output().apply(caze.input());
      if (caze.mock() != null) {
        caze.mock().accept(caze.input(), output);
      }
      try {
        assertEquals(output, toTest.apply(caze.input()));
        LOG.debug(String.format("Test Case \"%s\" PASSED.", name));
      } catch (AssertionError e) {
        String message = String.format(
          "Test Case \"%s\" FAILED. %s",
          name,
          e.getMessage()
        );
        LOG.error(message);
        throw new AssertionError(message, e.getCause());
      }
    });
  }
}
