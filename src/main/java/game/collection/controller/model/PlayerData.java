package game.collection.controller.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import game.collection.entity.Game;
import game.collection.entity.Membership;
import game.collection.entity.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerData {
  private Long playerId;
  private String name;
  private String email;
  private String phone;
  private Set<MembershipData> memberships = new HashSet<>();
  private Set<GameData> gameCollection = new HashSet<>();
  
  public PlayerData(Player player) {
    this.playerId = player.getPlayerId();
    this.name = player.getName();
    this.email = player.getEmail();
    this.phone = player.getPhone();
    
    for(Membership membership : player.getMemberships()) {
      this.memberships.add(new MembershipData(membership));
    }
    
    for(Game game : player.getGameCollection()) {
      this.gameCollection.add(new GameData(game));
    }
  }
  
  public PlayerData (Long playerId, String name, String email, String phone) {
    this.playerId = playerId;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
  
  public Player toPlayer() {
    Player player = new Player();
    
    player.setPlayerId(playerId);
    player.setName(name);
    player.setEmail(email);
    player.setPhone(phone);
    
    for(MembershipData membershipData : memberships) {
      player.getMemberships().add(membershipData.toMembership());
    }
    
    for(GameData gameData : gameCollection) {
      player.getGameCollection().add(gameData.toGame());
    }
    
    return player;
  }
}
