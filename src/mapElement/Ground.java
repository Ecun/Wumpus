package mapElement;

import model.RoomType;

public class Ground extends MapElement {
	public final int WEIGHT = 0;

	public RoomType getType() {
		return RoomType.GROUND;
	}

	public String hint() {
		return "Room is safe";
	}

	@Override
	public String getImagePath() {
		return "./image/Ground.png";
	}

	public boolean isSafe() {
		return true;
	}
	
	@Override
	public int getWeight(){
		return WEIGHT;
	}

}
