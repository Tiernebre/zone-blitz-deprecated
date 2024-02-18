package com.tiernebre.engine.dto.game;

import com.tiernebre.engine.dto.Team;

public record Game(GameState state, Team home, Team away) {}
