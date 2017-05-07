package skeleton;

import java.io.IOException;
import java.util.Scanner;
/**
* S�nek modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class Rail extends Field{
	
	Rail r1, r2; //el�z� �s k�vetkez� s�n
	boolean occupied; //foglalts�g
	/**
	   * Rail konstruktor
	   * @param r1 El�z� s�n
	   * @param r2 K�vetkez� s�n
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public Rail(Rail r1, Rail r2, int id){	//az az eset, mikor a k�t szomsz�dos s�n m�r l�tezik, k�lcs�n�sen �sszekapcsol�dnak
		super(id);
		
		Form.ind++;
		Form.logging("Rail: konstruktor");
		
		//saj�t pointerek, �s a szomsz�d s�nek pointereinek be�ll�t�sa:
		
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
		  
		occupied=false;	//a l�trehoz�s pillanat�ban nem foglaltak a s�nek
		Form.ind--;
	}
	/**
	   * Rail konstruktor
	   * @param r1 El�z� s�n
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public Rail(Rail r1, int id){ //amikor m�g csak az el�z� s�n l�tezik, k�lcs�n�sen �sszekkapcsolja magukat
		super(id);
		
		Form.ind++;
		Form.logging("Rail: konstruktor");
		
		if (r1!=null){
			this.r1=r1;
			if (r1.getR2()==null){
				r1.setNext(this);
			}else{
				r1.setPrev(this);
			}
		}
		
		occupied=false;
		Form.ind--;
	}
	/**
	   * Rail konstruktor
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public Rail(int id){	//az els� s�n l�trehoz�s�hoz, akinek nincsenek m�g szomsz�djai
		super(id);
		
		Form.ind++;
		Form.logging("Rail: konstruktor");
		
		occupied=false;
		Form.ind--;
	}
	/**
	   * Rail pass met�dusa, mely a tov�bb�t�st szolg�lja. A rajta tart�zkod� TrainELementet tov�bb�tja
	   * @param pre El�z� s�n, a tov�bb�t�s ir�ny�t hivatott heghat�rozni
	   * @param te A tov�bb�tand� Trainelementet hat�rozza meg
	   * @return boolean A l�p�s sikeress�g�t visszajelz� igaz/hamis �rt�k
	   */
	public boolean pass(Rail pre, TrainElement te) throws IOException{	//a s�nen tal�lhat� kocsi mozgat�sa, a kocsi els�z� s�nje alapj�n
		Form.ind++;
		Form.logging("Rail: pass()");
		
		
		if (r1==pre && r2.getOccupied()==false){	//ha az r1 fel�l j�tt, akkor az r2 fel� tart, �gy ha az nem foglalt akkor l�phet.
			//a kocsi pointereinek �ll�t�sa, valamint a s�nek foglalts�g�nak �ll�t�sa:
			te.setRail(r2);
			te.setrPrev(this);			
			r2.setOccupied(true);
			this.setOccupied(false);
			Form.ind--;
			return true;		//sikeres volt a l�ptet�s
		} else if (r2==pre && r1.getOccupied()==false) {	//ha az r2 fel�l j�tt, akkor az r1 fel� tart, �gy ha az nem foglalt akkor l�phet.
			//a kocsi pointereinek �ll�t�sa, valamint a s�nek foglalts�g�nak �ll�t�sa:
			te.setRail(r1);
			te.setrPrev(this);			
			r1.setOccupied(true);
			this.setOccupied(false);
			Form.ind--;
			return true;		//sikeres volt a l�ptet�s
		} else {
			Form.ind--;
			return false;
		}
	}
	/**
	   * Rail: occupied tagv�ltoz� settere
	   * @param b K�v�nt �rt�k
	   * @return Nothing.
	   */
	public void setOccupied(boolean b){ //foglalts�g �ll�t�s
		Form.ind++;
		Form.logging("Rail: setOccupied()");
		
		//ellenkez�j�re �ll�tja a foglalts�got
		occupied=b;
		Form.ind--;
	}
	/**
	   * Rail: occupied getter
	   * 
	   * @return boolean A k�rt igaz/hamis �rt�k 
	   */
	public boolean getOccupied() throws IOException{ //foglalts�g lek�rdez�se
		Form.ind++;
		Form.logging("Rail: getOccupied()");
		//a tesztelhet�s�g miatt a felhaszn�l� d�nti el hogy foglalt legyen e a s�n, vagy sem
		Form.ind--;
		return occupied;
		
	}
	/**
	   * Rail: r1 tagv�ltoz� settere
	   * @param r1 K�v�nt �rt�k
	   * @return Nothing.
	   */
	public void setPrev(Rail r1){	//k�vetkez� s�n be�ll�t�sa, egyoldal� kapcsolat�p�t�s
		Form.ind++;
		Form.logging("Rail: setPrev()");
		
		this.r1 = r1;
		//r1.setNext(this);
		Form.ind--;
	}
	/**
	   * Rail: r2 tagv�ltoz� settere
	   * @param r2 K�v�nt �rt�k
	   * @return Nothing.
	   */
	public void setNext(Rail r2){	//el�z� s�n be�ll�t�sa, egyoldal� kapcsolat �p�t�s
		Form.ind++;
		Form.logging("Rail: setNext()");
		
		this.r2 = r2;
		Form.ind--;
	}
	/**
	   * Rail: r1 getter
	   * @return Rail A k�rt s�ndarab 
	   */
	public Rail getR1(){return r1;};
	/**
	   * Rail: r2 getter
	   * @return Rail A k�rt s�ndarab 
	   */
	public Rail getR2(){return r2;};
	/**
	   * Az oszt�ly �sszes sz�rmaztatott �s egyedi �rt�keib�l String-et k�sz�t a ki�rat�shoz
	   *
	   * @return String Az adatokat tartalmaz� String
	   */
	@Override
	public String tostr(){
		String str=super.tostr();
		return (str+" Occupied: "+occupied+" Prev: "+(r1 != null? r1.id: r1)+" Next: "+(r2 != null? r2.id: r2));
	}
}
