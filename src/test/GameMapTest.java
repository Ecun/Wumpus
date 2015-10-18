package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.GameMap;

public class GameMapTest {

	@Test
	public void testCreateRooms() {
		GameMap map = new GameMap();
		map.createRooms();
		assertEquals(100, map.getRoomNumber());
	}

	@Test
	public void testAddRoomToMap() {
		GameMap map = new GameMap();
		map.createRooms();
		map.addRoomsToMap();
		assertEquals(0, map.getRoomNumber());
	}

}
