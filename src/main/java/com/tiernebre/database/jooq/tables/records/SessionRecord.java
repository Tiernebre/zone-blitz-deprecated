/*
 * This file is generated by jOOQ.
 */
package com.tiernebre.database.jooq.tables.records;


import com.tiernebre.database.jooq.tables.Session;

import java.time.LocalDateTime;
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
    public void setAccountId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.session.account_id</code>.
     */
    public Long getAccountId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.session.expires_at</code>.
     */
    public void setExpiresAt(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.session.expires_at</code>.
     */
    public LocalDateTime getExpiresAt() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.session.revoked</code>.
     */
    public void setRevoked(Boolean value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.session.revoked</code>.
     */
    public Boolean getRevoked() {
        return (Boolean) get(3);
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
    public SessionRecord(UUID id, Long accountId, LocalDateTime expiresAt, Boolean revoked) {
        super(Session.SESSION);

        setId(id);
        setAccountId(accountId);
        setExpiresAt(expiresAt);
        setRevoked(revoked);
        resetChangedOnNotNull();
    }
}
