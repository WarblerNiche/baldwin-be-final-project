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
  
  @Data
  @NoArgsConstructor
  public class MembershipData {
    private Long membershipId;
    private String membershipType;
    private String membershipRenewalFreq;
    private Date startDate;
    private boolean isActive;
    
    public MembershipData(Membership membership) {
      this.membershipId = membership.getMembershipId();
      this.membershipType = membership.getMembershipType();
      this.membershipRenewalFreq = membership.getMembershipRenewalFreq();
      this.startDate = membership.getStartDate();
      this.isActive = membership.isActive();
    }
    
    public Membership toMembership() {
      Membership membership = new Membership();
      
      membership.setMembershipId(membershipId);
      membership.setMembershipType(membershipType);
      membership.setMembershipRenewalFreq(membershipRenewalFreq);
      membership.setStartDate(startDate);
      membership.setActive(isActive);
      
      return membership;
    }
  }
  
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
      
      for(Player player : game.getPlayers()) {
        this.getPlayers().add(new PlayerData(player));
      }
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
}
