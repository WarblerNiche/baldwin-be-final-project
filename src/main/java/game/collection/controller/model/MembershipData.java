package game.collection.controller.model;

import java.util.Date;
import game.collection.entity.Membership;
import game.collection.entity.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipData {
  private Long membershipId;
  private String membershipType;
  private String membershipRenewalFreq;
  private Date startDate;
  private boolean isActive;
  private Player player;
  
  public MembershipData(Membership membership) {
    this.membershipId = membership.getMembershipId();
    this.membershipType = membership.getMembershipType();
    this.membershipRenewalFreq = membership.getMembershipRenewalFreq();
    this.startDate = membership.getStartDate();
    this.isActive = membership.isActive();
    this.player = membership.getPlayer();
  }
  
  public MembershipData(Long membershipId, String membershipType, String membershipRenewalFreq, Date startDate, boolean isActive, Player player) {
    this.membershipId = membershipId;
    this.membershipType = membershipType;
    this.membershipRenewalFreq = membershipRenewalFreq;
    this.startDate = startDate;
    this.isActive = isActive;
    this.player = player;
  }
  
  public Membership toMembership() {
    Membership membership = new Membership();
    
    membership.setMembershipId(membershipId);
    membership.setMembershipType(membershipType);
    membership.setMembershipRenewalFreq(membershipRenewalFreq);
    membership.setStartDate(startDate);
    membership.setActive(isActive);
    membership.setPlayer(player);
    
    return membership;
  }
}