package puzzle8;

import java.util.Deque;

/**
 * Main Program for the 8-Puzzle-Problem.
 * @author Smokey95
 */
public class Puzzle8 {
	
	public static void main(String[] args) {

		testRandomBoard();
		//testMainBoard();

	}
	
	public static void testMainBoard() {
		
		System.out.println("------------------------------------------------------------------------------------");
		Board testBoard = new Board(new int[]{7,2,4,5,0,6,8,3,1});
		
		if(testBoard.parity() == true) {
			System.out.println("Puzzle: " + testBoard + " is solvable.");
		} else {
			System.out.println("Puzzle is not solvable.");
		}
		
		System.out.println("----- A* -----");
		Deque<Board> res2 = A_Star.aStar(testBoard);
		int n2 = res2 == null ? -1 : res2.size() - 1;										// -1, cause the first board is the start board
		System.out.println("Req. steps: " + n2 + ": " + res2);
		System.out.println("Depth: " + A_Star.steps);
		
		System.out.println("----- IDFS -----");
		res2 = IDFS.idfs(testBoard);
		n2 = res2 == null ? -1 : res2.size() - 1;												// -1, cause the first board is the start board
		System.out.println("Req. steps: " + n2 + ": " + res2);
		System.out.println("Depth: " + IDFS.steps);
	}
	
	
	public static void testRandomBoard() {
		
		System.out.println("------------------------------------------------------------------------------------");
		Board b = new Board(true); 													// Loesbares Puzzle b zufällig genrieren.
		System.out.println(b);
		
		if(b.parity() == true) {
			System.out.println("Puzzle: " + b + " is solvable.");
		} else {
			System.out.println("Puzzle is not solvable.");
		}

		System.out.println("----- A* -----");
		Deque<Board> res = A_Star.aStar(b);
		int n = res == null ? -1 : res.size() - 1;										// -1, cause the first board is the start board
		System.out.println("Req. steps: " + n + ": " + res);
		
		System.out.println("----- IDFS -----");
		res = IDFS.idfs(b);
		n = res == null ? -1 : res.size() - 1;												// -1, cause the first board is the start board
		System.out.println("Req. steps: " + n + ": " + res);
	}
}
