import java.util.Random;

public class Board {
	
	private int size;
	public Square[][] board;
	
	
	public Board(int size) {
		this.size = size;
		board = populate();
	}
	
	
	// fill boggle grid of specified size with random characters between 'A'-'Z'
	private Square[][] populate() {
		Square[][] board = new Square[size][size];
		Random r = new Random();
		
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				int x = r.nextInt(26);
				Square s = new Square((char) (x + 65), row, column);
				board[row][column] = s;
			}
		}
		return board;
	}
	
	
	public String toString() {
		String boardString = "";
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				boardString += board[row][column];
			}
			boardString += "\n";
		}
		return boardString;
	}
	
	
	// resets used attribute of all squares in board to false
	public void reset() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Square s = board[row][column];
				if (s.isUsed()) {
					s.setUnused();
				}
			}
		}
	}
	
	
	//getters and setters
	public int getSize() {
		return size;
	}
}
