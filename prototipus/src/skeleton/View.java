package skeleton;

import java.awt.Color;
/**
* A view-k interface-e
*
* @author  KözvilágítáSCH
*/
public class View {
	public Color c;
	public int x, y;
	public Color cBefore;
	
	/**
	 * Visszaadja az elem színét
	 * @return c color*/
	public Color getColor(){
		return c;
	}
	
	/**
	 * beállítja az elem színét
	 * @param c szín
	 */
	public void setColor(Color c){
		this.c=c;
	}
	
	/**
	 * visszaadja az elõzõ színt
	 * @return cBefore elõzõszín*/
	public Color getCBefore(){
		return cBefore;
	}
	
	/**
	 * beállítja az elõzõ színt
	 * @param cBefore elõzõszín*/
	public void setCBefore(Color c){
		this.cBefore=c;
	}
	
	/**
	 * visszaadja a tartalmazott objektumot*/
	public Object getA(){
		return null;
	}
	
	/**visszaadja az id-t*/
	public int getID(){
		return -1;
	}
}
