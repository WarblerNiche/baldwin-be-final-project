package game.collection.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import game.collection.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
