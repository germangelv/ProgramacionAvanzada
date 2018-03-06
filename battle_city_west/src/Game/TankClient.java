package Game;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import javax.swing.JOptionPane;

public class TankClient extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final int Fram_width = 1000;
	public static final int Fram_length = 700;
	public static boolean printable = true;
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null, jm5 = null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null, jmi6 = null, jmi7 = null, jmi8 = null,
			 jmi9 = null, jmi10 = null, jmi11 = null, jmi12 = null;
	Image screenImage = null;
	
	//Ubicacion de Tanques aliados
	Tank homeTank1 = new Tank(410, 660, true, Direccion.STOP, this, 1);
	Tank homeTank2 = new Tank(550, 660, true, Direccion.STOP, this, 2);
	Tank homeTank3 = new Tank(350, 660, true, Direccion.STOP, this, 3);
	Tank homeTank4 = new Tank(100, 660, true, Direccion.STOP, this, 4);
	
	Boolean Player1 = false;
	Boolean Player2 = false;
	Boolean Player3 = false;
	Boolean Player4 = false;
	Boolean Enemigo1 = true;
	Boolean Enemigo2 = true;
	Boolean Enemigo3 = true;
	Salud ItemSalud = new Salud();
	public Aguila home = new Aguila(480, 660, this);
	Boolean win = false, lose = false;
	List<Agua> AguaRio = new ArrayList<Agua>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<ExplosionTanque> ExplosionTanques = new ArrayList<ExplosionTanque>();
	List<Balas> Balas = new ArrayList<Balas>();
	List<Pasto> Pastos = new ArrayList<Pasto>();
	List<ParedNormal> ParedAguila = new ArrayList<ParedNormal>();
	List<ParedNormal> ParedLadrillo = new ArrayList<ParedNormal>();
	List<ParedMetal> ParedMetal = new ArrayList<ParedMetal>();

	public void update(Graphics g) {

		screenImage = this.createImage(Fram_width, Fram_length);

		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.black);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);
		framPaint(gps);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void framPaint(Graphics g) {

		Color c = g.getColor();
		g.setColor(Color.red);

		Font f1 = g.getFont();

		g.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		g.drawString("Enemigos: ", 20, 70);

		g.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		g.drawString("" + tanks.size(), 200, 70);
		g.setFont(new Font("Arial Narrow", Font.BOLD, 20));

		// SALUD
		g.setColor(Color.GREEN);
		g.drawString("Aliados ", 300, 70);
		g.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		if (Player1){
			g.setColor(Color.YELLOW);
			g.drawString("JP1:  " + homeTank1.getLife(), 450, 70);
			//g.drawString("Puntaje:  " + homeTank1.puntaje, 550, 70);
		}
		if (Player2) {
			g.setColor(Color.BLUE);
			g.drawString("JP2:  " + homeTank2.getLife(), 600, 70);
		}
		if (Player3) {
			g.setColor(Color.MAGENTA);
			g.drawString("JP3:  " + homeTank3.getLife(), 750, 70);
		}
		if (Player4) {
			g.setColor(Color.WHITE);
			g.drawString("JP4:  " + homeTank4.getLife(), 900, 70);
		}
		g.setFont(f1);

		/*
		 * Pantallas de Win/Lose dependiendo del Socket de los Players
		 */
		//Con 1 Players
		if (Player1 && !Player2 && !Player3 && !Player4){
			if (tanks.size() == 0 && home.isvivo() && homeTank1.isLive() && lose == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 60));
				g.drawString("Felicitaciones! ", 300, 200);
				g.setFont(f);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				win = true;
			}
			if(homeTank1.isLive() == false && win == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 40));
				g.drawString("Perdiste...", 350, 300);
				g.setColor(Color.GREEN);
				g.drawString("Dedicate al Pinball !", 400, 380);
				g.setFont(f);
				this.home.setvivo(false);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				lose = true;
			}
		}
		//2 Jugadores
		if ( Player1 && Player2  && !Player3 && !Player4){
			if(tanks.size() == 0 && home.isvivo() && (homeTank1.isLive() || homeTank2.isLive()) && lose == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 60));
				g.drawString("Felicitaciones! ", 300, 200);
				g.setFont(f);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				win = true;
			}
			if( homeTank1.isLive()  == false && homeTank2.isLive() == false  && win == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 40));
				g.drawString("Perdiste...", 350, 300);
				g.setColor(Color.GREEN);
				g.drawString("Dedicate al Pinball !", 400, 380);
				g.setFont(f);
				this.home.setvivo(false);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				lose = true;
				}
		}
		//3 Jugadores
		if(Player1 && Player2 && Player3 && !Player4){
			if(tanks.size() == 0 && home.isvivo() && (homeTank1.isLive() || homeTank2.isLive() || homeTank3.isLive()) && lose == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 60));
				g.drawString("Felicitaciones! ", 300, 200);
				g.setFont(f);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				win = true;
			}
			if(homeTank1.isLive() == false && homeTank2.isLive() == false && homeTank3.isLive()  == false && win == false){
				Font f = g.getFont();
				g.setFont(new Font("Arial Narrow", Font.BOLD, 40));
				g.drawString("Perdiste...", 350, 300);
				g.setColor(Color.GREEN);
				g.drawString("Dedicate al Pinball !", 400, 380);
				g.setFont(f);
				this.home.setvivo(false);
				this.ParedLadrillo.clear();
				this.Pastos.clear();
				this.ParedMetal.clear();
				this.AguaRio.clear();
				this.tanks.clear();
				this.ParedAguila.clear();
				lose = true;
				}
			}
			//Cuatro Jugadores
			if(Player1 && Player2 && Player3 && Player4){
				if(tanks.size() == 0 && home.isvivo() && (homeTank1.isLive() || homeTank2.isLive() || homeTank3.isLive() || homeTank4.isLive()) && lose == false){
					Font f = g.getFont();
					g.setFont(new Font("Arial Narrow", Font.BOLD, 60));
					g.drawString("Felicitaciones! ", 300, 200);
					g.setFont(f);
					this.ParedLadrillo.clear();
					this.Pastos.clear();
					this.ParedMetal.clear();
					this.AguaRio.clear();
					this.tanks.clear();
					this.ParedAguila.clear();
					win = true;
				}
				if(homeTank1.isLive() == false && homeTank2.isLive() == false && homeTank3.isLive()  == false && homeTank4.isLive()  == false && win == false){
					Font f = g.getFont();
					g.setFont(new Font("Arial Narrow", Font.BOLD, 40));
					g.drawString("Perdiste...", 350, 300);
					g.setColor(Color.GREEN);
					g.drawString("Dedicate al Pinball !", 400, 380);
					g.setFont(f);
					this.home.setvivo(false);
					this.ParedLadrillo.clear();
					this.Pastos.clear();
					this.ParedMetal.clear();
					this.AguaRio.clear();
					this.tanks.clear();
					this.ParedAguila.clear();
					lose = true;
				}
			}
		g.setColor(c);

		///////////////// *****************************//////////////////

		for (int i = 0; i < AguaRio.size(); i++) {
			Agua r = AguaRio.get(i);
			r.draw(g);
		}

		for (int i = 0; i < AguaRio.size(); i++) {
			Agua r = AguaRio.get(i);
			if (Player1)
				homeTank1.collideRiver(r);
			if (Player2)
				homeTank2.collideRiver(r);
			if (Player3)
				homeTank3.collideRiver(r);
			if (Player4)
				homeTank4.collideRiver(r);
			r.draw(g);
		}

		home.draw(g);
		if (Player1){
			homeTank1.draw(g);
			homeTank1.eat(ItemSalud);
			homeTank1.collideWithTanks(tanks);
			homeTank1.collideHome(home);
			homeTank1.collideAliado(homeTank2,homeTank3,homeTank4);
		}
		if (Player2) {
			homeTank2.draw(g);
			homeTank2.eat(ItemSalud);
			homeTank2.collideWithTanks(tanks);
			homeTank2.collideHome(home);
			homeTank2.collideAliado(homeTank1,homeTank3,homeTank4);
		}
		if (Player3) {
			homeTank3.draw(g);
			homeTank3.eat(ItemSalud);
			homeTank3.collideWithTanks(tanks);
			homeTank3.collideHome(home);
			homeTank3.collideAliado(homeTank1,homeTank2,homeTank4);
		}
		if (Player4) {
			homeTank4.draw(g);
			homeTank4.eat(ItemSalud);
			homeTank4.collideWithTanks(tanks);
			homeTank4.collideHome(home);
			homeTank4.collideAliado(homeTank1,homeTank2,homeTank3);
		}

		for (int i = 0; i < Balas.size(); i++) {
			Balas m = Balas.get(i);
			//QUITA PUNTOS CUANDO ME DAN
			m.hitTank(homeTank1);
			//A PUNTOS SI MATAMOS CUALQUIER TANQUE
			if (m.hitTanks(tanks)){
				
			}
			
			//NECESITO DARME PUNTOS CUANDO YO DOY
			
			m.hitTank(homeTank2);
			m.hitTank(homeTank3);
			m.hitTank(homeTank4);
			m.hitAguila();
			for (int j = 0; j < Balas.size(); j++) {
				if (i == j)
					continue;
				Balas bts = Balas.get(j);
				m.hitBala(bts);
			}
			for (int j = 0; j < ParedMetal.size(); j++) {
				ParedMetal mw = ParedMetal.get(j);
				m.hitPared(mw);
			}

			for (int j = 0; j < ParedLadrillo.size(); j++) {
				ParedNormal w = ParedLadrillo.get(j);
				m.hitPared(w);
			}

			for (int j = 0; j < ParedAguila.size(); j++) {
				ParedNormal cw = ParedAguila.get(j);
				m.hitPared(cw);
			}
			m.draw(g);
		}

		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);

			for (int j = 0; j < ParedAguila.size(); j++) {
				ParedNormal cw = ParedAguila.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < ParedLadrillo.size(); j++) {
				ParedNormal cw = ParedLadrillo.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < ParedMetal.size(); j++) {
				ParedMetal mw = ParedMetal.get(j);
				t.collideWithWall(mw);
				mw.draw(g);
			}
			for (int j = 0; j < AguaRio.size(); j++) {
				Agua r = AguaRio.get(j);
				t.collideRiver(r);
				r.draw(g);
				// t.draw(g);
			}

			t.collideWithTanks(tanks);
			t.collideHome(home);

			t.draw(g);
		}

		ItemSalud.draw(g);

		for (int i = 0; i < Pastos.size(); i++) {
			Pasto tr = Pastos.get(i);
			tr.draw(g);
		}

		for (int i = 0; i < ExplosionTanques.size(); i++) {
			ExplosionTanque bt = ExplosionTanques.get(i);
			bt.draw(g);
			
		}

		for (int i = 0; i < ParedLadrillo.size(); i++) {
			ParedNormal cw = ParedLadrillo.get(i);
			cw.draw(g);
		}

		for (int i = 0; i < ParedMetal.size(); i++) {
			ParedMetal mw = ParedMetal.get(i);
			mw.draw(g);
		}


		for (int i = 0; i < ParedMetal.size(); i++) {
			ParedMetal w = ParedMetal.get(i);
			if (Player1)
				homeTank1.collideWithWall(w);
			if (Player2)
				homeTank2.collideWithWall(w);
			if (Player3)
				homeTank3.collideWithWall(w);
			if (Player4)
				homeTank4.collideWithWall(w);
			w.draw(g);
		}

		for (int i = 0; i < ParedLadrillo.size(); i++) {
			ParedNormal cw = ParedLadrillo.get(i);
			if (Player1)
				homeTank1.collideWithWall(cw);
			if (Player2)
				homeTank2.collideWithWall(cw);
			if (Player3)
				homeTank3.collideWithWall(cw);
			if (Player4)
				homeTank4.collideWithWall(cw);
			cw.draw(g);
		}

		for (int i = 0; i < ParedAguila.size(); i++) {
			ParedNormal w = ParedAguila.get(i);
			if (Player1)
				homeTank1.collideWithWall(w);
			if (Player2)
				homeTank2.collideWithWall(w);
			if (Player3)
				homeTank3.collideWithWall(w);
			if (Player4)
				homeTank4.collideWithWall(w);
			w.draw(g);
		}
	}

	public TankClient() {

		jmb = new MenuBar();
		jm1 = new Menu("Juego");
		jm2 = new Menu("Pausa/Continuar");
		jm3 = new Menu("Ayuda");
		jm4 = new Menu("Nivel");
		jm5 = new Menu("Agregar");
		jm1.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jm2.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jm3.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jm4.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jm5.setFont(new Font("Arial Narrow", Font.BOLD, 15));

		jmi1 = new MenuItem("Nuevo Juego");
		jmi2 = new MenuItem("Salir");
		jmi3 = new MenuItem("Stop");
		jmi4 = new MenuItem("Continuar");
		jmi5 = new MenuItem("Ayuda");
		jmi6 = new MenuItem("Nivel1");
		jmi7 = new MenuItem("Nivel2");
		jmi8 = new MenuItem("Nivel3");
		jmi9 = new MenuItem("Nivel4");
		jmi10 = new MenuItem("Agregar Player 2");
		jmi11 = new MenuItem("Agregar Player 3");
		jmi12 = new MenuItem("Agregar Player 4");
		// jmi11= new MenuItem("Join other's game");
		jmi1.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jmi2.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jmi3.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jmi4.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		jmi5.setFont(new Font("Arial Narrow", Font.BOLD, 15));

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);
		jm5.add(jmi10);
		jm5.add(jmi11);
		jm5.add(jmi12);
		// jm5.add(jmi11);

		jmb.add(jm1);
		jmb.add(jm2);

		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm3);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("JuegoNuevo");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Salir");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Stop");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continuar");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("ayuda");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("nivel1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("nivel2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("nivel3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("nivel4");
		jmi10.addActionListener(this);
		jmi10.setActionCommand("Player2");
		jmi11.addActionListener(this);
		jmi11.setActionCommand("Player3");
		jmi12.addActionListener(this);
		jmi12.setActionCommand("Player4");
		
		// jmi11.addActionListener(this);
		// jmi11.setActionCommand("Unir");

		this.setMenuBar(jmb);
		this.setVisible(true);

		// *********UBICACIONES**************
		// PARED QUE RODEA AL AGUILA
		for (int i = 0; i < 15; i++) {
			if (i < 4)
				ParedAguila.add(new ParedNormal(450, 680 - 21 * i, this));
			else if (i < 8)
				ParedAguila.add(new ParedNormal(450 + 23 * (i - 4), 617, this));
			else
				ParedAguila.add(new ParedNormal(518, 638 + (i - 8) * 21, this));
		}

		// PAREDES NORMALES
		for (int i = 0; i < 40; i++) {
			if (i < 26) {
				ParedLadrillo.add(new ParedNormal(130 + 21 * i, 400, this));
				ParedLadrillo.add(new ParedNormal(500 + 21 * i, 280, this));
				ParedLadrillo.add(new ParedNormal(250, 500 + 21 * i, this));
				ParedLadrillo.add(new ParedNormal(600, 500 + 21 * i, this));
				ParedLadrillo.add(new ParedNormal(750, 500 + (i * 60), this));
			} else if (i < 32) {
				ParedLadrillo.add(new ParedNormal(200 + 21 * (i - 16), 420, this));
				ParedLadrillo.add(new ParedNormal(500 + 21 * (i - 16), 320, this));
				ParedLadrillo.add(new ParedNormal(222, 500 + 21 * (i - 16), this));
				ParedLadrillo.add(new ParedNormal(522, 500 + 21 * (i - 16), this));
			}
		}

		// PAREDES DE METAL
		for (int i = 0; i < 20; i++) {
			if (i < 2) {
				ParedMetal.add(new ParedMetal(30 + (i * 90), 550, this));
				ParedMetal.add(new ParedMetal(60 + (i * 90), 660, this));
			}
			if (i < 10) {
				ParedMetal.add(new ParedMetal(140 + 30 * i, 250, this));
				ParedMetal.add(new ParedMetal(300, 500 + 20 * (i), this));
				ParedMetal.add(new ParedMetal(650, 500 + (i * 60), this));
			} else if (i < 20)
				ParedMetal.add(new ParedMetal(140 + 30 * (i - 10), 280, this));
		}

		// Limite Superior
		for (int i = 0; i < Fram_width - 30; i++) {
			ParedMetal.add(new ParedMetal(0 + (i * 30), 75, this));
		}

		// PASTO
		for (int i = 0; i < 15; i++) {
			if (i < 4) {
				Pastos.add(new Pasto(0 + 30 * i, 460, this));
				Pastos.add(new Pasto(220 + 30 * i, 460, this));
				Pastos.add(new Pasto(440 + 30 * i, 460, this));
				Pastos.add(new Pasto(660 + 30 * i, 460, this));
			} else
				Pastos.add(new Pasto(20 + 30 * (i * 2), 360, this));
		}

		// AGUA
		for (int i = 0; i < 15; i++) {
			if (i < 6) {
				AguaRio.add(new Agua(85, 250 * i, this));
			} else {
				AguaRio.add(new Agua(900, 200 * (i - 5), this));
				AguaRio.add(new Agua(25, 200 * (i - 2), this));
			}
		}

		// TANQUES ENEMIGOS
		for (int i = 0; i < 20; i++) {
			if (i < 12) {
				tanks.add(new Tank(70 + 70 * i, 210, false, Direccion.U, this, 5));
			} else {
				if (i < 17) {
					tanks.add(new Tank(950, 40 + 50 * (i - 6), false, Direccion.D, this, 6));
				} else {
					tanks.add(new Tank(30, 70 * (i - 12), false, Direccion.D, this, 7));
				}
			}
		}

		// PANTALLA
		this.setSize(Fram_width, Fram_length);
		this.setLocation(280, 50);
		this.setTitle("Battle West City");
		setLocationRelativeTo(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);

		this.addKeyListener(new KeyMonitor());
		//Ojo papu para los sockets siempre existe el juego 1 cuando es ejecutado est constructor gigantezco
		this.Player1 = true;

		//Audio Inicio
	    try {
	      File file = new File("start.mp3");
	      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	      Clip clip = AudioSystem.getClip();
	      clip.open(stream);
	      clip.start();
	      //clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } 
	    catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	    
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		//Inicio Juego
		new TankClient();
	}

	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(75);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			if (Player1){
				homeTank1.keyReleased(e);
			}
			if (Player2){
				homeTank2.keyReleased(e);
			}
			if (Player3){
				homeTank3.keyReleased(e);
			}
			if (Player4){
				homeTank4.keyReleased(e);
			}
		}

		public void keyPressed(KeyEvent e) {
			if (Player1){
				homeTank1.keyPressed(e);
			}
			if (Player2){
				homeTank2.keyPressed(e);
			}
			if (Player3){
				homeTank3.keyPressed(e);
			}
			if (Player4){
				homeTank4.keyPressed(e);
			}
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("JuegoNuevo")) {
			printable = false;
			Object[] options = { "Confirmar", "Cancelar" };
			int response = JOptionPane.showOptionDialog(this, "Iniciar una nueva partida?", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {

				printable = true;
				this.dispose();
				new TankClient();
			} else {
				printable = true;
				new Thread(new PaintThread()).start();
			}

		} else if (e.getActionCommand().endsWith("Stop")) {
			printable = false;
			// try {
			// Thread.sleep(10000);
			//
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
		} else if (e.getActionCommand().equals("Continuar")) {

			if (!printable) {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		} else if (e.getActionCommand().equals("Salir")) {
			printable = false;
			Object[] options = { "Confirmar", "Cancelar" };
			int response = JOptionPane.showOptionDialog(this, "Seguro que desea salir?", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("break down");
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start();

			}

		} else if (e.getActionCommand().equals("Player2")) {
			printable = false;
			Object[] options = { "Confirmar", "Cancelar" };
			int response = JOptionPane.showOptionDialog(this, "Agregar jugador player2?", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				printable = true;
				this.dispose();
				TankClient Player2add = new TankClient();
				Player2add.Player2 = true;
			} else {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		}else if (e.getActionCommand().equals("Player3")) {
			printable = false;
			Object[] options = { "Confirmar", "Cancelar" };
			int response = JOptionPane.showOptionDialog(this, "Agregar jugador player3?", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				printable = true;
				this.dispose();
				TankClient Player3add = new TankClient();
				Player3add.Player2 = true;
				Player3add.Player3 = true;
			} else {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		}else if (e.getActionCommand().equals("Player4")) {
			printable = false;
			Object[] options = { "Confirmar", "Cancelar" };
			int response = JOptionPane.showOptionDialog(this, "Agregar jugador player4?", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				printable = true;
				this.dispose();
				TankClient Player4add = new TankClient();
				Player4add.Player2 = true;
				Player4add.Player3 = true;
				Player4add.Player4 = true;
			} else {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		}
		else if (e.getActionCommand().equals("ayuda")) {
			printable = false;
			JOptionPane.showMessageDialog(null,
					"Usar WSAD para mover Player1, usar F para disparar. R reinicia la partida.\nUsar las flechas para mover Player2, L para disparar",
					"Ayuda", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start();
		} else if (e.getActionCommand().equals("nivel1")) {
			Tank.count = 20;
			Tank.speedX = 6;
			Tank.speedY = 6;
			// Balas.speedX = 10;
			// Balas.speedY = 10;
			this.dispose();
			new TankClient();
		} else if (e.getActionCommand().equals("nivel2")) {
			Tank.count = 22;
			Tank.speedX = 8;
			Tank.speedY = 8;
			// Balas.speedX = 12;
			// Balas.speedY = 12;
			this.dispose();
			new TankClient();

		} else if (e.getActionCommand().equals("nivel3")) {
			Tank.count = 25;
			Tank.speedX = 10;
			Tank.speedY = 10;
			//Balas.speedX = 16;
			//Balas.speedY = 16;
			this.dispose();
			new TankClient();
		} else if (e.getActionCommand().equals("nivel4")) {
			Tank.count = 28;
			Tank.speedX = 12;
			Tank.speedY = 12;
			// Balas.speedX = 18;
			// Balas.speedY = 18;
			this.dispose();
			new TankClient();
		} /*
			 * else if (e.getActionCommand().equals("Join")){ printable = false;
			 * String s=JOptionPane.showInputDialog("Ingresar URL:");
			 * System.out.println(s); printable = true; new Thread(new
			 * PaintThread()).start(); }
			 */
	}
}