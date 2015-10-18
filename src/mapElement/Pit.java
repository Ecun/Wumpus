package mapElement;

import model.RoomType;

public class Pit extends MapElement {
	public final static int WEIGHT = 3;

	public RoomType getType() {
		return RoomType.PIT;
	}

	public String hint() {
		return "I am flying to the core of Earth, see ya!";
	}

	@Override
	public String getImagePath() {
		return "./image/SlimePit.png";
	}

	@Override
	public boolean isSafe() {
		return false;
	}

	@Override
	public int getWeight() {
		return WEIGHT;
	}
}
