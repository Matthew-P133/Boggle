import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameUI extends JFrame implements ActionListener{
	
	private JTextField input;
	private Board b = new Board(10);
	private WordEngine w = new WordEngine(b);
	private JTextField message;
	private BogglePanel panel = new BogglePanel(this, b);
	
	public GameUI() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,300);
		this.setLayout(new FlowLayout());	
		this.add(panel);	
	}
	
	// when user enters a word
	public void actionPerformed(ActionEvent e) {
			b.reset();
			String word = panel.input.getText();
			boolean valid = w.validate(word);
			if (valid) {
				panel.message.setText(word + " is valid. Score: " + w.score(word) + " points!");
				panel.highlightWord(Color.GREEN);
			}
			else {
				panel.message.setText(word + " not present. Score: " + 0 + " points!");
				panel.highlightWord(Color.WHITE);
			}	
	}
	
	public static void main(String[] args) {
		GameUI game = new GameUI();
		game.setVisible(true);
	}
}
