package skeleton;

import java.io.IOException;
import java.util.Scanner;
/**
* Sínek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class Rail extends Field{
	
	Rail r1, r2; //elõzõ és következõ sín
	boolean occupied; //foglaltság
	/**
	   * Rail konstruktor
	   * @param r1 Elõzõ sín
	   * @param r2 Következõ sín
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public Rail(Rail r1, Rail r2, int id){	//az az eset, mikor a két szomszédos sín már létezik, kölcsönösen összekapcsolódnak
		super(id);
		
		Form.ind++;
		Form.logging("Rail: konstruktor");
		
		//saját pointerek, és a szomszéd sínek pointereinek beállítása:
		
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
		  
		occupied=false;	//a létrehozás pillanatában nem foglaltak a sínek
		Form.ind--;
	}
	/**
	   * Rail konstruktor
	   * @param r1 Elõzõ sín
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public Rail(Rail r1, int id){ //amikor még csak az elõzõ sín létezik, kölcsönösen összekkapcsolja magukat
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
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public Rail(int id){	//az elsõ sín létrehozásához, akinek nincsenek még szomszédjai
		super(id);
		
		Form.ind++;
		Form.logging("Rail: konstruktor");
		
		occupied=false;
		Form.ind--;
	}
	/**
	   * Rail pass metódusa, mely a továbbítást szolgálja. A rajta tartózkodó TrainELementet továbbítja
	   * @param pre Elõzõ sín, a továbbítás irányát hivatott heghatározni
	   * @param te A továbbítandó Trainelementet határozza meg
	   * @return boolean A lépés sikerességét visszajelzõ igaz/hamis érték
	   */
	public boolean pass(Rail pre, TrainElement te) throws IOException{	//a sínen található kocsi mozgatása, a kocsi elsõzõ sínje alapján
		Form.ind++;
		Form.logging("Rail: pass()");
		
		
		if (r1==pre && r2.getOccupied()==false){	//ha az r1 felõl jött, akkor az r2 felé tart, így ha az nem foglalt akkor léphet.
			//a kocsi pointereinek állítása, valamint a sínek foglaltságának állítása:
			te.setRail(r2);
			te.setrPrev(this);			
			r2.setOccupied(true);
			this.setOccupied(false);
			Form.ind--;
			return true;		//sikeres volt a léptetés
		} else if (r2==pre && r1.getOccupied()==false) {	//ha az r2 felõl jött, akkor az r1 felé tart, így ha az nem foglalt akkor léphet.
			//a kocsi pointereinek állítása, valamint a sínek foglaltságának állítása:
			te.setRail(r1);
			te.setrPrev(this);			
			r1.setOccupied(true);
			this.setOccupied(false);
			Form.ind--;
			return true;		//sikeres volt a léptetés
		} else {
			Form.ind--;
			return false;
		}
	}
	/**
	   * Rail: occupied tagváltozó settere
	   * @param b Kívánt érték
	   * @return Nothing.
	   */
	public void setOccupied(boolean b){ //foglaltság állítás
		Form.ind++;
		Form.logging("Rail: setOccupied()");
		
		//ellenkezõjére állítja a foglaltságot
		occupied=b;
		Form.ind--;
	}
	/**
	   * Rail: occupied getter
	   * 
	   * @return boolean A kért igaz/hamis érték 
	   */
	public boolean getOccupied() throws IOException{ //foglaltság lekérdezése
		Form.ind++;
		Form.logging("Rail: getOccupied()");
		//a tesztelhetõség miatt a felhasználó dönti el hogy foglalt legyen e a sín, vagy sem
		Form.ind--;
		return occupied;
		
	}
	/**
	   * Rail: r1 tagváltozó settere
	   * @param r1 Kívánt érték
	   * @return Nothing.
	   */
	public void setPrev(Rail r1){	//következõ sín beállítása, egyoldaló kapcsolatépítés
		Form.ind++;
		Form.logging("Rail: setPrev()");
		
		this.r1 = r1;
		//r1.setNext(this);
		Form.ind--;
	}
	/**
	   * Rail: r2 tagváltozó settere
	   * @param r2 Kívánt érték
	   * @return Nothing.
	   */
	public void setNext(Rail r2){	//elõzõ sín beállítása, egyoldalú kapcsolat építés
		Form.ind++;
		Form.logging("Rail: setNext()");
		
		this.r2 = r2;
		Form.ind--;
	}
	/**
	   * Rail: r1 getter
	   * @return Rail A kért síndarab 
	   */
	public Rail getR1(){return r1;};
	/**
	   * Rail: r2 getter
	   * @return Rail A kért síndarab 
	   */
	public Rail getR2(){return r2;};
	/**
	   * Az osztály összes származtatott és egyedi értékeibõl String-et készít a kiíratáshoz
	   *
	   * @return String Az adatokat tartalmazó String
	   */
	@Override
	public String tostr(){
		String str=super.tostr();
		return (str+" Occupied: "+occupied+" Prev: "+(r1 != null? r1.id: r1)+" Next: "+(r2 != null? r2.id: r2));
	}
}
