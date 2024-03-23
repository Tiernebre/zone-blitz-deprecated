package com.tiernebre.league_management.league;

public interface LeagueService {
  public League create(long accountId, UserLeagueRequest request);
}
