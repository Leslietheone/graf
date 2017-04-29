package skeleton;
/**
* Interface a vonat notifyTrain metódusához
*
* @author  KözvilágítáSCH
*/
public interface Notifiable {
	// sajnos nem nevezhettem notify()-nak a függvényt névütközés miatt:
	//úgy tûnik van ilyen alapfüggvénye a javának
	void notifyTrain(); 
}
