package skeleton;

import java.io.IOException;
/**
* A keresztez�d� s�nek modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class CrossRail extends Rail{

	public Rail r3;
	public Rail r4;
	
	/**
	   * CrossRail konstruktor
	   * @param r1 El�z� s�n.
	   * @param id Azonos�t�
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
	   * @param r1 El�z� s�n.
	   * @param r2 r1-hez tartoz� k�vetkez� s�n.
	   * @param id Azonos�t�
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
	   * @param r1 El�z� s�n.
	   * @param r2 r1-hez tartoz� k�vetkez� s�n.
	   * @param r3 CrossRail 3-as kapcsol�d� sine
	   * @param id Azonos�t�
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
	   * @param r1 El�z� s�n.
	   * @param r2 r1-hez tartoz� k�vetkez� s�n.
	   * @param r3 K�vetkez� s�n.
	   * @param r4 K�vetkez� s�n.
	   * @param id Azonos�t�
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
	   * @param r be�ll�tand� �rt�k
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
	   * @param r be�ll�tand� �rt�k
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
	   * CrossRail pass met�dusa, mely a tov�bb�t�st szolg�lja. A rajta tart�zkod� TrainELementet tov�bb�tja
	   * @param pre El�z� s�n, a tov�bb�t�s ir�ny�t hivatott heghat�rozni
	   * @param te A tov�bb�tand� Trainelementet hat�rozza meg
	   * @return boolean A l�p�s sikeress�g�t visszajelz� igaz/hamis �rt�k
	   */
	@Override
	public boolean pass(Rail pre, TrainElement te) throws IOException{	//a s�nen tal�lhat� kocsi mozgat�sa, a kocsi els�z� s�nje alapj�n
		Skeleton.ind++;
		Skeleton.logging("CrossRail: pass()");
		
		if (r1==pre && r2.getOccupied()==false){	//ha az r1 fel�l j�tt, akkor az r2 fel� tart, �gy ha az nem foglalt akkor l�phet.
			//a kocsi pointereinek �ll�t�sa, valamint a s�nek foglalts�g�nak �ll�t�sa:
			te.setRail(r2);
			te.setrPrev(this);			
			r2.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;		//sikeres volt a l�ptet�s
		} else if (r2==pre && r1.getOccupied()==false) {	//ha az r2 fel�l j�tt, akkor az r1 fel� tart, �gy ha az nem foglalt akkor l�phet.
			//a kocsi pointereinek �ll�t�sa, valamint a s�nek foglalts�g�nak �ll�t�sa:
			te.setRail(r1);
			te.setrPrev(this);			
			r1.setOccupied(true);
			this.setOccupied(false);
			Skeleton.ind--;
			return true;		//sikeres volt a l�ptet�s
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
	   * Az oszt�ly �sszes sz�rmaztatott �s egyedi �rt�keib�l String-et k�sz�t a ki�rat�shoz
	   *
	   * @return String Az adatokat tartalmaz� String
	   */
	@Override
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3)+" R4: "+(r4 != null? r4.id: r4);}
}
