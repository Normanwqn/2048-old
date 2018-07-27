import java.awt.event.KeyEvent;

//Static Keyboard Class
public class Keyboard {
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];
	
	private Keyboard() {
		
	}
	public static void update() {
		for (int i = 0; i < 256; i++) {
			prev[i] = pressed[i];
		}
	}
	
	public static void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}
	
	public static void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;
	}
	
	public static boolean typed(int keyEvent) {
		return !pressed[keyEvent] && prev[keyEvent];
	}
	
	//public static 
}
