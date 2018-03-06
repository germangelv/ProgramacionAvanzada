package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ExplosionTanque {
	private int x, y;
	private boolean vivo = true;
	private TankClient tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs = { 
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast1.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast2.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast3.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast4.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast5.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast6.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast7.gif")),
			tk.getImage(ExplosionTanque.class.getResource("images/Explosion/blast8.gif")) };
	int step = 0;

	public ExplosionTanque(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) {

		if (!vivo) {
			tc.ExplosionTanques.remove(this);
			return;
		}
		if (step == imgs.length) {
			vivo = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
	}
}