package kalah;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class AlphaBetaPruningHeuristic {
  private static int callCounter = 0;
  
  public static KalahBoard alphaBetaSearch(KalahBoard board, int maxDepth){
		
		KalahBoard action       = null;
    int curr_max_count      = Integer.MIN_VALUE;
		int alpha               = Integer.MIN_VALUE;
		int beta                = Integer.MAX_VALUE;
    
    callCounter = 0;
		
		if(checkOnFinished(board, maxDepth))
			return board;
		
		for(KalahBoard poss_action : heuristic(board)){
			
      int tmp_max_count = curr_max_count;
       
      if(poss_action.isBonus())
        curr_max_count = Integer.max(curr_max_count, maxValue(poss_action, maxDepth - 1, alpha, beta));
      else
        curr_max_count = Integer.max(curr_max_count, minValue(poss_action, maxDepth - 1, alpha, beta));
			
			if(tmp_max_count != curr_max_count)
        action = poss_action;
				
			alpha = Integer.max(alpha, curr_max_count);
		}

		return action;
	}
	
	private static int minValue(KalahBoard board, int maxDepth, int alpha, int beta){

    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getAKalah();
		
		int curr_max_val = Integer.MAX_VALUE;
		
		for(KalahBoard poss_action : heuristic(board)){
      if(poss_action.isBonus())
        curr_max_val = Integer.min(curr_max_val, minValue(poss_action, maxDepth - 1, alpha, beta));
      else
        curr_max_val = Integer.min(curr_max_val, maxValue(poss_action, maxDepth - 1, alpha, beta));
				
			if(curr_max_val <= alpha)
				return curr_max_val;
				
			beta = Integer.min(beta, curr_max_val);
		}
		
		return curr_max_val;
	}
	
	private static int maxValue(KalahBoard board, int maxDepth, int alpha, int beta){
		
    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getBKalah();
		
		int curr_min_val = Integer.MIN_VALUE;
		
		for(KalahBoard poss_action : heuristic(board)){
      if(poss_action.isBonus())
        curr_min_val = Integer.max(curr_min_val, maxValue(poss_action, maxDepth - 1, alpha, beta));
      else
        curr_min_val = Integer.max(curr_min_val, minValue(poss_action, maxDepth - 1, alpha, beta));
				
			if(curr_min_val >= beta)
				return curr_min_val;
			
			alpha = Integer.max(alpha, curr_min_val);
		}
		
		return curr_min_val;
	}
  
  
  /**
   * Checks if the maxDepth is reached or the board is finished
   * @param board
   * @param maxDepth
   * @return
   */
  private static boolean checkOnFinished(KalahBoard board, int maxDepth){
    if(maxDepth == 0 || board.isFinished()){
      return true;
    }
    return false;
  }
  
  
  public static int getCallCounter(){
    return callCounter;
  }
  
  
  /**
   * Simple Heuristik für die Reihenfolge der möglichen Züge:
   * Bonuszüge werden vor den normalen Zügen ausgeführt!
   */
  public static List<KalahBoard> heuristic(KalahBoard board){
    List<KalahBoard> list = new LinkedList<>();
    
    for(KalahBoard poss_action : board.possibleActions()){
      if(poss_action.isBonus())
        list.add(0, poss_action);
      else
        list.add(poss_action);
    }
    
    return list;
  }
}
