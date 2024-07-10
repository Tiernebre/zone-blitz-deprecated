/*
 * This file is generated by jOOQ.
 */
package com.tiernebre.database.jooq;


import com.tiernebre.database.jooq.tables.Account;
import com.tiernebre.database.jooq.tables.League;
import com.tiernebre.database.jooq.tables.Person;
import com.tiernebre.database.jooq.tables.Player;
import com.tiernebre.database.jooq.tables.Registration;
import com.tiernebre.database.jooq.tables.Session;
import com.tiernebre.database.jooq.tables.Team;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account</code>.
     */
    public final Account ACCOUNT = Account.ACCOUNT;

    /**
     * The table <code>public.league</code>.
     */
    public final League LEAGUE = League.LEAGUE;

    /**
     * The table <code>public.person</code>.
     */
    public final Person PERSON = Person.PERSON;

    /**
     * The table <code>public.player</code>.
     */
    public final Player PLAYER = Player.PLAYER;

    /**
     * The table <code>public.registration</code>.
     */
    public final Registration REGISTRATION = Registration.REGISTRATION;

    /**
     * The table <code>public.session</code>.
     */
    public final Session SESSION = Session.SESSION;

    /**
     * The table <code>public.team</code>.
     */
    public final Team TEAM = Team.TEAM;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Account.ACCOUNT,
            League.LEAGUE,
            Person.PERSON,
            Player.PLAYER,
            Registration.REGISTRATION,
            Session.SESSION,
            Team.TEAM
        );
    }
}
