package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

import mapElement.*;
import model.*;

public class GameTest {
	
	@Test
	public void testGameInitialiazation() throws IOException{
		GameMap map = new GameMap();
		map.initializeMap();
		Game game = new Game(map);
		game.initialization();
		
		assertTrue(game.isSafe());
	}
	
	@Test
	public void testGetHunterOldPoint() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(5);
		assertTrue(game.isSafe());
		assertEquals(game.getHunterOldPoint(), new Point(250, 250));
	}

	@Test
	public void testIfHunterLocationIsNotSafe() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(0);// put hunter in an unsafe room
		assertTrue(game.isSafe());
	}

	@Test
	public void testMoveHunter() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(1);
		assertEquals(game.promptTextViewTitle(), Game.PROMPT + RoomType.GROUND.toString());

		game.moveHunter(Direction.WEST);
		assertTrue(game.isSafe());
		assertEquals(game.getHunterOldPoint(), new Point(50, 50));
		assertEquals(game.promptTextViewTitle(), Game.PROMPT + RoomType.GROUND.toString());

		game.moveHunter(Direction.NORTH);
		assertFalse(game.isSafe());
		assertEquals(game.getHunterOldPoint(), new Point(0, 50));
		assertEquals(game.promptTextViewTitle(), Game.PROMPT + RoomType.WUMPUS.toString());
	}

	@Test
	public void testHunterFire() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(1);
		assertFalse(game.hunterFire(Direction.NORTH));
		assertFalse(game.hunterFire(Direction.SOUTH));
		assertFalse(game.hunterFire(Direction.EAST));
		assertFalse(game.hunterFire(Direction.WEST));
		assertFalse(game.isContinuing());

		game.moveHunter(Direction.WEST);
		assertTrue(game.hunterFire(Direction.NORTH));
		assertTrue(game.hunterFire(Direction.SOUTH));
		assertFalse(game.hunterFire(Direction.EAST));
		assertFalse(game.hunterFire(Direction.WEST));
		assertFalse(game.isContinuing());

		game.moveHunter(Direction.EAST);
		game.moveHunter(Direction.NORTH);
		assertFalse(game.hunterFire(Direction.NORTH));
		assertFalse(game.hunterFire(Direction.SOUTH));
		assertTrue(game.hunterFire(Direction.EAST));
		assertTrue(game.hunterFire(Direction.WEST));
		assertFalse(game.isContinuing());
	}

	@Test
	public void testMessageAfterFire() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(1);
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.NORTH));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.SOUTH));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.EAST));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.WEST));

		game.moveHunter(Direction.WEST);
		assertEquals(Game.SHOT_WUMPUS_MESSAGE, game.shotWumpusOrHunter(Direction.NORTH));
		assertEquals(Game.SHOT_WUMPUS_MESSAGE, game.shotWumpusOrHunter(Direction.SOUTH));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.EAST));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.WEST));

		game.moveHunter(Direction.EAST);
		game.moveHunter(Direction.NORTH);
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.NORTH));
		assertEquals(Game.SHOT_HUNTER_MESSAGE, game.shotWumpusOrHunter(Direction.SOUTH));
		assertEquals(Game.SHOT_WUMPUS_MESSAGE, game.shotWumpusOrHunter(Direction.EAST));
		assertEquals(Game.SHOT_WUMPUS_MESSAGE, game.shotWumpusOrHunter(Direction.WEST));
	}

	@Test
	public void testGetRoomHint() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		map.extendBloodAndSlime();
		Game game = new Game(map);
		game.initializeHunterLocation(2);

		MapElement ground = new Ground();
		assertEquals(game.hintFromCurrentRoom(), ground.hint());

		MapElement slime = new Slime();
		game.moveHunter(Direction.NORTH);
		assertEquals(game.hintFromCurrentRoom(), slime.hint());

		MapElement goop = new Goop();
		game.moveHunter(Direction.WEST);
		assertEquals(game.hintFromCurrentRoom(), goop.hint());

		MapElement pit = new Pit();
		game.moveHunter(Direction.NORTH);
		assertEquals(game.hintFromCurrentRoom(), pit.hint());

		MapElement wumpus = new Wumpus();
		game.moveHunter(Direction.WEST);
		assertEquals(game.hintFromCurrentRoom(), wumpus.hint());
	}

	@Test
	public void testPrintMap() throws IOException {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		map.extendBloodAndSlime();
		Game game = new Game(map);
		game.initializeHunterLocation(2);

		MapElement ground = new Ground();
		StringBuffer text = map.printMap(new StringBuffer());
		assertEquals(game.printMap().toString(), text.append(ground.hint()).toString());
		text.setLength(0);

		MapElement slime = new Slime();
		game.moveHunter(Direction.NORTH);
		text = map.printMap(new StringBuffer());
		assertEquals(game.printMap().toString(), text.append(slime.hint()).toString());
		text.setLength(0);

		MapElement goop = new Goop();
		game.moveHunter(Direction.WEST);
		text = map.printMap(new StringBuffer());
		assertEquals(game.printMap().toString(), text.append(goop.hint()).toString());
		text.setLength(0);

		MapElement pit = new Pit();
		game.moveHunter(Direction.NORTH);
		text = map.printMap(new StringBuffer());
		assertEquals(game.printMap().toString(), text.append(pit.hint()).toString());
		text.setLength(0);

		MapElement wumpus = new Wumpus();
		game.moveHunter(Direction.WEST);
		text = map.printMap(new StringBuffer());
		assertEquals(game.printMap().toString(), text.append(wumpus.hint()).toString());
	}
}
