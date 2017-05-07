package skeleton;

import java.awt.Color;
/**
* A view-k interface-e
*
* @author  K�zvil�g�t�SCH
*/
public class View {
	public Color c;
	public int x, y;
	public Color cBefore;
	
	/**
	 * Visszaadja az elem sz�n�t
	 * @return c color*/
	public Color getColor(){
		return c;
	}
	
	/**
	 * be�ll�tja az elem sz�n�t
	 * @param c sz�n
	 */
	public void setColor(Color c){
		this.c=c;
	}
	
	/**
	 * visszaadja az el�z� sz�nt
	 * @return cBefore el�z�sz�n*/
	public Color getCBefore(){
		return cBefore;
	}
	
	/**
	 * be�ll�tja az el�z� sz�nt
	 * @param cBefore el�z�sz�n*/
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
