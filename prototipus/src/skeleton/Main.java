package skeleton;

import java.io.IOException;
/**
* Main met�dus, mely a program bel�p�si pontj�t adja
*
* @author  K�zvil�g�t�SCH
*/
public class Main {
	/**
	   * main f�ggv�ny, mely megh�vja a Skeleton oszt�ly menu() f�ggv�ny�t, amely v�gzi a skeleton lehet�s�geinek ir�ny�t�s�t, �s figyeli a felhaszn�l� parancsait
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
		//itt nem �gy kell majd ind�tani hanem a grafikus fel�let fogja valahogy h�vni a menut...
		//b�r belegondolva nem is lesz menu() h�v�s hiszen a fel�leten lesz a men�...
		
		//Form s=new Form();
		//s.menu();		//Ez a met�dus v�gzi a skeleton lehet�s�geinek ir�ny�t�s�t, �s figyeli a felhaszn�l� parancsait
	}
}
