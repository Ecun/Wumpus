package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import mapElement.Blood;
import mapElement.Pit;
import mapElement.Slime;
import mapElement.Wumpus;

/**
 * 
 * @author Yicun Zeng, Siru Liu Contains map generator and map.
 *
 */
public class GameMap {
	public static int MAP_ROW = 10;
	public static int MAP_COLUMN = 10;

	private ArrayList<Room> rooms = new ArrayList<Room>();
	private Room[][] map;

	public GameMap() {
		map = new Room[MAP_ROW][MAP_COLUMN];
	}

	public void initializeMap() throws IOException {
		createRooms();
		initializeWumpusAndPit(randomPitNumber());
		Collections.shuffle(rooms);
		addRoomsToMap();
		extendBloodAndSlime();
		setMapImage();
	}

	/**
	 * For test purpose.
	 * 
	 * @return
	 */
	public int getRoomNumber() {
		return rooms.size();
	}

	public Room getRoom(int row, int col) {
		return map[row][col];
	}

	public Room getLocationFrom(Hunter hunter) {
		return map[hunter.getCurrentRow()][hunter.getCurrentColumn()];
	}

	public int getWumpusRow() {
		int row = 0;
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				if (map[i][j].getType() == RoomType.WUMPUS)
					row = i;
			}
		}
		return row;
	}

	public int getWumpusColumn() {
		int col = 0;
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				if (map[i][j].getType() == RoomType.WUMPUS)
					col = j;
			}
		}
		return col;
	}

	public StringBuffer printMap(StringBuffer mapToText) {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				mapToText.append(map[i][j].toText());
			}
			mapToText.append(System.getProperty("line.separator"));
		}
		mapToText.append(System.getProperty("line.separator"));
		mapToText.append(System.getProperty("line.separator"));
		return mapToText;
	}

	/**
	 * reveal the map when game ends
	 */
	public void reveal() {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				map[i][j].reveal();
			}
		}
	}

	/**
	 * 
	 * @return generator random number from 3 to 5
	 */
	public int randomPitNumber() {
		return ThreadLocalRandom.current().nextInt(3, 5 + 1);
	}

	public void createRooms() {
		for (int i = 0; i < 100; i++)
			rooms.add(new Room());
	}

	/**
	 * Add Wumpus and pit to room list then shuffle.
	 */
	public void initializeWumpusAndPit(int randomPit) {
		Wumpus wp = new Wumpus();
		rooms.get(0).add(wp);
		for (int i = 1; i < randomPit+1; i++) {
			Pit pit = new Pit();
			rooms.get(i).add(pit);
		}
	}

	/**
	 * Add shuffled rooms to map.
	 */
	public void addRoomsToMap() {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				map[i][j] = rooms.remove(0);
			}
		}
	}
	
	public void extendBloodAndSlime(){
		extendBloodAroundWumpus();
		extendSlimeAroundPits();
	}

	private void extendBloodAroundWumpus() {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				if (map[i][j].getType() == RoomType.WUMPUS)
					addBloodAround(i, j);
			}
		}
	}

	private void addBloodAround(int row, int col) {
		Blood blood = new Blood();
		map[(row - 2 + 10) % 10][(col + 10) % 10].add(blood);
		map[(row - 1 + 10) % 10][(col - 1 + 10) % 10].add(blood);
		map[(row - 1 + 10) % 10][(col + 10) % 10].add(blood);
		map[(row - 1 + 10) % 10][(col + 1 + 10) % 10].add(blood);
		map[(row + 10) % 10][(col - 2 + 10) % 10].add(blood);
		map[(row + 10) % 10][(col - 1 + 10) % 10].add(blood);
		map[(row + 10) % 10][(col + 1 + 10) % 10].add(blood);
		map[(row + 10) % 10][(col + 2 + 10) % 10].add(blood);
		map[(row + 1 + 10) % 10][(col - 1 + 10) % 10].add(blood);
		map[(row + 1 + 10) % 10][(col + 10) % 10].add(blood);
		map[(row + 1 + 10) % 10][(col + 1 + 10) % 10].add(blood);
		map[(row + 2 + 10) % 10][(col + 10) % 10].add(blood);
	}

	private void extendSlimeAroundPits() {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				if (map[i][j].getType() == RoomType.PIT)
					addSlimeAround(i, j);
			}
		}
	}

	private void addSlimeAround(int row, int col) {
		Slime slime = new Slime();
		map[(row - 1 + 10) % 10][(col) % 10].addAfterCheckGoop(slime);
		map[(row + 1 + 10) % 10][(col) % 10].addAfterCheckGoop(slime);
		map[(row) % 10][(col - 1 + 10) % 10].addAfterCheckGoop(slime);
		map[(row) % 10][(col + 1 + 10) % 10].addAfterCheckGoop(slime);
	}

	/**
	 * Load image when map has been initialized to avoid lag if initialize image
	 * in UI.
	 * 
	 * @throws IOException
	 */
	private void setMapImage() throws IOException {
		for (int i = 0; i < MAP_ROW; i++) {
			for (int j = 0; j < MAP_COLUMN; j++) {
				map[i][j].setImage();
			}
		}
	}
}
