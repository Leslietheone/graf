package skeleton;

public class TunnelView extends View{
	public Tunnel t;
	public TunnelView(Tunnel t){
		this.t=t;
	}
	public Object getA(){
		return t;
	}
	public int getID(){
		return t.id;
	}
}
