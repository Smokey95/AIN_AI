package puzzle8;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * Class Board for 8-Puzzle-Problem
 * @author Smoke95
 */
public class Board {

	/**
	 * Problmegröße
	 */
	public static final int N = 8;

	/**
	 * Board als Feld. 
	 * Gefüllt mit einer Permutation von 0,1,2, ..., 8.
	 * 0 bedeutet leeres Feld.
	 */
	protected int[] board = new int[N+1];

	/**
	 * Generates a random board which is either solvable or not
	 * @param solvable true = solvable
	 */
	public Board(boolean solvable) {
		this.board = new int[N+1];
		List<Integer> list = new LinkedList<>();
		
		// Creates a random board as long as the parity is false
		do{
			/*
			 * Fills list with 0,1,2, ..., 8.
			 */
			for (int i = 0; i < N+1; i++) {
				list.add(i);
			}
		
		 	// Fills board with random elements from list.
			for (int i = 0; i < N+1; i++) {
				int j = (int) (Math.random() * list.size());
				this.board[i] = list.get(j);
				list.remove(j);
			}
		} while(this.parity() == !solvable);
	}
	
	
	/**
	 * Generiert ein Board und initialisiert es mit board.
	 * @param board Feld gefüllt mit einer Permutation von 0,1,2, ..., 8.
	 */
	public Board(int[] board) {
		this.board = board;
	}

	
	@Override
	public String toString() {
		return "Puzzle{" + "board=" + Arrays.toString(board) + '}';
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Board other = (Board) obj;
		return Arrays.equals(this.board, other.board);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Arrays.hashCode(this.board);
		return hash;
	}
	
	
	/**
	 * Parity check.
	 * @return true if parity is even and board is solvable, false if parity is odd.
	 */
	public boolean parity() {
		
		int pairs = 0;
		
		/*
		 * Count the number of pairs in which the first element is greater than the second.
		 * Only pairs that do not contain 0 are counted.
		 */
		for (int i = 0; i < this.board.length; i++)
		{
			for (int j = i + 1; j < this.board.length; j++)
			{
				if ((this.board[i] > this.board[j]) && (this.board[i] != 0) && (this.board[j] != 0))
				{
					pairs++;
				}
			}
		}
		
		return pairs % 2 == 0;
	}
	
	/**
	 * Heuristic h1: Amount of misplaced tiles.
	 * @return Heuristic value
	 */
	public int h1() {
		int h = 0;
		
		/**
		 * Count the number of tiles that are not in their correct position.
		 * The empty tile is not counted.
		 */
		for (int i = 0; i < this.board.length; i++)
		{
			if (this.board[i] != i && this.board[i] != 0)
			{
				h++;
			}
		}
		
		return h;
	}
	
	/**
	 * Heuristic h2: Sum of Manhattan distances.
	 * @return Heuristikwert.
	 */
	public int h2() {
		int h = 0;
		
		/**
		 * Calculate the Manhattan distance for each tile and sum them up.
		 * The empty tile is not counted.
		 */
		for (int i = 0; i < this.board.length; i++)
		{
			if (this.board[i] != 0)
			{
				/*
				 * Example: The tile with the value 7 is at the index 0 in the board array.
				 * The coordinates of the tile are (0 % 3, 0 / 3) = (0, 0).
				 * The coordinates of the goal position are (7 % 3, 7 / 3) = (1, 2).
				 * The Manhattan distance is the sum of the absolute values of the differences
				 * of the coordinates of the tile and the coordinates of the goal position.
				 * The Manhattan distance is |0 - 1| + |0 - 2| = 3.
				 */
				h += Math.abs(i % 3 - this.board[i] % 3) + Math.abs(i / 3 - this.board[i] / 3);
			}
		}
		
		return h;
	}
	
	
	/**
	 * Liefert eine Liste der möglichen Aktion als Liste von Folge-Boards zurück.
	 * @return Folge-Boards.
	 */
	public List<Board> possibleActions() {
		
		List<Board> boardList = new LinkedList<>();
		
		// Find the index of the empty tile.
		int emptyTileIndex = 0;
		for (int i = 0; i < this.board.length; i++)
		{
			if (this.board[i] == 0)
			{
				emptyTileIndex = i;
				break;
			}
		}
		
		
		/*
		 * Create a new board for each possible action and add it to the list.
		 */
		
		// Move the empty tile left.
		if (emptyTileIndex % 3 != 0)
		{
			int[] newBoard = this.board.clone();
			newBoard[emptyTileIndex] = newBoard[emptyTileIndex - 1];
			newBoard[emptyTileIndex - 1] = 0;
			boardList.add(new Board(newBoard));
		}
		// Move the empty tile right.
		if (emptyTileIndex % 3 != 2)
		{
			int[] newBoard = this.board.clone();
			newBoard[emptyTileIndex] = newBoard[emptyTileIndex + 1];
			newBoard[emptyTileIndex + 1] = 0;
			boardList.add(new Board(newBoard));
		}
		// Move the empty tile up.
		if (emptyTileIndex / 3 != 0)
		{
			int[] newBoard = this.board.clone();
			newBoard[emptyTileIndex] = newBoard[emptyTileIndex - 3];
			newBoard[emptyTileIndex - 3] = 0;
			boardList.add(new Board(newBoard));
		}
		// Move the empty tile down.
		if (emptyTileIndex / 3 != 2)
		{
			int[] newBoard = this.board.clone();
			newBoard[emptyTileIndex] = newBoard[emptyTileIndex + 3];
			newBoard[emptyTileIndex + 3] = 0;
			boardList.add(new Board(newBoard));
		}
		
		return boardList;
	}
	
	
	/**
	 * Prüft, ob das Board ein Zielzustand ist.
	 * @return true, falls Board Ziestzustand (d.h. 0,1,2,3,4,5,6,7,8)
	 */
	public boolean isSolved() {
		Board finalState = new Board(new int[]{0,1,2,3,4,5,6,7,8});
		return this.equals(finalState);
	}
	
	
	public static void main(String[] args) {
		Board b = new Board(new int[]{7,2,4,5,0,6,8,3,1});		// abc aus Aufgabenblatt
		Board goal = new Board(new int[]{0,1,2,3,4,5,6,7,8});
		Board rand_t = new Board(true);
		
		System.out.println("----- Board rand --------");
		System.out.println("Board:   	" + rand_t);
		System.out.println("Solvable:	" + rand_t.parity());
		System.out.println("Val h(1): " + rand_t.h1());
		System.out.println("Val h(2): " + rand_t.h2());
		
		System.out.println("----- Board b --------");
		System.out.println("Board:   	" + b);
		System.out.println("Solvable:	" + b.parity());
		System.out.println("Val h(1): " + b.h1());
		System.out.println("Val h(2): " + b.h2());
		
		for (Board child : b.possibleActions())
			System.out.println(child);
		
		System.out.println(b.isSolved());
		//System.out.println(goal.isSolved());
	}
}
	
