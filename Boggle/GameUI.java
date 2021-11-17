import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameUI extends JFrame implements ActionListener{
	
	private JTextField input;
	private Board b;
	private WordEngine w;
	private JTextField message;
	
	
	public GameUI() {
		
		Board b = new Board(8);
		w = new WordEngine(b);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setLayout(new FlowLayout());

		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(b.getSize(),b.getSize()));
		
		// add each square in board to a JPanel
		for (int row = 0; row < b.getSize(); row++) {
			for (int column = 0; column < b.getSize(); column++) {
				JTextField square = new JTextField(b.board[row][column].toString());
				square.setEditable(false);
				grid.add(square);
			}
		}
		
		//create a JPanel for input
		JPanel control = new JPanel();
		control.setLayout(new FlowLayout());
		JLabel prompt = new JLabel("Enter a word");
		input = new JTextField(15);
		input.setEditable(true);
		input.addActionListener(this);
		control.add(prompt);
		control.add(input);
		
		message = new JTextField(15);
		message.setText("Let's play BOGGLE");
		message.setEditable(false);
	
		this.add(grid);
		this.add(control);
		this.add(message);
		
		
		
	}
	
	// when user enters a word
	public void actionPerformed(ActionEvent e) {
			String word = input.getText();
			boolean valid = w.validate(word);
			message.setText("" + valid);	
	}
	
	public static void main(String[] args) {
		GameUI game = new GameUI();
		game.setVisible(true);
	}
}
