package mapElement;

import model.RoomType;

public class Slime extends MapElement {
	public final static int WEIGHT = 1;

	public RoomType getType() {
		return RoomType.SLIME;
	}

	public String hint() {
		return "I cannot stand evenly";
	}

	@Override
	public String getImagePath() {
		return "./image/Slime.png";
	}

	@Override
	public boolean isSafe() {
		return true;
	}
	
	@Override
	public int getWeight(){
		return WEIGHT;
	}
}
