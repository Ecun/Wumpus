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

	public static final String PROMPT = "Current Room: ";
	public static final String SHOT_WUMPUS_MESSAGE = "Wumpus is Killed, Win!";
	public static final String SHOT_HUNTER_MESSAGE = "Hunter is killed, Game Over!";
	
	private Hunter hunter;
	private GameMap map;
	private boolean gameContinue;

	public Game(GameMap gameMap) throws IOException {
		this.map = gameMap;
	}
	
	public Game(){
		map = new GameMap();
	}
	
	public void initialization() throws IOException{
		map.initializeMap();
		initializeHunterLocation(randomHunterLocation());
		gameContinue = true;
	}
	
	public void initializeHunterLocation(int random) {
		int row = random;
		int col = random;
	    while (!map.getRoom(row, col).isGround()){
	    	row = randomHunterLocation();
			col = randomHunterLocation();
	    };
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
		if(!isSafe()){
			map.reveal();
			gameContinue = false;
		}
		setChanged();
		notifyObservers(dir);
	}

	public boolean isSafe() {
		return map.getLocationFrom(hunter).isSafe();
	}
	
	public String promptTextViewTitle(){
		return PROMPT + checkCurrentRoomType().toString();
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
		gameContinue = false;
		setChanged();
		notifyObservers();
		return hunterFire(dir)? SHOT_WUMPUS_MESSAGE: SHOT_HUNTER_MESSAGE;
	}
	
	public boolean isContinuing(){
		return gameContinue;
	}

	public String printMap() {
		StringBuffer textView = new StringBuffer();
        textView = map.printMap(textView);
		textView.append(hintFromCurrentRoom());
		return textView.toString();
	}
	
	public String hintFromCurrentRoom(){
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