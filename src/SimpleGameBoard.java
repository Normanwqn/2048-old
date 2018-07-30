
public class SimpleGameBoard {
	public static final int ROWS = 4;
	public static final int COLS = 4;
	public final int startingTiles = 2;
	public Tile[][] board;
	public boolean dead; //end of game
	public boolean won; //Won the game
	public int score = 0;
	public int highScore = 0; //Highest Score
	
	public void printBoard() {
		for (Tile[] a: board) {
			for (Tile b: a) {
				if (b != null) {
					System.out.print(b.getValue() + " ");
				} else {
					System.out.print("0 ");
				}
				
			}
			System.out.println();
		}
	}
	
}
