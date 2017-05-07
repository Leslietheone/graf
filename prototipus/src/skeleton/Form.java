package skeleton;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;

import javax.swing.*;

public class Form extends JFrame {
	public static Timer timer;
	public View[][] views;
	
	public static final int ROWS = 11;
	public static final int COLS = 11;
	public static final int CELL_SIZE = 60;
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	public ArrayList<JButton> buttons;
	public Container cp;
	
	// itt lehet majd megadni az elemeket amik szerepelnem a megjelenítésben, nem int[][] hanem view[][] lesz
	//itt lehet majd a view-kon végigiterálni és meghívni az "újrarajzol függvényt"
	//private int[][] board =
	     // {	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0},
	    //	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0},
	    //	  {0,0, 1, 5, 1, 2, 1, 5, 1, 0,0},
	    //	  {0,0, 1, 0, 0, 1, 0, 0, 3, 4,0},
	    //	  {0,0, 1, 0, 0, 1, 0, 0, 1, 0,0},
	    //	  {0,0, 2, 1, 1, 1, 1, 1, 2, 0,0},
	    //	  {0,0, 1, 0, 0, 1, 0, 0, 1, 0,0},
	    //	  {1,1, 2, 0, 0, 1, 0, 0, 5, 0,0},
	    	//  {0,0, 1, 3, 1, 2, 1, 5, 1, 0,0},
	    //	  {0,0, 0, 4, 0, 0, 0, 0, 0, 0,0},
	    //	  {0,0, 0, 0, 0, 0, 0, 0, 0, 0,0}};
	   //1: sín
	   //2: váltó
	   //3: állomás
	   //4: állomáshoz "váró"
	   //5: alagút, többre is mûködik
	   
	   /** ez a konstruktor hozzá, a változásokat majd a függvény végzi */
	public Form(){
		cp=getContentPane();
	    cp.setLayout(new GridLayout(6,3));
	    JButton newgame =new JButton("Új Játék");
	    newgame.addActionListener(new ActionListener()
	    {
    		  public void actionPerformed(ActionEvent e)
    		  {
    			  cp.removeAll();
    			  game();
    			  
    		  }
    	});
	    JButton exit=new JButton("Kilépés");
	    exit.addActionListener(new ActionListener()
    	{
    		  public void actionPerformed(ActionEvent e)
    		  {
    			  System.exit(0);
    		  }
    	});
	    
	    for(int i=1;i<=6;i++){
	    	for(int j=1;j<=3;j++){
	    		
	    	
	    		if(i==2 && j==2){
	    			newgame.setSize(new Dimension(60,30));
	    			cp.add(newgame);
	    		}
	    		else if(i==4 &&j==2){
	    			exit.setSize(new Dimension(60,30));
	    			cp.add(exit);
	    		}
	    		else{
	    			cp.add(new Label());
	    		}
	    	}
	    }
	    
	    cp.setPreferredSize(new Dimension(600,300));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setTitle("közvilágítáSCH");
	    setVisible(true); 
		
	}
	
	
	
