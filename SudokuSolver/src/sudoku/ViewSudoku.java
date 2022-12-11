package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ViewSudoku {
	private boolean badInput;
	
	public static void main(String[] args) {
		//test
		int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0},
								  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		SudokuSolver s = new Solver(matrix);
		new ViewSudoku(s);
	}

	public ViewSudoku(SudokuSolver sudoku) {
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 500, 500));
	}
	
	private void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 700));
		Container pane = frame.getContentPane();
		JPanel gridPanel = new JPanel(new GridLayout(9, 9));
		gridPanel.setPreferredSize(new Dimension(600, 600));
		JPanel buttonPanel = new JPanel();
		JTextField[][] grid = new JTextField[9][9];
		
		addFields(grid, gridPanel);
		updateFields(grid, sudoku.getMatrix());
		
		
		JButton solve = new JButton("Solve");
		solve.addActionListener(event -> {
			getSudokuNumbers(grid, sudoku);
			if(badInput){
				badInput = false;
			}else if(sudoku.solve()) {
				updateFields(grid, sudoku.getMatrix());
			}else {
				JOptionPane.showMessageDialog(null, "This sudoku is unsolvable.");
			}
		});
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(event -> {
			sudoku.clear();
			updateFields(grid, sudoku.getMatrix());
		});
		buttonPanel.add(solve);
		buttonPanel.add(clear);
		
		pane.add(buttonPanel, BorderLayout.SOUTH);
		pane.add(gridPanel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addFields(JTextField[][] grid, JPanel panel) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				JTextField textField = new JTextField();
				textField.setFont(new Font("Default", Font.PLAIN, 30));
				textField.setHorizontalAlignment(0);
				textField.setDocument(new RestrictLength(1));
				grid[i][j] = textField;
				textField.setBackground(Color.LIGHT_GRAY);
				if(j == j % 3 + 3 && i != i % 3 + 3) {
					textField.setBackground(Color.white);
				}else if(j != j % 3 + 3 && i == i % 3 + 3) {
					textField.setBackground(Color.white);
				}
				panel.add(textField);
			}
		}
	}
	
	private void updateFields(JTextField[][] grid, int[][] matrix) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(matrix[i][j] == 0) {
					grid[i][j].setText("");
				}else {
					grid[i][j].setText(Integer.toString(matrix[i][j]));
				}
			}
		}
	}
	
	private void getSudokuNumbers(JTextField[][] grid, SudokuSolver sudoku) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				String input = grid[i][j].getText();
				if(isValid(input)) {
					int num = Integer.parseInt(input);
					sudoku.add(i, j, num);
				}else {
					sudoku.remove(i, j);
					grid[i][j].setText("");
				}
			}
		}
	}
	
	private boolean isValid(String input) {
		if(input.equals("")) {
			return false;
		}else if(Character.isDigit(input.charAt(0))) {
			if(Integer.parseInt(input) == 0) {
				JOptionPane.showMessageDialog(null, "Invalid input, enter a digit between 1-9.");
				badInput = true;
				return false;
			}else {
				return true;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Invalid input, enter a digit between 1-9.");
			badInput = true;
			return false;
		}
	}
}
