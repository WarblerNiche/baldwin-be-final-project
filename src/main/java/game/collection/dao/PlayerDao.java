package game.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import game.collection.entity.Player;

public interface PlayerDao extends JpaRepository<Player, Long> {

}
