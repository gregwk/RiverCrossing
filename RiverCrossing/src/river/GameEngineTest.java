package river;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import river.GameEngine.Item;
import river.GameEngine.Location;

/*
 * (1) Create a private method called transport, that loads the boat,
 * rows the boat, and unloads the boat.
 * 
 * (2) Create a inner class called State that stores the locations
 * of each of the four game-objects. The constructor should take four arguments
 * (locations of top, mid, bottom, and player respectively)
 * Implement an equals method for the the State class
 * 
 * (3) Use the State object to store and check the state in the testError method
 */

public class GameEngineTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testMidTransport() {
        GameEngine engine = new GameEngine();
        assertThat(engine.getLocation(Item.MID), is(equalTo(Location.START)));
        engine.loadBoat(Item.MID);
        assertThat(engine.getLocation(Item.MID), is(equalTo(Location.BOAT)));
        engine.rowBoat();
        assertThat(engine.getLocation(Item.MID), is(equalTo(Location.BOAT)));
        engine.unloadBoat();
        assertThat(engine.getLocation(Item.MID), is(equalTo(Location.FINISH)));
    }

    @Test
    public void testWinningGame() {

        GameEngine engine = new GameEngine();

        // transport the middle object
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // go back alone
        engine.rowBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // transport the top object
        engine.loadBoat(Item.TOP);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // transport the middle object
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // transport the bottom object
        engine.loadBoat(Item.BOTTOM);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // go back alone
        engine.rowBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // transport the middle object
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(true));
    }

    @Test
    public void testLosingGame() {

        GameEngine engine = new GameEngine();

        // transport the middle object
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // go back alone
        engine.rowBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // transport the top object
        engine.loadBoat(Item.TOP);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // go back alone
        engine.rowBoat();
        assertThat(engine.gameIsLost(), is(true));
        assertThat(engine.gameIsWon(), is(false));
    }

    @Test
    public void testError() {

        GameEngine engine = new GameEngine();

        // transport the middle object
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        assertThat(engine.gameIsLost(), is(false));
        assertThat(engine.gameIsWon(), is(false));

        // save the state
        GameEngine.Location topLoc = engine.getLocation(Item.TOP);
        GameEngine.Location midLoc = engine.getLocation(Item.MID);
        GameEngine.Location bottomLoc = engine.getLocation(Item.BOTTOM);
        GameEngine.Location playerLoc = engine.getLocation(Item.PLAYER);

        engine.loadBoat(Item.TOP);

        // check that the state has not changed
        assertThat(engine.getLocation(Item.TOP), is(equalTo(topLoc)));
        assertThat(engine.getLocation(Item.MID), is(equalTo(midLoc)));
        assertThat(engine.getLocation(Item.BOTTOM), is(equalTo(bottomLoc)));
        assertThat(engine.getLocation(Item.PLAYER), is(equalTo(playerLoc)));
    }
}
