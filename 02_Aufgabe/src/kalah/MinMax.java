package kalah;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * Class MinMax for min-max search required for first task
 * @author Smokey95
 */
public class MinMax {
  
  private static int callCounter = 0;
  
  public static KalahBoard maxActions(KalahBoard board, int maxDepth){
		
		KalahBoard action       = null;
    int v                   = Integer.MIN_VALUE;
    
    callCounter = 0;
		
		if(checkOnFinished(board, maxDepth))
			return board;
		
		for(KalahBoard a : board.possibleActions()){
			
      int v1 = v;
       
      if(a.isBonus())
        v = Integer.max(v, maxValue(a, maxDepth - 1));
      else
        v = Integer.max(v, minValue(a, maxDepth - 1));
			
			if(v1 != v)
        action = a;
		}

		return action;
	}
	
	private static int minValue(KalahBoard board, int maxDepth){

    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getAKalah();
		
		int curr_max_val = Integer.MAX_VALUE;
		
		for(KalahBoard curr_board : board.possibleActions()){
      if(curr_board.isBonus())
        curr_max_val = Integer.min(curr_max_val, minValue(curr_board, maxDepth - 1));
      else
        curr_max_val = Integer.min(curr_max_val, maxValue(curr_board, maxDepth - 1));
		}
		
		return curr_max_val;
	}
	
	private static int maxValue(KalahBoard board, int maxDepth){
		
    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getBKalah();
		
		int curr_min_val = Integer.MIN_VALUE;
		
		for(KalahBoard a : board.possibleActions()){
      if(a.isBonus())
        curr_min_val = Integer.max(curr_min_val, maxValue(a, maxDepth - 1));
      else
        curr_min_val = Integer.max(curr_min_val, minValue(a, maxDepth - 1));
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
}
