import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Marker {
	public static final String img_file = "marker.png";
	public static final int WIDTH = 75;
	public static final int HEIGHT = 60;
	public static final int INIT_X = 10;
	public static final int INIT_Y = 25;

	private static BufferedImage img;
	
	public int pos_x;
	public int index;
	
	public Marker() {
		super();
		pos_x = INIT_X;
		index = 0;
		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));				
			}
		}
		catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
			}
		}

	public void draw(Graphics g) {
		g.drawImage(img, pos_x, INIT_Y, WIDTH, HEIGHT, null);
	}
}
