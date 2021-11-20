import javax.swing.JTextField;

public class Square {
	
	private char letter;
	private int row;
	private int column;
	private boolean used = false;
	private JTextField UIRef;
	
	public Square(char c, int row, int column) {
		letter = c;
		this.row = row;
		this.column = column;
	}
	
	public String toString() {
		return "" + letter;
	}
	
	public void setUsed() {
		used = true;
	}
	
	public void setUnused() {
		used = false;
	}
	
	public boolean isUsed() {
		return (used ? true : false);
	}
	
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	public JTextField getUIRef() {
		return UIRef;
	}
	public void setUIRef(JTextField t) {
		this.UIRef = t;
	}
}
