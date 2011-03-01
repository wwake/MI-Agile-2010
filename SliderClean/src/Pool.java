import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Pool implements Iterable<Puzzle> {
	Set<Puzzle> puzzles = new HashSet<Puzzle>();

	public Pool(String[] words) {
		for (String string : words)
			puzzles.add(new IndentedWord(string));
	}
	
	public void add(Puzzle puzzle) {
		puzzles.add(puzzle);
	}

	public int size() {
		return puzzles.size();
	}

	public Puzzle any() {
		for (Puzzle puzzle : puzzles)
			return puzzle;
		throw new NoSuchElementException();
	}

	public boolean contains(Puzzle puzzle) {
		return puzzles.contains(puzzle);
	}

	public void remove(String string) {
		for (Puzzle puzzle : puzzles) {
			if (puzzle.contains(string)) {
				puzzles.remove(puzzle);
				return;
			}
		}
	}

	public Iterator<Puzzle> iterator() {
		return puzzles.iterator();
	}

	public Pool candidates() {
		List<Puzzle> puzzleList = new ArrayList<Puzzle>(puzzles);
		Pool result = new Pool(new String[]{});
		
		for (int i = 0; i < puzzleList.size(); i++)
			for (int j = i + 1; j < puzzleList.size(); j++)
				result.addAllCombos(puzzleList.get(i), puzzleList.get(j));

		return result;
	}

	public void addAllCombos(Puzzle puzzle1, Puzzle puzzle2) {
		this.allSlidePositions(puzzle1, puzzle2);
		this.allSlidePositions(puzzle1, puzzle2.inverted());
		this.allSlidePositions(puzzle1.inverted(), puzzle2);
		this.allSlidePositions(puzzle1.inverted(), puzzle2.inverted());
	}

	public void allSlidePositions(Puzzle puzzle1, Puzzle puzzle2) {
		IndentedWord lastWordFromPuzzle1 = puzzle1.last();
		IndentedWord firstWordFromPuzzle2 = puzzle2.first();

		int distanceToAlignFirstLetters = lastWordFromPuzzle1.indent()
				- firstWordFromPuzzle2.indent();

		for (int i = 0; i < lastWordFromPuzzle1.word().length(); i++)
			this.add(new JoinedPuzzle(puzzle1, puzzle2, distanceToAlignFirstLetters + i));

		for (int i = 1; i < firstWordFromPuzzle2.word().length(); i++)
			this.add(new JoinedPuzzle(puzzle1, puzzle2, distanceToAlignFirstLetters - i));
	}

	public Puzzle bestIn(Scorer scorer) {
		int bestScore = -1;
		Puzzle bestResult = null;
		
		for (Puzzle puzzle : this) {
			int score = scorer.score(puzzle);
			if (score > bestScore) {
				bestResult = puzzle;
				bestScore = score;
			}
		}
		
		return bestResult;
	}
}