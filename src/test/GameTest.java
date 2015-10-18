package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

import model.Direction;
import model.Game;
import model.GameMap;
import model.RoomType;

public class GameTest {
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
	public void testIfHunterLocationIsNotSafe() {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(0);// put hunter in an unsafe room
		assertTrue(game.isSafe());
	}

	@Test
	public void testMoveHunter() {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		Game game = new Game(map);
		game.initializeHunterLocation(1);
		assertEquals(game.promptTitle(), Game.PROMPT+RoomType.GROUND.toString());
		
		game.moveHunter(Direction.WEST);
		assertTrue(game.isSafe());
		assertEquals(game.getHunterOldPoint(), new Point(50,50));
		assertEquals(game.promptTitle(), Game.PROMPT+RoomType.GROUND.toString());
		
		game.moveHunter(Direction.NORTH);
		assertTrue(!game.isSafe());
		assertEquals(game.getHunterOldPoint(), new Point(0,50));
		assertEquals(game.promptTitle(), Game.PROMPT+RoomType.WUMPUS.toString());
	}
}
