import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GreedyPuzzleMakerTest {
	Scorer scorer = new Scorer();
	GreedyPuzzleMaker puzzleMaker = new GreedyPuzzleMaker();
	
	@Test
	public void poolWithOneItemIsResult() {
		Pool pool = new Pool(new String[] { "only" });
		assertEquals("only", puzzleMaker.make(pool, scorer).toString());
	}

	@Test
	public void buildingCreatesPlausibleResult() { 
		String[] strings = new String[] { "fish", "ash", "wish", "fight" };
		Pool pool = new Pool(strings);
		assertEquals(".ash\nwish\nfish\nfight", puzzleMaker.make(pool, scorer).toString());
	}
}
