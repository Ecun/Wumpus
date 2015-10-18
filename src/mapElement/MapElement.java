package mapElement;

import model.RoomType;

/**
 * 
 * @author Yicun; 
 * Define behaviors that game element should have and implements
 * comparable to decide which element is dominating.
 */

public abstract class MapElement implements Comparable<MapElement> {
	/**
	 * 
	 * @return the type of roome that formed by game element.
	 */
	public abstract RoomType getType();

	/**
	 * 
	 * @return the game element hint in the text view.
	 */
	public abstract String hint();

	/**
	 * 
	 * @return the game element is safe to hunter or not.
	 */
	public abstract boolean isSafe();

	/**
	 * 
	 * @return higher weight game element will dominate the room.
	 */
	public abstract int getWeight();
	
	/**
	 * @return path of image for the element.
	 */
	public abstract String getImagePath();

	/**
	 * all sub classes will could compare to each other by weight.
	 */
	@Override
	public int compareTo(MapElement o) {
		return o.getWeight() - this.getWeight();
	}
}
