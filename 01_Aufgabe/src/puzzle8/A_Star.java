package puzzle8;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class A_Star for A* search
 * @author Smokey95
 */
public class A_Star {
	// cost ordnet jedem Board die Aktuellen Pfadkosten (g-Wert) zu.
	private static HashMap<Board,Integer> cost = new HashMap<>();
	// pred ordnet jedem Board den Elternknoten zu. (siehe Skript S. 2-25). 
	private static HashMap<Board,Board> pred = new HashMap<>();
	
	public static int steps = 0;
	
	// In cost und pred sind genau alle Knoten der closedList und openList enthalten!
	// Nachdem der Zielknoten erreicht wurde, lässt sich aus cost und pred der Ergebnispfad ermitteln.
	
	// openList als Prioritätsliste.
	// Die Prioritätswerte sind die geschätzen Kosten f = g + h (s. Skript S. 2-66)
	private static IndexMinPQ<Board, Integer> openList = new IndexMinPQ<>();
	
	public static Deque<Board> aStar(Board startBoard) {
		
		if (startBoard.isSolved())
			return new LinkedList<>();
		
	  // openList.add(start): adds the current board with its currents costs g + heuristic costs
		// @FAQ: 0 right cause initial step? 
		openList.add(startBoard, 0 + startBoard.h2());
		cost.put(startBoard, 0);
		
		// initialisiere closedList als leer;
		// @FAQ: Not needed cause pred?
		
		while(!openList.isEmpty())
		{
			
			Board parent = openList.removeMin();
			
			if(parent.isSolved())
				return getPath(parent);
			
			//pred.put(n, pred.get(n));
			//cost.put(n, cost.get(n));
			
			for(Board child : parent.possibleActions()){
				
				steps++;
				int h = cost.get(parent) + child.h2();																	// h = cost(n) + c(n, child): current costs of parent + heuristic costs
				
				if(openList.get(child) == null && pred.get(child) == null){ 						// child is not in openList and not in closedList (here pred is equal to closedList)											
					//cost.put(child, cost.get(parent) + parent.h2());
					cost.put(child, cost.get(parent) + 1);
					openList.add(child, h);									  														// openList.add(child): adds the current board with its heuristic costs
					pred.put(child, parent);
					
				} else if(openList.get(child) != null) {																// child is in openList
					if(cost.get(child) > h){								    													// checks if the new costs are better than the old costs [See script S. 2-62: red line]
						openList.change(child, h);																					// if so, change the costs
						cost.put(child, h);																									// and the costs
					}
				}
			}
		}
		return null;																																// no solution found
	}
	
	
	/**
	 * Helper function to return the path from the startBoard to the solvedBoard
	 * @param solvedBoard the solved board
	 * @return the path from the startBoard to the solvedBoard
	 */
	private static Deque<Board> getPath(Board solvedBoard){
		
		Deque<Board> result = new LinkedList<>();
		
		/*
		 * 1. Get the path from n to startBoard
		 * 2. Add n to the path
		 * 3. Return the path
		 */
				
		result.add(solvedBoard);
		while(pred.get(solvedBoard) != null){
			solvedBoard = pred.get(solvedBoard);
			result.addFirst(solvedBoard);
		}	
		return result;
	}
	
}
