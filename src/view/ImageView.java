package view;

/**
 * This is an Observer of a game that allows a player to move around the game board.
 *
 * @author Rick Mercer
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Direction;
import model.Game;

public class ImageView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4318264944688126131L;
	private Game game;
	private Image player;
	private Direction direction;
	private int X, Y;

	public ImageView(Game game) {
		this.game = game; // Avoid null pointer when board is first drawn
		X = game.getHunterOldPoint().x;
		Y = game.getHunterOldPoint().y;
		try {
			player = ImageIO.read(new File("./image/TheHunter.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void update(Observable observable, Object extraParameter) {
		game = (Game) observable;
		direction = (Direction) extraParameter;
		X = game.getHunterOldPoint().x;
		Y = game.getHunterOldPoint().y;

		drawBoardWithAnimation();
		System.out.println(game.getHunterOldPoint());
		if (!game.isSafe()){
			JOptionPane.showMessageDialog(null, game.hintFromCurrentRoom());
			game.deleteObservers();
		}

	}

	private Timer timer = new Timer(30, new AnimationPaintListener());
	private int tic;

	private void drawBoardWithAnimation() {
		tic = 0;
		timer.start();
	}

	private class AnimationPaintListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tic >= 24)
				timer.stop();
			if (direction == Direction.EAST)
				X = (X + 2 + 500) % 500;
			if (direction == Direction.WEST)
				X = (X - 2 + 500) % 500;
			if (direction == Direction.NORTH)
				Y = (Y - 2 + 500) % 500;
			if (direction == Direction.SOUTH)
				Y = (Y + 2 + 500) % 500;
			repaint();
			tic++;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Draw background image 100 times
		for (int r = 0; r < 500; r += 50)
			for (int c = 0; c < 500; c += 50)
				g2.drawImage(game.getImage(r / 50, c / 50), c, r, null);

		System.out.println(X + " " + Y);
		g2.drawImage(player, X, Y, null);
	}

}