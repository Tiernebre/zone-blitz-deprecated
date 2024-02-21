package com.tiernebre.game_simulation.game.state.handlers.scoring;

import com.tiernebre.game_simulation.dto.game.GameScoreboard;
import com.tiernebre.game_simulation.dto.game.GameState;
import com.tiernebre.game_simulation.dto.game.Score;
import com.tiernebre.game_simulation.dto.game.ScoringConfiguration;
import com.tiernebre.game_simulation.dto.game.Side;
import com.tiernebre.game_simulation.game.state.util.SideUtils;

public class DefaultScoringPlayHandler implements ScoringPlayHandler {

  private final ScoringConfiguration configuration;

  public DefaultScoringPlayHandler(ScoringConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public GameState handle(GameState state, Score score) {
    boolean isTouchdown = score == Score.TOUCHDOWN;
    return new GameState(
      state.drive(),
      this.score(state, mapScoreToPoints(score)),
      state.time(),
      state.coinTossDecision(),
      isTouchdown ? state.onOffense() : SideUtils.opposite(state.onOffense()),
      !isTouchdown,
      isTouchdown
    );
  }

  private int mapScoreToPoints(Score score) {
    return switch (score) {
      case TOUCHDOWN -> configuration.touchdownPoints();
      case EXTRA_POINT -> configuration.extraPointAttemptPoints();
      case FIELD_GOAL -> configuration.fieldGoalPoints();
      case SAFETY -> configuration.safetyPoints();
      case TWO_POINT_CONVERSION -> configuration.twoPointConversionAttemptPoints();
    };
  }

  private GameScoreboard score(GameState state, int points) {
    var previousScore = state.score();
    var isHome = state.onOffense() == Side.HOME;
    return new GameScoreboard(
      isHome ? previousScore.home() + points : previousScore.home(),
      isHome ? previousScore.away() : previousScore.away() + points
    );
  }
}
