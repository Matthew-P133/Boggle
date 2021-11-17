import java.util.HashMap;

public class WordEngine {
	
	private HashMap<String, String[]> pairs;
	
	public WordEngine(Board board) {
		// parse board into data structure containing letter pair information
		pairs = findPairs(board);
	}
	
	
	public boolean validate(String word) {

		HashMap<String, int[][]> relevantPairs;
		relevantPairs = this.getRelevantPairs(word);
		if (relevantPairs == null) {
			System.out.println("null");
			return false;
		}
		// get list of first pair in word
		int[][] firstOccurrence = relevantPairs.get(word.substring(0, 2));

		// create data structure to keep track of used letters
		String[] usedLetters = new String[2];

		// look for word from each occurrence of first pair until found or exhausted possibilities
		for (int i = 0; i < firstOccurrence.length; i++) {
			int r = firstOccurrence[i][2];
			int c = firstOccurrence[i][3];

			String firstLetter = String.format("%d,%d", firstOccurrence[i][0], firstOccurrence[i][1]);
			String secondLetter = String.format("%d,%d", firstOccurrence[i][2], firstOccurrence[i][3]);

			// add letters to used letters data structure
			usedLetters[0] = firstLetter;
			usedLetters[1] = secondLetter;

			if (join(word, relevantPairs, 0, r, c, usedLetters)) {
				return true;
			}
			// remove letters from used data structure
			usedLetters[0] = "";
			usedLetters[1] = "";
		}
		return false;
	}

	private boolean join(String word, HashMap<String, int[][]> relevantPairs, int i, int r, int c,
			String[] usedLetters) {

		String nextPair = word.substring(i + 1, i + 3);
		int[][] nextOccurrence = relevantPairs.get(nextPair);

		for (int k = 0; k < nextOccurrence.length; k++) {
			String letter = String.format("%d,%d", nextOccurrence[k][2], nextOccurrence[k][3]);
			if (r == nextOccurrence[k][0] && c == nextOccurrence[k][1] && used(usedLetters, letter) == false) {
				// add to used letters
				usedLetters = use(usedLetters, letter);

				if (i == word.length() - 3) {
					for (int y = 0; y < usedLetters.length; y++) {
						System.out.println(usedLetters[y]);
					}
					return true;
				}
				if (join(word, relevantPairs, i + 1, nextOccurrence[k][2], nextOccurrence[k][3], usedLetters)) {

					return true;
				}
				// remove from used letters
				usedLetters = unuse(usedLetters, letter);
			}
		}
		return false;
	}
	
	private String[]  use(String[] usedLetters, String letter) {
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
	
	
	public HashMap<String, int[][]> getRelevantPairs(String word) {
		HashMap<String, int[][]> relevantPairs = new HashMap<String, int[][]>();
		
		String pair;
		for (int letterIndex = 0; letterIndex < word.length()-1; letterIndex++) {
			pair = word.substring(letterIndex, letterIndex+2);
			String[] occurrences = this.pairs.get(pair);
			if (occurrences == null) {
				return null;
			}
			int[][] currentPair = new int[occurrences.length][4];
			for (int i = 0; i < occurrences.length; i++) {
				String[] stringDigits = occurrences[i].split(",");
				for (int j = 0; j < 4; j++) {
					currentPair[i][j] = Integer.parseInt(stringDigits[j]);
				}	
			}
			relevantPairs.put(pair, currentPair);
		}
		return relevantPairs;
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
