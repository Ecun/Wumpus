package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.Game;

public class GameTest {
	@Test
	public void testGetHunterOldPoint() throws IOException{
		Game game = new Game();
		game.initialization();
		assertTrue(game.isSafe());
	}


}
