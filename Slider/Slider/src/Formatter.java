import java.util.ArrayList;
import java.util.List;

public class Formatter {

	public String format(Cluster cluster) {
		StringBuffer result = new StringBuffer();
		
		List<OffsetWord> words = bracketWords(new OffsetWord(""), cluster, new OffsetWord(""));
		
		for (int i = 1; i < words.size() - 1; i++)
			formatWord(result, words.get(i - 1), words.get(i), words.get(i + 1));

		return result.toString();
	}

	private void formatWord(StringBuffer result, OffsetWord prevWord, OffsetWord thisWord, OffsetWord nextWord) {
		for (int j = 0; j < thisWord.length(); j++) 
			result.append(charFor(prevWord.at(j), thisWord.at(j), nextWord.at(j)));

		result.append('\n');
}

	private char charFor(char prevCh, char thisCh, char nextCh) {
		if (thisCh == '.') return '.';
		
		return thisCh == prevCh || thisCh == nextCh ? '-' : thisCh;
	}

	private List<OffsetWord> bracketWords(OffsetWord before, Cluster cluster, OffsetWord after) {
		List<OffsetWord> words = new ArrayList<OffsetWord>();
		words.add(before);
		for (OffsetWord word : cluster)
			words.add(word);
		words.add(after);
		return words;
	}
}
