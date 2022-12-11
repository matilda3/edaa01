package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSolver {
	
	private SudokuSolver sud;
	private int[][] matrix;
	private int[][] setTo;
	private int[][] bad;

	@BeforeEach
	void setUp() throws Exception {
		//matrix = new int[9][9];
		//sud = new Solver(matrix);
		setTo = new int[][] {{1, 2, 3, 4, 5, 6, 7, 8, 9},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		bad = new int[][] {{1, 2, 3},
									  {1, 2, 3},
									  {10, -1, 0}};
	}

	@AfterEach
	void tearDown() throws Exception {
		sud = null;
		matrix = null;
	}
	
	@Test
	void testAddRemoveGet() {
		matrix = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		sud.add(0, 0, 1);
		assertEquals(1, matrix[0][0]);
		assertEquals(1, sud.get(0, 0));
		sud.remove(0, 0);
		assertEquals(0, matrix[0][0]);
		
	}
	
	@Test
	void testGetSet() {
		matrix = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		sud.setMatrix(setTo);
		//sud.printBoard();
		assertEquals(setTo, sud.getMatrix());
	}

	@Test
	void testEmpty() {
		matrix = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		assertTrue(sud.solve());
		//sud.printBoard();
	}
	
	@Test
	void testImpossibleIsValidAndClear() {
		//same row
		matrix = new int[][] {{1, 0, 0, 0, 0, 0, 1, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		assertFalse(sud.isValid());
		assertFalse(sud.solve());
		sud.clear();
		//sud.printBoard();
		//same col
		matrix = new int[][] {{1, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {1, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		assertFalse(sud.isValid());
		assertFalse(sud.solve());
		matrix = new int[][] {{1, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 1, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		assertFalse(sud.isValid());
		assertFalse(sud.solve());
		matrix = new int[][] {{1, 2, 3, 0, 0, 0, 0, 0, 0},
										  {4, 5, 6, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 7, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		assertTrue(sud.isValid());
		assertFalse(sud.solve());
	}
	
	@Test
	void testSolvable() {
		matrix = new int[][] {{0, 0, 8, 0, 0, 9, 0, 6, 2},
										  {0, 0, 0, 0, 0, 0, 0, 0, 5},
										  {1, 0, 2, 5, 0, 0, 0, 0, 0},
										  {0, 0, 0, 2, 1, 0, 0, 9, 0},
										  {0, 5, 0, 0, 0, 0, 6, 0, 0},
										  {6, 0, 0, 0, 0, 0, 0, 2, 8},
										  {4, 1, 0, 6, 0, 8, 0, 0, 0},
										  {8, 6, 0, 0, 3, 0, 1, 0, 0},
										  {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sud = new Solver(matrix);
		assertTrue(sud.isValid());
		sud.solve();
		assertTrue(sud.solve());
	    sud.printBoard();
	}
	
	@Test
	void testExceptions() {
		matrix = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0},
										  {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sud = new Solver(matrix);
		
		assertThrows(IllegalArgumentException.class, () -> sud.add(0, 0, -1));
		assertThrows(IllegalArgumentException.class, () -> sud.remove(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> sud.get(11, 0));
		assertThrows(IllegalArgumentException.class, () -> sud.setMatrix(bad));
	}

}
