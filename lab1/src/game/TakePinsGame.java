package game;

import javax.swing.JOptionPane;

public class TakePinsGame {

	public static void main(String[] args) {
		Board board = new Board();
		board.setUp(10);
		Player p1 = new HumanPlayer("P1");
		Player p2 = new ComputerPlayerv2("P2");
		
		while(board.getNoPins() >  0) {
			int a = 0;
			while(a == 0) {
				a = p1.takePins(board);
			}
			board.takePins(a);
			if(board.getNoPins() ==0) {
				UserInterface.printMsg("P1 is the winner!");
				//JOptionPane.showMessageDialog(null, "p1 is the winner!");
				//System.out.println("p1 is the winner!");
				System.exit(0);
			}
			//System.out.println(board.getNoPins() + " pins remain.");
			JOptionPane.showMessageDialog(null, board.getNoPins() + " pins remain");
			a = p2.takePins(board);
			board.takePins(a);
			//System.out.println(board.getNoPins() + " pins remain");
			JOptionPane.showMessageDialog(null, "P2 takes " + a + " pins.");
			//JOptionPane.showMessageDialog(null, board.getNoPins() + " pins remain");
			if(board.getNoPins() ==0) {
				//System.out.println("p2 is the winner!");
				//JOptionPane.showMessageDialog(null, "p1 is the winner!");
				UserInterface.printMsg("P2 is the winner!");
				System.exit(0);
			}
		}
	}

}
