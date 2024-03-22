package com.tiernebre.game_simulation.play;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.play.regular.RegularPlayPersonnelUtils;
import org.junit.jupiter.api.Test;

public class PersonnelUtilsTest {

  @Test
  public void getAverageAttributeForEmptyPlayers() {
    Player[] players = new Player[0];
    assertEquals(
      EngineConstants.MINIMUM_PLAYER_ATTRIBUTE,
      RegularPlayPersonnelUtils.getAverageAttribute(
        players,
        attributes -> attributes.runBlocking()
      )
    );
  }

  @Test
  public void getAverageAttributeForNullPlayers() {
    Player[] players = new Player[1];
    assertEquals(
      EngineConstants.MINIMUM_PLAYER_ATTRIBUTE,
      RegularPlayPersonnelUtils.getAverageAttribute(
        players,
        attributes -> attributes.runBlocking()
      )
    );
  }

  @Test
  public void getAverageAttributeForSinglePlayer() {
    int attribute = 23;
    Player[] player = new Player[] { DtoMockFactory.player(attribute) };
    assertEquals(
      attribute,
      RegularPlayPersonnelUtils.getAverageAttribute(
        player,
        attributes -> attributes.runBlocking()
      )
    );
  }
}
