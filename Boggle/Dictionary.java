import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
	
	private HashSet<String> dictionary;
	
	public Dictionary() {
		this.dictionary = makeDictionary();
	}
	
	
	// reads wordlist into a HashSet used by other classes for word validation
	public HashSet<String> makeDictionary() {
		
		HashSet<String> dictionary = new HashSet<String>();
		FileReader fileReader = null;
		String filePath = System.getProperty("user.dir") + "\\wordlist";
		
		try {
			fileReader = new FileReader(filePath);
		} 
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
		Scanner scanner = new Scanner(fileReader);
		while (scanner.hasNextLine()) {
			String word = scanner.nextLine();
			word = word.toUpperCase().strip();
			if (word.length() > 2) {
			dictionary.add(word);
			}
		}
		scanner.close();
		return dictionary;
	}
	
	
	// returns true if a word is valid
	public boolean hasWord(String word) {
		if (this.dictionary.contains(word)) {
			return true;
		}
		return false;
	}
	
	
	public Object[] toArray() {
		return dictionary.toArray();
	}

	
}
