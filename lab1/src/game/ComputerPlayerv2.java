package game;

//import java.util.Random;

public class ComputerPlayerv2 extends Player {
	
	public ComputerPlayerv2(String userID) {
		super(userID);
	}
	
	//methods
		public int takePins(Board board) {
			int ans;
			if(board.getNoPins() % 2 == 0) {
				ans = 2;
			}else {
				ans = 1;
			}
			return ans;
		}
}
