package game.collection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import game.collection.controller.model.PlayerData;
import game.collection.entity.Player;

public class CollectionServiceTestSupport {
  
  private static final String PLAYER_TABLE = "player";
  // @formatter:off
  private PlayerData insertPlayer1 = new PlayerData(
      1L,
      "Charles Bronson",
      "ChukBsn@hotmail.com",
      "205-667-2298"
    );
  private PlayerData insertPlayer2 = new PlayerData(
      2L,
      "Max Steel",
      "Steely29@hotmail.com",
      "396-456-1789"
    );
  // @formatter:on
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private CollectionController collectionController;
  
  protected PlayerData buildInsertPlayer(int which) {
    
    return which == 1 ? insertPlayer1 : insertPlayer2;
  }
  
  protected int rowsInPlayerTable() {
    
    return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAYER_TABLE);
  }

  protected PlayerData insertPlayer(PlayerData playerData) {
    Player player = playerData.toPlayer();
    PlayerData clone = new PlayerData(player);
    
    clone.setPlayerId(null);
    return collectionController.createPlayer(clone);
  }

}
