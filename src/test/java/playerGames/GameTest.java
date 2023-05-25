package playerGames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    private Game game;
    private List<Player> players;

    @BeforeEach
    public void setUp() {
        game = new Game();
        players = new ArrayList<>();

        players.add(new Player(234, "Матвей", 15));
        players.add(new Player(235, "Константин", 30));
        players.add(new Player(336, "Максим", 20));
        players.add(new Player(337, "Руслан", 15));

        for (Player player : players) {
            game.register(player);
        }
    }

    @Test
    public void testRoundPlayer1Wins() throws NotRegisteredException {
        int result = game.round("Матвей", "Константин");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testRoundPlayer2Wins() throws NotRegisteredException {
        int result = game.round("Константин", "Максим");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testRoundDraw() throws NotRegisteredException {
        int result = game.round("Матвей", "Руслан");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testRoundPlayer1NotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Анатолий", "Максим");
        });
    }

    @Test
    public void testRoundPlayer2NotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Матвей", "Анатолий");
        });
    }

    @Test
    public void testRoundBothPlayersNotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Анатолий", "Эдуард");
        });
    }

}
