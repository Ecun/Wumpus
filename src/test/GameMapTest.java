package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.GameMap;
import model.RoomType;

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

	@Test
	public void testGetWumpusRowAndColumn() {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		assertEquals(0, map.getWumpusRow());
		assertEquals(0, map.getWumpusColumn());
	}

	@Test
	public void testGetMapTextView() {
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(5);
		map.addRoomsToMap();
		map.reveal();
		
		StringBuffer result = new StringBuffer();

		StringBuffer expect = new StringBuffer();
		expect.append("[W] ");
		expect.append("[P] ");
		expect.append("[P] ");
		expect.append("[P] ");
		expect.append("[P] ");
		for (int i = 5; i < GameMap.MAP_ROW; i++)
			expect.append("[ ] ");
		expect.append(System.getProperty("line.separator"));
		for (int i = 1; i < GameMap.MAP_ROW; i++) {
			for (int j = 0; j < GameMap.MAP_COLUMN; j++) {
				expect.append("[ ] ");
			}
			expect.append(System.getProperty("line.separator"));
		}
		expect.append(System.getProperty("line.separator"));
		expect.append(System.getProperty("line.separator"));
		
		assertEquals(expect.toString(), map.printMap(result).toString());
	}
	
	@Test
	public void testExtendBloodAndSlime(){
		GameMap map = new GameMap();
		map.createRooms();
		map.initializeWumpusAndPit(4);
		map.addRoomsToMap();
		map.extendBloodAndSlime();
		
		assertEquals(map.getRoom(0, 0).getType(), RoomType.WUMPUS);
		assertEquals(map.getRoom(0, 1).getType(), RoomType.PIT);
		assertEquals(map.getRoom(0, 2).getType(), RoomType.PIT);
		assertEquals(map.getRoom(0, 3).getType(), RoomType.PIT);
		assertEquals(map.getRoom(0, 4).getType(), RoomType.SLIME);
		assertEquals(map.getRoom(0, 8).getType(), RoomType.BLOOD);
		assertEquals(map.getRoom(0, 9).getType(), RoomType.BLOOD);
		
		assertEquals(map.getRoom(1, 0).getType(), RoomType.BLOOD);
		assertEquals(map.getRoom(1, 1).getType(), RoomType.GOOP);
		assertEquals(map.getRoom(1, 2).getType(), RoomType.SLIME);
		assertEquals(map.getRoom(1, 3).getType(), RoomType.SLIME);
		assertEquals(map.getRoom(1, 4).getType(), RoomType.GROUND);
		
		assertEquals(map.getRoom(2, 0).getType(), RoomType.BLOOD);
		assertEquals(map.getRoom(2, 1).getType(), RoomType.GROUND);
			
		assertEquals(map.getRoom(9, 0).getType(), RoomType.BLOOD);
		assertEquals(map.getRoom(9, 1).getType(), RoomType.GOOP);
		assertEquals(map.getRoom(9, 2).getType(), RoomType.SLIME);
		assertEquals(map.getRoom(9, 3).getType(), RoomType.SLIME);
		assertEquals(map.getRoom(9, 4).getType(), RoomType.GROUND);
	}
}
