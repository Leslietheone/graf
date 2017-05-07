package skeleton;

import java.awt.Color;

/**
* A tunnel-ek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class TunnelView extends View{
	public Tunnel t;
	
	/**
	 * Konstruktor
	 * @param t tunnel
	*/
	public TunnelView(Tunnel t){
		this.t=t;
		this.c=new Color(245,222,179);
	}
	
	
	/**
	 * visszaadja a tartalmazott tunnel elemet
	 * @return t tunnel*/
	public Object getA(){
		return t;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return t.id;
	}
}
