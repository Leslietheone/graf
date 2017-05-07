package skeleton;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
/**
* Az állomások modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class Station extends Rail{
	
	Color clr;	//állomás színe
	boolean hasPassenger=false;
	
	  /**
	   * Stringként visszadaja a Station adatait
	   * @return adatok*/
	@Override
	public String tostr(){return super.tostr()+" Color: "+clr+" HasPassenger: "+hasPassenger;}
	
	/**
	 * Station konstruktor egy szomszéddal
	 * @param c Szín
	 * @param r egyik szomszéd
	 * @param h van e utas
	 * @param id azonosító
	 * @return nothing*/
	public Station(Color c, Rail r, boolean h, int id) {	//állomás színe, és hogy melyik sín után következik
		super(r, id);
		
		clr=c;
		hasPassenger=h;
		
		Form.ind++;
		Form.logging("Station: konstruktor");
		
		Form.ind--;
	}
	
	/**
	 * Station konstruktor két szomszéddal
	 * @param c Szín
	 * @param r1 egyik szomszéd
	 * @param r2 másik szomszéd
	 * @param h van e utas
	 * @param id azonosító
	 * @return nothing*/
	public Station(Color c, Rail r1, Rail r2, boolean h, int id) {	//állomás színe, és hogy melyik sín után következik
		super(r1,r2, id);
		
		clr=c;
		hasPassenger=h;
		
		Form.ind++;
		Form.logging("Station: konstruktor");
		
		Form.ind--;
	}
	
	/**
	 * Vonat továbbítása a Station-on, intézi a fel és leszálló utasokat
	 * @param r A vonatelem elõzõ síne
	 * @param te vonatelem
	 * @return sikerült e*/
	@Override
	public boolean pass(Rail r, TrainElement te) throws IOException{ //kocsi léptetése
		Form.ind++;
		Form.logging("Station: pass()");
		
		if(te.getPass()){
			Color c=te.getNowColor();
			if(c.getRGB()==clr.getRGB()){
				Form.setTColor(te.id, 0,0,0, false);
			}
		}
		if(hasPassenger && clr.getRGB()==te.getBeforeColor().getRGB()) { //van utas és a kocsi üres, tehát felszállhatnak
					te.setNowColor(clr);
					te.setPass(true);
					hasPassenger=false;
		}
		
		//léptetés, attól függõen hogy melyik irányból jöttünk
		
		if (r==r1 && r2.getOccupied()==false){
			
			te.setRail(r2);
			te.setrPrev(this);			
			
			r2.setOccupied(true);
			this.setOccupied(false);
			
			Form.ind--;
			return true;
		}
		else if (r==r2 && r1.getOccupied()==false) {
			
			te.setRail(r1);
			te.setrPrev(this);			
			
			r1.setOccupied(true);
			this.setOccupied(false);
			
			Form.ind--;
			return true;
		}
		else {
			Form.ind--;
			return false;
		}
	}
	/**
	 * Felszálló utasok meglétének állítása
	 * @param b van e utas
	 * @return nothing*/
	public void setPassanger( boolean b) {
		hasPassenger=b;
	}
	
	/**
	 * Utasok meglétének lekérdezése
	 * @return van e utas*/
	public boolean getPassanger() {
		return hasPassenger;
	}
		
}
