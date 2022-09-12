package game;

//import java.util.Scanner;

import javax.swing.JOptionPane;

public class HumanPlayer extends Player{

	//constructor
	public HumanPlayer(String userID) {
		super(userID);
	}
	
	//methods
	public int takePins(Board board) {
		int pins = board.getNoPins();
		//System.out.println("There are " + pins + " pins on the board.");
		//System.out.println("1 or 2?");
		JOptionPane.showMessageDialog(null, "There are " + pins + " pins on the board.");
		//Scanner scan = new Scanner(System.in);
		//int ans = scan.nextInt();
		//int ans = Integer.parseInt(JOptionPane.showInputDialog("Will you take 1 or 2?"));
		int ans = UserInterface.askInt();
		if(ans > pins || ans < 1 || ans > 2) {
			//System.out.println("That doesn't work.");
			JOptionPane.showMessageDialog(null, "That doesn't work.");
			return 0;
		}
		return ans;
	}
}
