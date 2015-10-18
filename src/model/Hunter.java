package model;

import java.awt.Point;

/**
 * 
 * @author Yicun Zeng
 *Contain locations and move behavior of hunter.
 */
public class Hunter {
	public static int TILE_SIZE = 50;

	private int currentRow, currentCol, oldRow, oldCol;

	public Hunter(int row, int column) {
		currentRow = row;
		currentCol = column;
		oldRow = row;
		oldCol = column;
	}

	public void move(Direction dir) {
		oldRow = currentRow;
		oldCol = currentCol;
		if (dir == Direction.NORTH)
			currentRow = (currentRow - 1 + 10) % 10;
		if (dir == Direction.EAST)
			currentCol = (currentCol + 1 + 10) % 10;
		if (dir == Direction.SOUTH)
			currentRow = (currentRow + 1 + 10) % 10;
		if (dir == Direction.WEST)
			currentCol = (currentCol - 1 + 10) % 10;
	}
	
	public void updateOldLocation(){
		oldRow = currentRow;
		oldCol = currentCol;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public int getCurrentColumn() {
		return currentCol;
	}

	public Point getOldPoint() {
		return new Point(oldCol * TILE_SIZE, oldRow * TILE_SIZE);
	}

	public Point getPoint() {
		return new Point(currentCol * TILE_SIZE, currentRow * TILE_SIZE);
	}

	@Override
	public String toString() {
		return "Hunter";
	}
}
