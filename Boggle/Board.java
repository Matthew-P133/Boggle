import java.util.Random;

public class Board {
	
	private int size;
	private Square[][] board;
	
	public Board(int size) {
		this.size = size;
		board = populate();
	}
	
	private Square[][] populate() {
		Square[][] board = new Square[size][size];
		Random r = new Random();
		
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				int x = r.nextInt(26);
				Square s = new Square((char) (x + 65));
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
}
