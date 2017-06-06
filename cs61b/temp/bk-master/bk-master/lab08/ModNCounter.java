public class ModNCounter {

	private int myCount;

	public ModNCounter(int N) {
		myCount = 0;
	}

	public void increment() {
		myCount = (myCount+1)%3;
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

}
