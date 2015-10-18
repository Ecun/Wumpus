package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Direction;
import model.Game;

public class FireButtonPanel extends ArrowButtonPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3760151737421502327L;
	private Game game;
	
	public FireButtonPanel(Game game){
		this.game = game;
		upArrowButton.addActionListener(new NorthFireButtonListener());
		downArrowButton.addActionListener(new SouthFireButtonListener());
		leftArrowButton.addActionListener(new WestFireButtonListener());
		rightArrowButton.addActionListener(new EastFireButtonListener());
	}
	
	private class NorthFireButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, game.shotWumpusOrHunter(Direction.NORTH));
			game.deleteObservers();
		}

	}
	
	private class SouthFireButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, game.shotWumpusOrHunter(Direction.SOUTH));
			game.deleteObservers();
		}

	}
	
	private class WestFireButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, game.shotWumpusOrHunter(Direction.WEST));
			game.deleteObservers();
		}

	}
	
	private class EastFireButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, game.shotWumpusOrHunter(Direction.EAST));
			game.deleteObservers();
		}

	}

}
