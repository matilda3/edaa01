package game;

import java.util.Random;

public class ComputerPlayer extends Player{

	//constructor
	public ComputerPlayer(String userID) {
		super(userID);
	}
	
	//methods
	public int takePins(Board board) {
		Random r = new Random();
		int ans = r.nextInt(2) + 1;
		return ans;
	}

}
