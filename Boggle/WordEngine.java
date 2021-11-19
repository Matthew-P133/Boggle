import java.util.HashMap;

public class WordEngine {
	
	private HashMap<String, int[][]> pairs;
	
	public WordEngine(Board board) {
		// parse board into data structure containing letter pair information
		pairs = findPairs(board);
	}
	
	
	public boolean validate(String word) {

		if (pairs == null) return false;
		int[][] pair = pairs.get(word.substring(0, 2));
		if (pair == null) return false;
	
		String[] usedLetters = new String[2];

		// look for word from each occurrence of first pair until found word or exhausted possibilities
		for (int i = 0; i < pair.length; i++) {
			// row and column of second letter in pair
			int row = pair[i][2];
			int column = pair[i][3];

			// add letters to used letters data structure
			String firstLetter = String.format("%d,%d", pair[i][0], pair[i][1]);
			String secondLetter = String.format("%d,%d", pair[i][2], pair[i][3]);
			usedLetters[0] = firstLetter;
			usedLetters[1] = secondLetter;

			if (find(word, pairs, 0, row, column, usedLetters)) {
				return true;
			}
			// remove letters from used data structure
			usedLetters[0] = "";
			usedLetters[1] = "";
		}
		return false;
	}

	private boolean find(String word, HashMap<String, int[][]> pairs, int index, int row, int column,
			String[] usedLetters) {

		int[][] pair = pairs.get(word.substring(index + 1, index + 3));

		for (int i = 0; i < pair.length; i++) {
			String letter = String.format("%d,%d", pair[i][2], pair[i][3]);
			if (row == pair[i][0] && column == pair[i][1] && !used(usedLetters, letter)) {
				
				usedLetters = use(usedLetters, letter);
				
				if (index == word.length() - 3) {
					return true;
				}
				
				if (find(word, pairs, index + 1, pair[i][2], pair[i][3], usedLetters)) {
					return true;
				}
				
				usedLetters = unuse(usedLetters, letter);
			}
		}
		return false;
	}
	
	private String[] use(String[] usedLetters, String letter) {
		// increase size of array and add new letter to end
		String[] temp = new String[usedLetters.length+1];
		for (int i = 0; i < usedLetters.length; i++) {
			temp[i] = usedLetters[i];
		}
		temp[temp.length-1] = letter;
		usedLetters = temp;
		return usedLetters;
	}
	
	private String[] unuse(String[] usedLetters, String letter) {
		// decrease size of array, thereby removing letter from end
		String[] temp = new String[usedLetters.length-1];
		for (int i = 0; i < usedLetters.length-1; i++) {
			temp[i] = usedLetters[i];
		}
		usedLetters = temp;
		return usedLetters;
	}
	
	private boolean used(String[] usedLetters, String letter) {
		for (int i = 0; i < usedLetters.length; i++) {
			if (letter.equals(usedLetters[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	public HashMap<String, int[][]> findPairs(Board b) {
		HashMap<String, int[][]> pairs = new HashMap<String, int[][]>();
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
								int[][] s = new int[1][1];
								s[0] = positions;
								pairs.put(pair, s);
							}
							// if letter pair already in HashMap add position to end of value array
							else {
								int[][] temp = pairs.get(pair);
								int[][] s = new int[temp.length+1][4];
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
