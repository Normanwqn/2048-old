import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
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
	public static final Font main = new Font("Clear Sans", Font.PLAIN, 20);
	private Thread game; //Game Thread
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//Double Buffering 
	
	private long startTime;
	private long timeElapsed;
	private boolean set;
	private boolean running;
	
	public Game() {
		setFocusable(true); //Being able to receive input
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		
	}
	
	public void update() {
		if (Keyboard.pressed[KeyEvent.VK_SPACE]) {
			System.out.println("Hit Space");
		}
		if (Keyboard.pressed[KeyEvent.VK_RIGHT]) {
			System.out.println("RIGHT");
		}
		Keyboard.update();
	}
	public void render() {
		//Graphics object implements how the image is drawn 
		//What needs to be drawn is stored in "image"
		//
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Render Board
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
		int update = 0;
		long fpsTimer = System.currentTimeMillis();
		//How many nanoseconds there are between updates
		double nsPerUpdate = 10^9/60;
		
		
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
				update ++;
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
			System.out.printf("%d fps %d upates",fps, update);
			System.out.println();
			fps = 0;
			update = 0;
			fpsTimer += 1000;
		}
	}

}
