package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class OperationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1842000345515397464L;

	private ArrowButtonPanel moveButtons;
	private ArrowButtonPanel fireButtons;

	public OperationPanel(Game game) {
		setLayout(null);

		JLabel label = new JLabel("Move");
		label.setSize(50,20);
		label.setLocation(73,80);
		add(label);

		moveButtons = new MoveButtonPanel(game);
		moveButtons.setSize(150, 100);
		moveButtons.setLocation(15, 100);
		add(moveButtons);

		label = new JLabel("Shoot");
		label.setSize(50,20);
		label.setLocation(73,230);
		add(label);

		fireButtons = new FireButtonPanel(game);
		fireButtons.setSize(150,100);
		fireButtons.setLocation(15, 260);
		add(fireButtons);
	}
}
