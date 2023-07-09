package game.collection.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import game.collection.controller.model.PlayerData;
import game.collection.dao.PlayerDao;
import game.collection.entity.Player;

@Service
public class CollectionService {
  
  @Autowired
  private PlayerDao playerDao;

  @Transactional(readOnly = false)
  public PlayerData savePlayer(PlayerData playerData) {
    Player player = playerData.toPlayer();
    Player dbPlayer = playerDao.save(player);
    return new PlayerData(dbPlayer);
  }

  @Transactional(readOnly = true)
  public PlayerData retrievePlayerById(Long playerId) {
    Player player = findPlayerById(playerId);
    return new PlayerData(player);
  }

  private Player findPlayerById(Long playerId) {
    return playerDao.findById(playerId).orElseThrow(() -> new NoSuchElementException("Player with ID=" + playerId + "was not found."));
  }

}
