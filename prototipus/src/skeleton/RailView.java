package skeleton;

public class RailView extends View {
	public Rail r;
	public RailView(Rail r){
		this.r=r;
	}
	public Object getA(){
		return r;
	}
}
