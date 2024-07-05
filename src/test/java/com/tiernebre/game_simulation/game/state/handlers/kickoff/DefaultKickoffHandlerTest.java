package com.tiernebre.game_simulation.game.state.handlers.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.game.GameMockFactory;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffReturnAttemptResult;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffReturnResult;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffTouchbackResult;
import org.junit.jupiter.api.Test;

public final class DefaultKickoffHandlerTest {

  private final KickoffHandler kickoffHandler = new DefaultKickoffHandler();

  @Test
  public void handlesATouchbackForEastFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.EAST));
    var newState = kickoffHandler.handle(
      state,
      new KickoffTouchbackResult(DtoMockFactory.player(), 1)
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.WEST_TOUCHBACK_STARTING_YARD_LINE
    );
  }

  @Test
  public void handlesATouchbackForWestFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.WEST));
    var newState = kickoffHandler.handle(
      state,
      new KickoffTouchbackResult(DtoMockFactory.player(), 1)
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.EAST_TOUCHBACK_STARTING_YARD_LINE
    );
  }

  @Test
  public void handlesATouchbackByDistanceForEastFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.EAST));
    var overEndZoneDistance = Math.abs(
      EngineConstants.WEST_GOAL_POST_YARD_LINE -
      EngineConstants.EAST_KICKOFF_YARD_LINE
    );
    var newState = kickoffHandler.handle(
      state,
      new KickoffReturnResult(
        DtoMockFactory.player(),
        overEndZoneDistance,
        new KickoffReturnAttemptResult(
          1,
          DtoMockFactory.player(),
          DtoMockFactory.player()
        )
      )
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.WEST_TOUCHBACK_STARTING_YARD_LINE
    );
  }

  @Test
  public void handlesATouchbackByDistanceForWestFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.WEST));
    var overEndZoneDistance =
      EngineConstants.EAST_GOAL_POST_YARD_LINE -
      EngineConstants.WEST_KICKOFF_YARD_LINE;
    var newState = kickoffHandler.handle(
      state,
      new KickoffReturnResult(
        DtoMockFactory.player(),
        overEndZoneDistance,
        new KickoffReturnAttemptResult(
          1,
          DtoMockFactory.player(),
          DtoMockFactory.player()
        )
      )
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.EAST_TOUCHBACK_STARTING_YARD_LINE
    );
  }
}
