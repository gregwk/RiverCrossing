package river;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import river.GameEngine.Item;
import river.GameEngine.Location;

/*
 * (1) Most test methods start off by creating a local variable named engine.
 *     Instead of doing this, declare a (private) field named engine and 
 *     initialize it in the setUp method.
 * 
 * (2) Create a private method called transport, that loads the boat with an
 *     item, rows the boat, and unloads the boat. Use this method wherever you
 *     can to replace code that loads, rows, then unloads the boat. 
 */

public class GameEngineTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testObjects() {
        GameObject farmer = new Farmer();
        GameObject wolf = new Wolf();
        GameObject goose = new Goose();
        GameObject beans = new Beans();
        Assert.assertEquals("Farmer", farmer.getName());
        Assert.assertEquals(Location.START, farmer.getLocation());
        Assert.assertEquals("", farmer.getSound());
        Assert.assertEquals("Wolf", wolf.getName());
        Assert.assertEquals(Location.START, wolf.getLocation());
        Assert.assertEquals("Howl", wolf.getSound());
        Assert.assertEquals("Goose", goose.getName());
        Assert.assertEquals(Location.START, goose.getLocation());
        Assert.assertEquals("Honk", goose.getSound());
        Assert.assertEquals("Beans", beans.getName());
        Assert.assertEquals(Location.START, beans.getLocation());
        Assert.assertEquals("", beans.getSound());
    }
    
    @Test
    public void testMidTransport() {
        GameEngine engine = new GameEngine();
        Assert.assertEquals(Location.START, engine.getLocation(Item.MID));
        engine.loadBoat(Item.MID);
        Assert.assertEquals(Location.BOAT, engine.getLocation(Item.MID));
        engine.rowBoat();
        Assert.assertEquals(Location.BOAT, engine.getLocation(Item.MID));
        engine.unloadBoat();
        Assert.assertEquals(Location.FINISH, engine.getLocation(Item.MID));
    }

    @Test
    public void testWinningGame() {

        GameEngine engine = new GameEngine();

        // transport the goose
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the wolf
        engine.loadBoat(Item.TOP);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the goose
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the beans
        engine.loadBoat(Item.BOTTOM);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the goose
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());
    }

    @Test
    public void testLosingGame() {

        GameEngine engine = new GameEngine();

        // transport the goose
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the wolf
        engine.loadBoat(Item.TOP);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        engine.rowBoat();
        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }

    @Test
    public void testError() {

        GameEngine engine = new GameEngine();

        // transport the goose
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        GameEngine.Location topLoc = engine.getLocation(Item.TOP);
        GameEngine.Location midLoc = engine.getLocation(Item.MID);
        GameEngine.Location bottomLoc = engine.getLocation(Item.BOTTOM);
        GameEngine.Location playerLoc = engine.getLocation(Item.PLAYER);

        engine.loadBoat(Item.TOP);

        // check that the state has not changed
        Assert.assertEquals(topLoc, engine.getLocation(Item.TOP));
        Assert.assertEquals(midLoc, engine.getLocation(Item.MID));
        Assert.assertEquals(bottomLoc, engine.getLocation(Item.BOTTOM));
        Assert.assertEquals(playerLoc, engine.getLocation(Item.PLAYER));
    }
}
