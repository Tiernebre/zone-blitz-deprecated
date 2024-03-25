package com.tiernebre.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.authentication.registration.Registration;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageEdge;
import com.tiernebre.util.pagination.PageInfo;
import com.tiernebre.util.pagination.PageRequest;
import java.util.Collection;
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
    var expected = new Page<>(
      edges,
      new PageInfo(edges.getLast().cursor(), false)
    );
    var seeked = paginationStrategy.seek(
      Tables.REGISTRATION,
      Tables.REGISTRATION.ID,
      new PageRequest(first, null),
      Registration.class
    );
    assertEquals(expected, seeked);
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
