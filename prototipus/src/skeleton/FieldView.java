package skeleton;

import java.awt.Color;
/**
* A field-ek modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class FieldView extends View  {
	public Field f;
	public Color c;
	
	/**
	 * Konstruktor
	*/
	public FieldView(){
		f=new Field(-2);
	}
	
	/**
	 * visszaadja a tartalmazott field elemet
	 * @return f field*/
	public Object getA(){
		return f;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return f.id;
	}
}
