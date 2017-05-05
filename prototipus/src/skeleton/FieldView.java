package skeleton;

import java.awt.Color;

public class FieldView extends View  {
	public Field f;
	public Color c;
	
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
