package game.collection.entity;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gameId;
  
  private String title;
  private String genre;
  private String platform;
  private String esrbRating;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "gameCollection")
  @JsonIgnore
  private Set<Player> players = new HashSet<>();

}
