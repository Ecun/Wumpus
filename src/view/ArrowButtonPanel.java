package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

public class ArrowButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1688196623730192797L;

	JButton upArrowButton;
	JButton downArrowButton;
	JButton leftArrowButton;
	JButton rightArrowButton;
	JLabel label;

	public ArrowButtonPanel() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		upArrowButton = new BasicArrowButton(BasicArrowButton.NORTH);
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 30;
		c.ipady = 30;
		c.gridx = 1;
		c.gridy = 1;
		add(upArrowButton, c);

		leftArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		add(leftArrowButton, c);

		downArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		add(downArrowButton, c);

		rightArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		add(rightArrowButton, c);
	}


}
