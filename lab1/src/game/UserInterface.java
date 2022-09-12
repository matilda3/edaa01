package game;

import javax.swing.JOptionPane;

public class UserInterface {
	
	public static void printMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	public static int askInt() {
		return Integer.parseInt(JOptionPane.showInputDialog("Will you take 1 or 2?"));
	}
}
