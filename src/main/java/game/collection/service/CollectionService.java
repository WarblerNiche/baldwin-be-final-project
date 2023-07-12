package game.collection.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import game.collection.controller.model.PlayerData;
import game.collection.controller.model.PlayerData.MembershipData;
import game.collection.dao.GameDao;
import game.collection.dao.PlayerDao;
import game.collection.entity.Game;
import game.collection.entity.Membership;
import game.collection.entity.Player;

@Service
public class CollectionService {
  
  @Autowired
  private PlayerDao playerDao;
  private GameDao gameDao;

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
  
  @Transactional(readOnly = true)
  public List<PlayerData> retrieveAllPlayers() {
    List<Player> playerEntities = playerDao.findAll();
    List<PlayerData> playerDtos = new LinkedList<>();
    
    for(Player player : playerEntities) {
      PlayerData playerData = new PlayerData(player);
      playerDtos.add(playerData);
    }

    return playerDtos;
  }

  @Transactional(readOnly = false)
  public void deletePlayer(Long playerId) {
    Player player = findPlayerById(playerId);
    playerDao.delete(player);
    
  }

  @Transactional(readOnly = false)
  public PlayerData saveMembership(Long playerId, MembershipData membershipData) {
    Player player = findPlayerById(playerId);
    Membership membership = membershipData.toMembership();
    Set<Membership> memberships = new HashSet<>();
    memberships.add(membership);
    player.setMemberships(memberships);
    Player dbPlayer = playerDao.save(player);
    return new PlayerData(dbPlayer);
  }

  @Transactional(readOnly = false)
  public PlayerData addNewGameToPlayer(Long playerId, Long gameId) {
    Player player = findPlayerById(playerId);
    Game game = findGameById(gameId);
    Set<Game> gameCollection = new HashSet<>();
    gameCollection.add(game);
    player.setGameCollection(gameCollection);
    game.getPlayers().add(player);
    Player dbPlayer = playerDao.save(player);
    return new PlayerData(dbPlayer);
  }

  private Game findGameById(Long gameId) {
    return gameDao.findById(gameId).orElseThrow(() -> new NoSuchElementException("Game with ID=" + gameId + "was not found."));
  }

}
