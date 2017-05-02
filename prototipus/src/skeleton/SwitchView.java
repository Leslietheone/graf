package skeleton;

public class SwitchView extends View{
	public Switch sw;
	public SwitchView(Switch sw){
		this.sw=sw;
	}
	public Object getA(){
		return sw;
	}
	public int getID(){
		return sw.id;
	}
}
