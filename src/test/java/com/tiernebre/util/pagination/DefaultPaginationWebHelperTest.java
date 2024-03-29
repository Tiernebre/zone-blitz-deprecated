package com.tiernebre.util.pagination;

import static org.mockito.Mockito.mock;

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
          "null first",
          mock(Context.class),
          __ -> new PageRequest(PaginationConstants.DEFAULT_PAGE_SIZE, null)
        )
      ),
      webPaginationHelper::pageRequest
    );
  }
}
