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
		testMiniMaxAndAlphaBetaWithGivenBoard();
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
	 * KI gegen Mensch
	 */
	public static void testHumanMinMaxGame() {
		
		KalahBoard kalahBd = new KalahBoard(new int[]{2, 0, 4, 3, 2, 0, 0, 1, 0, 1, 3, 2, 1, 0}, 'A');
		//kalahBd.setAI();
		
		//KalahBoard kalahBd = new KalahBoard(true);
		
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			int action = kalahBd.readAction();
			kalahBd.move(action);
			kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
	
	public static void testMiniMaxAndAlphaBetaWithGivenBoard() {
		
		KalahBoard kalahBd = new KalahBoard(new int[]{2, 0, 4, 3, 2, 0, 0, 1, 0, 1, 3, 2, 1, 0}, 'A');
		// A ist am Zug und kann aufgrund von Bonuszügen 8-aml hintereinander ziehen!
		// A muss deutlich gewinnen!
		kalahBd.print();

		while (!kalahBd.isFinished()) {
				int action;
				if (kalahBd.getCurPlayer() == 'A') {
						KalahBoard best_action = MinMax.maxActions(kalahBd, 13);
						int action_index = best_action.getLastPlay();
						System.out.println("Best action: %2d".formatted(action_index) + "  [Anzahl Aufrufe: " + MinMax.getCallCounter() + "]");
				}
				action = kalahBd.readAction();
				kalahBd.move(action);
				kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	
}
