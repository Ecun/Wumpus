package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import model.Direction;
import model.Game;

public class RunGameGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002059556042104670L;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				RunGameGUI window;
				try {
					window = new RunGameGUI();
					window.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	private ViewPanel viewPanel;
	private OperationPanel operationPanel;
	private Game game;

	public RunGameGUI() throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(730, 570);
		setLocation(100, 30);
		setLayout(null);

		game = new Game();
		game.initialization();
		viewPanel = new ViewPanel(game);
		operationPanel = new OperationPanel(game);

		setGridBagLayout();

		this.addKeyListener(new MoveKeyListener());
		this.setFocusable(true);
	}

	public void setGridBagLayout() {
		operationPanel.setSize(175, 590);
		operationPanel.setLocation(0, 0);
		add(operationPanel);

		viewPanel.setSize(530, 550);
		viewPanel.setLocation(180, 0);
		add(viewPanel);
	}

	private class MoveKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent ke) {

			if (ke.getKeyCode() == KeyEvent.VK_UP)
				game.moveHunter(Direction.NORTH);

			if (ke.getKeyCode() == KeyEvent.VK_DOWN)
				game.moveHunter(Direction.SOUTH);

			if (ke.getKeyCode() == KeyEvent.VK_LEFT)
				game.moveHunter(Direction.WEST);

			if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
				game.moveHunter(Direction.EAST);
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

}
