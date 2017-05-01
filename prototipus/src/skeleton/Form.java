package skeleton;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.*;

public class Form extends JFrame {
	public Timer timer;
	public ArrayList<View> views;
	
	public static final int ROWS = 11;
	public static final int COLS = 11;
	public static final int CELL_SIZE = 60;
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

	 
	// itt lehet majd megadni az elemeket amik szerepelnem a megjelen�t�sben, nem int[][] hanem view[][] lesz
	//itt lehet majd a view-kon v�gigiter�lni �s megh�vni az "�jrarajzol f�ggv�nyt"
	private int[][] board =
	      {	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0},
	    	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0},
	    	  {0,0, 1, 5, 1, 2, 1, 5, 1, 0,0},
	    	  {0,0, 1, 0, 0, 1, 0, 0, 3, 4,0},
	    	  {0,0, 1, 0, 0, 1, 0, 0, 1, 0,0},
	    	  {0,0, 2, 1, 1, 1, 1, 1, 2, 0,0},
	    	  {0,0, 1, 0, 0, 1, 0, 0, 1, 0,0},
	    	  {1,1, 2, 0, 0, 1, 0, 0, 5, 0,0},
	    	  {0,0, 1, 3, 1, 2, 1, 5, 1, 0,0},
	    	  {0,0, 0, 4, 0, 0, 0, 0, 0, 0,0},
	    	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0}};
	   //1: s�n
	   //2: v�lt�
	   //3: �llom�s
	   //4: �llom�shoz "v�r�"
	   //5: alag�t, t�bbre is m�k�dik
	   
	   /** ez a konstruktor hozz�, a v�ltoz�sokat majd a f�ggv�ny v�gzi */
	   public Form() {
		  trains=new ArrayList<Train>();
		  fields=new ArrayList<Field>();
		  views=new ArrayList<View>();
		   
		   
		  Container cp = getContentPane();
	      cp.setLayout(new GridLayout(ROWS, COLS));
	 
	      ArrayList<JButton> buttons=new ArrayList();
	     
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	        
	            JTextField f=new JTextField();
	            int number = board[row][col];
	            //itt majd nem sz�mok lesznek hanem t�pusok
	            switch(number){
	            case 0:
	            	f.setBackground(Color.green);
	            	f.setEditable(false);
	            	cp.add(f);
	            	break;
	            case 1:
	            	f.setBackground(new Color(139,69,19));
	            	f.setEditable(false);
	            	cp.add(f);
	            	break;
	            case 2:
	            	JButton button=new JButton("1");
	            	button.setForeground(Color.yellow);
	            	button.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {
	            		    if(button.getText().equals("2"))button.setText("1");
	            		    else button.setText("2");
	            		  }
	            	});
	            	button.setBackground(new Color(205,133,63));
	            	cp.add(button);
	            	break;
	            
	            case 3:
	            	f.setBackground(new Color(139,69,19));
	            	f.setEditable(false);
	            	cp.add(f);
	            	//stationview van itt hozz�adva
	            	break;
	            case 4:            	
	            	JButton station=new JButton();
	            	station.setForeground(Color.white);
	            	station.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {
	            		    // megh�vja az �llom�s setPass() f�ggv�ny�t
	            			 
	            			 if(station.getText().equals("P")) station.setText("");
	            			 else station.setText("P");
	            		    
	            		  }
	            	});
	            	for (int i=row-1;i<row+2;i++)
	            		for (int j=col-1;j<col+2;j++)
	            			//nem 3-asra vizsg�lunk hanem station t�pusra
	            			if (board[i][j]==3){
	            				//lek�ri a Stationview sz�n�t, az indexeket a board-b�l kapja
	            				station.setBackground(Color.red);
	            			}
	            	cp.add(station);		
	            	break;
	            
	            case 5:
	            	JButton tunnel=new JButton();
	            	
	            	tunnel.setForeground(Color.black);
	            	tunnel.setBackground(new Color(245,222,179));
	            	tunnel.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {
	            			  //bele kell m�g venni az enum �ll�tgat�st, meg az alag�tk�t�get�st de vizu�lisan m�k�dik
	            			  if (tunnel.getText().equals("Built")){
	             				int index=buttons.indexOf(tunnel);
	             				if(index%2==1){
	             					buttons.get(index).setText("");
	             					buttons.get(index-1).setText("");
	             					buttons.remove(index-1);
	             					buttons.remove(index-1);
	             				}else {
	             					buttons.get(index).setText("");
	             					buttons.get(index+1).setText("");
	             					buttons.remove(index);
	             					buttons.remove(index);
	             				}
	             				return;
	            			  }
	            			  
	            			  buttons.add(tunnel);
	            			  
	            			  if (buttons.size()%2==0){
	            		    	for (JButton b : buttons) {
	            		    	    b.setText("Built");
	            		    	}            		      
	            		    }         			    
	            		  }
	            	});
	            	f.setEditable(false);
	            	cp.add(tunnel);
	            	break;
	            }

	         }
	      }
	      
	      cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      pack();
	      setTitle("k�zvil�g�t�SCH");
	      setVisible(true);      
	   }
	static int ind; //az aktu�lisan ki�rni k�v�nt sz�veg beh�z�s�nak m�rt�k�t adja meg
	static boolean log=true;
	/**
	 * megadott stringeket �r ki, az �ppen aktu�lis m�rt�k� beh�z�ssal egy�tt
	 * @param s �zenet
	 * @return nothing*/
	public static void logging(String s) {	
		if (log){
			for(int i=0;i<ind;i++) System.out.print("   ");
			System.out.print(s+"\n");
		}
	}
	
	
	static ArrayList<Train> trains;
	ArrayList<Field> fields;
	/**
	 * Konstruktor
	 * @return nothing*/
	//public Form() {
		///trains=new ArrayList<Train>();
		//fields=new ArrayList<Field>();
		//views=new ArrayList<View>();
	//}
	
	/**
	 * A program f�men�je
	 * @return nothing
	 */
	public void menu() throws IOException {		//men�pontok felsorol�sa, �s a v�lasztottnak megfelel� met�dus elind�t�sa
		System.out.println("1. Play\n2. Test\n");
		ArrayList<ArrayList<ArrayList<String>>> outputs=readFile("output.txt");
		String s;
		Scanner sc=new Scanner(System.in);
			switch (sc.next()) {
				case "1": log=true; play(); break;
				case "2": log=false;
					System.out.println("Irja be a teszt szamat (0-24)\n");
					s=sc.next();
					System.out.println("Helyes m�k�d�s eset�n a k�vetkez�t kell kapnia:\n");
					for(int i=0; i<outputs.get(Integer.parseInt(s)).size(); i++){
						System.out.println(outputs.get(Integer.parseInt(s)).get(i).get(0));
					}
					System.out.println("\nA kapott eredm�ny:\n");
					test(Integer.parseInt(s)); break;
			}
			sc.close();
	}
	
	
	/**
	 * Tesztel�shez haszn�latos f�jlok beolvas�sa
	 * @param filename F�jl n�v
	 * @return parancsok beolvasva*/
	private ArrayList<ArrayList<ArrayList<String>>> readFile(String filename)
	{
	  ArrayList<ArrayList<ArrayList<String>>> maps = new ArrayList<ArrayList<ArrayList<String>>>();
	  try
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    
	    
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	    	if (line.startsWith("begin")){
	    	  String line2;
	    	  ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
	    	  while (!(line2 = reader.readLine()).matches("end")){
	    		  String[] cmds = line2.trim().split(";");
	    		  ArrayList<String> commands = new ArrayList<String>();
	    		  for (int i=0;i<cmds.length;i++){
	    			  commands.add(cmds[i]);
	    		  }
	    		  map.add(commands);
	    	  }
	    	  maps.add(map);
	      }
	    }
	    reader.close();
	    return maps;
	  }
	  catch (Exception e)
	  {
	    e.printStackTrace();
	    return null;
	  }
	}
	
	/**
	 * Teszt bet�lt�se
	 * @param i teszt sorsz�m
	 * @return nothing*/
	public void test(int i){
		ArrayList<ArrayList<ArrayList<String>>> db = readFile("test.txt");
		ArrayList<ArrayList<ArrayList<String>>> cmds = readFile("cmds.txt");
		loadCmds(i, cmds, db);	
	}
	
	/**
	 * A teszthez sz�ks�ges p�lya beolvas�sa
	 * @param id azonos�t�
	 * @param db p�lya adatb�zis
	 * @return nothing*/
	public void loadMap(int id, ArrayList<ArrayList<ArrayList<String>>> db) {
		
		ArrayList<ArrayList<String>>field=db.get(id);
		for (int i=0; i<field.size(); i++){
			ArrayList<String> cField= field.get(i);
			Rail r1=null;
			Rail r2=null;
			Rail r3=null;
			Rail r4=null;
			switch(cField.get(0)){
				case "Rail": 
					r1=null;
					r2=null;
					for(int j=0;j<fields.size();j++) if(!(cField.get(1).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(1))) r1=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(2).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(2))) r2=(Rail) fields.get(j);
					Rail r=new Rail(r1,r2,Integer.parseInt(cField.get(3)));
					fields.add(r);
					views.add(new RailView((Rail)fields.get(fields.size()-1)));
					break;
				case "Switch": 
					r1=null;
					r2=null;
					r3=null;
					for(int j=0;j<fields.size();j++) if(!(cField.get(1).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(1))) r1=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(2).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(2))) r2=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(3).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(3))) r3=(Rail) fields.get(j);
					Switch sw=new Switch(r1, r2, r3, Integer.parseInt(cField.get(4)));
					fields.add(sw);
					views.add(new SwitchView((Switch)fields.get(fields.size()-1)));
					break;
				case "Station":
					r1=null;
					r2=null;
					for(int j=0;j<fields.size();j++) if(!(cField.get(4).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(4))) r1=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(5).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(5))) r2=(Rail) fields.get(j);
					boolean b;
					if(cField.get(6).matches("false")) b=false;
					else b=true;
					Station st=new Station(new Color(Integer.parseInt(cField.get(1)),Integer.parseInt(cField.get(2)), Integer.parseInt(cField.get(3))), r1, r2, b, Integer.parseInt(cField.get(7)));
					fields.add(st);
					views.add(new StationView((Station)fields.get(fields.size()-1)));
					break;
				case "Tunnel": 
					r1=null;
					r2=null;
					r3=null;
					for(int j=0;j<fields.size();j++) if(!(cField.get(1).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(1))) r1=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(2).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(2))) r2=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(3).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(3))) r3=(Rail) fields.get(j);
					Tunnel tu=new Tunnel(r1,r2,r3, Integer.parseInt(cField.get(4)));
					fields.add(tu);
					views.add(new TunnelView((Tunnel)fields.get(fields.size()-1)));
					break;
				case "CrossRail": 
					r1=null;
					r2=null;
					r3=null;
					r4=null;
					for(int j=0;j<fields.size();j++) if(!(cField.get(1).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(1))) r1=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(2).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(2))) r2=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(3).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(3))) r3=(Rail) fields.get(j);
					for(int j=0;j<fields.size();j++) if(!(cField.get(4).matches("null")) && fields.get(j).id==Integer.parseInt(cField.get(4))) r4=(Rail) fields.get(j);
					CrossRail cr=new CrossRail(r1,r2,r3,r4, Integer.parseInt(cField.get(5)));
					fields.add(cr);
					views.add(new CrossRailView((CrossRail)fields.get(fields.size()-1)));
					break;
			}
		}

	}
	
	/**
	 * Parancsok beolvas�sa az adott teszthez
	 * @param id azonos�t�
	 * @param cmds parancsok a teszthez
	 * @param db p�lya
	 * @return nothing*/
	public void loadCmds(int id, ArrayList<ArrayList<ArrayList<String>>> cmds, ArrayList<ArrayList<ArrayList<String>>> db) {
		ArrayList<ArrayList<String>>cCmds=cmds.get(id);
		
		for (int i=0; i<cCmds.size(); i++){
			ArrayList<String> cCmd= cCmds.get(i);
			//System.out.println(">> "+cCmd.get(0));
			switch (cCmd.get(0)) {
				case "loadMap": loadMap(Integer.parseInt(cCmd.get(1)), db); break;
				case "addTrain": addTrain(Integer.parseInt(cCmd.get(1)), Integer.parseInt(cCmd.get(2)), Boolean.parseBoolean(cCmd.get(3)));break;
				case "addElement": addElement(Integer.parseInt(cCmd.get(1)), Integer.parseInt(cCmd.get(2)));break;
				case "setSwitch": setSwitch(Integer.parseInt(cCmd.get(1)));break;
				case "buildTunnel": buildTunnels(Integer.parseInt(cCmd.get(1)), Integer.parseInt(cCmd.get(2))); break;
				case "setPassenger": setPassengers(Integer.parseInt(cCmd.get(1)), cCmd.get(2)) ;break;
				case "listTrain": listTrain();break;
				case "listTrainElement": listTrainElement();break;
				case "listMap":listMap(); break;
				case "step": step(); break;
				case "setColor": setTColor(Integer.parseInt(cCmd.get(1)), Integer.parseInt(cCmd.get(2)), Integer.parseInt(cCmd.get(3)), Integer.parseInt(cCmd.get(4)), true);break;
			}
		}		
	}
	
	/**
	 * J�t�k men�je
	 * @return nothing*/
	public void play() throws IOException {		//men�pontok felsorol�sa, �s a v�lasztottnak megfelel� met�dus elind�t�sa
		ArrayList<ArrayList<ArrayList<String>>> db = readFile("test.txt");
		Scanner sc=new Scanner(System.in);
		
		while(sc.hasNext()){
			String[] s=sc.next().split(";");
			System.out.println(">> "+s[0]);
			switch (s[0]) {
				case "loadMap": loadMap(Integer.parseInt(s[1]), db); break;
				case "addTrain": addTrain(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Boolean.parseBoolean(s[3]));break;
				case "addElement": addElement(Integer.parseInt(s[1]), Integer.parseInt(s[2]));break;
				case "setSwitch": setSwitch(Integer.parseInt(s[1]));break;
				case "buildTunnel": buildTunnels(Integer.parseInt(s[1]), Integer.parseInt(s[2])); break;
				case "setPassenger": setPassengers(Integer.parseInt(s[1]), s[2]) ;break;
				case "listTrain": listTrain();break;
				case "listTrainElement": listTrainElement();break;
				case "listMap":listMap(); break;
				case "step": step(); break;
				case "setColor": setTColor(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), true);break;
				//default: �res kocsi. ezzel lehet �ll�tani
			}
		}
			sc.close();
	}
	
	/**
	 * P�lya �p�t�se j�t�k m�dban
	 * @return nothing*/
	
	
	/**
	 * �j vonat ad�sa a p�ly�ra
	 * @param id vonatazonos�t�
	 * @param RailId s�nazonos�t�
	 * @param dir ir�ny
	 * @return nothing*/
	public void addTrain(int id, int RailId, boolean dir) {
		Rail rail=new Rail(0);
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==RailId) rail=(Rail) fields.get(i); 
		}
		if (dir){
			trains.add(new Train(rail,rail.r1, id));
		}else{
			trains.add(new Train(rail,rail.r2, id));
		}
	}
	
	/**
	 * �j Element a Trainhez. WARNING: egy l�trej�tt train �res, am�g nem adsz hozz� elemet!
	 * @param TRid vonat azonos�t�ja
	 * @param TEid vonatelem azonos�t�ja
	 * @return nothing*/
	public void addElement(int TRid, int TEid) {
		Rail r=new Rail(0);
		for(int i=0;i<trains.size()+1;i++) {
			if(trains.get(i).id==TRid) {
				boolean dir=false;
				if(trains.get(i).rPrev==trains.get(i).r.getR1()) dir=true;
				
				if(trains.get(i).t.isEmpty()){
					TrainElement te=new TrainElement(new Color(0,0,0),TEid);
					te.setRail(trains.get(i).r);
					te.setrPrev(trains.get(i).rPrev);
					trains.get(i).t.add(te);
					trains.get(i).r.setOccupied(true);
					views.add(new TrainElementView(trains.get(i).t.get(trains.get(i).t.size()-1)));
				}
				else {
					r=trains.get(i).t.get(trains.get(i).t.size()-1).rPrev;
					TrainElement te=new TrainElement(new Color(0,0,0),TEid);
					te.setRail(r);
					if (dir) {
						te.setrPrev(r.r1);
					}else{
						te.setrPrev(r.r2);
					}
					trains.get(i).t.add(te);
					r.setOccupied(true);
					views.add(new TrainElementView(trains.get(i).t.get(trains.get(i).t.size()-1)));
				}
				break;
			}
		}
		
	}
	
	/**
	 * V�lt��ll�t�s
	 * @param Sid v�lt� id
	 * @return nothing*/
	public void setSwitch(int Sid) {
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==Sid)  {
				Switch s=(Switch)fields.get(i);
				s.setDir();
				for(int j=0;j<views.size();j++){
					if(views.get(j).getA().getClass().getName()=="skeleton.Switch")  {
						if(((Switch)views.get(j).getA()).id==Sid)  {
							views.remove(j);
						}
					}
				}
				fields.remove(i);
				fields.add(s);
				views.add(new SwitchView((Switch)fields.get(fields.size()-1)));
			}
		}
	}
	
	/**
	 * Tunnel �p�t�s, a megadott k�t Tunnel k�zt
	 * @param ENDid alag�tv�g id
	 * @param BEGINid alag�t eleje id
	 * @return nothing*/
	public void buildTunnels(int ENDid, int BEGINid) {
		Tunnel begin=new Tunnel(0);
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==BEGINid) {
				begin=(Tunnel) fields.get(i);
				for(int j=0;j<views.size();j++){
					if(views.get(j).getA().getClass().getName()=="skeleton.Tunnel")  {
						if(((Tunnel)views.get(j).getA()).id==BEGINid)  {
							views.remove(j);
						}
					}
				}
				fields.remove(i);
				break;
			}
		}
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==ENDid) {
				Tunnel end=(Tunnel) fields.get(i);
				end.buildTunnel(begin);
				for(int j=0;j<views.size();j++){
					if(views.get(j).getA().getClass().getName()=="skeleton.Tunnel")  {
						if(((Tunnel)views.get(j).getA()).id==ENDid)  {
							views.remove(j);
						}
					}
				}
				fields.remove(i);
				fields.add(end);
				views.add(new TunnelView((Tunnel)fields.get(fields.size()-1)));
				fields.add(begin);
				views.add(new TunnelView((Tunnel)fields.get(fields.size()-1)));
				break;
			}
		}
	}
	
	/**
	 * Utast pakolhatsz az �llom�sra. DEFAULT: nincs.
	 * @param Sid �llom�s id
	 * @param b van e utas
	 * @return nothing*/
	public void setPassengers(int Sid, String b) {
		boolean p; 
		if(b.matches("false")) p=false;
		else p=true;
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==Sid)  {
				Station s=(Station) fields.get(i);
				s.setPassanger(p);
				for(int j=0;j<views.size();j++){
					if(views.get(j).getA().getClass().getName()=="skeleton.Station")  {
						if(((Station)views.get(j).getA()).id==Sid)  {
							views.remove(j);
						}
					}
				}
				fields.remove(i);
				fields.add(s);
				views.add(new StationView((Station)fields.get(fields.size()-1)));
			}
		}
	}
	/**
	 * Vonatok list�z�sa
	 * @return nothing*/
	public void listTrain() {
		for(int i=0;i<trains.size();i++) {
			System.out.print("Train id: "+trains.get(i).id);
			for (int j=0; j<trains.get(i).t.size();j++){
				System.out.print(", Element id: "+trains.get(i).t.get(j).id);
			}
			System.out.print("\n");
		}
	}
	/**
	 * Vonatelemek list�z�sa
	 * @return nothing*/
	public void listTrainElement() {
		for(int i=0;i<trains.size();i++) {
			for(int j=0;j<trains.get(i).t.size();j++) {
				System.out.println(trains.get(i).t.get(j).tostr());
				
			}
		}
	}
	/**
	 * P�lya list�z�sa
	 * @return nothing*/
	public void listMap() {
		for(int i=0;i<fields.size();i++) {
			
			
			String str=fields.get(i).getClass().getName();
			switch (str) {
			case "skeleton.Rail":
				Rail tmp=(Rail)fields.get(i);
				System.out.println(str+" "+tmp.tostr());
				break;
			case "skeleton.Station":
				Station tmp2=(Station)fields.get(i);
				System.out.println(str+" "+tmp2.tostr());
				break;
			case "skeleton.CrossRail":
				CrossRail tmp3=(CrossRail)fields.get(i);
				System.out.println(str+" "+ tmp3.tostr());
				break;
			case "skeleton.Switch":
				Switch tmp4=(Switch)fields.get(i);
				System.out.println(str+" "+ tmp4.tostr());
				break;
			case "skeleton.Tunnel":
				Tunnel tmp5=(Tunnel)fields.get(i);
				System.out.println(str+" "+ tmp5.tostr());
				break;
			default: System.out.println("Field: "+fields.get(i).getClass().getName()+", "
					+ "Field id: "+fields.get(i).id+" ");
			}
			
		}
	}
	
	/**
	 * L�ptet, figyeli hogy nyert�l e
	 * @return nothing*/
	public void step() {
		boolean b=false;
		for(int i=0;i<trains.size();i++) {
			trains.get(i).notifyTrain();
			if(trains.get(i).empty()) {
				b=true;
			}
		}
		if(b==true) win();
	}
	
	/**
	 * Kiv�lasztott TrainElement sz�n �ll�t�s, mert: default mindegyik �res
	 * @param id vonatelem id
	 * @param r RGB red
	 * @param g RGB green
	 * @param b RGB blue
	 * @param first els� e a vonatban a kocsi
	 * @return nothing*/
	public static void setTColor(int id, int r, int g, int b, boolean first) {
		for(int i=0;i<trains.size();i++) {
			for(int j=0;j<trains.get(i).t.size();j++) {
				if (trains.get(i).t.get(j).id==id) {
					trains.get(i).t.get(j).setNowColor(new Color(r,g,b));
					trains.get(i).t.get(j).setPass(true);
					if (first){
						trains.get(i).t.get(j).setBeforeColor(new Color(r,g,b));
					} else if(r==0 && g==0 && b==0) {
						trains.get(i).t.get(j).setPass(false);
					}
				}
				
			}
		}
	}
	
	/**
	 * Gy�zelem
	 * @return nothing*/
	public void win() {	//gy�zelem jelz�
		ind++;
		System.out.println("Nyert�l :3");
		logging("Skeleton: win()");
		ind--;
	}
	/**
	 * Vesztett�l
	 * @return nothing*/
	static public void gameover() {	//veres�g jelz�
		ind++;
		System.out.println("Vesztett�l :P");
		logging("Skeleton: gameover()");
		ind--;
		System.exit(0);
	}
	
	
	
	
	public void rePaint(){
		
	}
}
