package view;

import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Game;

public class ViewPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3650435378865678367L;
	private JTabbedPane severalPanels;
	
	private JPanel viewOne;
	private JPanel viewTwo;
	
	public ViewPanel(Game game){
		severalPanels = new JTabbedPane();
		setLayout(new BorderLayout());
		viewOne = new ImageView(game);
		viewTwo = new TextView(game);
		severalPanels.add(viewOne, "Graphical View");
		severalPanels.add(viewTwo, "Text View");
		game.addObserver((Observer) viewOne);
		game.addObserver((Observer) viewTwo);

		add(severalPanels, BorderLayout.CENTER);
	}
}
