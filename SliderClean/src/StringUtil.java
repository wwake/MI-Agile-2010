import java.util.Arrays;

public class StringUtil {
	static String repeat(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return String.valueOf(chars);
	}
}
