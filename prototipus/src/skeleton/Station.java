package skeleton;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
/**
* Az �llom�sok modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class Station extends Rail{
	
	Color clr;	//�llom�s sz�ne
	boolean hasPassenger=false;
	
	  /**
	   * Stringk�nt visszadaja a Station adatait
	   * @return adatok*/
	@Override
	public String tostr(){return super.tostr()+" Color: "+clr+" HasPassenger: "+hasPassenger;}
	
	/**
	 * Station konstruktor egy szomsz�ddal
	 * @param c Sz�n
	 * @param r egyik szomsz�d
	 * @param h van e utas
	 * @param id azonos�t�
	 * @return nothing*/
	public Station(Color c, Rail r, boolean h, int id) {	//�llom�s sz�ne, �s hogy melyik s�n ut�n k�vetkezik
		super(r, id);
		
		clr=c;
		hasPassenger=h;
		
		Form.ind++;
		Form.logging("Station: konstruktor");
		
		Form.ind--;
	}
	
	/**
	 * Station konstruktor k�t szomsz�ddal
	 * @param c Sz�n
	 * @param r1 egyik szomsz�d
	 * @param r2 m�sik szomsz�d
	 * @param h van e utas
	 * @param id azonos�t�
	 * @return nothing*/
	public Station(Color c, Rail r1, Rail r2, boolean h, int id) {	//�llom�s sz�ne, �s hogy melyik s�n ut�n k�vetkezik
		super(r1,r2, id);
		
		clr=c;
		hasPassenger=h;
		
		Form.ind++;
		Form.logging("Station: konstruktor");
		
		Form.ind--;
	}
	
	/**
	 * Vonat tov�bb�t�sa a Station-on, int�zi a fel �s lesz�ll� utasokat
	 * @param r A vonatelem el�z� s�ne
	 * @param te vonatelem
	 * @return siker�lt e*/
	@Override
	public boolean pass(Rail r, TrainElement te) throws IOException{ //kocsi l�ptet�se
		Form.ind++;
		Form.logging("Station: pass()");
		
		if(te.getPass()){
			Color c=te.getNowColor();
			if(c.getRGB()==clr.getRGB()){
				Form.setTColor(te.id, 0,0,0, false);
			}
		}
		if(hasPassenger && clr.getRGB()==te.getBeforeColor().getRGB()) { //van utas �s a kocsi �res, teh�t felsz�llhatnak
					te.setNowColor(clr);
					te.setPass(true);
					hasPassenger=false;
		}
		
		//l�ptet�s, att�l f�gg�en hogy melyik ir�nyb�l j�tt�nk
		
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
	 * Felsz�ll� utasok megl�t�nek �ll�t�sa
	 * @param b van e utas
	 * @return nothing*/
	public void setPassanger( boolean b) {
		hasPassenger=b;
	}
	
	/**
	 * Utasok megl�t�nek lek�rdez�se
	 * @return van e utas*/
	public boolean getPassanger() {
		return hasPassenger;
	}
		
}
