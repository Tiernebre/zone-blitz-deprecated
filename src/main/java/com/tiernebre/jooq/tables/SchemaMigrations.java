/*
 * This file is generated by jOOQ.
 */
package com.tiernebre.jooq.tables;

import com.tiernebre.jooq.Keys;
import com.tiernebre.jooq.Public;
import com.tiernebre.jooq.tables.records.SchemaMigrationsRecord;
import java.util.Collection;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SchemaMigrations extends TableImpl<SchemaMigrationsRecord> {

  private static final long serialVersionUID = 1L;

  /**
   * The reference instance of <code>public.schema_migrations</code>
   */
  public static final SchemaMigrations SCHEMA_MIGRATIONS =
    new SchemaMigrations();

  /**
   * The class holding records for this type
   */
  @Override
  public Class<SchemaMigrationsRecord> getRecordType() {
    return SchemaMigrationsRecord.class;
  }

  /**
   * The column <code>public.schema_migrations.version</code>.
   */
  public final TableField<SchemaMigrationsRecord, String> VERSION = createField(
    DSL.name("version"),
    SQLDataType.VARCHAR(128).nullable(false),
    this,
    ""
  );

  private SchemaMigrations(Name alias, Table<SchemaMigrationsRecord> aliased) {
    this(alias, aliased, (Field<?>[]) null, null);
  }

  private SchemaMigrations(
    Name alias,
    Table<SchemaMigrationsRecord> aliased,
    Field<?>[] parameters,
    Condition where
  ) {
    super(
      alias,
      null,
      aliased,
      parameters,
      DSL.comment(""),
      TableOptions.table(),
      where
    );
  }

  /**
   * Create an aliased <code>public.schema_migrations</code> table reference
   */
  public SchemaMigrations(String alias) {
    this(DSL.name(alias), SCHEMA_MIGRATIONS);
  }

  /**
   * Create an aliased <code>public.schema_migrations</code> table reference
   */
  public SchemaMigrations(Name alias) {
    this(alias, SCHEMA_MIGRATIONS);
  }

  /**
   * Create a <code>public.schema_migrations</code> table reference
   */
  public SchemaMigrations() {
    this(DSL.name("schema_migrations"), null);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Public.PUBLIC;
  }

  @Override
  public UniqueKey<SchemaMigrationsRecord> getPrimaryKey() {
    return Keys.SCHEMA_MIGRATIONS_PKEY;
  }

  @Override
  public SchemaMigrations as(String alias) {
    return new SchemaMigrations(DSL.name(alias), this);
  }

  @Override
  public SchemaMigrations as(Name alias) {
    return new SchemaMigrations(alias, this);
  }

  @Override
  public SchemaMigrations as(Table<?> alias) {
    return new SchemaMigrations(alias.getQualifiedName(), this);
  }

  /**
   * Rename this table
   */
  @Override
  public SchemaMigrations rename(String name) {
    return new SchemaMigrations(DSL.name(name), null);
  }

  /**
   * Rename this table
   */
  @Override
  public SchemaMigrations rename(Name name) {
    return new SchemaMigrations(name, null);
  }

  /**
   * Rename this table
   */
  @Override
  public SchemaMigrations rename(Table<?> name) {
    return new SchemaMigrations(name.getQualifiedName(), null);
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations where(Condition condition) {
    return new SchemaMigrations(
      getQualifiedName(),
      aliased() ? this : null,
      null,
      condition
    );
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations where(Collection<? extends Condition> conditions) {
    return where(DSL.and(conditions));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations where(Condition... conditions) {
    return where(DSL.and(conditions));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations where(Field<Boolean> condition) {
    return where(DSL.condition(condition));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  @PlainSQL
  public SchemaMigrations where(SQL condition) {
    return where(DSL.condition(condition));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  @PlainSQL
  public SchemaMigrations where(@Stringly.SQL String condition) {
    return where(DSL.condition(condition));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  @PlainSQL
  public SchemaMigrations where(
    @Stringly.SQL String condition,
    Object... binds
  ) {
    return where(DSL.condition(condition, binds));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  @PlainSQL
  public SchemaMigrations where(
    @Stringly.SQL String condition,
    QueryPart... parts
  ) {
    return where(DSL.condition(condition, parts));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations whereExists(Select<?> select) {
    return where(DSL.exists(select));
  }

  /**
   * Create an inline derived table from this table
   */
  @Override
  public SchemaMigrations whereNotExists(Select<?> select) {
    return where(DSL.notExists(select));
  }
}
