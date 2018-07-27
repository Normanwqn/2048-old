import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 100;
	public static final int SLIDE_SPEED = 20;
	public static final int ARC_WIDTH = 10;
	public static final int ARC_HEIGHT = 10;
	
	private int value;
	private BufferedImage tileImage;
	private Color background;
	private Color text;
	private Font font;
	private int x;
	private int y;
	
	public Tile(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}
	
	public void drawImage() {
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		text = Color.WHITE;
		if ((value == 2) || (value == 4)) {
			text = new Color(118,110,102);
		}
		switch (value) {
			case 2: background = new Color(235,228,219); //2
			case 4: background = new Color(234,223,202); //4
			case 8: background = new Color(232,179,129);  //8
			case 16: background = new Color(232,153,108); //16
			case 32: background = new Color(226,132,102); //32
			case 64: background = new Color(215,99,65); //64
			case 128: background = new Color(239,216,124); //128
			case 256: background = new Color(234,209,101); //256
			case 512: background = new Color(224,192,75); //512
			case 1024: background = new Color(229,195,90); //1024
			case 2048: background = new Color(230,196,66); //2048
			case 4096: background = new Color(229,69,203); //4096
			case 8192: background = new Color(219,89,93); //8192
			case 16384: background = new Color(229,71,159); //16384
			case 32768: background = new Color(165,181,84); //32768
			case 65536: background = new Color(109,159,216); //65536
			case 131072: background = new Color(52,124,183); //131072
			default: background = Color.BLACK;
		}
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(background);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		font = Game.main;
		g.setFont(font);
		//Getting the width
		int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g)/2;
		//Getting the height of the text; Need to change to +
		int drawY = HEIGHT /2 + DrawUtils.getMessageHeight(""+value, font, g)/2;
		g.drawString(""+value, drawX, drawY);
		g.dispose();
	}
}