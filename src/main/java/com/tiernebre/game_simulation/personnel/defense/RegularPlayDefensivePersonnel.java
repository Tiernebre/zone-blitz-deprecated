package com.tiernebre.game_simulation.personnel.defense;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.PlayerAttributes;
import com.tiernebre.game_simulation.playbook.personnel.RegularPlayPersonnel;
import java.util.function.Function;

public abstract class RegularPlayDefensivePersonnel
  extends RegularPlayPersonnel {

  private final Player[] linemen;
  private final Player[] linebackers;
  private final Player[] cornerbacks;
  private final Player freeSafety;
  private final Player strongSafety;

  public RegularPlayDefensivePersonnel(
    Player[] linemen,
    Player[] linebackers,
    Player[] cornerbacks,
    Player freeSafety,
    Player strongSafety
  ) {
    this.linemen = linemen;
    this.linebackers = linebackers;
    this.cornerbacks = cornerbacks;
    this.freeSafety = freeSafety;
    this.strongSafety = strongSafety;
  }

  public Player[] linemen() {
    return linemen;
  }

  public Player[] linebackers() {
    return linebackers;
  }

  public Player[] cornerbacks() {
    return cornerbacks;
  }

  public Player freeSafety() {
    return freeSafety;
  }

  public Player strongSafety() {
    return strongSafety;
  }

  public Player[] players() {
    Player[] defensivePlayers =
      new Player[EngineConstants.NUMBER_OF_PLAYERS_ON_FIELD_PER_SIDE];
    var defensivePlayersIndex = 0;
    for (int i = 0; i < linemen.length; i++) {
      defensivePlayers[defensivePlayersIndex++] = linemen[i];
    }
    for (int i = 0; i < linebackers.length; i++) {
      defensivePlayers[defensivePlayersIndex++] = linebackers[i];
    }
    for (int i = 0; i < cornerbacks.length; i++) {
      defensivePlayers[defensivePlayersIndex++] = cornerbacks[i];
    }
    defensivePlayers[defensivePlayersIndex++] = freeSafety;
    defensivePlayers[defensivePlayersIndex++] = strongSafety;
    return defensivePlayers;
  }

  public int averageAttribute(Function<PlayerAttributes, Integer> toAttribute) {
    return super.averageAttribute(players(), toAttribute);
  }
}
