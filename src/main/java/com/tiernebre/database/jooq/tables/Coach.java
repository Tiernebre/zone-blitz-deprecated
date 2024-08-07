/*
 * This file is generated by jOOQ.
 */
package com.tiernebre.database.jooq.tables;


import com.tiernebre.database.jooq.Keys;
import com.tiernebre.database.jooq.Public;
import com.tiernebre.database.jooq.tables.records.CoachRecord;

import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
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
public class Coach extends TableImpl<CoachRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.coach</code>
     */
    public static final Coach COACH = new Coach();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoachRecord> getRecordType() {
        return CoachRecord.class;
    }

    /**
     * The column <code>public.coach.id</code>.
     */
    public final TableField<CoachRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    private Coach(Name alias, Table<CoachRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Coach(Name alias, Table<CoachRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.coach</code> table reference
     */
    public Coach(String alias) {
        this(DSL.name(alias), COACH);
    }

    /**
     * Create an aliased <code>public.coach</code> table reference
     */
    public Coach(Name alias) {
        this(alias, COACH);
    }

    /**
     * Create a <code>public.coach</code> table reference
     */
    public Coach() {
        this(DSL.name("coach"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CoachRecord, Long> getIdentity() {
        return (Identity<CoachRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CoachRecord> getPrimaryKey() {
        return Keys.COACH_PKEY;
    }

    @Override
    public Coach as(String alias) {
        return new Coach(DSL.name(alias), this);
    }

    @Override
    public Coach as(Name alias) {
        return new Coach(alias, this);
    }

    @Override
    public Coach as(Table<?> alias) {
        return new Coach(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Coach rename(String name) {
        return new Coach(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Coach rename(Name name) {
        return new Coach(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Coach rename(Table<?> name) {
        return new Coach(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach where(Condition condition) {
        return new Coach(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Coach where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Coach where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Coach where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Coach where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Coach whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
