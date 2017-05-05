package skeleton;

import java.awt.Color;

public class SwitchView extends View{
	public Switch sw;
	public SwitchView(Switch sw){
		this.sw=sw;
		this.c=new Color(205,133,63);
	}
	public Object getA(){
		return sw;
	}
	public int getID(){
		return sw.id;
	}
}
