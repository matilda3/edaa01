package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver implements SudokuSolver {
	private int[][] matrix = new int[9][9];

	public Solver(int[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public boolean solve() {
		if(!isValid()) {
			return false;
		}
		return solve(0, 0);
	}
	
	private boolean solve(int row, int col) {
		int nextR = row;
		int nextC;
		if(col != 8) {
			nextC = col + 1;
		}else {
			nextC = 0;
			nextR = row + 1;
		}
		if(row == 9) {
			return true;
		}
		if(matrix[row][col] == 0) {
			for(int i = 1; i < 10; i++) {
				matrix[row][col] = i;
				if(isValid()) {
					if(solve(nextR, nextC)) {
						return true;
					}else {
						matrix[row][col] = 0;
					}
				}
				matrix[row][col] = 0;
			}
			return false;
		}

		return solve(nextR, nextC);
	}

	@Override
	public void add(int row, int col, int digit) {
		if(row > 9 || row < 0 || col > 9 || col < 0 || digit > 9 || digit < 0) {
			throw new IllegalArgumentException("Out of range");
		}else{
			matrix[row][col] = digit;
		}
	}

	@Override
	public void remove(int row, int col) {
		if(row > 9 || row < 0 || col > 9 || col < 0) {
			throw new IllegalArgumentException("Out of range");
		}else{
			matrix[row][col] = 0;
		}
	}

	@Override
	public int get(int row, int col) {
		if(row > 9 || row < 0 || col > 9 || col < 0) {
			throw new IllegalArgumentException("Out of range");
		}else{
			return matrix[row][col];
		}
	}

	@Override
	public boolean isValid() {
		//check each row
		for(int i = 0; i < 9; i++) {
			ArrayList<Integer> rows = IntStream.of(matrix[i]).boxed().collect(Collectors.toCollection(ArrayList::new));
			rows.removeIf(n -> n==0);
			Set<Integer> set = new HashSet<>(rows);
			//if length of set and rows arent the same there are doubles
			if(set.size() < rows.size()) {
				return false;
			}
		}
		//each col
		for(int i = 0; i < 9; i++) {
			ArrayList<Integer> cols = new ArrayList<>();
			for(int j = 0; j < 9; j++) {
				cols.add(matrix[j][i]);
			}
			cols.removeIf(n -> n == 0);
			Set<Integer> colSet = new HashSet<>(cols);
			if(colSet.size() < cols.size()) {
				return false;
			}
		}
		//each region (3x3)
		//reg top left
		ArrayList<Integer> g = getGroup(matrix, 0, 2, 0, 2);
		Set<Integer> s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//top mid
		g = getGroup(matrix, 0, 2, 3, 5);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//top right
		g = getGroup(matrix, 0, 2, 6, 8);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//mid left
		g = getGroup(matrix, 3, 5, 0, 2);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//mid mid
		g = getGroup(matrix, 3, 5, 3, 5);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//mid right
		g = getGroup(matrix, 3, 5, 6, 8);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//bot left
		g = getGroup(matrix, 6, 8, 0, 2);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//bot mid
		g = getGroup(matrix, 6, 8, 3, 5);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		//bot right
		g = getGroup(matrix, 6, 8, 6, 8);
		s = new HashSet<>(g);
		if(s.size() < g.size()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * returns list of elements in a 3x3 group, with 0s removed.
	 * 
	 * @param matrix the matrix
	 * @param cstart start index of columns
	 * @param cstop stop index of columns
	 * @param rstart start index of rows
	 * @param rstop stop index of rows
	 * @throws IllegalArgumentException if m has wrong dimensions, wrong values or indeces are out of bounds
	 * */
	private ArrayList<Integer> getGroup(int[][] matrix, int cstart, int cstop, int rstart, int rstop) {
		
		if(matrix.length != 9 || matrix[0].length != 9) {
			throw new IllegalArgumentException("Wrong dimension");
		}
		for(int i = 0; i <9; i++) {
			for(int j = 0; j < 9; j++) {
				if(matrix[i][j] > 9 || matrix[i][j] < 0) {
					throw new IllegalArgumentException("Bad entries");
				}
			}
		}
		if(cstart > 6 || cstart < 0 || rstart > 6 || rstart < 0 || cstop > 8 || cstop < 2 || rstop > 8 || rstop < 2) {
			throw new IllegalArgumentException("out of bounds");
		}
		
		ArrayList<Integer> group = new ArrayList<>();
		for(int i = rstart; i < rstop + 1; i++) {
			for(int j = cstart; j < cstop + 1; j++) {
				group.add(matrix[i][j]);
			}
		}
		group.removeIf(n -> n == 0);
		return group;
	}

	@Override
	public void clear() {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	@Override
	public void setMatrix(int[][] m) {
		if(m.length != 9 || m[0].length != 9) {
			throw new IllegalArgumentException("Wrong dimension");
		}
		for(int i = 0; i <9; i++) {
			for(int j = 0; j < 9; j++) {
				if(m[i][j] > 9 || m[i][j] < 0) {
					throw new IllegalArgumentException("Bad entries");
				}
			}
		}
		matrix = m;
	}

	@Override
	public int[][] getMatrix() {
		int[][] matrixC = matrix;
		return matrixC;
	}
	
	@Override
	public void printBoard() {
		for(int  i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
