import java.util.HashMap;
import java.util.ArrayList;

public class WordEngine {
	
	private HashMap<String, ArrayList<int[]>> pairs;
	private Board b;
	
	public WordEngine(Board board) {
		// parse board into data structure containing letter pair information
		b = board;
		pairs = findPairs(board);
	}
	
	public int score(String word) {
		int score = 0;
		int length = word.length();
		if (length >= 3 && length <= 4) {
			score = 1;
		}
		else if (length == 5) {
			score = 2;			
		}
		else if (length == 6) {
			score = 3;			
		}
		else if (length == 7) {
			score = 4;			
		}
		else if (length > 7) {
			score = 11;
		}
		return score;
	}
	
	public boolean validate(String word) {

		if (pairs == null) return false;
		ArrayList<int[]> pair = pairs.get(word.substring(0, 2));
		if (pair == null) return false;

		// look for word from each occurrence of first pair until found word or exhausted possibilities
		for (int i = 0; i < pair.size(); i++) {
			
			int[] pairInstance = pair.get(i);
			
			Square firstLetter = b.board[pairInstance[0]][pairInstance[1]];
			Square secondLetter = b.board[pairInstance[2]][pairInstance[3]];

			firstLetter.setUsed();
			secondLetter.setUsed();

			if (find(word, pairs, 0, secondLetter.getRow(), secondLetter.getColumn())) {
				return true;
			}

			firstLetter.setUnused();
			secondLetter.setUnused();
		}
		return false;
	}

	private boolean find(String word, HashMap<String, ArrayList<int[]>> pairs, int index, int row, int column) {

		ArrayList<int[]> pair = pairs.get(word.substring(index + 1, index + 3));
		if (pair == null) {return false;}

		for (int i = 0; i < pair.size(); i++) {
			
			int[] pairInstance = pair.get(i);
			
			Square firstLetter = b.board[pairInstance[0]][pairInstance[1]];
			Square secondLetter = b.board[pairInstance[2]][pairInstance[3]];
			
			if (row == firstLetter.getRow() && column == firstLetter.getColumn() && !secondLetter.isUsed()) {
				
				secondLetter.setUsed();
				if (index == word.length() - 3) {
					return true;
				}
				if (find(word, pairs, index + 1, secondLetter.getRow(), secondLetter.getColumn())) {
					return true;
				}
				secondLetter.setUnused();
			}
		}
		return false;
	}
	
	public HashMap<String, ArrayList<int[]>> findPairs(Board b) {
		HashMap<String, ArrayList<int[]>> pairs = new HashMap<String, ArrayList<int[]>>();
		int size = b.getSize();
		
		// for each square in Board loop over adjacent squares and add letter pair information to HashMap
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				for (int i = row - 1; i <= row + 1; i++) {
					for (int j = column - 1; j <= column + 1; j++) {
						if ((i >= 0 && j >= 0) && (i <= size - 1 && j <= size - 1)
								&& !((i == row) && (j == column))) {
							
							String pair = String.format("%s%s", b.board[row][column].toString(), b.board[i][j].toString());
							int[] positions =  new int[] {row, column, i, j};
							
							// if letter pair not already in HashMap then make an entry
							if (!(pairs.containsKey(pair))) {
								ArrayList<int[]> s = new ArrayList<int[]>();
								s.add(positions);
								pairs.put(pair, s);
							}
							// if letter pair already in HashMap add position to end of value array
							else {
								ArrayList<int[]> s = pairs.get(pair);
								s.add(positions);
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
