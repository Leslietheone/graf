package skeleton;

import java.io.IOException;
/**
* Main metódus, mely a program belépési pontját adja
*
* @author  KözvilágítáSCH
*/
public class Main {
	/**
	   * main függvény, mely meghívja a Skeleton osztály menu() függvényét, amely végzi a skeleton lehetõségeinek irányítását, és figyeli a felhasználó parancsait
	   * @param args Unused
	   * @return Nothing.
	   */
	public static void main(String[] args) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            Form s=new Form();
	         }
	      });
		//itt nem így kell majd indítani hanem a grafikus felület fogja valahogy hívni a menut...
		//bár belegondolva nem is lesz menu() hívás hiszen a felületen lesz a menü...
		
		//Form s=new Form();
		//s.menu();		//Ez a metódus végzi a skeleton lehetõségeinek irányítását, és figyeli a felhasználó parancsait
	}
}
