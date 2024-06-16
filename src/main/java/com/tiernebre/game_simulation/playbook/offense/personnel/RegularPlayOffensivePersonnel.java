package com.tiernebre.game_simulation.playbook.offense.personnel;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.OffensiveLine;

public abstract class RegularPlayOffensivePersonnel {

  private final Player quarterback;
  private final Player[] runningBacks;
  private final Player[] tightEnds;
  private final Player[] wideReceivers;
  private final OffensiveLine offensiveLine;

  public RegularPlayOffensivePersonnel(
    Player quarterback,
    Player[] runningBacks,
    Player[] tightEnds,
    Player[] wideReceivers,
    OffensiveLine offensiveLine
  ) {
    this.quarterback = quarterback;
    this.runningBacks = runningBacks;
    this.tightEnds = tightEnds;
    this.wideReceivers = wideReceivers;
    this.offensiveLine = offensiveLine;
  }

  public Player quarterback() {
    return quarterback;
  }

  public Player[] runningBacks() {
    return runningBacks;
  }

  public Player[] wideReceivers() {
    return wideReceivers;
  }

  public Player[] tightEnds() {
    return tightEnds;
  }

  public Player leftTackle() {
    return offensiveLine.leftTackle();
  }

  public Player leftGuard() {
    return offensiveLine.leftGuard();
  }

  public Player center() {
    return offensiveLine.center();
  }

  public Player rightGuard() {
    return offensiveLine.rightGuard();
  }

  public Player rightTackle() {
    return offensiveLine.rightTackle();
  }

  public Player[] players() {
    Player[] players =
      new Player[EngineConstants.NUMBER_OF_PLAYERS_ON_FIELD_PER_SIDE];
    var playersIndex = 0;
    for (int i = 0; i < runningBacks.length; i++) {
      players[playersIndex++] = runningBacks[i];
    }
    for (int i = 0; i < wideReceivers.length; i++) {
      players[playersIndex++] = wideReceivers[i];
    }
    for (int i = 0; i < tightEnds.length; i++) {
      players[playersIndex++] = tightEnds[i];
    }
    players[playersIndex++] = quarterback;
    players[playersIndex++] = offensiveLine.leftTackle();
    players[playersIndex++] = offensiveLine.leftGuard();
    players[playersIndex++] = offensiveLine.center();
    players[playersIndex++] = offensiveLine.rightGuard();
    players[playersIndex++] = offensiveLine.rightTackle();
    return players;
  }

  public Player[] offensiveLinemen() {
    Player[] offensiveLinemen =
      new Player[EngineConstants.NUMBER_OF_OFFENSIVE_LINEMEN];
    var i = 0;
    offensiveLinemen[i++] = offensiveLine.leftTackle();
    offensiveLinemen[i++] = offensiveLine.leftGuard();
    offensiveLinemen[i++] = offensiveLine.center();
    offensiveLinemen[i++] = offensiveLine.rightGuard();
    offensiveLinemen[i++] = offensiveLine.rightTackle();
    return offensiveLinemen;
  }
}
