package skeleton;

import java.awt.Color;

public class RailView extends View {
	public Rail r;
	public RailView(Rail r){
		this.r=r;
		this.c=new Color(139,69,19);
	}
	public Object getA(){
		return r;
	}
	public int getID(){
		return r.id;
	}
}
