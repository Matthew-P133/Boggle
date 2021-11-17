import java.util.HashMap;

public class WordEngine {
	
	private HashMap<String, String[]> pairs;
	
	public WordEngine(Board board) {
		// parse board into data structure containing letter pair information
		pairs = findPairs(board);
	}
	
	
	public boolean validate(String word) {
		//TODO - take word as input and determine if it is a valid boggle word for the board
		return true;
	}
	
	public HashMap<String, String[]> findPairs(Board b) {
		HashMap<String, String[]> pairs = new HashMap<String, String[]>();
		int size = b.getSize();
		
		// for each square in Board loop over adjacent squares and add letter pair information to HashMap
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				for (int i = row - 1; i <= row + 1; i++) {
					for (int j = column - 1; j <= column + 1; j++) {
						if ((i >= 0 && j >= 0) && (i <= size - 1 && j <= size - 1)
								&& !((i == row) && (j == column))) {
							
							String pair = String.format("%s%s", b.board[row][column].toString(), b.board[i][j].toString());
							String positions = String.format("%d,%d,%d,%d", row, column, i, j);
							
							// if letter pair not already in HashMap then make an entry
							if (!(pairs.containsKey(pair))) {
								String[] s = new String[] {positions};
								pairs.put(pair, s);
							}
							// if letter pair already in HashMap add position to end of value array
							else {
								String[] temp = pairs.get(pair);
								String[] s = new String[temp.length + 1];
								for (int m = 0; m < temp.length; m++) {
									s[m] = temp[m];
								}
								s[s.length-1] = positions;
								pairs.replace(pair, s);
							}
						}
					}
						
				}
			}
		}
		return pairs;
	}
	
}
