package com.tiernebre.game_simulation.game.state.handlers.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.dto.game.Direction;
import com.tiernebre.game_simulation.game.GameMockFactory;
import com.tiernebre.game_simulation.game.play.kickoff.KickoffTouchbackResult;
import org.junit.jupiter.api.Test;

public final class DefaultKickoffHandlerTest {

  private final KickoffHandler kickoffHandler = new DefaultKickoffHandler();

  @Test
  public void handlesATouchbackForEastFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.EAST));
    var kicker = DtoMockFactory.player();
    var newState = kickoffHandler.handle(
      state,
      new KickoffTouchbackResult(kicker, 1)
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.WEST_TOUCHBACK_STARTING_YARD_LINE
    );
  }

  @Test
  public void handlesATouchbackForWestFacingTeam() {
    var state = GameMockFactory.state(GameMockFactory.drive(Direction.WEST));
    var kicker = DtoMockFactory.player();
    var newState = kickoffHandler.handle(
      state,
      new KickoffTouchbackResult(kicker, 1)
    );
    assertEquals(
      newState.drive().lineOfScrimmage(),
      EngineConstants.EAST_TOUCHBACK_STARTING_YARD_LINE
    );
  }
}
