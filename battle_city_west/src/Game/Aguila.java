package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Aguila {
	private int x, y;
	private TankClient tc;
	public static final int width = 43, length = 43;
	private boolean vivo = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(ParedNormal.class.getResource("Images/home.png")), };
	}

	public Aguila(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void gameOver(Graphics g) {
		tc.tanks.clear();
		tc.ParedMetal.clear();
		tc.ParedAguila.clear();
		tc.ExplosionTanques.clear();
		tc.AguaRio.clear();
		tc.Pastos.clear();
		tc.Balas.clear();
		tc.homeTank1.setLive(false);
		tc.homeTank2.setLive(false);
		tc.homeTank3.setLive(false);
		tc.homeTank4.setLive(false);
		Color c = g.getColor();
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.setFont(f);
		g.setColor(c);
	}

	public void draw(Graphics g) {

		if (vivo) {
			g.drawImage(homeImags[0], x, y, null);

			for (int i = 0; i < tc.ParedAguila.size(); i++) {
				ParedNormal w = tc.ParedAguila.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g);
		}
	}

	public boolean isvivo() {
		return vivo;
	}

	public void setvivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}
}