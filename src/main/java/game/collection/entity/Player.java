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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long playerId;
  
  @EqualsAndHashCode.Exclude
  private String name;
  @EqualsAndHashCode.Exclude
  private String email;
  @EqualsAndHashCode.Exclude
  private String phone;
  
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Membership> memberships = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "player_game",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "game_id")
      )
  private Set<Game> gameCollection = new HashSet<>();

}
