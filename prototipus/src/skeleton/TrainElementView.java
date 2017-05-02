package skeleton;

public class TrainElementView extends View {
	public TrainElement te;
	public TrainElementView(TrainElement te){
		this.te=te;
	}
	public Object getA(){
		return te;
	}
	public int getID(){
		return te.id;
	}
}
