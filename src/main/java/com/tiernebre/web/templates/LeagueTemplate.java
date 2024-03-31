package com.tiernebre.web.templates;

import com.tiernebre.league_management.league.League;
import io.jstach.jstache.JStache;

@JStache(path = "league")
public record LeagueTemplate(League league) {}
