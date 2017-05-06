package skeleton;

import java.io.IOException;
/**
* A v�lt�k modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class Switch extends Rail{

	Rail r3;
	boolean dir;
	
	/**
	 * Switch konstruktor h�rom megl�v� szomsz�ddal
	 * @param r1 els� szomsz�d
	 * @param r2 m�sodik szomsz�d
	 * @param r3 harmadik szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Switch(Rail r1, Rail r2, Rail r3, int id){	//alap ki �s bemenet be�ll�t�sa
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
		
		Skeleton.ind++;
		Skeleton.logging("Switch: konstruktor");
		Skeleton.ind--;
	}
	
	/**
	 * Switch konstruktor k�t megl�v� szomsz�ddal
	 * @param r1 els� szomsz�d
	 * @param r2 m�sodik szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Switch(Rail r1, Rail r2, int id){	//alap ki �s bemenet be�ll�t�sa
		super(r1,r2, id);
		
		dir=false;
		
		Skeleton.ind++;
		Skeleton.logging("Switch: konstruktor");
		Skeleton.ind--;
	}
	
	/**
	 * Switch konstruktor egy megl�v� szomsz�ddal
	 * @param r1 els� szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Switch(Rail r1, int id){		//csak az el�z� s�n ismert
		super(r1, id);
		dir=false;
		
		Skeleton.ind++;
		Skeleton.logging("Switch: konstruktor");
		Skeleton.ind--;
	}
	
	/**
	 * A v�lt� ir�ny�nak �ll�t�sa
	 * @return nothing*/
	public void setDir(){		//ir�ny �ll�t�sa
		Skeleton.ind++;
		Skeleton.logging("Switch: setDir()");
		
		if(dir==false) dir=true;
		else dir=false;
		
		 Skeleton.ind--;
	 }
	 
	/**
	 * Tov�bb�tja a vonatot annak megfelel�en, hogy merre �ll
	 * @param r A vonatelem el�z� s�ne
	 * @param te a vonatelem
	 * @return siker�lt e*/
	 public boolean pass(Rail r, TrainElement te) throws IOException{ //kocsi l�ptet�se
		 Skeleton.ind++;
		 Skeleton.logging("Switch: pass()");
			
		 if (r==r1 && dir==true && r2.getOccupied()==false){ 	//ha a kiv�lasztott ir�nyban szabad a kimenet
			te.setRail(r2);
			te.setrPrev(this);	
		 	this.r2.setOccupied(true);
			this.setOccupied(false);
			
			Skeleton.ind--;
			return true;
		 }else if(r==r1 && dir==false && r3.getOccupied()==false){		//ha a kiv�lasztott ir�nyban szabad a kimenet
			 te.setRail(r3);
			 te.setrPrev(this);	
			 this.r3.setOccupied(true);
		   	 this.setOccupied(false);
			
		   	 Skeleton.ind--;
		   	 return true;
		 }else if(r==r2 && dir==true && r1.getOccupied()==false) {
			 te.setRail(r1);
			 te.setrPrev(this);	
			 this.r1.setOccupied(true);
			 this.setOccupied(false);
				
			 Skeleton.ind--;
			 return true;
		 }else if(r==r3 && dir==false && r1.getOccupied()==false) {
			 te.setRail(r1);
			 te.setrPrev(this);	
			 this.r1.setOccupied(true);
			 this.setOccupied(false);
				
			 Skeleton.ind--;
			 return true;
		 }
		 
		 else  {		//ha nem tudott l�pni
			 Skeleton.ind--;
			 return false;
		 }
	 }
	 
	 /**
	  * Harmadik szomsz�d �ll�t�sa
	  * @param r3 harmadik szomsz�d
	  * @return nothing*/
	 public void setR3(Rail r3){
		 Skeleton.ind++;
		 Skeleton.logging("Switch: setR3()");
		 this.r3=r3;
		 //r3 ir�ny�nak eld�nt�se
		 if (r3.getR1()==null){
			 r3.setPrev(this);
		 } else {
			 r3.setNext(this);
		 }
		 
		 Skeleton.ind--;
	 }
	 
	 /**
	  * M�sodik szomsz�d �ll�t�sa
	  * @param r2 m�sodik szomsz�d
	  * @return nothing*/
	 public void setR2(Rail r2) {
		 Skeleton.ind++;
		 Skeleton.logging("Switch: setR2()");
		 this.r2=r2;
		 Skeleton.ind--;
	 }
	 /**
	  * Stringk�nt visszaadja a Switch adatait
	  * @return adatok*/
	 @Override
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3)+ " Direction: " + (dir == true? "right": "left");}
}
