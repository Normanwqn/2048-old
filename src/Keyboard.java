import java.awt.event.KeyEvent;

//Static Keyboard Class
public class Keyboard {
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];
	public static boolean[] AI_Input = new boolean[256];
	private Keyboard() {
		
	}
	public static void update() {
		/*for (int i = 0; i < 256; i++) {
			prev[i] = pressed[i];
		}*/
		/*prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
		prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
		prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
		prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];*/
		for (int i = 0; i < 4; i++) {
			if (i == 0) prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
			if (i == 1) prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
			if (i == 2) prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
			if (i == 3) prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
		}
		for (int i = 0; i < 256; i++) {
			AI_Input[i] = false;
		}
		String AINext = GameBoard.agent.nextMove();
		
		if (AINext != null) {
			switch (AINext) {
			case "LEFT": AI_Input[KeyEvent.VK_LEFT] = true; 
				break;
			case "RIGHT": AI_Input[KeyEvent.VK_RIGHT] = true;
				break;
			case "UP": AI_Input[KeyEvent.VK_KP_UP] = true;
				break;
			case "DOWN": AI_Input[KeyEvent.VK_DOWN] = true;
			}
		}
	}
	
	public static void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}
	
	public static void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;
	}
	
	public static boolean typed(int keyEvent) {
		return (!pressed[keyEvent] && prev[keyEvent]) || AI_Input[keyEvent];
		
	}
	
	
	//public static 
}
