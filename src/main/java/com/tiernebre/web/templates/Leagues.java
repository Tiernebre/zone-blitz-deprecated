package com.tiernebre.web.templates;

import com.tiernebre.league_management.league.League;
import com.tiernebre.util.pagination.PageEdge;
import io.jstach.jstache.JStache;
import java.util.Collection;

@JStache(path = "leagues")
public record Leagues(Collection<PageEdge<League>> leagues) {}
