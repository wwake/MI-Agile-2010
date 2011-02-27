import java.util.ArrayList;
import java.util.List;

public class Formatter {
	public String format(Puzzle puzzle) {
		StringBuffer result = new StringBuffer();
		
		List<IndentedWord> words = bracketWords(new IndentedWord(""), puzzle, new IndentedWord(""));
		
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
		if (thisCh == IndentedWord.UNOCCUPIED) return '.';
		
		if (thisCh != prevCh && thisCh == nextCh) return '/';
		if (thisCh == prevCh && thisCh != nextCh) return '\\';
		if (thisCh == prevCh && thisCh == nextCh) return '|';
		
		return thisCh;
	}

	private List<IndentedWord> bracketWords(IndentedWord before, Puzzle puzzle, IndentedWord after) {
		List<IndentedWord> words = new ArrayList<IndentedWord>();
		words.add(before);
		
		for (int i = 0; i < puzzle.height(); i++)
			words.add(puzzle.wordAt(i));

		words.add(after);
		return words;
	}
}
