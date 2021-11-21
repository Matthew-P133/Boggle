import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BogglePanel extends JPanel {
	
	protected JTextField input;
	protected JTextField message;
	protected JButton button;
	private Board b;
	
	public BogglePanel(ActionListener parent, Board b) {
		this.b = b;
		this.setLayout(new GridLayout(2,1));
		
		// set up JPanel for board
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(b.getSize(),b.getSize()));
		for (int row = 0; row < b.getSize(); row++) {
			for (int column = 0; column < b.getSize(); column++) {
				Square s = b.board[row][column];
				JTextField square = new JTextField(s.toString());
				s.setUIRef(square);
				square.setEditable(false);
				grid.add(square);
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
		for (int row = 0; row < b.getSize(); row++) {
			for (int column = 0; column < b.getSize(); column++) {
				Square s = b.board[row][column];
				if (s.isUsed()) {
					s.getUIRef().setBackground(color);
				}
				else {
					s.getUIRef().setBackground(Color.WHITE);
				}
			}
		}
	}
}
