package skeleton;

import java.awt.Color;
/**
* A rail-ek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class RailView extends View {
	public Rail r;
	
	/**
	 * Konstruktor
	 * @param r rail
	*/
	public RailView(Rail r){
		this.r=r;
		this.c=new Color(139,69,19);
	}
	
	/**
	 * visszaadja a tartalmazott rail elemet
	 * @return r rail*/
	public Object getA(){
		return r;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return r.id;
	}
}
