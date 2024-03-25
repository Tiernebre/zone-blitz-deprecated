package com.tiernebre.database;

import com.tiernebre.util.pagination.CursorMapper;
import com.tiernebre.util.pagination.Identifiable;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageEdge;
import com.tiernebre.util.pagination.PageInfo;
import com.tiernebre.util.pagination.PageRequest;
import io.vavr.control.Try;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;

public final class JooqRepositoryPaginationStrategy {

  private final DSLContext dsl;
  private final CursorMapper cursorMapper;

  public JooqRepositoryPaginationStrategy(
    DSLContext dsl,
    CursorMapper cursorMapper
  ) {
    this.dsl = dsl;
    this.cursorMapper = cursorMapper;
  }

  public <R extends Record, T extends Identifiable> Page<T> seek(
    Table<R> table,
    TableField<R, Long> field,
    PageRequest request,
    Class<T> fetchInto
  ) {
    return seek(table, field, request, fetchInto, Collections.emptyList());
  }

  public <R extends Record, T extends Identifiable> Page<T> seek(
    Table<R> table,
    TableField<R, Long> field,
    PageRequest request,
    Class<T> fetchInto,
    Collection<Condition> conditions
  ) {
    List<PageEdge<T>> edges = dsl
      .select()
      .from(table)
      .where(
        Stream.concat(
          Collections.singleton(
            field.greaterThan(cursorMapper.cursorToId(request.after()))
          ).stream(),
          conditions.stream()
        ).collect(Collectors.toList())
      )
      .orderBy(field, field.desc())
      .limit(request.first() + 1)
      .fetchInto(fetchInto)
      .stream()
      .map(node -> new PageEdge<>(node, cursorMapper.toCursor(node)))
      .collect(Collectors.toUnmodifiableList());
    return new Page<>(
      edges.stream().limit(request.first()).collect(Collectors.toList()),
      new PageInfo(
        Try.of(() -> edges.getLast())
          .map(PageEdge::cursor)
          .toOption()
          .getOrNull(),
        edges.size() > request.first()
      )
    );
  }
}
