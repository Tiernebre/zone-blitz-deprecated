package com.tiernebre.util.pagination;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.javalin.http.Context;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

public final class DefaultPaginationWebHelperTest {

  WebPaginationHelper webPaginationHelper = new DefaultWebPaginationHelper();

  @Test
  public void pageRequest() {
    TestCaseRunner.run(
      DefaultPaginationWebHelperTest.class,
      List.of(
        new TestCase<Context, PageRequest>(
          "null context",
          null,
          __ -> PaginationConstants.DEFAULT_PAGE_REQUEST
        ),
        new TestCase<Context, PageRequest>(
          "null first",
          mock(Context.class),
          __ -> PaginationConstants.DEFAULT_PAGE_REQUEST,
          (input, output) -> {
            when(
              input.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME)
            ).thenReturn(null);
          }
        ),
        new TestCase<Context, PageRequest>(
          "null cursor",
          mock(Context.class),
          __ -> PaginationConstants.DEFAULT_PAGE_REQUEST,
          (input, output) -> {
            when(
              input.queryParam(PaginationConstants.CURSOR_QUERY_PARAM_NAME)
            ).thenReturn(null);
          }
        ),
        new TestCase<Context, PageRequest>(
          "custom first",
          mock(Context.class),
          __ -> new PageRequest(302, null),
          (input, output) -> {
            when(
              input.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME)
            ).thenReturn(Integer.toString(output.first()));
          }
        ),
        new TestCase<Context, PageRequest>(
          "custom cursor",
          mock(Context.class),
          __ ->
            new PageRequest(PaginationConstants.DEFAULT_PAGE_SIZE, "abcdef"),
          (input, output) -> {
            when(
              input.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME)
            ).thenReturn(null);
            when(
              input.queryParam(PaginationConstants.CURSOR_QUERY_PARAM_NAME)
            ).thenReturn(output.after());
          }
        ),
        new TestCase<Context, PageRequest>(
          "non number size",
          mock(Context.class),
          __ -> PaginationConstants.DEFAULT_PAGE_REQUEST,
          (input, output) -> {
            when(
              input.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME)
            ).thenReturn("not a number");
          }
        )
      ),
      webPaginationHelper::pageRequest
    );
  }
}
