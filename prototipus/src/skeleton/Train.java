package skeleton;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* A vonatok modellj�nek megval�s�t�sa
*
* @author  K�zvil�g�t�SCH
*/
public class Train implements Notifiable {
	ArrayList<TrainElement> t;	 //a vonathoz tartoz� kocsik list�ja
	int id;
	Rail r;
	Rail rPrev;
	
	/**
	 * Train konstruktor
	 * @param r A vonat alatti s�n
	 * @param rPrev El�z� s�n
	 * @param id Azonos�t�
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
	 * Vonatelem ad�sa a Trainhez
	 * @param te Vonatelem
	 * @return nothing*/
	public void addElement(TrainElement te) {
		t.add(te);
		
		if(!t.isEmpty()) {
			t.get(t.size()-1).setNext(te);
		}
	}
	
	/**
	 * Megvizsg�lja �res e a vonatunk
	 * @return �res e*/
	public boolean empty() {	//kocsik �ress�g�nek lek�rdez�se
		Skeleton.ind++;
		Skeleton.logging("Train: empty()");
		
		boolean e=true; //�resvonatka
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
	 * Train �rtes�l arr�l, hogy telik az id�, itt h�vja meg az alatta l�v� s�nek pass() f�ggv�ny�t
	 * @return nothing*/
	@Override
	public void notifyTrain() {	//l�p�s jelz�s�nek fogad�sa
		Skeleton.ind++;
		Skeleton.logging("Train: notifyTrain()");
		
		
		for(TrainElement te : t){		//v�gigmegy a kocsikon
			boolean b;
			try {
				b = te.r.pass(te.rPrev, te);	//l�pteti az adott kocsit
				if(b==false) Skeleton.gameover();	//ha nem siker�lt a l�p�s, v�ge a j�t�knak
			} catch (IOException e) {
				
			}
			
		}
		Skeleton.ind--;
	}
}
