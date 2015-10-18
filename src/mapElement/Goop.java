package mapElement;

import model.RoomType;

public class Goop extends MapElement {
	public final int WEIGHT = 2;

	public RoomType getType() {
		return RoomType.GOOP;
	}

	public String hint() {
		return "I feel two dangers both are approaching";
	}

	@Override
	public String getImagePath() {
		return "./image/Goop.png";
	}

	@Override
	public boolean isSafe() {
		return true;
	}

	@Override
	public int getWeight() {
		return WEIGHT;
	}
}
