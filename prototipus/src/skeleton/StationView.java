package skeleton;
/**
* A station-ök modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class StationView extends View {
	public Station s;
	
	/**
	 * Konstruktor
	 * @param s station
	*/
	public StationView(Station s){
		this.s=s;
		this.setCBefore(s.clr);
		this.setColor(s.clr);
		this.c=s.clr;
	}
	
	/**
	 * visszaadja a tartalmazott station elemet
	 * @return s station*/
	public Object getA(){
		return s;
	}
	
	/**
	 * visszaadja az id-t
	 * @return id id*/
	public int getID(){
		return s.id;
	}
}
