package game;

public abstract class Player {
	//init
	protected String userID;
	
	//constructor
	public Player(String userID) {
		this.userID = userID;
	}
	
	//methods
	public String getUserID() {
		return userID;
	}
	public abstract int takePins(Board board);
}
