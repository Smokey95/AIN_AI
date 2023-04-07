package kalah;

/**
 * Hauptprogramm für KalahMuster.
 * @since 29.3.2021
 * @author oliverbittel
 */
public class Kalah {
	
	private static final String ANSI_BLUE = "\u001B[34m";

	/**
	 *
	 * @param args wird nicht verwendet.
	 */
	public static void main(String[] args) {
		//testExample();
		//testHHGame();
		//testHumanMinMaxGame();
		//testMiniMaxWithGivenBoard();
		//testAlphaBetaWithGivenBoard();
		//testAlphaBetaHeuristicWithGivenBoard();
		//testHAGame();
		testAAGame();
	}
	
	/**
	 * Beispiel von https://de.wikipedia.org/wiki/Kalaha
	 */
	public static void testExample() { 
		KalahBoard kalahBd = new KalahBoard(new int[]{5,3,2,1,2,0,0,4,3,0,1,2,2,0}, 'B');
		kalahBd.print();
		
		System.out.println("B spielt Mulde 11");
		kalahBd.move(11);
		kalahBd.print();
		
		System.out.println("B darf nochmals ziehen und spielt Mulde 7");
		kalahBd.move(7);
		kalahBd.print();
	}
	
	/**
	 * Mensch gegen Mensch
	 */
	public static void testHHGame() {
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			int action = kalahBd.readAction();
			kalahBd.move(action);
			kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
	
	/**
	 * Mensch gegen KI
	 */
	public static void testHAGame(){
		
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			
			if (kalahBd.getCurPlayer() == 'A') {
				System.out.println("AI ist am Zug...");
				KalahBoard best_action = AlphaBetaPruningHeuristic.alphaBetaSearch(kalahBd, 16);
				int action_index = best_action.getLastPlay();
				System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + AlphaBetaPruningHeuristic.getCallCounter() + "]");
				kalahBd.move(action_index);
				kalahBd.print();
			} else {
				int action = kalahBd.readAction();
				kalahBd.move(action);
				kalahBd.print();
			}
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	/**
	 * KI gegen KI
	 */
	public static void testAAGame(){
		
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			
			if (kalahBd.getCurPlayer() == 'A') {
				System.out.println("AI A ist am Zug...");
				try{
					Thread.sleep(300);
				} catch (Exception e) {
				}
				KalahBoard best_action = AlphaBetaPruningHeuristic.alphaBetaSearch(kalahBd, 12);
				int action_index = best_action.getLastPlay();
				System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + AlphaBetaPruningHeuristic.getCallCounter() + "]");
				kalahBd.move(action_index);
				kalahBd.print();
			} else {
				System.out.println("AI B ist am Zug...");
				try{
					Thread.sleep(300);
				} catch (Exception e) {
				}
				KalahBoard best_action = MiniMax.maxActions(kalahBd, 8);
				int action_index = best_action.getLastPlay();
				System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + MiniMax.getCallCounter() + "]");
				kalahBd.move(action_index);
				kalahBd.print();
			}
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * Testmethode für den Minimax-Algorithmus
	 */
	public static void testMiniMaxWithGivenBoard() {
		
		KalahBoard kalahBd = new KalahBoard(new int[]{2, 0, 4, 3, 2, 0, 0, 1, 0, 1, 3, 2, 1, 0}, 'A');
		// A ist am Zug und kann aufgrund von Bonuszügen 8-aml hintereinander ziehen!
		// A muss deutlich gewinnen!
		kalahBd.print();

		while (!kalahBd.isFinished()) {
				int action;
				if (kalahBd.getCurPlayer() == 'A') {
						KalahBoard best_action = MiniMax.maxActions(kalahBd, 16);
						int action_index = best_action.getLastPlay();
						System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + MiniMax.getCallCounter() + "]");
				}
				action = kalahBd.readAction();
				kalahBd.move(action);
				kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
	
	public static void testAlphaBetaWithGivenBoard() {
		
		KalahBoard kalahBd = new KalahBoard(new int[]{2, 0, 4, 3, 2, 0, 0, 1, 0, 1, 3, 2, 1, 0}, 'A');
		// A ist am Zug und kann aufgrund von Bonuszügen 8-aml hintereinander ziehen!
		// A muss deutlich gewinnen!
		kalahBd.print();

		while (!kalahBd.isFinished()) {
				int action;
				if (kalahBd.getCurPlayer() == 'A') {
						KalahBoard best_action = AlphaBetaPruning.alphaBetaSearch(kalahBd, 16);
						int action_index = best_action.getLastPlay();
						System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + AlphaBetaPruning.getCallCounter() + "]");
				}
				action = kalahBd.readAction();
				kalahBd.move(action);
				kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
	
	public static void testAlphaBetaHeuristicWithGivenBoard() {
		
		KalahBoard kalahBd = new KalahBoard(new int[]{2, 0, 4, 3, 2, 0, 0, 1, 0, 1, 3, 2, 1, 0}, 'A');
		// A ist am Zug und kann aufgrund von Bonuszügen 8-aml hintereinander ziehen!
		// A muss deutlich gewinnen!
		kalahBd.print();

		while (!kalahBd.isFinished()) {
				int action;
				if (kalahBd.getCurPlayer() == 'A') {
						KalahBoard best_action = AlphaBetaPruningHeuristic.alphaBetaSearch(kalahBd, 16);
						int action_index = best_action.getLastPlay();
						System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + AlphaBetaPruningHeuristic.getCallCounter() + "]");
				}
				action = kalahBd.readAction();
				kalahBd.move(action);
				kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
}
