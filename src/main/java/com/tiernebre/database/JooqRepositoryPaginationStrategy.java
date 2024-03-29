package com.tiernebre.database;

import com.tiernebre.util.pagination.CursorMapper;
import com.tiernebre.util.pagination.Identifiable;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageEdge;
import com.tiernebre.util.pagination.PageInfo;
import com.tiernebre.util.pagination.PageRequest;
import com.tiernebre.util.pagination.PaginationConstants;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

  /**
   * Cursor based pagination utility function that works with given jOOQ table
   * and field data.
   *
   * @param <R> The jOOQ Record to paginate over.
   * @param <T> The DTO to map jOOQ records to.
   * @param table The table to paginate over.
   * @param field The table field that maps to the cursor being paginated over. Most likely the primary key id.
   * @param request Pagination request data.
   * @param fetchInto Mapping class for the jOOQ record.
   * @return A Page of the found mapped jOOQ records.
   */
  public <R extends Record, T extends Identifiable> Page<T> seek(
    Table<R> table,
    TableField<R, Long> field,
    PageRequest request,
    Class<T> fetchInto
  ) {
    return seek(table, field, request, fetchInto, Collections.emptyList());
  }

  /**
   * Cursor based pagination utility function that works with given jOOQ table
   * and field data.
   *
   * @param <R> The jOOQ Record to paginate over.
   * @param <T> The DTO to map jOOQ records to.
   * @param table The table to paginate over.
   * @param field The table field that maps to the cursor being paginated over. Most likely the primary key id.
   * @param request Pagination request data.
   * @param fetchInto Mapping class for the jOOQ record.
   * @param conditions Specific conditions to add onto the `WHERE` clause of the pagination.
   * @return A Page of the found mapped jOOQ records.
   */
  public <R extends Record, T extends Identifiable> Page<T> seek(
    Table<R> table,
    TableField<R, Long> field,
    PageRequest request,
    Class<T> fetchInto,
    Collection<Condition> conditions
  ) {
    var normalizedRequest = Option.of(request).getOrElse(
      PaginationConstants.DEFAULT_PAGE_REQUEST
    );
    var pageSize = normalizedRequest.first();
    List<PageEdge<T>> edges = dsl
      .select()
      .from(table)
      .where(
        Stream.concat(
          Collections.singleton(
            field.greaterThan(
              cursorMapper.cursorToId(normalizedRequest.after())
            )
          ).stream(),
          Optional.ofNullable(conditions)
            .orElse(Collections.emptyList())
            .stream()
        ).collect(Collectors.toUnmodifiableList())
      )
      .orderBy(field, field.desc())
      .limit(pageSize + 1)
      .fetchInto(fetchInto)
      .stream()
      .map(node -> new PageEdge<>(node, cursorMapper.toCursor(node)))
      .collect(Collectors.toUnmodifiableList());
    var limitedEdges = edges
      .stream()
      .limit(pageSize)
      .collect(Collectors.toUnmodifiableList());
    return new Page<>(
      limitedEdges,
      new PageInfo(
        Try.of(() -> limitedEdges.getLast())
          .map(PageEdge::cursor)
          .toOption()
          .getOrNull(),
        edges.size() > pageSize
      )
    );
  }
}
