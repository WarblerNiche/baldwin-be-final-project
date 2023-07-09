package game.collection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import game.collection.controller.model.PlayerData;
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
  
  @GetMapping("/player/{playerId}")
  public PlayerData retrievePlayer(@PathVariable Long playerId) {
    log.info("Retrieving player with ID={}", playerId);
    return collectionService.retrievePlayerById(playerId);
  }
}
