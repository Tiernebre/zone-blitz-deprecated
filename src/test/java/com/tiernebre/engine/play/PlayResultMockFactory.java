package com.tiernebre.engine.play;

import com.tiernebre.engine.dto.DtoMockFactory;
import com.tiernebre.engine.play.field_goal.blocked.BlockedFieldGoalResult;
import com.tiernebre.engine.play.field_goal.result.FieldGoalAttemptResult;
import com.tiernebre.engine.play.regular.rush.RushResult;

public final class PlayResultMockFactory {

  public static RushResult rushResult(int yards) {
    return new RushResult(
      yards,
      DtoMockFactory.player(),
      DtoMockFactory.player()
    );
  }

  public static BlockedFieldGoalResult blockedFieldGoalResult() {
    return new BlockedFieldGoalResult(
      DtoMockFactory.player(),
      DtoMockFactory.player()
    );
  }

  public static FieldGoalAttemptResult fieldGoalAttemptResult() {
    return fieldGoalAttemptResult(32, true);
  }

  public static FieldGoalAttemptResult fieldGoalAttemptResult(
    int yards,
    boolean accurate
  ) {
    return new FieldGoalAttemptResult(DtoMockFactory.player(), yards, accurate);
  }
}
