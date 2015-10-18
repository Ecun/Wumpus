package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.imageio.ImageIO;

import mapElement.Goop;
import mapElement.Ground;
import mapElement.MapElement;

/**
 * 
 * @author Yicun Zeng, Siru Liu
 * 
 *
 */
public class Room {

	private boolean visible;
	private Image image;
	private Image unvisibleImage;
	/**
	 * Container for hunter.
	 */
	private HashSet<Hunter> seat;

	/**
	 * The element that dominating the room.
	 */
	private MapElement highElement;
	private ArrayList<MapElement> elements;

	public Room() {
		visible = false;
		seat = new HashSet<Hunter>();
		elements = new ArrayList<MapElement>();
		elements.add(new Ground());
		setHighElement();
	}

	public void add(MapElement e) {
		elements.add(e);
		setHighElement();
	}

	/**
	 * Special add for adding slime to map when bloods have already added.
	 * 
	 * @param e
	 */
	public void addAfterCheckGoop(MapElement e) {
		if (highElement.getType() == RoomType.BLOOD && e.getType() == RoomType.SLIME)
			add(new Goop());
		else
			add(e);
	}

	public ArrayList<MapElement> getList() {
		return elements;
	}

	public void setImage() throws IOException {
		unvisibleImage = ImageIO.read(new File("./image/Unvisible.png"));
		image = ImageIO.read(new File(highElement.getImagePath()));
	}

	public void enteredBy(Hunter hunter) {
		seat.add(hunter);
		visible = true;
	}

	public boolean isGround() {
		return getType() == RoomType.GROUND;
	}

	public boolean isEmpty() {
		return seat.isEmpty();
	}

	public boolean isSafe() {
		return highElement.isSafe();
	}

	public void leave(Hunter hunter) {
		seat.remove(hunter);
	}

	public void reveal() {
		visible = true;
	}

	public RoomType getType() {
		return highElement.getType();
	}

	public String hint() {
		return highElement.hint();
	}

	public Image getImage() {
		if (!visible)
			return unvisibleImage;
		else
			return image;
	}

	private void setHighElement() {
		Collections.sort(elements);
		highElement = elements.get(0);
	}

	public String toText() {
		String text = "";
		if (!this.visible)
			text = "X";
		else if (!seat.isEmpty())
			text = "O";
		else
			text = String.valueOf(getType().RoomSymbol());
		return "[" + text + "] ";
	}

}
