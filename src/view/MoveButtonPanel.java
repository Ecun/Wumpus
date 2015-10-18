package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Direction;
import model.Game;

public class MoveButtonPanel extends ArrowButtonPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2134497730601617681L;
	private Game game;
	
	public MoveButtonPanel(Game game){
		this.game = game;
		upArrowButton.addActionListener(new UpMoveButtonListener());
		downArrowButton.addActionListener(new DownMoveButtonListener());
		leftArrowButton.addActionListener(new LeftMoveButtonListener());
		rightArrowButton.addActionListener(new RightMoveButtonListener());
	}
	
	private class UpMoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			game.moveHunter(Direction.NORTH);
		}

	}
	
	private class DownMoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			game.moveHunter(Direction.SOUTH);
		}

	}
	
	private class LeftMoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			game.moveHunter(Direction.WEST);
		}

	}
	
	private class RightMoveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			game.moveHunter(Direction.EAST);
		}

	}
}
