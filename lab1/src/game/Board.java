package game;

public class Board {
	//initial variables
	private int noPins;
	
	//methods
	public void setUp(int initPins){
		noPins = initPins;
	}
	public void takePins(int pins) {
		noPins = noPins - pins;
	}
	public int getNoPins() {
		return noPins;
	}
}
