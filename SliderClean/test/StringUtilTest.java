import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilTest {
	@Test
	public void repeats() {
		assertEquals("", StringUtil.repeat('x', 0));
		assertEquals("xxx", StringUtil.repeat('x', 3));
	}
}
