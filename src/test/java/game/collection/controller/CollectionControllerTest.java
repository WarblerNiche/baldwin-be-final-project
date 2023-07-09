package game.collection.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.IntPredicate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import game.collection.GameCollectionApplication;
import game.collection.controller.model.PlayerData;


@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = GameCollectionApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class CollectionControllerTest extends CollectionServiceTestSupport {

  @Test
  void testInsertPlayer() {
    // Given: a player request
    PlayerData request = buildInsertPlayer(1);
    PlayerData expected = buildInsertPlayer(1);
    // when: the player is added to the table
    PlayerData actual = insertPlayer(request);
    // then: the player returned is what is expected
    assertThat(actual).isEqualTo(expected);
    // and: there is one row in the player table
    assertThat(rowsInPlayerTable()).isOne();
  }

  



}
