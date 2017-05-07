package skeleton;

import java.awt.Color;

/**
* A keresztez�d� s�nek grafikus modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class CrossRailView extends View{
	public CrossRail cr;
	
	/**
	 * Konstruktor
	 * @param cr crossrail
	*/
	CrossRailView(CrossRail cr){
		this.cr=cr;
		this.c=Color.white;
	}
	
	/**
	 * visszaadja a tartalmazott crossrail elemet
	 * @return cr crossrail*/
	public Object getA(){
		return cr;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return cr.id;
	}
}
