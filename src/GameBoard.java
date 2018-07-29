import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {
	public static final int ROWS = 4;
	public static final int COLS = 4;
	private final int startingTiles = 2;
	private Tile[][] board;
	private boolean dead; //end of game
	private boolean won; //Won the game
	private BufferedImage gameBoard;
	private BufferedImage finalBoard;
	private int x;
	private int y;
	private boolean hasStarted;
	private static int SPACING = 10;
	//There is spacing before the first tile, thus COLS+1; 
	public static int BOARD_WIDTH = (COLS + 1)* SPACING + COLS * Tile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS+1) * SPACING + ROWS* Tile.HEIGHT;
	
	public GameBoard(int x, int y) {
		this.x = x;
		this.y = y;
		board = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		createBoardImage(); //Draw the background of the Board
		start();
	}
	public void createBoardImage() {
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
	    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE); 
		g.setColor(new Color(185,173,162));
		g.fillRoundRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
		g.setColor(new Color(202,193,181));
		
		for (int row = 0; row < ROWS; row ++) {
			for (int col = 0; col < COLS; col++) {
				int x_rect = SPACING + SPACING * col + Tile.WIDTH*col;
				int y_rect = SPACING + SPACING * row + Tile.HEIGHT*row;
				g.fillRoundRect(x_rect, y_rect, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
			}
		}
	}
	private void start() {
		for (int i = 0; i < startingTiles; i++) {
			spawnRandom();
		}
	}
	private void spawnRandom() {
		Random random = new Random();
		boolean notValid = true;
		while (notValid) {
			int location= random.nextInt(ROWS*COLS);
			int row = location / ROWS;
			int col = location % COLS;
			Tile current = board[row][col];
			if (current == null) {
				int value = random.nextInt(10) < 9? 2:4;
				Tile tile = new Tile(value, getTileX(col), getTileY(row), true, true);
				board[row][col] = tile;
				notValid = false;
			}
			
		}
	}
	public int getTileX(int col) {
		return SPACING + col*Tile.WIDTH + col*SPACING;
	}
	public int getTileY(int row) {
		return SPACING + row*Tile.HEIGHT + row*SPACING;
	}
	
	//Continously rendering the board
	public void render(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	      g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	      g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
	      g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	      g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);   
	      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	      g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	      g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	      g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
	      g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	      g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	      g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	      g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE); 
		g2d.drawImage(gameBoard, 0, 0, null);
		//Draw Tiles
		for (int row = 0; row < ROWS; row ++) {
			for (int col = 0; col < COLS; col++) {
				Tile current = board[col][row];
				if (current == null) {
					continue;
				}
				current.render(g2d);
			}
		}
		//Drawing to the Screen
		g.drawImage(finalBoard,	x, y, null);
		g2d.dispose();
	}
	
	public void update() {
		checkKeys();
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				if (current == null) continue;
				current.update();
				//Reset position; Slide across the screen
				resetPosition(current, row, col);
				if (current.getValue() == 2048) {
					won = true;
				}
				
			}
		}
	}
	private void resetPosition(Tile current, int row, int col) {
		if (current == null) return; //If current does not exist
		int x = getTileX(col);
		int y = getTileY(row);
		
		int distX = current.getX() - x;
		int distY = current.getY() - y;
		if (Math.abs(distX) < Tile.SLIDE_SPEED) {
			current.setX(current.getX() - distX); //x
		} 
		if (Math.abs(distY) < Tile.SLIDE_SPEED) {
			current.setY(current.getY() - distY); //y
		}
		if (distX < 0) {
			current.setX(current.getX() + Tile.SLIDE_SPEED);
		}
		if (distY < 0) {
			current.setY(current.getY() + Tile.SLIDE_SPEED);
		}
		if (distX > 0) {
			current.setX(current.getX() - Tile.SLIDE_SPEED);
		}
		if (distY > 0) {
			current.setY(current.getY() - Tile.SLIDE_SPEED);
		}
	}
	private boolean move(int row, int col, int horizontalDirection, int verticalDirection, Direction dir) {
		boolean canMove = false;
		Tile current = board[row][col];
		if (current == null) return false;
		boolean move = true;
		int newCol = col;
		int newRow = row;
		while (move) {
			//Predict the next position
			newCol += horizontalDirection;
			newRow += verticalDirection;
			if (checkOutOfBounds(dir, newRow, newCol)) {
				break; //break if out of bounds 
			}
			if (board[newRow][newCol] == null) { //move the tile if it is null
				board[newRow][newCol] = current;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
				canMove = true;
			}
			else if ((board[newRow][newCol].getValue() == current.getValue()) && (board[newRow][newCol].canCombine())){ //Combine 
				board[newRow][newCol].setCanCombine(false);
				board[newRow][newCol].setValue(board[newRow][newCol].getValue()*2);
				canMove = true;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
				board[newRow][newCol].setCombineAnimation(true);
				//add to score 
			} else {
				move = false;
			}
		}
		return canMove;
	}
	private boolean checkOutOfBounds(Direction dir, int row, int col) {
		// TODO Auto-generated method stub
		if (dir == Direction.LEFT) {
			return col<0;
		}
		else if (dir == Direction.RIGHT) {
			return col> COLS-1;
		}
		else if (dir == Direction.UP) {
			return row <0;
		}
		else if (dir == Direction.DOWN) {
			return row > ROWS-1;
		}
		return false;
	}
	private void moveTiles(Direction dir) {
		boolean canMove = false; //Whether the tiles can move or not
		int horizontalDirection = 0;
		int verticalDirection = 0;
		if (dir == Direction.LEFT) {
			horizontalDirection = -1;
			System.out.println("Moving Left");
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					}else { move(row, col, horizontalDirection, verticalDirection, dir);}
				} 
			}
		}
		//The direction of update needs to be changed. Update from the right
		else if (dir == Direction.RIGHT) {
			horizontalDirection = 1;
			System.out.println("Moving Right");
			for (int row = 0; row < ROWS; row++) {
				for (int col = COLS-1; col >=0; col--) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					} else move(row, col, horizontalDirection, verticalDirection, dir);
				} 
			}
		}
		else if (dir == Direction.UP) {
			verticalDirection = -1;
			System.out.println("Moving Up");
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					} else move(row, col, horizontalDirection, verticalDirection, dir);
				} 
			}
		}
		else if (dir == Direction.DOWN) {
			verticalDirection = 1;
			System.out.println("Moving Down");
			for (int row = ROWS -1; row >= 0; row--) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					} else move(row, col, horizontalDirection, verticalDirection, dir);
				} 
			}
		} else {
			System.out.println(dir + "is not a valid direction.");
		}
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				if (current == null) {
					continue;
				}
				current.setCanCombine(true);
			}
		}
		if (canMove) {
			spawnRandom();
			//check Dead
			checkDead();
		}
		printBoard();
	}
	private void checkDead() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == null) {
					return;
				} //There is still space
				if (checkSurroundingTiles(row,col,board[row][col])) {
					return; //Check the surrounding Tiles to see whether it is able to combine or not
				}
			}
		}
		dead = true;
		//setHighScore; Timer...
	}
	private boolean checkSurroundingTiles(int row, int col, Tile current) {
		if (row >0) {
			Tile check = board[row-1][col]; //Move up a row
			if (check == null) return true;
			if (current.getValue() == check.getValue()) return true;
		}
		if (row < ROWS-1) {
			Tile check = board[row+1][col]; //Moving down a row
			if ((check == null) ||(current.getValue() == check.getValue())) return true;
		}
		if (col > 0) {
			Tile check = board[row][col-1]; //Move to the left
			if (check == null) return true;
			if (current.getValue() == check.getValue()) return true;
		}
		if (col < COLS-1) {
			Tile check = board[row][col+1]; //Move to the right
			if (check == null) return true;
			if (current.getValue() == check.getValue()) return true;
		}
		return false;
	}
	private void checkKeys() {
		if (Keyboard.typed(KeyEvent.VK_UP)) {
			System.out.println("Checked Pressed Up");
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			System.out.println("Checked Pressed DOWN");
		}
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			System.out.println("Checked Pressed LEFT");
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			System.out.println("Checked Pressed RIGHT ");
		}
		if ((Keyboard.typed(KeyEvent.VK_LEFT)) || (Keyboard.typed(KeyEvent.VK_RIGHT)) || (Keyboard.typed(KeyEvent.VK_UP)) || (Keyboard.typed(KeyEvent.VK_DOWN))) {
			if (!hasStarted) hasStarted = true; 
		}
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			System.out.println("Left");
			moveTiles(Direction.LEFT);
			if (!hasStarted) hasStarted = true; 
			System.out.println("Left");
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			System.out.println("RIGHT");
			moveTiles(Direction.RIGHT);
			if (!hasStarted) hasStarted = true; 
			
		}
		if (Keyboard.typed(KeyEvent.VK_UP)) {
			System.out.println("UP");
			moveTiles(Direction.UP);
			if (!hasStarted) hasStarted = true; 
			
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			System.out.println("DOWN");
			moveTiles(Direction.DOWN);
			if (!hasStarted) hasStarted = true; 
			
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
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
