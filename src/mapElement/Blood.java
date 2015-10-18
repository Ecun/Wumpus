package mapElement;

import model.RoomType;

public class Blood extends MapElement{
	public final int WEIGHT = 1;
	
	public RoomType getType(){
		return RoomType.BLOOD;
	}
	
	public String hint(){
		return "I smell something foul";
	}
	@Override
	public String getImagePath(){
		return "./image/Blood.png";
	}

	public boolean isSafe() {
		return true;
	}
	
	@Override
	public int getWeight(){
		return WEIGHT;
	}
}
