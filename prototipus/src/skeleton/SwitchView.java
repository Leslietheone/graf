package skeleton;

import java.awt.Color;

/**
* A switch-ek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class SwitchView extends View{
	public Switch sw;
	
	/**
	 * Konstruktor
	 * @param sw switch
	*/
	public SwitchView(Switch sw){
		this.sw=sw;
		this.c=new Color(205,133,63);
	}
	
	/**
	 * visszaadja a tartalmazott switch elemet
	 * @return sw switch*/
	public Object getA(){
		return sw;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return sw.id;
	}
}
