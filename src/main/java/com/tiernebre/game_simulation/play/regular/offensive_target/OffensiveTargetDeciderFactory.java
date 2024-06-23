package com.tiernebre.game_simulation.play.regular.offensive_target;

public class OffensiveTargetDeciderFactory {

  public OffensiveTargetDecider create() {
    return new DefaultOffensiveTargetDecider(new DefaultRushingTargetDecider());
  }
}
