import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawUtils {
	private DrawUtils() {
		//Creating a static class meaning that it just contains static variables
	}
	public static int getMessageWidth(String message, Font font, Graphics2D g) {
		//Take in the message and output the width the text
		g.setFont(font);
		//Created a rectangle which includes the message
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
		return (int) bounds.getWidth();
	}
	
	public static int getMessageHeight(String message, Font font, Graphics2D g) {
		g.setFont(font);
		if (message.length() == 0) return 0;
		TextLayout tl = new TextLayout(message, font, g.getFontRenderContext());
		return (int) tl.getBounds().getHeight();
		
	}
}
