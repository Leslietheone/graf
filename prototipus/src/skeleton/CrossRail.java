package skeleton;

import java.io.IOException;
/**
* A keresztezõdõ sínek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class CrossRail extends Rail{

	public Rail r3;
	public Rail r4;
	
	/**
	   * CrossRail konstruktor
	   * @param r1 Elõzõ sín.
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public CrossRail(Rail r1, int id) {
		super(id);
		Skeleton.ind++;
		Skeleton.logging("CrossRail: konstruktor");;
		
		if (r1!=null){
			this.r1=r1;
			if (r1.getR2()==null){
				r1.setNext(this);
			}else{
				r1.setPrev(this);
			}
		}
		
		Skeleton.ind--;
		// TODO Auto-generated constructor stub
	}
	/**
	   * CrossRail konstruktor
	   * @param r1 Elõzõ sín.
	   * @param r2 r1-hez tartozó következõ sín.
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public CrossRail(Rail r1, Rail r2, int id) {
		super( id);
		Skeleton.ind++;
		Skeleton.logging("CrossRail: konstruktor");;
				
		if (r1!=null){
			this.r1=r1;
			if (r1.getR2()==null){
				r1.setNext(this);
			}else{
				r1.setPrev(this);
			}
		}
		
		if (r2!=null){
			this.r2=r2;
			if (r2.getR1()==null){
				r2.setPrev(this);
			}else{
				r2.setNext(this);
			}
		}
		
		
		// TODO Auto-generated constructor stub
	}
	/**
	   * CrossRail konstruktor
	   * @param r1 Elõzõ sín.
	   * @param r2 r1-hez tartozó következõ sín.
	   * @param r3 CrossRail 3-as kapcsolódó sine
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public CrossRail(Rail r1, Rail r2, Rail r3, int id) {
		super( id);
		Skeleton.ind++;
		Skeleton.logging("CrossRail: konstruktor");;
		
		if (r1!=null){
			this.r1=r1;
			if (r1.getR2()==null){
				r1.setNext(this);
			}else{
				r1.setPrev(this);
			}
		}
		
		if (r2!=null){
			this.r2=r2;
			if (r2.getR1()==null){
				r2.setPrev(this);
			}else{
				r2.setNext(this);
			}
		}
		
		if (r3!=null){
			this.r3=r3;
			if (r3.getR2()==null){
				r3.setNext(this);
			}else{
				r3.setPrev(this);
			}
		}
		
		
		
		// TODO Auto-generated constructor stub
	}
	/**
	   * CrossRail konstruktor
	   * @param r1 Elõzõ sín.
	   * @param r2 r1-hez tartozó következõ sín.
	   * @param r3 Következõ sín.
	   * @param r4 Következõ sín.
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public CrossRail(Rail r1, Rail r2, Rail r3, Rail r4,  int id) {
		super( id);
		Skeleton.ind++;
		Skeleton.logging("CrossRail: konstruktor");;
		
		if (r1!=null){
			this.r1=r1;
			if (r1.getR2()==null){
				r1.setNext(this);
			}else{
				r1.setPrev(this);
			}
		}
		
		if (r2!=null){
			this.r2=r2;
			if (r2.getR1()==null){
				r2.setPrev(this);
			}else{
				r2.setNext(this);
			}
		}
		
		if (r3!=null){
			this.r3=r3;
			if (r3.getR2()==null){
				r3.setNext(this);
			}else{
				r3.setPrev(this);
			}
		}
		
		if (r4!=null){
			this.r4=r4;
			if (r4.getR1()==null){
				r4.setPrev(this);
			}else{
				r4.setNext(this);
			}
		}
		
		Skeleton.ind--;
		// TODO Auto-generated constructor stub
	}
	/**
	   * R3 settere
	   * @param r beállítandó érték
	   * @return Nothing.
	   */
	public void setR3(Rail r) {
		Skeleton.ind++;
		Skeleton.logging("CrossRail: setR4()");
		
		this.r3=r3;
		if (r3.getR2()==null){
			r3.setNext(this);
		}else{
			r3.setPrev(this);
		}
		
		Skeleton.ind--;
	}
	/**
	   * R4 settere
	   * @param r beállítandó érték
	   * @return Nothing.
	   */
	public void setR4(Rail r) {
		Skeleton.ind++;
		Skeleton.logging("CrossRail: setR4()");
		
		
		this.r4=r4;
		if (r4.getR1()==null){
			r4.setPrev(this);
		}else{
			r4.setNext(this);
		}
		
		Skeleton.ind--;
	}
	/**
	   * CrossRail pass metódusa, mely a továbbítást szolgálja. A rajta tartózkodó TrainELementet továbbítja
	   * @param pre Elõzõ sín, a továbbítás irányát hivatott heghatározni
	   * @param te A továbbítandó Trainelementet határozza meg
	   * @return boolean A lépés sikerességét visszajelzõ igaz/hamis érték
	   */
	@Override
	public boolean pass(Rail pre, TrainElement te) throws IOException{	//a sínen található kocsi mozgatása, a kocsi elsõzõ sínje alapján
		Skeleton.ind++;
		Skeleton.logging("CrossRail: pass()");
		
		if (r1==pre && r2.getOccupied()==false){	//ha az r1 felõl jött, akkor az r2 felé tart, így ha az nem foglalt akkor léphet.
			//a kocsi pointereinek állítása, valamint a sínek foglaltságának állítása:
			te.setRail(r2);
			te.setrPrev(this);			
			r2.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;		//sikeres volt a léptetés
		} else if (r2==pre && r1.getOccupied()==false) {	//ha az r2 felõl jött, akkor az r1 felé tart, így ha az nem foglalt akkor léphet.
			//a kocsi pointereinek állítása, valamint a sínek foglaltságának állítása:
			te.setRail(r1);
			te.setrPrev(this);			
			r1.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;		//sikeres volt a léptetés
		} else if(r3==pre && r4.getOccupied()==false){
			te.setRail(r4);
			te.setrPrev(this);			
			r4.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;
		} else if(r4==pre && r3.getOccupied()==false){
			te.setRail(r3);
			te.setrPrev(this);			
			r3.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;
		}
		else {
			Skeleton.ind--;
			return false;
		}
	}
	/**
	   * Az osztály összes származtatott és egyedi értékeibõl String-et készít a kiíratáshoz
	   *
	   * @return String Az adatokat tartalmazó String
	   */
	@Override
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3)+" R4: "+(r4 != null? r4.id: r4);}
}
