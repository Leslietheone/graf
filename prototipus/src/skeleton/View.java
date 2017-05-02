package skeleton;

import java.awt.Color;

public class View {
	public Color c;
	public int x, y;
	public Color cBefore;
	
	public Color getColor(){
		return c;
	}
	public void setColor(Color c){
		this.c=c;
	}
	public void Draw(){
		
	}
	public Color getCBefore(){
		return cBefore;
	}
	public void setCBefore(Color c){
		this.cBefore=c;
	}
	public Object getA(){
		return null;
	}
	public int getID(){
		return -1;
	}
}
