package com.tiernebre.engine.play;

import com.tiernebre.engine.play.field_goal.FieldGoalBlockPlayCall;
import com.tiernebre.engine.play.field_goal.FieldGoalKickPlayCall;
import com.tiernebre.engine.play.field_goal.FieldGoalSimulator;
import com.tiernebre.engine.play.kickoff.KickReturnPlayCall;
import com.tiernebre.engine.play.kickoff.KickoffPlayCall;
import com.tiernebre.engine.play.kickoff.KickoffSimulator;
import com.tiernebre.engine.play.punt.PuntDefensivePlayCall;
import com.tiernebre.engine.play.punt.PuntOffensivePlaycall;
import com.tiernebre.engine.play.punt.PuntSimulator;
import com.tiernebre.engine.play.regular.RegularPlayDefensivePlayCall;
import com.tiernebre.engine.play.regular.RegularPlayOffensivePlayCall;
import com.tiernebre.engine.play.regular.RegularPlaySimulator;

public final class DefaultPlaySimulator implements PlaySimulator {

  private final RegularPlaySimulator regularPlaySimulator;
  private final FieldGoalSimulator fieldGoalSimulator;
  private final PuntSimulator puntSimulator;
  private final KickoffSimulator kickoffSimulator;

  public DefaultPlaySimulator(
    RegularPlaySimulator regularPlaySimulator,
    FieldGoalSimulator fieldGoalSimulator,
    PuntSimulator puntSimulator,
    KickoffSimulator kickoffSimulator
  ) {
    this.regularPlaySimulator = regularPlaySimulator;
    this.fieldGoalSimulator = fieldGoalSimulator;
    this.puntSimulator = puntSimulator;
    this.kickoffSimulator = kickoffSimulator;
  }

  @Override
  public PlayResult simulate(PlaySimulatorArguments arguments) {
    var defensivePlayCall = arguments.defensivePlayCall();

    return switch (arguments.offensivePlayCall()) {
      case RegularPlayOffensivePlayCall regularPlayOffensivePlayCall -> regularPlaySimulator.simulate(
        regularPlayOffensivePlayCall,
        (RegularPlayDefensivePlayCall) defensivePlayCall
      );
      case FieldGoalKickPlayCall fieldGoalOffensivePlayCall -> fieldGoalSimulator.simulate(
        fieldGoalOffensivePlayCall,
        (FieldGoalBlockPlayCall) defensivePlayCall
      );
      case PuntOffensivePlaycall puntOffensivePlaycall -> puntSimulator.simulate(
        puntOffensivePlaycall,
        (PuntDefensivePlayCall) defensivePlayCall
      );
      case KickReturnPlayCall kickReturnPlaycall -> kickoffSimulator.simulate(
        kickReturnPlaycall,
        (KickoffPlayCall) defensivePlayCall
      );
      default -> throw new IllegalArgumentException(
        "Unexpected value: " + arguments.offensivePlayCall()
      );
    };
  }
}
