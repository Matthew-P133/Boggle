import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameUI extends JFrame implements ActionListener{
	
	private Board b;
	private Dictionary d;
	private WordEngine w; 
	private BogglePanel panel; 
	
	public GameUI() {
		b = new Board(10);
		d = new Dictionary();
		w = new WordEngine(b, d);
		panel = new BogglePanel(this, b);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700,300);
		this.setLayout(new FlowLayout());	
		this.add(panel);
	}


	public void actionPerformed(ActionEvent e) {
		// if a word is typed in by user
		if (e.getSource() == panel.input) {
			String word = panel.input.getText().strip().toUpperCase();
			update(word);
		}
		
		// if 'find all' button is pressed
		if (e.getSource() == panel.button) {
			Object[] dictArray = d.toArray();
			String words = "";
			for (int i = 0; i < dictArray.length; i++) {
				String word = dictArray[i].toString();
				if (w.validate(word)) {
					if (words.equals("")) {
						words += word;
					} else {
						words += ", " + word;
					}
				}
			}
			//TODO create something in the UI for these words to be displayed in
			System.out.println(words);
		}
	}
	
	
	// look for word and update appearance of UI if appropriate
	public void update(String word) {
		b.reset();
		boolean valid = w.validate(word);
		if (valid) {
			if (w.inDict(word)) {
				panel.message.setText(word + " is present. Score: " + w.score(word) + " points!");
				panel.highlightWord(Color.GREEN);
			}
			else {
				panel.message.setText(" Not a real word! Score: " + 0 + " points!");
				panel.highlightWord(Color.RED);
			}
		}
		else {
			if (w.inDict(word)) {
				panel.message.setText(word + " not present. Score: " + 0 + " points!");
				panel.highlightWord(Color.WHITE);
			}
			else {
				panel.message.setText(word + " not present or a valid word!. Score: " + 0 + " points!");
				panel.highlightWord(Color.WHITE);
			}
		}	
	}
	
	// getter and setter for dictionary
	public Dictionary getD() {
		return d;
	}


	// main function
	public static void main(String[] args) {
		GameUI game = new GameUI();
		game.setVisible(true);
	}
}
