package game.collection.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  private Long playerId;
  
  private String name;
  private String email;
  private String phone;
  
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Membership> memberships = new HashSet<>();
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "player_game",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id")
      )
  private Set<Game> gameCollection = new HashSet<>();

}
