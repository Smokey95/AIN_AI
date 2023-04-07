package puzzle8;

import java.lang.Thread.State;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Class IDFS for iterative deepening depth-first search
 * @author Smoke95
 */
public class IDFS {
	
	public static int steps = 0;

	private static Deque<Board> dfs(Board curBoard, Deque<Board> path, int limit) {
		
		if(curBoard.isSolved()) {																										// Check if board is already solved
			return path;
		} else if(limit == 0 || curBoard.parity() == false){												// @FAQ: Legal?
			return null;
		} else {
			boolean cutOffOccurrent = false;
			List<Board> possibleActions = curBoard.possibleActions(); 								// get possible actions
			steps++;
			
			for (Board b : possibleActions) { 																				// for each possible action
				if (!path.contains(b)) { 																								// if action is not in path
					path.addLast(b);																											// push successor
					Deque<Board> result = dfs(b, path, limit-1); 													// call dfs with successor as curBoard
					
					if(result != null) {																									// if result is null
						return result;																											// return result
					} else if(result == null) {																						// if result is not null
						cutOffOccurrent = true;																							// set cutOffOccurrent to true
					}
					
					path.removeLast();																										// pop successor
				}
				
			}
			
			// @FAQ: Cause this is missing there will be no depth limit which will 
			// Cause boards are always solvable
			//if(cutOffOccurrent) {
			//	return null;
			//} else {
			//	return new LinkedList<>();
			//}
		}
		return null;
	}
	
	
	/**
	 * Calls the depth-first search with a limit
	 * @param curBoard 
	 * @param path
	 * @return
	 */
	private static Deque<Board> idfs(Board curBoard, Deque<Board> path) {
		
		int boarder = Integer.MAX_VALUE / 10000;
		
		for (int limit = 5; limit < boarder; limit++) {
			Deque<Board> result = dfs(curBoard,path,limit);
			if (result != null)
				return result;
		}
		return null;
	}
	
	
	/**
	 * Calls the iterative deepening depth-first search
	 * @param curBoard the current board
	 * @return the path to the solution
	 */
	public static Deque<Board> idfs(Board curBoard) {
		Deque<Board> path = new LinkedList<>();																		 	// path as a stack
		path.addLast(curBoard);																											// push curBoard
		Deque<Board> res =  idfs(curBoard, path); 																	// call private idfs with path as a stack
		return res;
	}
}
