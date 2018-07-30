import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 630;
	public static final Font main = new Font("Clear Sans", Font.PLAIN, 40);
	private Thread game; //Game Thread
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//Double Buffering 
	private GameBoard board;
	//public static AI agent;
	
	private long startTime;
	private long timeElapsed;
	private boolean set;
	private boolean running;
	public Game() {
		setFocusable(true); //Being able to receive input
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
		board = new GameBoard(WIDTH/2 - GameBoard.BOARD_WIDTH/2, HEIGHT - GameBoard.BOARD_HEIGHT -10);
		
	}
	
	public void update() {
		if (Keyboard.typed(KeyEvent.VK_UP)) {
			System.out.println("Pressed Up");
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			System.out.println("Pressed DOWN");
		}
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			System.out.println("Pressed LEFT");
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			System.out.println("Pressed RIGHT ");
		}
		board.update();
		Keyboard.update();
		
		
	}
	public void render() {
		//Graphics object implements how the image is drawn 
		//What needs to be drawn is stored in "image"
		//
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		 g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		 g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		 g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		 g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
		 g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		 g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		 g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		 g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE); 
	  
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Render Board
		board.render(g);
		g.dispose();
		
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Keyboard.keyReleased(e);
	}
	public synchronized void start() {
		if (running) return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	public synchronized void stop() {
		if (running) return;
		running = false;
		System.exit(0);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int fps = 0;
		int updates = 0;
		long fpsTimer = System.currentTimeMillis();
		//How many nanoseconds there are between updates
		double nsPerUpdate = 1000000000.0/60;
		
		
		//Last Update Time in nanoseconds
		double then = System.nanoTime();
		double unprocessed = 0; //How many updates there needs to be
		while (running) {
			double now = System.nanoTime();
			unprocessed += (now - then) / nsPerUpdate;
			then = now;
			
			//Determine whether rendering should be done
			boolean shouldRender = false;
			//Update Queue
			while (unprocessed >= 1) {
				updates ++;
				update();
				unprocessed --;
				shouldRender = true;
			}
			if (shouldRender) {
				fps ++;
				render();
				shouldRender = false;
			} else {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//FPS Timer 
		//If the current time is larger than fpsTimer by 1s
		if (System.currentTimeMillis() - fpsTimer >1000) {
			System.out.printf("%d fps %d upates",fps, updates);
			System.out.println();
			fps = 0;
			updates = 0;
			fpsTimer += 1000;
		}
	}

}
