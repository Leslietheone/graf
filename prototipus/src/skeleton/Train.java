package skeleton;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* A vonatok modelljének megvalósítása
*
* @author  KözvilágítáSCH
*/
public class Train implements Notifiable {
	ArrayList<TrainElement> t;	 //a vonathoz tartozó kocsik listája
	int id;
	Rail r;
	Rail rPrev;
	
	/**
	 * Train konstruktor
	 * @param r A vonat alatti sín
	 * @param rPrev Elõzõ sín
	 * @param id Azonosító
	 * @return nothing*/
	public Train (Rail r, Rail rPrev, int id) {
		this.id=id;
		
		Skeleton.ind++;
		Skeleton.logging("Train: konstruktor");
		this.r=r;
		this.rPrev=rPrev;
		
		t=new ArrayList<TrainElement>();
		
		Skeleton.ind--;
	}
	
	/**
	 * Vonatelem adása a Trainhez
	 * @param te Vonatelem
	 * @return nothing*/
	public void addElement(TrainElement te) {
		t.add(te);
		
		if(!t.isEmpty()) {
			t.get(t.size()-1).setNext(te);
		}
	}
	
	/**
	 * Megvizsgálja üres e a vonatunk
	 * @return üres e*/
	public boolean empty() {	//kocsik ürességének lekérdezése
		Skeleton.ind++;
		Skeleton.logging("Train: empty()");
		
		boolean e=true; //üresvonatka
		Color c1=new Color(0,0,0);
		
		for(int i=0;i<t.size();i++) {
			TrainElement te =t.get(i);
		
			if(te.getPass()){	
				Color ctemp=te.getNowColor();
				if(ctemp.getRGB()!=c1.getRGB()) {
					e=false;
					Skeleton.logging("false, "+t.get(i).getNowColor());
					
				}
			}
		}
		
		Skeleton.ind--;
		return e;
	}

	/**
	 * Train értesül arról, hogy telik az idõ, itt hívja meg az alatta lévõ sínek pass() függvényét
	 * @return nothing*/
	@Override
	public void notifyTrain() {	//lépés jelzésének fogadása
		Skeleton.ind++;
		Skeleton.logging("Train: notifyTrain()");
		
		
		for(TrainElement te : t){		//végigmegy a kocsikon
			boolean b;
			try {
				b = te.r.pass(te.rPrev, te);	//lépteti az adott kocsit
				if(b==false) Skeleton.gameover();	//ha nem sikerült a lépés, vége a játéknak
			} catch (IOException e) {
				
			}
			
		}
		Skeleton.ind--;
	}
}
