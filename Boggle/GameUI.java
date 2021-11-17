import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
	
	public GameUI(Board b) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLayout(new FlowLayout());
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(b.getSize(),b.getSize()));
		
		for (int row = 0; row < b.getSize(); row++) {
			for (int column = 0; column < b.getSize(); column++) {
				JTextField square = new JTextField(b.board[row][column].toString());
				square.setEditable(false);
				grid.add(square);
			}
		}
		
		
		JPanel control = new JPanel();
		control.setLayout(new FlowLayout());
		JLabel prompt = new JLabel("Enter a word");
		JTextField input = new JTextField(15);
		input.setEditable(true);
		control.add(prompt);
		control.add(input);
		
		JTextField message = new JTextField(15);
		message.setText("Let's play BOGGLE");
		message.setEditable(false);
	
		this.add(grid);
		this.add(control);
		this.add(message);
	}
	
	public static void main(String[] args) {
		
		Board b = new Board(50);
		WordEngine w = new WordEngine(b);
		GameUI game = new GameUI(b);
		game.setVisible(true);
		
	}
}
