package game.collection.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import game.collection.controller.model.PlayerData;
import game.collection.controller.model.PlayerData.GameData;
import game.collection.controller.model.PlayerData.MembershipData;
import game.collection.entity.Game;
import game.collection.service.CollectionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/game_collection")
@Slf4j
public class CollectionController {
  @Autowired
  private CollectionService collectionService;
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public PlayerData createPlayer(@RequestBody PlayerData playerData) {
    log.info("Creating player {}", playerData);
    return collectionService.savePlayer(playerData);
  }
  
  @PostMapping("/player/{playerId}/memberships")
  @ResponseStatus(code = HttpStatus.CREATED)
  public PlayerData insertMembership(@PathVariable Long playerId, @RequestBody MembershipData membershipData) {
    log.info("Creating {} membership for player ID={}", membershipData, playerId);
    return collectionService.saveMembership(playerId, membershipData);
  }
  
  @PostMapping("/player/{playerId}/game/{gameId}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public PlayerData addPlayerGame(@PathVariable Long playerId, @PathVariable Long gameId) {
    log.info("Adding game ID={} to collection of player ID={}", gameId, playerId);
    return collectionService.addNewGameToPlayer(playerId, gameId);
  }
  
  @PutMapping("/player/{playerId}")
  public PlayerData updatePlayer(@PathVariable Long playerId, @RequestBody PlayerData playerData) {
    playerData.setPlayerId(playerId);

    log.info("Updating player {}", playerData);
    return collectionService.savePlayer(playerData);
  }
  
  @PutMapping("/player/{playerId}/membership/{membershipId}")
  public PlayerData updateMembership(@PathVariable Long playerId, @PathVariable Long membershipId, @RequestBody MembershipData membershipData) {
    membershipData.setMembershipId(membershipId);
    log.info("Updating {} membership info for player ID={}", membershipData, playerId);
    
    return collectionService.saveMembership(playerId, membershipData);
  }
  
  @GetMapping("/player/{playerId}")
  public PlayerData retrievePlayer(@PathVariable Long playerId) {
    log.info("Retrieving player with ID={}", playerId);
    return collectionService.retrievePlayerById(playerId);
  }
  
  @GetMapping("/player")
  public List<PlayerData> retrieveAllPlayers() {
    log.info("Retrieving all locations");
    return collectionService.retrieveAllPlayers();
  }
  
  @DeleteMapping("/player/{playerId}")
  public Map<String, String> deletePlayer(@PathVariable Long playerId) {
    log.info("Deleting player with ID=" + playerId + ".");
    collectionService.deletePlayer(playerId);
    
    return Map.of("message", "Player with ID=" + playerId + " was deleted successfully.");
  }
}
