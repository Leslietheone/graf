package skeleton;

import java.awt.Color;

/**
* A vonatelemek modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/

public class TrainElement {
	public Rail r;
	public Rail rPrev;
	public Color cBefore;
	public Color cNow;
	int id; //kell id a parancsokhoz!
	boolean passenger=false;
	public boolean vis=true;
	public TrainElement e1;
	public TrainElement e2;
	
	
	/**
	   * TrainElement konstruktor
	   * @param c Szín
	   * @param id Azonosító
	   * @return Nothing.
	   */
	public TrainElement(Color c, int id){
		this.id=id;
		this.cBefore=c;
		this.cNow=c;
		Form.ind++;
		Form.logging("TrainElement: Konstruktor");
		Form.ind--;		
	}
	
	/**
	 * TrainElement jelenlegi színének állítása
	 * @param c Szín
	 * @return nothing
	 */
	public void setNowColor(Color c){	//kocsi jelenlegi színének állítása
		this.cNow=c;
		Form.ind++;
		Form.logging("TrainElement: setNowColor()"+c);
		Form.ind--;
	}
	
	/**
	 * TrainElement elõzõ színének állítása
	 * @param c Szín
	 * @return nothing
	 */
	public void setBeforeColor(Color c){	//kocsi eredeti színének állítása
		this.cBefore=c;
		Form.ind++;
		Form.logging("TrainElement: setColor()"+c);
		Form.ind--;
	}  
	
	/**
	 * TrainElement jelenlegi színének lekérdezése
	 * @return Szín
	 */
	public Color getNowColor(){	//kocsi eredeti színének olvasása
		Form.ind++;
		Form.logging("TrainElement: getNowColor()");
		Form.ind--;
		return this.cNow;
	}
	
	/**
	 * TrainElement korábbi színének lekérdezése
	 * @return Szín*/
	public Color getBeforeColor(){	//kocsi jelenlegi színének olvasása
		Form.ind++;
		Form.logging("TrainElement: getBeforeColor()");
		Form.ind--;
		return this.cBefore;
	}
	
	/**
	 * TrainElement alatt lévõ sín állítása
	 * @param r Sín
	 * @return nothing*/
	public void setRail(Rail r){	//aktuális sín
		Form.ind++;
		Form.logging("TrainElement: setRail()");
		Form.ind--;
		
		this.r=r;
	}
	
	/**
	 * Annak a sínnek a beállítása, akin elõzõleg tartózkodott a TrainElement. Így tudja, honan jött
	 * @param r Rail
	 * @return nothing
	 */
	public void setrPrev(Rail r){	//elõzõ sín (a haladási irány eldöntéséhez)
		Form.ind++;
		Form.logging("TrainElement: setrPrev()");
		Form.ind--;
		
		this.rPrev=r;
	}
	
	/**
	 * A TrainElement láthatóságának állítása
	 * @return nothing*/
	public void setVis(){	//láthatóság állítása
		Form.ind++;
		Form.logging("TrainElement: setVis()");
		Form.ind--;
		if(vis==false) vis=true;
		else vis=false;
	}
	
	/**
	 * A vonatban lévõ következõ TrainElementet ismernie kell, itt lehet beállítani
	 * @param e következõ TrainElement
	 * @return nothing*/
	public void setNext(TrainElement e){	//következõ kocsi
		Form.ind++;
		Form.logging("TrainElement: setNext()");
		
	
		this.e2=e;
		e.setPrev(this);
		
		Form.ind--;
	}
	
	/**
	 * Vonatban lévõ elõzõ TrainElement állítása
	 * @param e elõzõ TrainElement
	 * @return nothing*/
	public void setPrev(TrainElement e){	//elõzõ kocsi
		
		Form.ind++;
		Form.logging("TrainElement: setPrev()");
		
		this.e1=e;
		
		Form.ind--;
	}
	
	/**
	 * Sín lekérdezése
	 * @return Sín*/
	public Rail getR(){return r;}
	/**
	 * Elõzõ sín lekérdezése
	 * @return elõzõ sín*/
	public Rail getrPrev(){return rPrev;}
	/**
	 * Utasok meglétének állítása
	 * @param b Van e utas
	 * @return nothing*/
	public void setPass(boolean b){passenger=b;} //szállíthat-e utast vagy sem;
	/**
	 * Utasok létének lekérdezése
	 * @return van e utas*/
	public boolean getPass(){return passenger;} //milyen fajta
	/**
	 * TrainElement adatainak visszaadása egy stringben
	 * @return adatok*/
	public String tostr(){
		return "TrainElement id: "+this.id+
			", NowColor: "+this.getNowColor()+
			", Visible: "+this.vis+
			", hasPassanger: "+this.passenger+
			", CurrentRail: "+(this.getR() != null? this.getR().id: this.getR())+
			", PrevRail: "+(this.getrPrev() != null? this.getrPrev().id: this.getrPrev());}
}
