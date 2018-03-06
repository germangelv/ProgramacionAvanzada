package Game;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Balas {
	public static int speedX = 12;
	public static int speedY = 12;

	public static final int ancho = 10;
	public static final int largo = 10;

	private int x, y;
	Direccion direccion;

	private boolean aliado;
	private boolean vivo = true;

	private TankClient tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] imagenBala = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();

	static {
		imagenBala = new Image[] { tk.getImage(Balas.class.getResource("Images/missile.gif")),
				tk.getImage(Balas.class.getResource("Images/missile.gif")),
				tk.getImage(Balas.class.getResource("Images/missile.gif")),
				tk.getImage(Balas.class.getResource("Images/missile.gif")), };

		imgs.put("L", imagenBala[0]);

		imgs.put("U", imagenBala[1]);

		imgs.put("R", imagenBala[2]);

		imgs.put("D", imagenBala[3]);

	}

	public Balas(int x, int y, Direccion dir) {
		this.x = x;
		this.y = y;
		this.direccion = dir;
	}

	public Balas(int x, int y, boolean aliado, Direccion dir, TankClient tc) {
		this(x, y, dir);
		this.aliado = aliado;
		this.tc = tc;
	}

	private void move() {

		switch (direccion) {
		case L:
			x -= speedX;
			break;

		case U:
			y -= speedY;
			break;

		case R:
			x += speedX;
			break;

		case D:
			y += speedY;
			break;

		case STOP:
			break;
		}

		if (x < 0 || y < 0 || x > TankClient.Fram_width || y > TankClient.Fram_length) {
			vivo = false;
		}
	}

	public void draw(Graphics g) {
		if (!vivo) {
			tc.Balas.remove(this);
			return;
		}

		switch (direccion) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;

		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;

		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;

		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		default:
			break;
		}

		move();
	}

	public boolean isvivo() {
		return vivo;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, ancho, largo);
	}

	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) {
				
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) {

		if (this.vivo && this.getRect().intersects(t.getRect()) && t.isLive() && this.aliado != t.isGood()) {

			ExplosionTanque e = new ExplosionTanque(t.getX(), t.getY(), tc);
			tc.ExplosionTanques.add(e);
			
		    try {
			      File file = new File("crash.wav");
			      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			      Clip clip = AudioSystem.getClip();
			      clip.open(stream);
			      clip.start();
			     // clip.loop(Clip.LOOP_CONTINUOUSLY);
			    } 
			    catch (Exception ex) {
			        System.out.println(ex.getMessage());
			    }
		    t.sumarPuntaje();
			if (t.isGood()) {
				t.setLife(t.getLife() - 50);
				if (t.getLife() <= 0)
					t.setLive(false);
				
			} else {
				
				t.setLive(false);
			}
			//Bala muere porque cumplio su objetivo
			this.vivo = false;

			return true;
		}
		return false;
	}

	public boolean hitPared(ParedNormal w) {
		if (this.vivo && this.getRect().intersects(w.getRect())) {
			this.vivo = false;
			this.tc.ParedLadrillo.remove(w);
			this.tc.ParedAguila.remove(w);
			return true;
		}
		return false;
	}

	public boolean hitBala(Balas w) {
		if (this.vivo && this.getRect().intersects(w.getRect())) {
			this.vivo = false;
			this.tc.Balas.remove(w);
			return true;
		}
		return false;
	}

	public boolean hitPared(ParedMetal w) {
		if (this.vivo && this.getRect().intersects(w.getRect())) {
			this.vivo = false;
			return true;
		}
		return false;
	}

	public boolean hitAguila() {
		if (this.vivo && this.getRect().intersects(tc.home.getRect())) {
			this.vivo = false;
			//Termina el cliente
			this.tc.home.setvivo(false);
			return true;
		}
		return false;
	}
}