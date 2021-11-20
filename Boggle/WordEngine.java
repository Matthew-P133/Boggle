import java.util.HashMap;
import java.util.ArrayList;

public class WordEngine {
	
	private HashMap<String, ArrayList<int[]>> pairs;
	
	public WordEngine(Board board) {
		// parse board into data structure containing letter pair information
		pairs = findPairs(board);
	}
	
	
	public boolean validate(String word) {

		if (pairs == null) return false;
		ArrayList<int[]> pair = pairs.get(word.substring(0, 2));
		if (pair == null) return false;
	
		ArrayList<String> usedLetters = new ArrayList<String>();

		// look for word from each occurrence of first pair until found word or exhausted possibilities
		for (int i = 0; i < pair.size(); i++) {
			
			int[] pairInstance = pair.get(i);
			
			// row and column of second letter in pair
			int row = pairInstance[2];
			int column = pairInstance[3];

			// add letters to used letters data structure
			String firstLetter = String.format("%d,%d", pairInstance[0], pairInstance[1]);
			String secondLetter = String.format("%d,%d", pairInstance[2], pairInstance[3]);
			usedLetters.add(firstLetter);
			usedLetters.add(secondLetter);

			if (find(word, pairs, 0, row, column, usedLetters)) {
				return true;
			}
			// remove letters from used data structure
			usedLetters.remove(usedLetters.size()-1);
			usedLetters.remove(usedLetters.size()-1);
		}
		return false;
	}

	private boolean find(String word, HashMap<String, ArrayList<int[]>> pairs, int index, int row, int column,
			ArrayList<String> usedLetters) {

		ArrayList<int[]> pair = pairs.get(word.substring(index + 1, index + 3));

		for (int i = 0; i < pair.size(); i++) {
			
			int[] pairInstance = pair.get(i);
			String letter = String.format("%d,%d", pairInstance[2], pairInstance[3]);
			
			if (row == pairInstance[0] && column == pairInstance[1] && !usedLetters.contains(letter)) {
				
				usedLetters.add(letter);
				
				if (index == word.length() - 3) {
					return true;
				}
				
				if (find(word, pairs, index + 1, pairInstance[2], pairInstance[3], usedLetters)) {
					return true;
				}
				
				usedLetters.remove(usedLetters.size()-1);
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
