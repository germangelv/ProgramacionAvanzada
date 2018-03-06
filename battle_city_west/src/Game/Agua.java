package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Agua {
	public static final int riverAncho = 30;
	public static final int riverLargo = 30;
	private int x, y;
	TankClient tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] ImagenRio = null;

	static {
		ImagenRio = new Image[] { tk.getImage(ParedNormal.class.getResource("Images/river.gif")), };
	}

	public Agua(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		g.drawImage(ImagenRio[0], x, y, null);
	}

	/*
	public static int getRiverWidth() {
		return riverAncho;
	}

	public static int getRiverLength() {
		return riverLargo;
	}
	*/

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

	public Rectangle getRect() {
		return new Rectangle(x, y, riverAncho, riverLargo);
	}
}