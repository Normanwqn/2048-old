import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Tile {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
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
	
	public int getValue() {
		return value;
	}
	public void drawImage() {
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		 /*RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		 RenderingHints ab = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);*/
		 g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	      g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	      g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
	      g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	      g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	      g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);   
		text = Color.WHITE;
		if ((value == 2) || (value == 4)) {
			text = new Color(118,110,102);
		}
		switch (value) {
			case 2: background = new Color(235,228,219);//2
				break;
			case 4: background = new Color(234,223,202); //4
				break;
			case 8: background = new Color(232,179,129);  //8
				break;
			case 16: background = new Color(232,153,108); //16
				break;
			case 32: background = new Color(226,132,102); //32
				break;
			case 64: background = new Color(215,99,65); //64
				break;
			case 128: background = new Color(239,216,124); //128
				break;
			case 256: background = new Color(234,209,101); //256
				break;
			case 512: background = new Color(224,192,75); //512
				break;
			case 1024: background = new Color(229,195,90); //1024
				break;
			case 2048: background = new Color(230,196,66); //2048
				break;
			case 4096: background = new Color(229,69,203); //4096
				break;
			case 8192: background = new Color(219,89,93); //8192
				break;
			case 16384: background = new Color(229,71,159); //16384
				break;
			case 32768: background = new Color(165,181,84); //32768
				break;
			case 65536: background = new Color(109,159,216); //65536
				break;
			case 131072: background = new Color(52,124,183); //131072
				break;
			default: background = Color.BLACK;
				break;
		}
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(background);
		System.out.println(background.getBlue());
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		font = Game.main;
		g.setFont(font);
		//Getting the width
		int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g)/2;
		//Getting the height of the text; Need to change to +
		int drawY = HEIGHT /2 + DrawUtils.getMessageHeight(""+value, font, g)/2;
		g.setColor(text);
		g.drawString(""+value, drawX, drawY);
		g.dispose();
	}
	public void update() {
		
	}
	public void render(Graphics2D g) {
		g.drawImage(tileImage, x, y, null);
	}
}