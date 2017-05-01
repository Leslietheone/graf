package skeleton;

public class CrossRailView extends View{
	public CrossRail cr;
	CrossRailView(CrossRail cr){
		this.cr=cr;
	}
	public Object getA(){
		return cr;
	}
}
