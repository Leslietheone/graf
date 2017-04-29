package skeleton;

import java.awt.Color;

/**
* A vonatelemek modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
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
	   * @param c Sz�n
	   * @param id Azonos�t�
	   * @return Nothing.
	   */
	public TrainElement(Color c, int id){
		this.id=id;
		this.cBefore=c;
		this.cNow=c;
		Skeleton.ind++;
		Skeleton.logging("TrainElement: Konstruktor");
		Skeleton.ind--;		
	}
	
	/**
	 * TrainElement jelenlegi sz�n�nek �ll�t�sa
	 * @param c Sz�n
	 * @return nothing
	 */
	public void setNowColor(Color c){	//kocsi jelenlegi sz�n�nek �ll�t�sa
		this.cNow=c;
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setNowColor()"+c);
		Skeleton.ind--;
	}
	
	/**
	 * TrainElement el�z� sz�n�nek �ll�t�sa
	 * @param c Sz�n
	 * @return nothing
	 */
	public void setBeforeColor(Color c){	//kocsi eredeti sz�n�nek �ll�t�sa
		this.cBefore=c;
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setColor()"+c);
		Skeleton.ind--;
	}  
	
	/**
	 * TrainElement jelenlegi sz�n�nek lek�rdez�se
	 * @return Sz�n
	 */
	public Color getNowColor(){	//kocsi eredeti sz�n�nek olvas�sa
		Skeleton.ind++;
		Skeleton.logging("TrainElement: getNowColor()");
		Skeleton.ind--;
		return this.cNow;
	}
	
	/**
	 * TrainElement kor�bbi sz�n�nek lek�rdez�se
	 * @return Sz�n*/
	public Color getBeforeColor(){	//kocsi jelenlegi sz�n�nek olvas�sa
		Skeleton.ind++;
		Skeleton.logging("TrainElement: getBeforeColor()");
		Skeleton.ind--;
		return this.cBefore;
	}
	
	/**
	 * TrainElement alatt l�v� s�n �ll�t�sa
	 * @param r S�n
	 * @return nothing*/
	public void setRail(Rail r){	//aktu�lis s�n
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setRail()");
		Skeleton.ind--;
		
		this.r=r;
	}
	
	/**
	 * Annak a s�nnek a be�ll�t�sa, akin el�z�leg tart�zkodott a TrainElement. �gy tudja, honan j�tt
	 * @param r Rail
	 * @return nothing
	 */
	public void setrPrev(Rail r){	//el�z� s�n (a halad�si ir�ny eld�nt�s�hez)
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setrPrev()");
		Skeleton.ind--;
		
		this.rPrev=r;
	}
	
	/**
	 * A TrainElement l�that�s�g�nak �ll�t�sa
	 * @return nothing*/
	public void setVis(){	//l�that�s�g �ll�t�sa
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setVis()");
		Skeleton.ind--;
		if(vis==false) vis=true;
		else vis=false;
	}
	
	/**
	 * A vonatban l�v� k�vetkez� TrainElementet ismernie kell, itt lehet be�ll�tani
	 * @param e k�vetkez� TrainElement
	 * @return nothing*/
	public void setNext(TrainElement e){	//k�vetkez� kocsi
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setNext()");
		
	
		this.e2=e;
		e.setPrev(this);
		
		Skeleton.ind--;
	}
	
	/**
	 * Vonatban l�v� el�z� TrainElement �ll�t�sa
	 * @param e el�z� TrainElement
	 * @return nothing*/
	public void setPrev(TrainElement e){	//el�z� kocsi
		
		Skeleton.ind++;
		Skeleton.logging("TrainElement: setPrev()");
		
		this.e1=e;
		
		Skeleton.ind--;
	}
	
	/**
	 * S�n lek�rdez�se
	 * @return S�n*/
	public Rail getR(){return r;}
	/**
	 * El�z� s�n lek�rdez�se
	 * @return el�z� s�n*/
	public Rail getrPrev(){return rPrev;}
	/**
	 * Utasok megl�t�nek �ll�t�sa
	 * @param b Van e utas
	 * @return nothing*/
	public void setPass(boolean b){passenger=b;} //sz�ll�that-e utast vagy sem;
	/**
	 * Utasok l�t�nek lek�rdez�se
	 * @return van e utas*/
	public boolean getPass(){return passenger;} //milyen fajta
	/**
	 * TrainElement adatainak visszaad�sa egy stringben
	 * @return adatok*/
	public String tostr(){
		return "TrainElement id: "+this.id+
			", NowColor: "+this.getNowColor()+
			", Visible: "+this.vis+
			", hasPassanger: "+this.passenger+
			", CurrentRail: "+(this.getR() != null? this.getR().id: this.getR())+
			", PrevRail: "+(this.getrPrev() != null? this.getrPrev().id: this.getrPrev());}
}
