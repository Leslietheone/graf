package skeleton;

import java.io.IOException;
import java.util.Scanner;
/**
* Az alagút modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class Tunnel extends Rail {
	
	boolean built=false;
	public static int num;
	Rail r3;	//alagút be/kijárat esetén szükség van egy harmadik kapcsolatra is, ami a végpontokat összekötõ sín(ek)re mutat

	
	/** 
	 * Tunnel konstruktor ha már megvan a három szomszéd hozzá
	 * @param r1 elsõ szomszéd
	 * @param r2 második szomszéd
	 * @param r3 harmadik szomszéd
	 * @param id azonosító
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
	 * Tunnel konstruktor ha már megvan két szomszéd hozzá
	 * @param r1 elsõ szomszéd
	 * @param r2 második szomszéd
	 * @param id azonosító
	 * @return nothing
	 */
	public Tunnel(Rail r1, Rail r2, int id){	
		super(r1,r2, id);
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Tunnel konstruktor ha már megvan egy szomszéd hozzá
	 * @param r1 elsõ szomszéd
	 * @param id azonosító
	 * @return nothing
	 */
	public Tunnel(Rail r1, int id){	
		super(r1, id);
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Tunnel konstruktor ha még nincsm szomszéd hozzá
	 * @param id azonosító
	 * @return nothing
	 */
	public Tunnel(int id) {
		super(id);
		
		Form.ind++;
		Form.logging("Tunnel: konstruktor");
		
		Form.ind--;
	}
	
	/** 
	 * Harmadik szomszéd állítása
	 * @param r3 harmadik szomszéd
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
	 * Alalgút építés. A paraméter az elegút eleje, a függvény az alagút végére hívódik
	 * @param t az alagút másik vége
	 * @return nothing
	 */
	public void buildTunnel(Tunnel t) {	//alagút építése a megadott végpontig
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
	 * Felépített alagútszájak számának állítása. A num statikus adattag
	 * @param n alagúszájak száma
	 * @return nothing
	 */
	public void setNum(int n) { 
		Form.ind++;
		Form.logging("Tunnel: setNum()");
		
		num=n;
		
		Form.ind--;
	}
	
	/**
	 * Alagút felépítettségének állítása
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
	 * Vonat léptetése alagútszájról. Ha az alagút fel van építve akkor keresztülmegy rajta.
	 * @param r a sín amin elõzõleg volt a TrainElement
	 * @param te a léptetendõ voantelem
	 * @return sikerült e a lépés
	 */
	@Override
	public boolean pass(Rail r, TrainElement te) throws IOException{ 
		 Form.ind++;
		 Form.logging("Tunnel: pass()");
			
		 if (built==false && r==r2 && r1.getOccupied()==false){		//ha nincs megépítve alagút és léphet a normál kimenetre
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
			 
		 }else  {				//ha nem tudott lépni a vonat
			 Form.ind--;
			 return false;
		 }
	 }
	
	@Override
	/**
	 * Alagút egy stringben leírja a parmétereit
	 * @return adatok
	 */
	 public String tostr(){return super.tostr()+" R3: "+(r3 != null? r3.id: r3) +" Built: "+built;}
	
}
