package Game.Test;

import Game.*;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;


public class Tests {
	
	@Test
	public void queElTanqueEsteVivo(){
		int x = 0,
			y = 0;
		Tank tanque = new Tank( x, y, true);
		assertTrue( tanque.isLive());
	}
	
	@Test
	public void queElTanqueChoqueConLasParedes(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		TankClient tc = null;
		Tank tanque = new Tank( x, y, true);
		ParedNormal pared = new ParedNormal( z, w, tc );
		assertTrue( tanque.collideWithWall(pared));
		
	}
	
	@Test
	public void queElTanqueNoChoqueConLasParedes(){
		int x = 0,
			y = 0,
			z = 50,
			w = 50;
		TankClient tc = null;
		Tank tanque = new Tank( x, y, true);
		ParedNormal pared = new ParedNormal( z, w, tc );
		assertFalse( tanque.collideWithWall(pared));
		
	}
	
	@Test
	public void queElTanqueChoqueConLasParedesDeMetal(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		TankClient tc = null;
		Tank tanque = new Tank( x, y, true);
		ParedMetal pared = new ParedMetal( z, w, tc );
		assertTrue( tanque.collideWithWall(pared));
		
	}
	
	@Test
	public void queElTanqueChoqueConElAguila(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		TankClient tc = null;
		Tank tanque = new Tank( x, y, true);
		Aguila aguila = new Aguila( z, w, tc );
		assertTrue( tanque.collideHome(aguila));
		
	}
	
	@Test
	public void queElTanqueChoqueConElAgua(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		TankClient tc = null;
		Tank tanque = new Tank( x, y, true);
		Agua agua = new Agua( z, w, tc );
		assertTrue( tanque.collideRiver(agua));
		
	}
	
	@Test
	public void queElTanqueChoqueConTanquesEnemigos(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		Tank tanque = new Tank( x, y, true);
		Tank tanque2 = new Tank( z, w, true);
		List<Tank> tanks = new ArrayList<Tank>();
		tanks.add(tanque2);
		assertTrue( tanque.collideWithTanks(tanks) );	
	}
	
	@Test
	public void queElTanqueChoqueConTanquesAliados(){
		int x = 0,
			y = 0,
			z = 1,
			w = 1;
		Tank tanque = new Tank( x, y, true);
		Tank tanque2 = new Tank( z, w, true);
		Tank tanque3 = new Tank( z, w, true);
		Tank tanque4 = new Tank( z, w, true);
		
		assertTrue( tanque.collideAliado(tanque2,tanque3,tanque4) );	
	}
	
	@Test
	public void queLaBalaImpacteTanque(){
			
		int x = 0,
			y = 0;
		TankClient tc = new TankClient();
		Tank tanque = new Tank( x, y, false);
		Balas bala = new Balas( x, y, true, Direccion.R, tc );
		List<Tank> tanks = new ArrayList<Tank>();
		tanks.add(tanque);
		assertTrue( bala.hitTanks(tanks) );
	}
	
	@Test
	public void queElImpactoDeBalaMateAlTanqueEnemigo(){
		int x = 0,
			y = 0;
		TankClient tc = new TankClient();
		Tank tanque = new Tank( x, y, false);
		Balas bala = new Balas( x, y, true, Direccion.R, tc );
		bala.hitTank(tanque);
		assertFalse( tanque.isLive() );
	}
	
	@Test
	public void queElImpactoDeBalaReduzcaVidaDelTanqueAliado(){
		int x = 0,
			y = 0;
		TankClient tc = new TankClient();
		Tank tanque = new Tank( x, y, true);
		Balas bala = new Balas( x, y, false, Direccion.R, tc );
		bala.hitTank(tanque);
		assertEquals( 150, tanque.getLife() );
	}
	
	@Test
	public void queLaBalaMueraAlImpactarTanque(){
			
		int x = 0,
			y = 0;
		TankClient tc = new TankClient();
		Tank tanque = new Tank( x, y, false);
		Balas bala = new Balas( x, y, true, Direccion.R, tc );
		List<Tank> tanks = new ArrayList<Tank>();
		tanks.add(tanque);
		bala.hitTanks(tanks);
		assertFalse( bala.isvivo() );
	}
	
	
	@Test
	public void queLaBalaNoImpactePared(){
			
		int x = 0,
		    y = 0,
			z = 12,
			w = 0;
		TankClient tc = new TankClient();
		Balas bala = new Balas( x, y, true, Direccion.D, tc );
		ParedNormal pared = new ParedNormal( z, w, tc );
		assertFalse( bala.hitPared(pared) );	
	}
	
	@Test
	public void queLaBalaImpactePared(){
			
		int x = 0,
		    y = 0,
			z = 1, 
			w = 0;
		TankClient abc = new TankClient();
		Balas bala = new Balas( x, y, true, Direccion.R, abc );
		ParedNormal pared = new ParedNormal( z, w, abc );
		assertTrue( bala.hitPared(pared) );	
	}
	
	
	@Test
	public void queLaBalaMueraAlImpactarPared(){
			
		int x = 0,
		    y = 0,
			z = 1, 
			w = 0;
		TankClient abc = new TankClient();
		Balas bala = new Balas( x, y, true, Direccion.R, abc );
		ParedNormal pared = new ParedNormal( z, w, abc );
		bala.hitPared(pared);
		assertFalse( bala.isvivo() );	
	}
	
	
	@Test
	public void queLaBalaMateAlAguila(){
			
		int x = 40,
		    y = 40,
			z = 45,
			w = 40;
		
		TankClient tc = new TankClient();
		Aguila aguila = new Aguila( z, w, tc );
		Balas bala = new Balas( x, y, true, Direccion.D, tc );
		tc.home = aguila;
		bala.hitAguila();
	
		assertFalse( tc.home.isvivo() );	
	}
	
	
	@Test
	public void queLaBalaMueraAlImpactarAguila(){
			
		int x = 40,
		    y = 40,
			z = 45,
			w = 40;
		TankClient tc = new TankClient();
		Aguila aguila = new Aguila( z, w, tc );
		Balas bala = new Balas( x, y, true, Direccion.D, tc );
		tc.home = aguila;
		bala.hitAguila();
	
		assertFalse( bala.isvivo() );	
	}
	
	@Test
	public void queLaBalaChoqueConOtrasBalas(){
		
		int x = 40,
			y = 40;
		TankClient tc = new TankClient();
		Balas bala = new Balas( x, y, true, Direccion.D, tc );
		Balas bala2 = new Balas( x, y, true, Direccion.U, tc );
		assertTrue( bala.hitBala(bala2) );
		
	}
	
	@Test
	public void queLaBalaMueraAlChocarConOtrasBalas(){
		
		int x = 40,
			y = 40;
		TankClient tc = new TankClient();
		Balas bala = new Balas( x, y, true, Direccion.D, tc );
		Balas bala2 = new Balas( x, y, true, Direccion.U, tc );
		bala.hitBala(bala2);
		assertFalse( bala.isvivo() );
		
	}
	
	@Test
	public void queUnTanqueNoSeaAliado(){
		int x = 10,
			y = 10;
		Tank tanque = new Tank( x, y, false);
		assertFalse( tanque.isGood() );
	}
	
	@Test
	public void queUnTanqueSeaAliado(){
		int x = 10,
			y = 10;
		Tank tanque = new Tank( x, y, true);
		assertTrue( tanque.isGood() );
	}

}
