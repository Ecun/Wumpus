package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Game;

public class TextView extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4318264944688126131L;
	private Game game;
	private JTextPane text;
	private JTextField roomTitleText;
	private JTextArea mapText;
	
	public TextView(Game game) {
		this.game = game; // Avoid null pointer when board is first drawn
		game.addObserver(this);
		setLayout(new BorderLayout());
		
		text = new JTextPane();
		text.setLayout(new BorderLayout());
		
		roomTitleText = new JTextField(game.promptTitle());
		roomTitleText.setHorizontalAlignment(JTextField.CENTER);
		roomTitleText.setBackground(Color.cyan);
		text.add(roomTitleText,BorderLayout.NORTH);
		
		mapText = new JTextArea();
		mapText.setEditable(false);
		mapText.setFont(new Font(Font.MONOSPACED,35, 20));
		mapText.append(game.printMap());
		text.add(mapText);
		
		
		
		add(text,BorderLayout.CENTER);
		
		
	}

	@Override
	public void update(Observable observable, Object extraParameter) {
		mapText.setText("");
		game = (Game)observable;
		mapText.append(game.printMap());
		roomTitleText.setText(game.promptTitle());
	}
}
