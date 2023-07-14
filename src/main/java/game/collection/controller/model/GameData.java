package game.collection.controller.model;

import java.util.HashSet;
import java.util.Set;
import game.collection.entity.Game;
import game.collection.entity.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameData {
  private Long gameId;
  private String title;
  private String genre;
  private String platform;
  private String esrbRating;
  
  private Set<PlayerData> players = new HashSet<>();
  
  public GameData(Game game) {
    this.gameId = game.getGameId();
    this.title = game.getTitle();
    this.genre = game.getGenre();
    this.platform = game.getPlatform();
    this.esrbRating = game.getEsrbRating();
    
//    for(Player player : game.getPlayers()) {
//      this.getPlayers().add(new PlayerData(player));
//    }
  }
  
  public Game toGame() {
    Game game = new Game();
    game.setGameId(gameId);
    game.setTitle(title);
    game.setGenre(genre);
    game.setPlatform(platform);
    game.setEsrbRating(esrbRating);
    
    for(PlayerData playerData : players) {
      game.getPlayers().add(playerData.toPlayer());
    }
    
    return game;
  }
}