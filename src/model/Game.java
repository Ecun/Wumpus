package model;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Yicun Zeng, Siru Liu
 * Model that hold the entire game. Contains map and hunter.
 *
 */
public class Game extends Observable {

	private Hunter hunter;
	private GameMap map;

	public Game() {
		map = new GameMap();
	}
	
	public void initialization() throws IOException{
		map.initializeMap();
		initializeHunterLocation();
	}
	
	private void initializeHunterLocation() {
		int row;
		int col;
		do {
			row = randomHunterLocation();
			col = randomHunterLocation();
		} while (!map.getRoom(row, col).isGround());
		hunter = new Hunter(row, col);
		map.getRoom(row, col).enteredBy(hunter);
	}
	
	public Point getHunterOldPoint() {
		return hunter.getOldPoint();
	}

	public Image getImage(int row, int col){
		return map.getRoom(row, col).getImage();
	}
	
	public void moveHunter(Direction dir) {
		hunterLeave();
		hunter.move(dir);
		hunterEnter();
		checkCurrentRoomType();
		if(!isSafe())
			map.reveal();
		setChanged();
		notifyObservers(dir);
	}

	public boolean isSafe() {
		return map.getLocationFrom(hunter).isSafe();
	}
	
	public String promptTitle(){
		return "Current Room: " + checkCurrentRoomType().toString();
	}

	public boolean hunterFire(Direction dir) {
		if (dir == Direction.NORTH || dir == Direction.SOUTH)
			return hunter.getCurrentColumn() == map.getWumpusColumn();
		else
			return hunter.getCurrentRow() == map.getWumpusRow();
	}
	
	public String shotWumpusOrHunter(Direction dir){
		map.reveal();
		hunter.updateOldLocation();
		setChanged();
		notifyObservers();
		return hunterFire(dir)? "Wumpus is Killed, Win!": "Hunter is killed, Game Over!";
	}

	public String printMap() {
		StringBuffer mapToText = new StringBuffer();
        mapToText = map.printMap(mapToText);
		mapToText.append(promptCurrentRoom());
		return mapToText.toString();
	}
	
	public String promptCurrentRoom(){
		return map.getLocationFrom(hunter).hint();
	}

	private int randomHunterLocation() {
		return ThreadLocalRandom.current().nextInt(0, 9 + 1);
	}

	private void hunterLeave() {
		map.getLocationFrom(hunter).leave(hunter);
	}
	
	private void hunterEnter(){
		map.getLocationFrom(hunter).enteredBy(hunter);
	}

	private RoomType checkCurrentRoomType(){
		return map.getLocationFrom(hunter).getType();
	}

}