package skeleton;

public class FieldView extends View  {
	public Field f;
	public FieldView(){
		f=new Field(-2);
	}
	public Object getA(){
		return f;
	}
	public int getID(){
		return f.id;
	}
}
