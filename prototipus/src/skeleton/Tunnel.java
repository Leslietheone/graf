package skeleton;

import java.io.IOException;
import java.util.Scanner;
/**
* Az alag�t modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class Tunnel extends Rail {
	
	boolean built=false;
	public static int num;
	Rail r3;	//alag�t be/kij�rat eset�n sz�ks�g van egy harmadik kapcsolatra is, ami a v�gpontokat �sszek�t� s�n(ek)re mutat

	
	/** 
	 * Tunnel konstruktor ha m�r megvan a h�rom szomsz�d hozz�
	 * @param r1 els� szomsz�d
	 * @param r2 m�sodik szomsz�d
	 * @param r3 harmadik szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Tunnel(Rail r1, Rail r2, Rail r3, int id){	
		super(r1,r2, id);
		if (r3!=null){
			this.r3=r3;
			if (r3.getR1()==null){
				r3.setPrev(this);
			}else{
				r3.setNext(this);
			}
		}
		
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Tunnel konstruktor ha m�r megvan k�t szomsz�d hozz�
	 * @param r1 els� szomsz�d
	 * @param r2 m�sodik szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Tunnel(Rail r1, Rail r2, int id){	
		super(r1,r2, id);
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Tunnel konstruktor ha m�r megvan egy szomsz�d hozz�
	 * @param r1 els� szomsz�d
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Tunnel(Rail r1, int id){	
		super(r1, id);
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Tunnel konstruktor ha m�g nincsm szomsz�d hozz�
	 * @param id azonos�t�
	 * @return nothing
	 */
	public Tunnel(int id) {
		super(id);
		
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Harmadik szomsz�d �ll�t�sa
	 * @param r3 harmadik szomsz�d
	 * @return nothing
	 */
	public void setR3(Rail r3){	
		Form.ind++;
		Form.logging("Tunnel: setR3()");
		
		this.r3=r3;
		if (r3.getR1()==null){
			r3.setPrev(this);
		}else{
			r3.setNext(this);
		}
		Form.ind--;
	}
	
	/** 
	 * Alalg�t �p�t�s. A param�ter az eleg�t eleje, a f�ggv�ny az alag�t v�g�re h�v�dik
	 * @param t az alag�t m�sik v�ge
	 * @return nothing
	 */
	public void buildTunnel(Tunnel t) {	//alag�t �p�t�se a megadott v�gpontig
		Form.ind++;
		Form.logging("Tunnel: buildTunnel()");
		
		r3=new Rail(null, null, -1);
		r3.setNext(this);
		r3.setPrev(t);
		t.setBuilt();
		t.setR3(r3);
		
		
		
		this.setBuilt();
		this.setR3(r3);
		
		//r3.setNext(this);
		
		//r3.r1=t;
		//r3.r2=this;
		
		Form.ind--;
	}
	
	/**
	 * Fel�p�tett alag�tsz�jak sz�m�nak �ll�t�sa. A num statikus adattag
	 * @param n alag�sz�jak sz�ma
	 * @return nothing
	 */
	public void setNum(int n) { 
		Form.ind++;
		Form.logging("Tunnel: setNum()");
		
		num=n;
		
		Form.ind--;
	}
	
	/**
	 * Alag�t fel�p�tetts�g�nek �ll�t�sa
	 * @return nothing*/
	public void setBuilt(){	
		Form.ind++;
		Form.logging("Tunnel: setBuilt()");
		System.out.println(built);
		built=!built;
		System.out.println(built);
		Form.ind--;
	}
	
	/**
	 * Vonat l�ptet�se alag�tsz�jr�l. Ha az alag�t fel van �p�tve akkor kereszt�lmegy rajta.
	 * @param r a s�n amin el�z�leg volt a TrainElement
	 * @param te a l�ptetend� voantelem
	 * @return siker�lt e a l�p�s
	 */
	@Override
	public boolean pass(Rail r, TrainElement te) throws IOException{ 
		 Form.ind++;
		 Form.logging("Tunnel: pass()");
			
		 if (built==false && r==r2 && r1.getOccupied()==false){		//ha nincs meg�p�tve alag�t �s l�phet a norm�l kimenetre
			te.setRail(r1);
			te.setrPrev(this);
		 	this.r1.setOccupied(true);
			this.setOccupied(false);
			
			Form.ind--;
			return true;
			
		 }else if(built==false && r==r1 && r2.getOccupied()==false){
			te.setRail(r2);
			te.setrPrev(this);
			this.r2.setOccupied(true);
			this.setOccupied(false);
				
			Form.ind--;
			 
			return true; 
		 }
		 else if(built==true){
			 if(te.rPrev.id != -1){
				 if(r3.getOccupied()==false){
					 te.setRail(r3);
					 te.setrPrev(this);
					 te.setVis();
					 this.r3.setOccupied(true);
				   	 this.setOccupied(false);
					
				   	 Form.ind--;
					 return true;
				 }else{
					 return false;
				 }
			 }else if(r2.getOccupied()==false){
				 te.setRail(r2);
				 te.setrPrev(this);
				 te.setVis();
				 this.r2.setOccupied(true);
			   	 this.setOccupied(false);
				
			   	 Form.ind--;
				 return true;
			 } else {
				 return false;
			 }
			 
		 }else  {				//ha nem tudott l�pni a vonat
			 Form.ind--;
			 return false;
		 }
	 }
	
	@Override
	/**
	 * Alag�t egy stringben le�rja a parm�tereit
	 * @return adatok
	 */
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3) +" Built: "+built;}
	
}
