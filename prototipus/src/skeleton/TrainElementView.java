package skeleton;

/**
* A trainelement-ek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class TrainElementView extends View {
	public TrainElement te;
	
	/**
	 * Konstruktor
	 * @param te trainelement
	*/
	public TrainElementView(TrainElement te){
		this.te=te;
	}
	
	/**
	 * visszaadja a tartalmazott trainelement elemet
	 * @return te trainelement*/
	public Object getA(){
		return te;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return te.id;
	}
}
