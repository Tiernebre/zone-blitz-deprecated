/*
 * This file is generated by jOOQ.
 */
package com.tiernebre.database.jooq.tables.records;

import com.tiernebre.database.jooq.tables.Session;
import java.util.UUID;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SessionRecord extends UpdatableRecordImpl<SessionRecord> {

  private static final long serialVersionUID = 1L;

  /**
   * Setter for <code>public.session.id</code>.
   */
  public void setId(UUID value) {
    set(0, value);
  }

  /**
   * Getter for <code>public.session.id</code>.
   */
  public UUID getId() {
    return (UUID) get(0);
  }

  /**
   * Setter for <code>public.session.account_id</code>.
   */
  public void setAccountId(String value) {
    set(1, value);
  }

  /**
   * Getter for <code>public.session.account_id</code>.
   */
  public String getAccountId() {
    return (String) get(1);
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  @Override
  public Record1<UUID> key() {
    return (Record1) super.key();
  }

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  /**
   * Create a detached SessionRecord
   */
  public SessionRecord() {
    super(Session.SESSION);
  }

  /**
   * Create a detached, initialised SessionRecord
   */
  public SessionRecord(UUID id, String accountId) {
    super(Session.SESSION);
    setId(id);
    setAccountId(accountId);
    resetChangedOnNotNull();
  }
}
