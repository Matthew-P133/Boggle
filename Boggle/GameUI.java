import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameUI extends JFrame implements ActionListener{
	
	private Board board;
	private Dictionary dictionary;
	private WordEngine wordEngine; 
	private BogglePanel panel; 
	
	public GameUI() {
		board = new Board(10);
		dictionary = new Dictionary();
		wordEngine = new WordEngine(board, dictionary);
		panel = new BogglePanel(this, board);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700,300);
		this.setLayout(new FlowLayout());	
		this.add(panel);
	}


	public void actionPerformed(ActionEvent event) {
		// if a word is typed in by user
		if (event.getSource() == panel.input) {
			String word = panel.input.getText().strip().toUpperCase();
			update(word);
		}
		
		// if 'find all' button is pressed
		if (event.getSource() == panel.button) {
			Object[] dictArray = dictionary.toArray();
			String words = "";
			for (int i = 0; i < dictArray.length; i++) {
				String word = dictArray[i].toString();
				if (wordEngine.validate(word)) {
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
		board.reset();
		boolean valid = wordEngine.validate(word);
		if (valid) {
			if (wordEngine.inDict(word)) {
				panel.message.setText(word + " is present. Score: " + wordEngine.score(word) + " points!");
				panel.highlightWord(Color.GREEN);
			}
			else {
				panel.message.setText(" Not a real word! Score: " + 0 + " points!");
				panel.highlightWord(Color.RED);
			}
		}
		else {
			if (wordEngine.inDict(word)) {
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
		return dictionary;
	}


	// main function
	public static void main(String[] args) {
		GameUI game = new GameUI();
		game.setVisible(true);
	}
}
