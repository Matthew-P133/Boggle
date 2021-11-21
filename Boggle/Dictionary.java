import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
	
	private HashSet<String> dictionary;
	
	public Dictionary() {
		this.dictionary = makeDictionary();
	}
	
	
	// reads wordlist.txt into a HashSet used by other classes for word validation
	public HashSet<String> makeDictionary() {
		
		HashSet<String> dictionary = new HashSet<String>();
		FileReader fr = null;
		String fp = System.getProperty("user.dir") + "\\wordlist";
		
		try {
			fr = new FileReader(fp);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner s = new Scanner(fr);
		while (s.hasNextLine()) {
			String word = s.nextLine();
			word = word.toUpperCase().strip();
			if (word.length() > 2) {
			dictionary.add(word);
			}
		}
		return dictionary;
	}
	
	
	// returns true if a word is valid
	public boolean hasWord(String s) {
		if (this.dictionary.contains(s)) {
			return true;
		}
		return false;
	}
	
	
	public Object[] toArray() {
		return dictionary.toArray();
	}

	
}
