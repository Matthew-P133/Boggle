
public class Square {
	
	private char letter;
	private int row;
	private int column;
	private boolean used = false;
	
	public Square(char c, int row, int column) {
		letter = c;
		this.row = row;
		this.column = column;
	}
	
	public String toString() {
		return "" + letter;
	}
}
