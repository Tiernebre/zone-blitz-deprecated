package com.tiernebre.game_simulation.play.field_goal;

import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.game_simulation.dto.personnel.FieldGoalKickingPersonnel;
import com.tiernebre.game_simulation.game.play.field_goal.FieldGoalBlockPlayCall;
import com.tiernebre.game_simulation.game.play.field_goal.FieldGoalKickPlayCall;

public final class FieldGoalSimulatorMockFactory {

  public static FieldGoalKickPlayCall kickPlayCall() {
    return new FieldGoalKickPlayCall(kickingPersonnel());
  }

  public static FieldGoalBlockPlayCall blockPlayCall() {
    return new FieldGoalBlockPlayCall(blockPersonnel());
  }

  public static FieldGoalKickingPersonnel kickingPersonnel() {
    return new FieldGoalKickingPersonnel(
      DtoMockFactory.player(),
      DtoMockFactory.player(),
      DtoMockFactory.player(),
      new Player[] { DtoMockFactory.player() }
    );
  }

  public static FieldGoalBlockPersonnel blockPersonnel() {
    return new FieldGoalBlockPersonnel(
      new Player[] { DtoMockFactory.player() }
    );
  }
}
