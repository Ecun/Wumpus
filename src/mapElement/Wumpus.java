package mapElement;

import model.RoomType;

public class Wumpus extends MapElement {
	public final int WEIGHT = 3;
	public RoomType getType() {
		return RoomType.WUMPUS;
	}

	public String hint() {
		return "Wumpus ate me, farewell my friend!";
	}

	@Override
	public String getImagePath() {
		return "./image/Wumpus.png";
	}

	public boolean isSafe() {
		return false;
	}
	
	@Override
	public int getWeight(){
		return WEIGHT;
	}
}
