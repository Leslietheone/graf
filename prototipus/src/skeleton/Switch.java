package skeleton;

import java.io.IOException;
/**
* A váltók modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class Switch extends Rail{

	Rail r3;
	boolean dir;
	
	/**
	 * Switch konstruktor három meglévõ szomszéddal
	 * @param r1 elsõ szomszéd
	 * @param r2 második szomszéd
	 * @param r3 harmadik szomszéd
	 * @param id azonosító
	 * @return nothing
	 */
	public Switch(Rail r1, Rail r2, Rail r3, int id){	//alap ki és bemenet beállítása
		super(r1,r2, id);

		if (r3!=null){
			this.r3=r3;
			if (r3.getR1()==null){
				r3.setPrev(this);
			}else{
				r3.setNext(this);
			}
		}
		
		dir=true;
		
		Form.ind++;
		Form.logging("Switch: konstruktor");
		Form.ind--;
	}
	
	/**
	 * Switch konstruktor két meglévõ szomszéddal
	 * @param r1 elsõ szomszéd
	 * @param r2 második szomszéd
	 * @param id azonosító
	 * @return nothing
	 */
	public Switch(Rail r1, Rail r2, int id){	//alap ki és bemenet beállítása
		super(r1,r2, id);
		
		dir=false;
		
		Form.ind++;
		Form.logging("Switch: konstruktor");
		Form.ind--;
	}
	
	/**
	 * Switch konstruktor egy meglévõ szomszéddal
	 * @param r1 elsõ szomszéd
	 * @param id azonosító
	 * @return nothing
	 */
	public Switch(Rail r1, int id){		//csak az elõzõ sín ismert
		super(r1, id);
		dir=false;
		
		Form.ind++;
		Form.logging("Switch: konstruktor");
		Form.ind--;
	}
	
	/**
	 * A váltó irányának állítása
	 * @return nothing*/
	public void setDir(){		//irány állítása
		Form.ind++;
		Form.logging("Switch: setDir()");
		
		if(dir==false) dir=true;
		else dir=false;
		
		 Form.ind--;
	 }
	 
	/**
	 * Továbbítja a vonatot annak megfelelõen, hogy merre áll
	 * @param r A vonatelem elõzõ síne
	 * @param te a vonatelem
	 * @return sikerült e*/
	 public boolean pass(Rail r, TrainElement te) throws IOException{ //kocsi léptetése
		 Form.ind++;
		 Form.logging("Switch: pass()");
			
		 if (r==r1 && dir==true && r2.getOccupied()==false){ 	//ha a kiválasztott irányban szabad a kimenet
			te.setRail(r2);
			te.setrPrev(this);	
		 	this.r2.setOccupied(true);
			this.setOccupied(false);
			
			Form.ind--;
			return true;
		 }else if(r==r1 && dir==false && r3.getOccupied()==false){		//ha a kiválasztott irányban szabad a kimenet
			 te.setRail(r3);
			 te.setrPrev(this);	
			 this.r3.setOccupied(true);
		   	 this.setOccupied(false);
			
		   	 Form.ind--;
		   	 return true;
		 }else if(r==r2 && dir==true && r1.getOccupied()==false) {
			 te.setRail(r1);
			 te.setrPrev(this);	
			 this.r1.setOccupied(true);
			 this.setOccupied(false);
				
			 Form.ind--;
			 return true;
		 }else if(r==r3 && dir==false && r1.getOccupied()==false) {
			 te.setRail(r1);
			 te.setrPrev(this);	
			 this.r1.setOccupied(true);
			 this.setOccupied(false);
				
			 Form.ind--;
			 return true;
		 }
		 
		 else  {		//ha nem tudott lépni
			 Form.ind--;
			 return false;
		 }
	 }
	 
	 /**
	  * Harmadik szomszéd állítása
	  * @param r3 harmadik szomszéd
	  * @return nothing*/
	 public void setR3(Rail r3){
		 Form.ind++;
		 Form.logging("Switch: setR3()");
		 this.r3=r3;
		 //r3 irányának eldöntése
		 if (r3.getR1()==null){
			 r3.setPrev(this);
		 } else {
			 r3.setNext(this);
		 }
		 
		 Form.ind--;
	 }
	 
	 /**
	  * Második szomszéd állítása
	  * @param r2 második szomszéd
	  * @return nothing*/
	 public void setR2(Rail r2) {
		 Form.ind++;
		 Form.logging("Switch: setR2()");
		 this.r2=r2;
		 Form.ind--;
	 }
	 /**
	  * Stringként visszaadja a Switch adatait
	  * @return adatok*/
	 @Override
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3)+ " Direction: " + (dir == true? "right": "left");}
}
