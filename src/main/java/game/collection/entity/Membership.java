package game.collection.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Membership {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  private Long membershipId;
  
  @EqualsAndHashCode.Exclude
  private String membershipType;
  @EqualsAndHashCode.Exclude
  private String membershipRenewalFreq;
  @EqualsAndHashCode.Exclude
  private Date startDate;
  @EqualsAndHashCode.Exclude
  private boolean isActive;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;

}
