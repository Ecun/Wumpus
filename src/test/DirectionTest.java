package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Direction;

public class DirectionTest {

	@Test
	public void testGetBlood() {
		assertEquals(Direction.valueOf("NORTH"), Direction.NORTH);
	}

	@Test
	public void testGetSlime() {
		assertEquals(Direction.valueOf("SOUTH"), Direction.SOUTH);
	}

	@Test
	public void testGetPit() {
		assertEquals(Direction.valueOf("EAST"), Direction.EAST);
	}

	@Test
	public void testGetGoop() {
		assertEquals(Direction.valueOf("WEST"), Direction.WEST);
	}

}
