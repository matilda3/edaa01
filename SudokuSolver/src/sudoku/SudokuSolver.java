package sudoku;

public interface SudokuSolver {
	/**
	 * solves the sudoku
	 *  */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * removes (replaces with 0) the digit from the box row, col.
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row or col isn't in range (0, 9)
	 */
	void remove(int row, int col);

	/**
	 * returns the digit in the box row, col.
	 * @param row the row
	 * @param col the column
	 * @throws IllegalArgumentException if row or col isn't in range (0, 9)
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 */
	boolean isValid();

	/**
	 * removes (replaces with 0) all of the digits in the boxes.
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * returns the matrix (the current board)
	 */
	int[][] getMatrix();

	/**
	 * returns the matrix printed in 9x9 form
	 * */
	void printBoard();
}
