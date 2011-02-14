import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BuilderTest {
	@Test
	public void poolWithOneItemIsResult() {
		Builder builder = new Builder(new String[] { "only" }, new Scorer());
		assertEquals("only", builder.build());
	}

	@Test
	public void buildingCreatesPlausibleResult() { 
		String[] strings = new String[] { "fish", "ash", "wish", "fight" };
		Builder builder = new Builder(strings, new Scorer());
		assertEquals(".ash\nwish\nfish\nfight", builder.build());
	}
}
