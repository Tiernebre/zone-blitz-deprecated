package com.tiernebre.engine.play.field_goal;

import com.tiernebre.engine.dto.DtoMockFactory;
import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.dto.personnel.FieldGoalBlockPersonnel;
import com.tiernebre.engine.dto.personnel.FieldGoalKickingPersonnel;

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
