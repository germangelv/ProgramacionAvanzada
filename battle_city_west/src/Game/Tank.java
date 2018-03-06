package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Tank {
	public static int speedX = 6, speedY = 6;
	public static int count = 0;
	public static final int width = 35, length = 35;
	private Direccion direction = Direccion.STOP;
	private Direccion Kdirection = Direccion.U;
	TankClient tc;
	private int player = 0;
	private boolean aliado;
	private int x, y;
	private int oldX, oldY;
	private boolean vivo = true;
	private int salud = 200;
	private int rate = 1;
	private static Random r = new Random();
	private int step = r.nextInt(10) + 5;
	public int puntaje = 0;

	private boolean bL = false, bU = false, bR = false, bD = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImags = null;
	static {

		tankImags = new Image[] {
				// Enemigos
				// Nivel 1
				// POSICION =0,1,2,3
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tankD.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tankU.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tankL.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tankR.gif")),
				// Nivel 2
				// POSICION =4,5,6,7
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tank2D.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tank2U.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tank2L.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/tank2R.gif")),

				// Nivel 3
				// POSICION =8,9,10,11
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/Tank3D.jpg")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/Tank3U.jpg")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/Tank3L.jpg")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/Tank3R.jpg")),
				// JUGADORES
				// Player1
				// POSICION =12,13,14,15
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP1D.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP1U.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP1L.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP1R.gif")),
				// Player2
				// POSICION =16,17,18,19
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP2D.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP2U.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP2L.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP2R.gif")),
				// Player3
				// POSICION =20,21,22,23
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP3D.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP3U.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP3L.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP3R.gif")),
				// Player4
				// POSICION =24,25,26,27
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP4D.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP4U.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP4L.gif")),
				tk.getImage(ExplosionTanque.class.getResource("Images/MisTanques/TankP4R.gif")), };
	}

	public Tank(int x, int y, boolean aliado) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.aliado = aliado;
	}

	public Tank(int x, int y, boolean aliado, Direccion dir, TankClient tc, int player) {
		this(x, y, aliado);
		this.direction = dir;
		this.tc = tc;
		this.player = player;
	}

	public void draw(Graphics g) {
		if (!vivo) {
			if (!aliado) {
				tc.tanks.remove(this);
			}
			return;
		}
		// if (aliado)
		// new DrawBloodbBar().draw(g);
		switch (Kdirection) {

		case D:
			if (tc.Player1 && player == 1) {
				g.drawImage(tankImags[12], x, y, null);
			} else if (tc.Player2 && player == 2) {
				g.drawImage(tankImags[16], x, y, null);
			} else if (tc.Player3 && player == 3) {
				g.drawImage(tankImags[20], x, y, null);
			} else if (tc.Player4 && player == 4) {
				g.drawImage(tankImags[24], x, y, null);
			} // ENEMIGOS
			else if (tc.Enemigo1 && player == 5) {
				g.drawImage(tankImags[0], x, y, null);
			} else if (tc.Enemigo2 && player == 6) {
				g.drawImage(tankImags[4], x, y, null);
			} else if (tc.Enemigo3 && player == 7) {
				g.drawImage(tankImags[8], x, y, null);
			}
			break;

		case U:
			if (tc.Player1 && player == 1) {
				g.drawImage(tankImags[13], x, y, null);
			} else if (tc.Player2 && player == 2) {
				g.drawImage(tankImags[17], x, y, null);
			} else if (tc.Player3 && player == 3) {
				g.drawImage(tankImags[21], x, y, null);
			} else if (tc.Player4 && player == 4) {
				g.drawImage(tankImags[25], x, y, null);
			} // ENEMIGOS
			else if (tc.Enemigo1 && player == 5) {
				g.drawImage(tankImags[1], x, y, null);
			} else if (tc.Enemigo2 && player == 6) {
				g.drawImage(tankImags[5], x, y, null);
			} else if (tc.Enemigo3 && player == 7) {
				g.drawImage(tankImags[9], x, y, null);
			}
			break;

		case L:
			if (tc.Player1 && player == 1) {
				g.drawImage(tankImags[14], x, y, null);
			} else if (tc.Player2 && player == 2) {
				g.drawImage(tankImags[18], x, y, null);
			} else if (tc.Player3 && player == 3) {
				//222
				g.drawImage(tankImags[22], x, y, null);
			} else if (tc.Player4 && player == 4) {
				g.drawImage(tankImags[26], x, y, null);
			} // ENEMIGOS
			else if (tc.Enemigo1 && player == 5) {
				g.drawImage(tankImags[2], x, y, null);
			} else if (tc.Enemigo2 && player == 6) {
				g.drawImage(tankImags[6], x, y, null);
			} else if (tc.Enemigo3 && player == 7) {
				g.drawImage(tankImags[10], x, y, null);
			}
			break;

		case R:
			if (tc.Player1 && player == 1) {
				g.drawImage(tankImags[15], x, y, null);
			} else if (tc.Player2 && player == 2) {
				g.drawImage(tankImags[19], x, y, null);
			} else if (tc.Player3 && player == 3) {
				g.drawImage(tankImags[23], x, y, null);
			} else if (tc.Player4 && player == 4) {
				g.drawImage(tankImags[27], x, y, null);
			} // ENEMIGOS
			else if (tc.Enemigo1 && player == 5) {
				g.drawImage(tankImags[3], x, y, null);
			} else if (tc.Enemigo2 && player == 6) {
				g.drawImage(tankImags[7], x, y, null);
			} else if (tc.Enemigo3 && player == 7) {
				g.drawImage(tankImags[11], x, y, null);
			}
			break;
		default:
			break;
		}
		//MoverTanque
		move();
	}

	void move() {

		this.oldX = x;
		this.oldY = y;

		switch (direction) {
		case L:
			x -= speedX;
			if (player == 5) {
				x -= speedX + 0.5;
			}
			if (player == 6) {
				x -= speedX + 3;
			}
			if (player == 7) {
				x -= speedX + 1;
			}
			break;
		case U:
			y -= speedY;
			if (player == 5) {
				y -= speedX + 0.5;
			}
			if (player == 6) {
				y -= speedX + 3;
			}
			if (player == 7) {
				y -= speedX + 1;
			}
			break;
		case R:
			x += speedX;
			if (player == 5) {
				x += speedX + 0.5;
			}
			if (player == 6) {
				x += speedX + 3;
			}
			if (player == 7) {
				x += speedX + 1;
			}
			break;
		case D:
			y += speedY;
			if (player == 5) {
				y += speedX + 0.5;
			}
			if (player == 6) {
				y += speedX + 3;
			}
			if (player == 7) {
				y += speedX + 1;
			}
			break;
		case STOP:
			break;
		}

		if (this.direction != Direccion.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 40)
			y = 40;
		if (x + Tank.width > TankClient.Fram_width)
			x = TankClient.Fram_width - Tank.width;
		if (y + Tank.length > TankClient.Fram_length)
			y = TankClient.Fram_length - Tank.length;

		if (!aliado) {
			Direccion[] directons = Direccion.values();
			if (step == 0) {
				step = r.nextInt(12) + 3;
				int mod = r.nextInt(9);
				if (jugadorCercano()) {
					if (x == tc.homeTank1.x) {
						if (y > tc.homeTank1.y)
							direction = directons[1];
						else if (y < tc.homeTank1.y)
							direction = directons[3];
					} else if (y == tc.homeTank1.y) {
						if (x > tc.homeTank1.x)
							direction = directons[0];
						else if (x < tc.homeTank1.x)
							direction = directons[2];
					} else {
						int rn = r.nextInt(directons.length);
						direction = directons[rn];
					}
					rate = 2;
				} else if (mod == 1) {
					rate = 1;
				} else if (1 < mod && mod <= 3) {
					rate = 1;
				} else {
					int rn = r.nextInt(directons.length);
					direction = directons[rn];
					rate = 1;
				}
			}
			step--;
			if (rate == 2) {
				if (r.nextInt(40) > 35)
					this.fire();
			} else if (r.nextInt(40) > 38)
				this.fire();
		}
	}

	public boolean jugadorCercano() {
		int rx = x - 15, ry = y - 15;
		if ((x - 15) < 0)
			rx = 0;
		if ((y - 15) < 0)
			ry = 0;
		Rectangle a = new Rectangle(rx, ry, 60, 60);
		if (this.vivo && a.intersects(tc.homeTank1.getRect())) {
			return true;
		}
		return false;
	}

	public int getzone(int x, int y) {
		int tempx = x;
		int tempy = y;
		if (tempx < 85 && tempy < 300)
			return 11;
		else if (tempx > 85 && tempx < 140 && tempy > 0 && tempy < 100)
			return 9;
		else if (tempx > 85 && tempx < 140 && tempy > 254 && tempy < 300)
			return 10;
		else if (tempx > 0 && tempx < 200 && tempy > 300 && tempy < 715)
			return 12;
		else if (tempx > 140 && tempx < 400 && tempy > 0 && tempy < 150)
			return 7;
		else if (tempx > 140 && tempx < 400 && tempy > 210 && tempy < 300)
			return 8;
		else if (tempx > 400 && tempx < 500 && tempy > 0 && tempy < 300)
			return 6;
		else if (tempx > 500 && tempy > 0 && tempy < 180)
			return 5;
		else if (tempx > 500 && tempy > 180 && tempy < 300)
			return 4;
		else if (tempx > 520 && tempx < 600 && tempy > 3000 && tempy < 715)
			return 2;
		else if (tempx > 600 && tempy > 300 && tempy < 715)
			return 3;
		return 1;
	}

	public int getdirect(int a, int b) {
		if (b == 13) {

		}
		return 4;
	}

	private void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		/*
		 * Reset el Juego mal implemetado, al final en vez de crear ABC tiene que hacer un Thread
		case KeyEvent.VK_R:
			tc.Balas.clear();
			tc.Pastos.clear();
			tc.ParedLadrillo.clear();
			tc.ParedMetal.clear();
			if (tc.tanks.size() == 0) {
				for (int i = 0; i < 20; i++) {
					if (i < 9)
						tc.tanks.add(new Tank(150 + 70 * i, 40, false, Direccion.R, tc, 0));
					else if (i < 15)
						tc.tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direccion.D, tc, 0));
					else
						tc.tanks.add(new Tank(10, 50 * (i - 12), false, Direccion.L, tc, 0));
				}
			}

			tc.homeTank1 = new Tank(300, 560, true, Direccion.STOP, tc, 0);
			if (!tc.home.isvivo())
				tc.home.setvivo(true);
			TankClient abc = new TankClient();
			if (tc.Player2)
				abc.Player2 = true;
			if (tc.Player3)
				abc.Player3 = true;
			if (tc.Player4)
				abc.Player4 = true;
			break;
			*/
		if (player == 1) {
			switch (key) {
			case KeyEvent.VK_D:
				bR = true;
				break;

			case KeyEvent.VK_A:
				bL = true;
				break;

			case KeyEvent.VK_W:
				bU = true;
				break;

			case KeyEvent.VK_S:
				bD = true;
				break;
			}
		}
		if (player == 2) {
			switch (key) {
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;

			case KeyEvent.VK_LEFT:
				bL = true;
				break;

			case KeyEvent.VK_UP:
				bU = true;
				break;

			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			}
		}
		if (player == 3) {
			switch (key) {
			case KeyEvent.VK_K:
				bR = true;
				break;

			case KeyEvent.VK_H:
				bL = true;
				break;

			case KeyEvent.VK_U:
				bU = true;
				break;

			case KeyEvent.VK_J:
				bD = true;
				break;
			}
		}
		if (player == 4) {
			switch (key) {
			case KeyEvent.VK_1:
				bR = true;
				break;

			case KeyEvent.VK_3:
				bL = true;
				break;

			case KeyEvent.VK_5:
				bU = true;
				break;

			case KeyEvent.VK_2:
				bD = true;
				break;
			}
		}
		decideDireccion();
	}

	void decideDireccion() {
		if (!bL && !bU && bR && !bD)
			direction = Direccion.R;

		else if (bL && !bU && !bR && !bD)
			direction = Direccion.L;

		else if (!bL && bU && !bR && !bD)
			direction = Direccion.U;

		else if (!bL && !bU && !bR && bD)
			direction = Direccion.D;

		else if (!bL && !bU && !bR && !bD)
			direction = Direccion.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (player == 1) {
			switch (key) {
			case KeyEvent.VK_F:
				fire();
				break;
			case KeyEvent.VK_D:
				bR = false;
				break;
			case KeyEvent.VK_A:
				bL = false;
				break;
			case KeyEvent.VK_W:
				bU = false;
				break;
			case KeyEvent.VK_S:
				bD = false;
				break;
			}
		}
		if (player == 2) {
			switch (key) {
			case KeyEvent.VK_L:
				fire();
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			}
		}
		if (player == 3) {
			switch (key) {
			case KeyEvent.VK_G:
				fire();
				break;
			case KeyEvent.VK_K:
				bR = false;
				break;
			case KeyEvent.VK_H:
				bL = false;
				break;
			case KeyEvent.VK_U:
				bU = false;
				break;
			case KeyEvent.VK_J:
				bD = false;
				break;
			}
		}
		if (player == 4) {
			switch (key) {
			case KeyEvent.VK_0:
				fire();
				break;
			case KeyEvent.VK_1:
				bR = false;
				break;
			case KeyEvent.VK_3:
				bL = false;
				break;
			case KeyEvent.VK_5:
				bU = false;
				break;
			case KeyEvent.VK_2:
				bD = false;
				break;
			}
		}
		decideDireccion();
	}

	public Balas fire() {
		if (!vivo)
			return null;
		int x = this.x + Tank.width / 2 - Balas.ancho / 2;
		int y = this.y + Tank.length / 2 - Balas.largo / 2;
		Balas m = new Balas(x, y + 2, aliado, Kdirection, this.tc);
		tc.Balas.add(m);
	    try {
		      File file = new File("fire.wav");
		      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
		      Clip clip = AudioSystem.getClip();
		      clip.open(stream);
		      clip.start();
		     // clip.loop(Clip.LOOP_CONTINUOUSLY);
		    } 
		    catch (Exception ex) {
		        System.out.println(ex.getMessage());
		    }
		return m;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return vivo;
	}

	public void setLive(boolean vivo) {
		this.vivo = vivo;
	}

	public boolean isGood() {
		return aliado;
	}

	public boolean collideWithWall(ParedNormal w) {
		if (this.vivo && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithWall(ParedMetal w) {
		if (this.vivo && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideRiver(Agua r) {
		if (this.vivo && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Aguila h) {
		if (this.vivo && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.vivo && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean collideAliado(Tank Tank1, Tank Tank2, Tank Tank3) {
		if ( this.vivo && Tank1.vivo && this.getRect().intersects(Tank1.getRect())){
			this.changToOldDir();
			Tank1.changToOldDir();
			return true;
		}
		if ( this.vivo && Tank2.vivo && this.getRect().intersects(Tank2.getRect())){
			this.changToOldDir();
			Tank2.changToOldDir();
			return true;
		}
		if ( this.vivo && Tank3.vivo && this.getRect().intersects(Tank3.getRect())){
			this.changToOldDir();
			Tank3.changToOldDir();
			return true;
		}
		return false;
	}

	public int getLife() {
		return salud;
	}

	public void setLife(int salud) {
		this.salud = salud;
	}

	/*
	private class DrawBloodbBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(375, 585, width, 10);
			int w = width * salud / 200;
			g.fillRect(375, 585, w, 10);
			g.setColor(c);
		}
	}
	*/
	
	public void sumarPuntaje(){
		this.puntaje = this.puntaje + 50;
		System.out.println(this.puntaje);
	}
	
	public boolean eat(Salud b) {
		if (this.vivo && b.isvivo() && this.getRect().intersects(b.getRect())) {
			if (this.salud <= 100)
				this.salud = this.salud + 100;
			else
				this.salud = 200;
			b.setvivo(false);
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