	public void game() {
		  trains=new ArrayList<Train>();
		  fields=new ArrayList<Field>();
		  views=new View[ROWS][COLS];
		  
		  
		  for (int row = 0; row < ROWS; ++row) {
		         for (int col = 0; col < COLS; ++col) {
		        
		        	 if(views[row][col]==null)views[row][col]=new FieldView();
		         }
		  }
		  
		  //System.out.println("Ird be a palya szamat!");
		  //Scanner sc=new Scanner(System.in);
		  //String s=sc.next();
		  ArrayList<ArrayList<ArrayList<String>>> db = readFile("test.txt");
		  loadMap(25, db);
		  
		  cp = getContentPane();
	      cp.setLayout(new GridLayout(ROWS, COLS));
	 
	      buttons=new ArrayList<JButton>();
	     
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	        	 
	            JTextField f=new JTextField();
	            String type = views[row][col].getA().getClass().getName();

	            switch(type){
	            case "skeleton.Field":
	            	f.setBackground(Color.green);
	            	f.setEditable(false);
	            	cp.add(f);
	            	break;
	            case "skeleton.CrossRail":
	            	f.setBackground(Color.white);
	            	f.setEditable(false);
	            	cp.add(f);
	            	break;
	            case "skeleton.Rail":
	            	f.setBackground(new Color(139,69,19));
	            	f.setEditable(false);
	            	cp.add(f);
	            	break;
	            case "skeleton.Switch":
	            	final JButton button=new JButton();
	            	button.setForeground(Color.yellow);
	            	
	            	if((views[row][col-1].getA().getClass().getName()!="skeleton.Field") && (views[row-1][col].getA().getClass().getName()!="skeleton.Field") && (views[row+1][col].getA().getClass().getName()!="skeleton.Field")){
	            		if (((Switch)views[row][col].getA()).r1==views[row][col-1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row-1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x255D));
			            	} else {
			            		button.setText(Character.toString((char)0x2557));
			            	}
	            		} else if (((Switch)views[row][col].getA()).r1==views[row-1][col].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row+1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2551));
			            	} else {
			            		button.setText(Character.toString((char)0x255D));
			            	}
	            		} else {
	            			if(((Switch)views[row][col].getA()).r2==views[row][col-1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2557));
			            	} else {
			            		button.setText(Character.toString((char)0x2551));
			            	}
	            		}	
            		} else if((views[row][col+1].getA().getClass().getName()!="skeleton.Field") && (views[row-1][col].getA().getClass().getName()!="skeleton.Field") && (views[row+1][col].getA().getClass().getName()!="skeleton.Field")){
	            		if (((Switch)views[row][col].getA()).r1==views[row][col+1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row-1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x255A));
			            	} else {
			            		button.setText(Character.toString((char)0x2554));
			            	}
	            		} else if (((Switch)views[row][col].getA()).r1==views[row-1][col].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row+1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2551));
			            	} else {
			            		button.setText(Character.toString((char)0x255A));
			            	}
	            		} else {
	            			if(((Switch)views[row][col].getA()).r2==views[row][col+1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2554));
			            	} else {
			            		button.setText(Character.toString((char)0x2551));
			            	}
	            		}	
            		} else if((views[row][col-1].getA().getClass().getName()!="skeleton.Field") && (views[row][col+1].getA().getClass().getName()!="skeleton.Field") && (views[row+1][col].getA().getClass().getName()!="skeleton.Field")){
	            		if (((Switch)views[row][col].getA()).r1==views[row][col-1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row][col+1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2550));
			            	} else {
			            		button.setText(Character.toString((char)0x2557));
			            	}
	            		} else if (((Switch)views[row][col].getA()).r1==views[row][col+1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row+1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2554));
			            	} else {
			            		button.setText(Character.toString((char)0x2550));
			            	}
	            		} else {
	            			if(((Switch)views[row][col].getA()).r2==views[row][col-1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2557));
			            	} else {
			            		button.setText(Character.toString((char)0x2554));
			            	}
	            		}	
            		} else {
            			if (((Switch)views[row][col].getA()).r1==views[row][col-1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row][col+1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x255D));
			            	} else {
			            		button.setText(Character.toString((char)0x2550));
			            	}
	            		} else if (((Switch)views[row][col].getA()).r1==views[row][col+1].getA()){
	            			
	            			if(((Switch)views[row][col].getA()).r2==views[row-1][col].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x2550));
			            	} else {
			            		button.setText(Character.toString((char)0x255A));
			            	}
	            		} else {
	            			if(((Switch)views[row][col].getA()).r2==views[row][col+1].getA() && ((Switch)views[row][col].getA()).dir){
			            		button.setText(Character.toString((char)0x255A));
			            	} else {
			            		button.setText(Character.toString((char)0x255D));
			            	}
	            		}
            		}
            		
	            	
	            	button.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {	
	            			  int x=button.getX()/60;
	            			  int y=button.getY()/60;
	            			  
	            			  if(((Switch)views[y][x].getA()).occupied)return;
	            			  
	            			  ((Switch)views[y][x].getA()).setDir();
	            			  if(views[y][x-1].getA().getClass().getName()!="skeleton.Field" && views[y-1][x].getA().getClass().getName()!="skeleton.Field" && views[y+1][x].getA().getClass().getName()!="skeleton.Field"){
	      	            		if (((Switch)views[y][x].getA()).r1==views[y][x-1].getA()){
	      	            			
	      	            			if(((Switch)views[y][x].getA()).r2==views[y-1][x].getA() && ((Switch)views[y][x].getA()).dir){
	      			            		button.setText(Character.toString((char)0x255D));
	      			            	} else {
	      			            		button.setText(Character.toString((char)0x2557));
	      			            	}
	      	            		} else if (((Switch)views[y][x].getA()).r1==views[y-1][x].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y+1][x].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2551));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x255D));
	    			            	}
	    	            		} else {
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x-1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2557));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2551));
	    			            	}
	    	            		} 
	                  		} else if((views[y][x+1].getA().getClass().getName()!="skeleton.Field") && (views[y-1][x].getA().getClass().getName()!="skeleton.Field") && (views[y+1][x].getA().getClass().getName()!="skeleton.Field")){
	    	            		if (((Switch)views[y][x].getA()).r1==views[y][x+1].getA()){
	    	            			if(((Switch)views[y][x].getA()).r2==views[y-1][x].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x255A));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2554));
	    			            	}
	    	            		} else if (((Switch)views[y][x].getA()).r1==views[y-1][x].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y+1][x].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2551));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x255A));
	    			            	}
	    	            		} else {
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x+1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2554));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2551));
	    			            	}
	    	            		}	
	                		} else if((views[y][x-1].getA().getClass().getName()!="skeleton.Field") && (views[y][x+1].getA().getClass().getName()!="skeleton.Field") && (views[y+1][x].getA().getClass().getName()!="skeleton.Field")){
	    	            		if (((Switch)views[y][x].getA()).r1==views[y][x-1].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x+1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2550));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2557));
	    			            	}
	    	            		} else if (((Switch)views[y][x].getA()).r1==views[y][x+1].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y+1][x].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2554));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2550));
	    			            	}
	    	            		} else {
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x-1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2557));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2554));
	    			            	}
	    	            		}	
	                		} else {
	                			if (((Switch)views[y][x].getA()).r1==views[y][x-1].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x+1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x255D));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x2550));
	    			            	}
	    	            		} else if (((Switch)views[y][x].getA()).r1==views[y][x+1].getA()){
	    	            			
	    	            			if(((Switch)views[y][x].getA()).r2==views[y-1][x].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x2550));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x255A));
	    			            	}
	    	            		} else {
	    	            			if(((Switch)views[y][x].getA()).r2==views[y][x+1].getA() && ((Switch)views[y][x].getA()).dir){
	    			            		button.setText(Character.toString((char)0x255A));
	    			            	} else {
	    			            		button.setText(Character.toString((char)0x255D));
	    			            	}
	    	            		}
	                		}
	            			  
	            		  }
	            	});
	            	button.setBackground(new Color(205,133,63));
	            	
	            	cp.add(button);
	            	break;
	            
	            case "skeleton.Station":            	
	            	final JButton station=new JButton();
	            	station.setForeground(Color.white);
	            	if(((Station)views[row][col].getA()).hasPassenger){
	            		station.setText("P");
	            	} else {
	            		station.setText("");
	            	}
	            	
	            	station.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {
	            		    // meghívja az állomás setPass() függvényét
	            			  
	            			  
	            			  int x=station.getX()/60;
	            			  int y=station.getY()/60;
	            			 
	            			  //Változás elõtt
	            			  //System.out.println(((Station)views[y][x].getA()).hasPassenger);
	            			  
	            			 if(station.getText().equals("P")){
	            				 station.setText("");
	            				 ((Station)views[y][x].getA()).hasPassenger=false;
	            			 }
	            			 else {
	            				 station.setText("P");
	            				 ((Station)views[y][x].getA()).hasPassenger=true;
	            			 }
	            			 
	            			 //Változás után
	            			 //System.out.println(((Station)views[y][x].getA()).hasPassenger);
	            		    
	            		  }
	            	});
	            	
	            	//Az egyszerûség kedvvért nem csináltam külön épületet
	            	station.setBackground(views[row][col].getColor());
	            	
	            	
	            	
	            	cp.add(station);		
	            	break;
	            
	            case "skeleton.Tunnel":
	            	final JButton tunnel=new JButton();
	            	
	            	tunnel.setForeground(Color.black);
	            	tunnel.setBackground(new Color(245,222,179));
	            	tunnel.addActionListener(new ActionListener()
	            	{
	            		  public void actionPerformed(ActionEvent e)
	            		  {
	            			  //bele kell még venni az enum állítgatást, meg az alagútkötögetést de vizuálisan mûködik
	            			  if (tunnel.getText().equals("Built")){
	             				int index=buttons.indexOf(tunnel);
	             				int x1=buttons.get(index).getX()/60;
            		    	    int y1=buttons.get(index).getY()/60;
            		    	    //Elõtte
	             				//System.out.println(((Tunnel)views[y1][x1].getA()).tostr());
            		    	    
	             				if(index%2==1){
	             					int x2=buttons.get(index-1).getX()/60;
	            		    	    int y2=buttons.get(index-1).getY()/60;
	            		    	    if(((Tunnel)views[y1][x1].getA()).occupied || ((Tunnel)views[y2][x2].getA()).occupied) return;
	            		    	    ((Tunnel)views[y2][x2].getA()).setBuilt();
	             					buttons.get(index).setText("");
	             					buttons.get(index-1).setText("");
	             					buttons.remove(index-1);
	             					buttons.remove(index-1);
	             				}else {
	             					int x2=buttons.get(index+1).getX()/60;
	            		    	    int y2=buttons.get(index+1).getY()/60;
	            		    	    if(((Tunnel)views[y1][x1].getA()).occupied || ((Tunnel)views[y2][x2].getA()).occupied) return;
	            		    	    ((Tunnel)views[y2][x2].getA()).setBuilt();
	             					buttons.get(index).setText("");
	             					buttons.get(index+1).setText("");
	             					buttons.remove(index);
	             					buttons.remove(index);
	             				}
	             				((Tunnel)views[y1][x1].getA()).setBuilt();
	             				//Utána
	             				//System.out.println(((Tunnel)views[y1][x1].getA()).tostr());
	             				return;
	            			  }
	            			  
	            			  if(!(buttons.contains(tunnel))){
	            				  buttons.add(tunnel);
	            			  }
	            			  
	            			  
	            			  if ((buttons.size()%2==0)&&buttons.size()!=0){
	            				  	int x1=buttons.get(buttons.size()-1).getX()/60;
	            		    	    int y1=buttons.get(buttons.size()-1).getY()/60;
	            		    	    int x2=buttons.get(buttons.size()-2).getX()/60;
	            		    	    int y2=buttons.get(buttons.size()-2).getY()/60;
	            		    	    if(((Tunnel)views[y1][x1].getA()).occupied || ((Tunnel)views[y2][x2].getA()).occupied) return;
	            		    	    //Építés elõtt
	            		    	    //System.out.println(((Tunnel)views[y2][x2].getA()).tostr());
	            		    	    //System.out.println(((Tunnel)views[y1][x1].getA()).tostr());
	            		    	    ((Tunnel)views[y2][x2].getA()).buildTunnel(((Tunnel)views[y1][x1].getA()));
	            		    	    buttons.get(buttons.size()-1).setText("Built");
	            		    	    buttons.get(buttons.size()-2).setText("Built");
	            		    	    //Építés után
	            		    	    //System.out.println(((Tunnel)views[y2][x2].getA()).tostr());
	            		    	    //System.out.println(((Tunnel)views[y1][x1].getA()).tostr());    		      
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
	      setTitle("közvilágítáSCH");
	      setVisible(true);  
	      
	      
	      /*for(int i=0;i<ROWS;i++) {
	    	  for(int j=0;j<COLS;j++) {
	    		  System.out.print(views[i][j].getID()+" ");
	    	  }
	    	  System.out.println("\n");
	      }*/
	      
	      Rail rtemp1=new Rail(-20);
	      fields.add(rtemp1);
	      Rail rtemp2=new Rail(rtemp1, -21);
	      fields.add(rtemp2);
	      Rail rtemp3=new Rail(rtemp2, -22);
	      fields.add(rtemp3);
	      RailView r=(RailView)views[7][0];
	      Rail rtemp4=new Rail(rtemp3, r.r ,-23);
	      fields.add(rtemp4);
	      
	      addTrain(1, -23, true);
	      addElement(1,1);
	      addElement(1,2);
	      addElement(1,3);
	      addElement(1,4);
	      //setTColor(1, 0,0,0,true);
	      setTColor(2, 128,0,128,true);
	      setTColor(3, 255,0,0,true);
	      setTColor(4, 255,0,255,true);
	      rePaint();
	     
	      timer=new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				step();
				//listTrain();
				//System.out.println(trains.get(0).t.get(0).r.id);
				rePaint();
			}
	    	  
	      });
	      timer.start();
	   
	   }
	
	
	   
	static int ind; //az aktuálisan kiírni kívánt szöveg behúzásának mértékét adja meg
	static boolean log=false;
	/**
	 * megadott stringeket ír ki, az éppen aktuális mértékû behúzással együtt
	 * @param s üzenet
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
	 * A program fõmenüje
	 * @return nothing
	 */
	public void menu() throws IOException {		//menüpontok felsorolása, és a választottnak megfelelõ metódus elindítása
		System.out.println("1. Play\n2. Test\n");
		ArrayList<ArrayList<ArrayList<String>>> outputs=readFile("output.txt");
		String s;
		Scanner sc=new Scanner(System.in);
			switch (sc.next()) {
				case "1": log=true; play(); break;
				case "2": log=false;
					System.out.println("Irja be a teszt szamat (0-24)\n");
					s=sc.next();
					System.out.println("Helyes mûködés esetén a következõt kell kapnia:\n");
					for(int i=0; i<outputs.get(Integer.parseInt(s)).size(); i++){
						System.out.println(outputs.get(Integer.parseInt(s)).get(i).get(0));
					}
					System.out.println("\nA kapott eredmény:\n");
					test(Integer.parseInt(s)); break;
			}
			sc.close();
	}
	
	
	/**
	 * Teszteléshez használatos fájlok beolvasása
	 * @param filename Fájl név
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
	 * Teszt betöltése
	 * @param i teszt sorszám
	 * @return nothing*/
	public void test(int i){
		ArrayList<ArrayList<ArrayList<String>>> db = readFile("test.txt");
		ArrayList<ArrayList<ArrayList<String>>> cmds = readFile("cmds.txt");
		loadCmds(i, cmds, db);	
	}
	
	/**
	 * A teszthez szükséges pálya beolvasása
	 * @param id azonosító
	 * @param db pálya adatbázis
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
					views[Integer.parseInt(cField.get(4))][Integer.parseInt(cField.get(5))]=new RailView((Rail)fields.get(fields.size()-1));
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
					views[Integer.parseInt(cField.get(5))][Integer.parseInt(cField.get(6))]=new SwitchView((Switch)fields.get(fields.size()-1));
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
					views[Integer.parseInt(cField.get(8))][Integer.parseInt(cField.get(9))]=new StationView((Station)fields.get(fields.size()-1));
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
					views[Integer.parseInt(cField.get(5))][Integer.parseInt(cField.get(6))]=new TunnelView((Tunnel)fields.get(fields.size()-1));
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
					views[Integer.parseInt(cField.get(6))][Integer.parseInt(cField.get(7))]=new CrossRailView((CrossRail)fields.get(fields.size()-1));
					break;
			}
		}

	}
	
	/**
	 * Parancsok beolvasása az adott teszthez
	 * @param id azonosító
	 * @param cmds parancsok a teszthez
	 * @param db pálya
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
	 * Játék menüje
	 * @return nothing*/
	public void play() throws IOException {		//menüpontok felsorolása, és a választottnak megfelelõ metódus elindítása
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
				//default: üres kocsi. ezzel lehet állítani
			}
		}
			sc.close();
	}
	
	/**
	 * Pálya építése játék módban
	 * @return nothing*/
	
	
	/**
	 * Új vonat adása a pályára
	 * @param id vonatazonosító
	 * @param RailId sínazonosító
	 * @param dir irány
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
	 * Új Element a Trainhez. WARNING: egy létrejött train üres, amíg nem adsz hozzá elemet!
	 * @param TRid vonat azonosítója
	 * @param TEid vonatelem azonosítója
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
					te.setPrev(trains.get(i).t.get(trains.get(i).t.size()-1));
					trains.get(i).t.add(te);
					r.setOccupied(true);
				}
				break;
			}
		}
		
	}
	
	/**
	 * Váltóállítás
	 * @param Sid váltó id
	 * @return nothing*/
	public void setSwitch(int Sid) {
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==Sid)  {
				Switch s=(Switch)fields.get(i);
				s.setDir();
				fields.remove(i);
				fields.add(s);
			}
		}
	}
	
	/**
	 * Tunnel építés, a megadott két Tunnel közt
	 * @param ENDid alagútvég id
	 * @param BEGINid alagút eleje id
	 * @return nothing*/
	public void buildTunnels(int ENDid, int BEGINid) {
		Tunnel begin=new Tunnel(0);
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==BEGINid) {
				begin=(Tunnel) fields.get(i);
				fields.remove(i);
				break;
			}
		}
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).id==ENDid) {
				Tunnel end=(Tunnel) fields.get(i);
				end.buildTunnel(begin);
				fields.remove(i);
				fields.add(end);
				fields.add(begin);
				break;
			}
		}
	}
	
	/**
	 * Utast pakolhatsz az állomásra. DEFAULT: nincs.
	 * @param Sid állomás id
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
				fields.remove(i);
				fields.add(s);
			}
		}
	}
	/**
	 * Vonatok listázása
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
	 * Vonatelemek listázása
	 * @return nothing*/
	public void listTrainElement() {
		for(int i=0;i<trains.size();i++) {
			for(int j=0;j<trains.get(i).t.size();j++) {
				System.out.println(trains.get(i).t.get(j).tostr());
				
			}
		}
	}
	/**
	 * Pálya listázása
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
	 * Léptet, figyeli hogy nyertél e
	 * @return nothing*/
	public void step() {
		boolean b=false;
		for(int i=0;i<trains.size();i++) {
			trains.get(i).notifyTrain();
			if(trains.get(i).empty()) {
				b=true;
			}
		}
		if(b==true) Form.win();
	}
	
	/**
	 * Kiválasztott TrainElement szín állítás, mert: default mindegyik üres
	 * @param id vonatelem id
	 * @param r RGB red
	 * @param g RGB green
	 * @param b RGB blue
	 * @param first elsõ e a vonatban a kocsi
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
	 * Gyõzelem
	 * @return nothing*/
	public static void win() {	//gyõzelem jelzõ
		//ind++;
		//System.out.println("Nyertél :3l");
		//logging("Skeleton: win()");
		//ind--;
		JOptionPane.showMessageDialog(null, "Nyertél!");
		System.exit(0);
	}
	/**
	 * Vesztettél
	 * @return nothing*/
	static public void gameover() {	//vereség jelzõ
		//ind++;
		//System.out.println("Vesztettél :Pl");
		//logging("Skeleton: gameover()");
		//ind--;
		//System.exit(0);
		JOptionPane.showMessageDialog(null, "Vesztettél!");
		System.exit(0);
	}
	
	
	
	
	public void rePaint(){
		for(int i=0;i<trains.size();i++) {
			for(int j=0;j<trains.get(i).t.size();j++) {
				for(int k=0;k<ROWS;k++) {
					for(int l=0;l<COLS;l++) {
						if(trains.get(i).t.get(j).r.id==views[k][l].getID() ) {
							
							cp.getComponent(k*11+l).setBackground(trains.get(i).t.get(j).cNow);
							int id=trains.get(i).t.get(j).rPrev.id;
							//System.out.println("Prev "+id);
							for(int m=0;m<ROWS;m++) {
								for(int n=0;n<COLS;n++) {
									if(views[m][n].getID()==id) {
										cp.getComponent(m*11+n).setBackground(views[m][n].c);
										
									}
								}
							}
						}
						else if(trains.get(i).t.get(j).r.id==-1){
							int id=trains.get(i).t.get(j).rPrev.id;
							//System.out.println("Prev "+id);
							for(int m=0;m<ROWS;m++) {
								for(int n=0;n<COLS;n++) {
									if(views[m][n].getID()==id) {
										cp.getComponent(m*11+n).setBackground(views[m][n].c);
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
