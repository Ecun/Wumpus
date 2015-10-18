package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mapElement.*;
import model.RoomType;

public class RoomTypeTest {

	@Test
	public void testGetBlood() {
		MapElement blood = new Blood();
		assertEquals(RoomType.valueOf("BLOOD"), blood.getType());
	}

	@Test
	public void testGetSlime() {
		MapElement slime = new Slime();
		assertEquals(RoomType.valueOf("SLIME"), slime.getType());
	}

	@Test
	public void testGetPit() {
		MapElement pit = new Pit();
		assertEquals(RoomType.valueOf("PIT"), pit.getType());
	}

	@Test
	public void testGetGoop() {
		MapElement goop = new Goop();
		assertEquals(RoomType.valueOf("GOOP"), goop.getType());
	}

	@Test
	public void testGetWumpus() {
		MapElement wumpus = new Wumpus();
		assertEquals(RoomType.valueOf("WUMPUS"), wumpus.getType());
	}

	@Test
	public void testGetGround() {
		MapElement ground = new Ground();
		assertEquals(RoomType.valueOf("GROUND"), ground.getType());
	}

}
