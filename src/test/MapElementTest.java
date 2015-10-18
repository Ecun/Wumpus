package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mapElement.*;

public class MapElementTest {
	
	@Test
	public void testGroundGetHint() {
		MapElement element = new Ground();
		Ground ground = new Ground();
		assertEquals(ground.hint(), element.hint());
	}

	@Test
	public void testBloodGetType() {
		MapElement element = new Blood();
		Blood blood = new Blood();
		assertEquals(blood.getType(), element.getType());
	}

	@Test
	public void testBloodGetHint() {
		MapElement element = new Blood();
		Blood blood = new Blood();
		assertEquals(blood.hint(), element.hint());
	}

	@Test
	public void testBloodisSafe() {
		MapElement element = new Blood();
		assertTrue(element.isSafe());
	}

	@Test
	public void testSlimeGetType() {
		MapElement element = new Slime();
		Slime slime = new Slime();
		assertEquals(slime.getType(), element.getType());
	}

	@Test
	public void testSlimeGetHint() {
		MapElement element = new Slime();
		Slime slime = new Slime();
		assertEquals(slime.hint(), element.hint());
	}
	
	@Test
	public void testSlimeisSafe() {
		MapElement element = new Slime();
		assertTrue(element.isSafe());
	}

	@Test
	public void testGoopGetType() {
		MapElement element = new Goop();
		Goop goop = new Goop();
		assertEquals(goop.getType(), element.getType());
	}

	@Test
	public void testGoopGetHint() {
		MapElement element = new Goop();
		Goop goop = new Goop();
		assertEquals(goop.hint(), element.hint());
	}
	
	@Test
	public void testGoopisSafe() {
		MapElement element = new Goop();
		assertTrue(element.isSafe());
	}

	@Test
	public void testPitGetType() {
		MapElement element = new Pit();
		Pit pit = new Pit();
		assertEquals(pit.getType(), element.getType());
	}

	@Test
	public void testPitGetHint() {
		MapElement element = new Pit();
		Pit pit = new Pit();
		assertEquals(pit.hint(), element.hint());
	}

	@Test
	public void testPitisSafe() {
		MapElement element = new Pit();
		assertFalse(element.isSafe());
	}
	
	@Test
	public void testWumpusGetType() {
		MapElement element = new Wumpus();
		Wumpus wumpus = new Wumpus();
		assertEquals(wumpus.getType(), element.getType());
	}

	@Test
	public void testWumpusGetHint() {
		MapElement element = new Wumpus();
		Wumpus wumpus = new Wumpus();
		assertEquals(wumpus.hint(), element.hint());
	}
	
	@Test
	public void testWumpusisSafe() {
		MapElement element = new Wumpus();
		assertFalse(element.isSafe());
	}

}
