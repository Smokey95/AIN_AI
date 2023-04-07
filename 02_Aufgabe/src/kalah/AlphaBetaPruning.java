package kalah;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class AlphaBetaPruning {
  
  public static int alphaBetaSearch(KalahBoard board, int maxDepth){
		
		KalahBoard action = null;
		List<KalahBoard> boards = board.possibleActions();
		
		if(maxDepth == 0 || board.isFinished()){
			return 0;
		}
		
		int v = Integer.MIN_VALUE;
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
		
		for(KalahBoard a : board.possibleActions()){
			
      //int v1 = 
			
			if(v1 > v){
				v = v1;
				action = a;
			}
		}
		
		int actionIndex = boards.indexOf(action);
		return actionIndex;
	}
	
	private static int minValue(KalahBoard board, int maxDepth){
		// Prüfen ob tiefensuche grenzwert erreicht oder endzustand
		if(maxDepth == 0 || board.isFinished()){
			return board.getAKalah(); //Why AKalah?
		}
		
		int v = Integer.MAX_VALUE;
		
		for(KalahBoard a : board.possibleActions()){
			v = Integer.min(v, maxValue(a, maxDepth - 1));
		}
		
		return v;
	}
	
	private static int maxValue(KalahBoard board, int maxDepth){
		
		// Prüfen ob tiefensuche grenzwert erreicht oder endzustand
		if(maxDepth == 0 || board.isFinished()){
			return board.getBKalah(); //Why BKalah?
		}
		
		int v = Integer.MIN_VALUE;
		
		for(KalahBoard a : board.possibleActions()){
			v = Integer.max(v, minValue(a, maxDepth - 1));
		}
		
		return v;
	}
}
