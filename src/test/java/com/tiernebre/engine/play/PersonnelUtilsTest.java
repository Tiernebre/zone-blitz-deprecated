package com.tiernebre.engine.play;

import static org.junit.Assert.assertEquals;

import com.tiernebre.engine.EngineConstants;
import com.tiernebre.engine.dto.DtoMockFactory;
import com.tiernebre.engine.dto.Player;
import com.tiernebre.engine.play.regular.RegularPlayPersonnelUtils;
import org.junit.Test;

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
