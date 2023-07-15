package game.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import game.collection.entity.Membership;

public interface MembershipDao extends JpaRepository<Membership, Long> {

}
