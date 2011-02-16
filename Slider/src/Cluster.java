import java.util.Arrays;

public abstract class Cluster {
	public abstract IndentedWord first();
	public abstract IndentedWord last();
	public abstract int width();
	public abstract String column(int c);
	public abstract IndentedWord wordAt(int i);
	public abstract int height();
	public abstract Cluster flipped();
	public abstract boolean contains(String string);
		
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Cluster)) return false;
		
		Cluster that = (Cluster) obj;
		
		if (this.height() != that.height()) return false;
		for (int i = 0; i < height(); i++) {
			if (!this.wordAt(i).equals(that.wordAt(i)))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		if (height() == 0) return "";
		StringBuffer result = new StringBuffer();
		result.append(wordAt(0));
		for (int i = 1; i < height(); i++) {
			result.append('\n');
			result.append(wordAt(i));
		}
		return result.toString();
	}
	
	protected String repeat(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return String.valueOf(chars);
	}
}

/*
- width() threw me a bit when I saw the overrides.  It looks like it really means longestWord (including offset).  
  I'm probably biased by my example but width of a multiword cluster to me would be leftmost
  character to rightmost (not necessarily in the same word).  
- Looking at this class it looked like Clusters could only contain a collection of words, until 
  I understood what Pair was doing.  Not sure if I would add an add(Cluster c) abstract method here
  to make it more obvious (then do nothing in the IndentedWord implementation) or leave it as is.
  I'm leaning to the way you have it, but it take some investigation to grok it.
- See comment in Flipper about the get() method.
- Feels like an odd place for the repeat() method.  A static might be a little better, but I'm
  thinking some kind of output formatter class.  Then you could pull out all the toString logic.
  This is not a really strong feeling though, so I could go either way.  I see you had a Formatter
  for OldCluster, but I'm guessing you had second thoughts?
*/