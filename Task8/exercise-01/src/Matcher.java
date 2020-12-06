public class Matcher {
	public Matcher() {
	}

	public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

		new Clipper().clip(actual, clipLimit);

		// Check for length differences
		if (actual.length != expected.length)
			return false;

		return compareEachEntry(expected, actual, delta);
	}

	private boolean compareEachEntry(int[] expected, int[] actual, int delta){
		// Check that each entry within expected +/- delta
		for (int i = 0; i < actual.length; i++)
			if (Math.abs(expected[i] - actual[i]) > delta)
				return false;

		return true;
	}
}