package com.tiernebre.league_management.league;

import com.tiernebre.util.pagination.Identifiable;

public record League(long id, long accountId, String name)
  implements Identifiable {}
