import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BogglePanel extends JPanel {
	
	protected JTextField input;
	protected JTextField message;
	
	public BogglePanel(ActionListener parent, Board b) {
		this.setLayout(new GridLayout(3,1));
		
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
		input.addActionListener(parent);
		message = new JTextField(15);
		message.setText("Let's play BOGGLE");
		message.setEditable(false);
		
		control.add(prompt);
		control.add(input);
		control.add(message);
		

		this.add(grid);
		this.add(control);
	
		
	}
}
