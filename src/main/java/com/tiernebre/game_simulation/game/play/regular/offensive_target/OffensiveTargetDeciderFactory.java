package com.tiernebre.game_simulation.game.play.regular.offensive_target;

public class OffensiveTargetDeciderFactory {

  public OffensiveTargetDecider create() {
    return new DefaultOffensiveTargetDecider(new DefaultRushingTargetDecider());
  }
}
