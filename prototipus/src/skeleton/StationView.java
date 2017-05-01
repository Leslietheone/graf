package skeleton;

public class StationView extends View {
	public Station s;
	public StationView(Station s){
		this.s=s;
	}
	public Object getA(){
		return s;
	}
}
