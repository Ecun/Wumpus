package test;
import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import model.Direction;
import model.Hunter;

public class HunterTest {

  @Test
  public void testGetters() {
    Hunter game = new Hunter(1, 4);
    Hunter game2 = new Hunter(8, 5);
    assertEquals(1, game.getCurrentRow());
    assertEquals(4, game.getCurrentColumn());

    assertEquals(8, game2.getCurrentRow());
    assertEquals(5, game2.getCurrentColumn());
  }

  @Test
  public void testMoveNorth() {
    Hunter game = new Hunter(1, 4);
    game.move(Direction.NORTH);
    assertEquals(0, game.getCurrentRow());
    assertEquals(4, game.getCurrentColumn());
  }

  @Test
  public void testMoveEast() {
    Hunter game = new Hunter(5, 7);
    game.move(Direction.EAST);
    assertEquals(5, game.getCurrentRow());
    assertEquals(8, game.getCurrentColumn());
  }

  @Test
  public void testMoveSouth() {
    Hunter game = new Hunter(5, 7);
    game.move(Direction.SOUTH);
    assertEquals(6, game.getCurrentRow());
    assertEquals(7, game.getCurrentColumn());
  }

  @Test
  public void testMoveWest() {
    Hunter game = new Hunter(5, 7);
    game.move(Direction.WEST);
    assertEquals(5, game.getCurrentRow());
    assertEquals(6, game.getCurrentColumn());
  }

  @Test
  public void testPixelsWithMoveWest() {
    Hunter game = new Hunter(5, 7);
    game.move(Direction.WEST);
    assertEquals(5, game.getCurrentRow());
    assertEquals(6, game.getCurrentColumn());
  }

  @Test
  public void testPixels() {
    Hunter game = new Hunter(0, 0);
    assertEquals(new Point(0, 0), game.getOldPoint());
    assertEquals(new Point(0, 0), game.getPoint());
  }

  @Test
  public void testPixels2() {
    Hunter game = new Hunter(1, 1);
    assertEquals(new Point(50, 50), game.getOldPoint());
    assertEquals(new Point(50, 50), game.getPoint());
  }

  @Test
  public void testPixels3() {
    Hunter game = new Hunter(6, 3);
    assertEquals(new Point(150, 300), game.getOldPoint());
    assertEquals(new Point(150, 300), game.getPoint());
  }

  @Test
  public void testPixelsAfterMovePlayer() {
    Hunter game = new Hunter(6, 3);
    game.move(Direction.NORTH);
    assertEquals(new Point(150, 300), game.getOldPoint());
    assertEquals(new Point(150, 250), game.getPoint());
  }

  @Test
  public void testPixelsAfterMovePlayer2() {
    Hunter game = new Hunter(6, 3);
    game.move(Direction.EAST);
    assertEquals(new Point(150, 300), game.getOldPoint());
    assertEquals(new Point(200, 300), game.getPoint());
  }

  @Test
  public void testPixelsAfterMovePlayer3() {
    Hunter game = new Hunter(6, 3);
    game.move(Direction.SOUTH);
    assertEquals(new Point(150, 300), game.getOldPoint());
    assertEquals(new Point(150, 350), game.getPoint());
  }

  @Test
  public void testPixelsAfterMovePlayer4() {
    Hunter game = new Hunter(6, 3);
    game.move(Direction.WEST);
    assertEquals(new Point(150, 300), game.getOldPoint());
    assertEquals(new Point(100, 300), game.getPoint());
  }

  @Test
  public void testPixelsAndGetRowsColumnsAfterManyMoves() {
    Hunter game = new Hunter(0, 0);
    assertEquals(new Point(0, 0), game.getOldPoint());
    assertEquals(new Point(0, 0), game.getPoint());
    game.move(Direction.EAST);
    assertEquals(new Point(0, 0), game.getOldPoint());
    assertEquals(new Point(50, 0), game.getPoint());
    game.move(Direction.EAST);
    game.move(Direction.EAST);
    game.move(Direction.SOUTH);
    game.move(Direction.SOUTH);
    game.move(Direction.WEST);
    game.move(Direction.EAST);
    game.move(Direction.EAST);

    assertEquals(2, game.getCurrentRow());
    assertEquals(4, game.getCurrentColumn());
    assertEquals(new Point(150, 100), game.getOldPoint());
    assertEquals(new Point(200, 100), game.getPoint());
  
    game.move(Direction.NORTH);
    assertEquals(1, game.getCurrentRow());
    assertEquals(4, game.getCurrentColumn());
    assertEquals(new Point(200, 100), game.getOldPoint());
    assertEquals(new Point(200, 50), game.getPoint());
    
  }
  
  @Test
  public void testResetLocation(){
	    Hunter game = new Hunter(0, 0);
	    game.move(Direction.EAST);
	    game.move(Direction.EAST);
	    game.move(Direction.SOUTH);
	    game.move(Direction.SOUTH);
	    assertEquals(new Point(100,100), game.getPoint());
	    
	    game.updateOldLocation();
	    assertEquals(new Point(100,100), game.getOldPoint());
  }

}
