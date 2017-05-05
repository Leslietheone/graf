package skeleton;

import java.awt.Color;

public class CrossRailView extends View{
	public CrossRail cr;
	
	
	CrossRailView(CrossRail cr){
		this.cr=cr;
		this.c=Color.white;
	}
	public Object getA(){
		return cr;
	}
	public int getID(){
		return cr.id;
	}
}
