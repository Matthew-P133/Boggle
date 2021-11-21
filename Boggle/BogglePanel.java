import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BogglePanel extends JPanel {
	
	protected JTextField input;
	protected JTextField message;
	protected JButton button;
	private Board board;
	
	public BogglePanel(ActionListener parent, Board board) {
		this.board = board;
		this.setLayout(new GridLayout(2,1));
		
		// set up JPanel for board
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(board.getSize(),board.getSize()));
		for (int row = 0; row < board.getSize(); row++) {
			for (int column = 0; column < board.getSize(); column++) {
				Square square = board.board[row][column];
				JTextField letter = new JTextField(square.toString());
				square.setUIRef(letter);
				letter.setEditable(false);
				grid.add(letter);
			}
		}
		
		// set up JPanel for user interaction
		JPanel control = new JPanel();
		control.setLayout(new FlowLayout());
		JLabel prompt = new JLabel("Enter a word");
		input = new JTextField(15);
		input.setEditable(true);
		input.addActionListener(parent);
		message = new JTextField(25);
		message.setText("Let's play BOGGLE");
		message.setEditable(false);
		button = new JButton("Find All");
		button.addActionListener(parent);
		
		control.add(prompt);
		control.add(input);
		control.add(message);
		control.add(button);

		// add both JPanels to overall JPanel
		this.add(grid);
		this.add(control);		
	}
	
	
	// highlights word in the specified colour
	public void highlightWord(Color color) {
		for (int row = 0; row < board.getSize(); row++) {
			for (int column = 0; column < board.getSize(); column++) {
				Square square = board.board[row][column];
				if (square.isUsed()) {
					square.getUIRef().setBackground(color);
				}
				else {
					square.getUIRef().setBackground(Color.WHITE);
				}
			}
		}
	}
}
