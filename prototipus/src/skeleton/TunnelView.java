package skeleton;

import java.awt.Color;

public class TunnelView extends View{
	public Tunnel t;
	public TunnelView(Tunnel t){
		this.t=t;
		this.c=new Color(245,222,179);
	}
	public Object getA(){
		return t;
	}
	public int getID(){
		return t.id;
	}
}
