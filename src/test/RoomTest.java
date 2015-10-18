package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import mapElement.Blood;
import mapElement.MapElement;
import mapElement.Pit;
import mapElement.Slime;
import mapElement.Wumpus;
import model.Hunter;
import model.Room;
import model.RoomType;

public class RoomTest {

	@Test
	public void testRoomIsEmpty()   {
		Room room = new Room();
		assertTrue(room.isGround());
	}

	@Test
	public void testAddElement()   {
		Room room = new Room();
		room.add(new Blood());
		assertFalse(room.isGround());
	}

	@Test
	public void testAddGoop()   {
		Room room = new Room();
		room.add(new Blood());
		room.addAfterCheckGoop(new Slime());
		assertTrue(room.getType() == RoomType.GOOP);
	}

	@Test
	public void testAddElements()   {
		Room room = new Room();
		room.add(new Blood());
		room.addAfterCheckGoop(new Slime());
		room.add(new Wumpus());
		room.add(new Pit());
		System.out.println(room.getType());
		System.out.println(room.getList().toString());
	}

	@Test
	public void testMakeGoop()   {
		Room room = new Room();
		room.add(new Blood());
		room.addAfterCheckGoop(new Slime());
		assertEquals(room.getType(), RoomType.GOOP);
	}

	@Test
	public void testHunterLeave()   {
		Room room = new Room();
		Hunter hunter = new Hunter(0, 0);
		assertTrue(room.isEmpty());

		room.enteredBy(hunter);
		assertFalse(room.isEmpty());

		room.leave(hunter);
		assertTrue(room.isEmpty());
	}

	@Test
	public void testHint()   {
		Room room = new Room();
		MapElement wumpus = new Wumpus();
		room.add(wumpus);
		assertEquals(wumpus.hint(), room.hint());
	}

	/**
	@Test
	public void testGetImageBeforeAndAfterReveal() throws IOException   {
		Room room = new Room();
		room.setImage();
		assertEquals(room.getImage(), ImageIO.read(new File("./image/Unvisible.png")).hashCode());		
	}*/
	
	@Test
	public void testToText(){
		Room room = new Room();
		assertEquals(room.toText(),"[X] ");
		
		Hunter hunter = new Hunter(0,0);
		room.enteredBy(hunter);
		assertEquals(room.toText(), "[O] ");
		
		room.leave(hunter);
		assertEquals(room.toText(), "[ ] ");
	}

}
