package com.tiernebre.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.authentication.registration.Registration;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageEdge;
import com.tiernebre.util.pagination.PageInfo;
import com.tiernebre.util.pagination.PageRequest;
import com.tiernebre.util.pagination.PaginationConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class JooqRepositoryPaginationStrategyTest extends JooqDatabaseTest {

  @Test
  public void seek() {
    var first = 2;
    var edges = seedRowsAsEdges(first);
    assertEquals(
      new Page<>(edges, new PageInfo(edges.getLast().cursor(), false)),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class
      )
    );
  }

  @Test
  public void seekAfterCursor() {
    var first = 3;
    var edges = seedRowsAsEdges(first);
    var cursor = edges.getFirst().cursor();
    assertEquals(
      new Page<>(
        edges.stream().skip(1).collect(Collectors.toUnmodifiableList()),
        new PageInfo(edges.getLast().cursor(), false)
      ),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, cursor),
        Registration.class
      )
    );
  }

  @Test
  public void seekHasNextPage() {
    var first = 3;
    var edges = seedRowsAsEdges(first + 1);
    var limitedEdges = edges
      .stream()
      .limit(first)
      .collect(Collectors.toUnmodifiableList());
    assertEquals(
      new Page<>(
        limitedEdges,
        new PageInfo(limitedEdges.getLast().cursor(), true)
      ),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class
      )
    );
  }

  @Test
  public void seekCanPaginate() {
    var edges = seedRowsAsEdges(20);
    var pageSize = 10;
    var firstSeekedPage = paginationStrategy.seek(
      Tables.REGISTRATION,
      Tables.REGISTRATION.ID,
      new PageRequest(pageSize, null),
      Registration.class
    );
    var expectedFirstEdges = edges
      .stream()
      .limit(pageSize)
      .collect(Collectors.toUnmodifiableList());
    var expectedFirstPage = new Page<>(
      expectedFirstEdges,
      new PageInfo(expectedFirstEdges.getLast().cursor(), true)
    );
    assertEquals(expectedFirstPage, firstSeekedPage);
    var secondSeekedPage = paginationStrategy.seek(
      Tables.REGISTRATION,
      Tables.REGISTRATION.ID,
      new PageRequest(pageSize, firstSeekedPage.info().endCursor()),
      Registration.class
    );
    var expectedSecondEdges = edges
      .stream()
      .skip(pageSize)
      .collect(Collectors.toUnmodifiableList());
    var expectedSecondPage = new Page<>(
      expectedSecondEdges,
      new PageInfo(expectedSecondEdges.getLast().cursor(), false)
    );
    assertEquals(expectedSecondPage, secondSeekedPage);
  }

  @Test
  public void seekEmpty() {
    assertEquals(
      new Page<>(Collections.emptyList(), new PageInfo(null, false)),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(20, null),
        Registration.class
      )
    );
  }

  @Test
  public void seekWithCustomConditions() {
    var first = 2;
    var edges = seedRowsAsEdges(first);
    var expectedEdge = edges.getFirst();
    var firstUsername = expectedEdge.node().username();
    assertEquals(
      new Page<>(
        List.of(expectedEdge),
        new PageInfo(expectedEdge.cursor(), false)
      ),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class,
        List.of(Tables.REGISTRATION.USERNAME.eq(firstUsername))
      )
    );
  }

  @Test
  public void seekWithNullConditions() {
    var first = 2;
    var edges = seedRowsAsEdges(first);
    assertEquals(
      new Page<>(edges, new PageInfo(edges.getLast().cursor(), false)),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class,
        null
      )
    );
  }

  @Test
  public void seekWithNullRequest() {
    var first = 2;
    var edges = seedRowsAsEdges(first);
    assertEquals(
      new Page<>(edges, new PageInfo(edges.getLast().cursor(), false)),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        null,
        Registration.class,
        null
      )
    );
  }

  @Test
  public void seekWithMinimumSize() {
    var first = -1;
    seedRowsAsEdges(2);
    assertEquals(
      new Page<>(Collections.emptyList(), new PageInfo(null, true)),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class,
        null
      )
    );
  }

  @Test
  public void seekWithMaximumSize() {
    var first = 2010;
    var edges = seedRowsAsEdges(first);
    var expectedEdges = edges
      .stream()
      .limit(PaginationConstants.MAX_PAGE_SIZE)
      .collect(Collectors.toList());
    assertEquals(
      new Page<>(
        expectedEdges,
        new PageInfo(expectedEdges.getLast().cursor(), true)
      ),
      paginationStrategy.seek(
        Tables.REGISTRATION,
        Tables.REGISTRATION.ID,
        new PageRequest(first, null),
        Registration.class,
        null
      )
    );
  }

  private List<PageEdge<Registration>> seedRowsAsEdges(int size) {
    return seedRows(size)
      .stream()
      .map(row -> new PageEdge<>(row, cursorMapper.toCursor(row)))
      .collect(Collectors.toUnmodifiableList());
  }

  private Collection<Registration> seedRows(int size) {
    return IntStream.range(0, size)
      .mapToObj(__ -> seedRow())
      .collect(Collectors.toUnmodifiableList());
  }

  private Registration seedRow() {
    var registration = dsl.newRecord(Tables.REGISTRATION);
    registration.setUsername(UUID.randomUUID().toString());
    registration.setPassword(UUID.randomUUID().toString());
    registration.store();
    return registration.into(Registration.class);
  }
}
