package skeleton;

public class StationView extends View {
	public Station s;
	public StationView(Station s){
		this.s=s;
		this.setCBefore(s.clr);
		this.setColor(s.clr);
		this.c=s.clr;
	}
	public Object getA(){
		return s;
	}
	public int getID(){
		return s.id;
	}
}
