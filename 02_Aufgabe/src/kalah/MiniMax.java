package kalah;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * Class MinMax for min-max search required for first task
 * @author Smokey95
 */
public class MiniMax {
  
  private static int callCounter = 0;
  
  public static KalahBoard maxActions(KalahBoard board, int maxDepth){
		
		KalahBoard action       = null;
    int curr_max_count      = Integer.MIN_VALUE;
    
    callCounter = 0;
		
		if(checkOnFinished(board, maxDepth))
			return board;
		
		for(KalahBoard poss_action : board.possibleActions()){
			
      int tmp_max_count = curr_max_count;
       
      if(poss_action.isBonus())
        curr_max_count = Integer.max(curr_max_count, maxValue(poss_action, maxDepth - 1));
      else
        curr_max_count = Integer.max(curr_max_count, minValue(poss_action, maxDepth - 1));
			
			if(tmp_max_count != curr_max_count)
        action = poss_action;
		}

		return action;
	}
	
	private static int minValue(KalahBoard board, int maxDepth){

    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getAKalah();
		
		int curr_max_val = Integer.MAX_VALUE;
		
		for(KalahBoard poss_action : board.possibleActions()){
      if(poss_action.isBonus())
        curr_max_val = Integer.min(curr_max_val, minValue(poss_action, maxDepth - 1));
      else
        curr_max_val = Integer.min(curr_max_val, maxValue(poss_action, maxDepth - 1));
		}
		
		return curr_max_val;
	}
	
	private static int maxValue(KalahBoard board, int maxDepth){
		
    callCounter++;
    
		if(checkOnFinished(board, maxDepth))
			return board.getBKalah();
		
		int curr_min_val = Integer.MIN_VALUE;
		
		for(KalahBoard poss_action : board.possibleActions()){
      if(poss_action.isBonus())
        curr_min_val = Integer.max(curr_min_val, maxValue(poss_action, maxDepth - 1));
      else
        curr_min_val = Integer.max(curr_min_val, minValue(poss_action, maxDepth - 1));
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
