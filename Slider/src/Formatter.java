import java.util.ArrayList;
import java.util.List;

public class Formatter {

	public String format(OldCluster cluster) {
		StringBuffer result = new StringBuffer();
		
		List<IndentedWord> words = bracketWords(new IndentedWord(""), cluster, new IndentedWord(""));
		
		for (int i = 1; i < words.size() - 1; i++)
			formatWord(result, words.get(i - 1), words.get(i), words.get(i + 1));

		return result.toString();
	}

	private void formatWord(StringBuffer result, IndentedWord prevWord, IndentedWord thisWord, IndentedWord nextWord) {
		for (int j = 0; j < thisWord.width(); j++) 
			result.append(charFor(prevWord.at(j), thisWord.at(j), nextWord.at(j)));

		result.append('\n');
}

	private char charFor(char prevCh, char thisCh, char nextCh) {
		if (thisCh == '.') return '.';
		
		return thisCh == prevCh || thisCh == nextCh ? '-' : thisCh;
	}

	private List<IndentedWord> bracketWords(IndentedWord before, OldCluster cluster, IndentedWord after) {
		List<IndentedWord> words = new ArrayList<IndentedWord>();
		words.add(before);
		for (IndentedWord word : cluster)
			words.add(word);
		words.add(after);
		return words;
	}
}
