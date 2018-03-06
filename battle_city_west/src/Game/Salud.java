package Game;

import java.awt.*;
import java.util.Random;

public class Salud {
	
	public static final int width = 30;
	public static final int length = 30;

	private int x, y;
	TankClient tc;
	private static Random r = new Random();

	int step = 0; 
	private boolean vivo = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bloodImags = null;
	static {
		bloodImags = new Image[] { tk.getImage(ParedNormal.class
				.getResource("Images/hp.gif")), };
	}

	private int[][] poition = { { 900, 200 },
								{ 700, 200 },
								{ 500, 200 },
								{ 300, 200 },
								{ 100, 200 },
								{ 100, 300 },
								{ 300, 300 },
								{ 500, 300 },
								{ 700, 300 },
								{ 900, 300 },
								{ 100, 500 },
								{ 300, 500 },
								{ 500, 500 },
								{ 700, 500 },
								{ 900, 300 },
								};

	public void draw(Graphics g) {
		if (r.nextInt(100) > 98) {
			this.vivo = true;
			move();
		}
		if (!vivo)
			return;
		g.drawImage(bloodImags[0], x, y, null);

	}

	private void move() {
		step = (int)(r.nextDouble() * poition.length + 0);
		x = poition[step][0];
		y = poition[step][1];
	}

	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}

	public boolean isvivo() {
		return vivo;
	}

	public void setvivo(boolean vivo) {  
		this.vivo = vivo;
	}

}
