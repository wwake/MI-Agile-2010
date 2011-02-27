
public class Scorer {

	public int score(Cluster cluster) {
		int result = 0;
		
		for (int i = 0; i < cluster.width(); i++) 
			result += this.scoreColumn(cluster.column(i));

		return result;
	}

	public int scoreColumn(String string) {
		if (string.length() == 0) return 0;

		String guardedString = string + '\0';

		int result = 0;
		int runLength = 0;
		
		for (int i = 0; i < string.length(); i++) {
			runLength++;

			if (guardedString.charAt(i) != guardedString.charAt(i+1)) {
				if (guardedString.charAt(i) != '.')
					result += square(runLength);
				runLength = 0;
			}	
		}
		
		return result;
	}

	private int square(int arg) {
		return arg * arg;
	}
}
