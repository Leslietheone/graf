package skeleton;
/**
* A p�lyaobjectumok �soszt�lya
*
* @author  K�zvil�g�t�SCH
*/
public class Field {
	
	int id;
	/**
	   * Field konstruktor
	   * 
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public Field(int id) {
		this.id=id;
	}
	/**
	   * Field adataib�l String-et k�sz�t
	   * 
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public String tostr() {
		return "ID: "+this.id;
	}
}
