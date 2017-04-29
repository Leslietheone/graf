package skeleton;
/**
* A pályaobjectumok õsosztálya
*
* @author  KözvilágítáSCH
*/
public class Field {
	
	int id;
	/**
	   * Field konstruktor
	   * 
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public Field(int id) {
		this.id=id;
	}
	/**
	   * Field adataiból String-et készít
	   * 
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public String tostr() {
		return "ID: "+this.id;
	}
}
