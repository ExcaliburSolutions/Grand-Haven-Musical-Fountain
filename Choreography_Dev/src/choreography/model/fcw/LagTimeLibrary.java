/**
 * 
 */
package choreography.model.fcw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author elementsking
 *
 */
public class LagTimeLibrary {
	private static LagTimeLibrary instance;
	private static LagTimeTable lagTimeInstance;
	private File lagTimeDef = new File("LagTimeDef.txt");
	
	public LagTimeLibrary getInstance() {
		if(instance == null) {
			instance = new LagTimeLibrary();
		}
		return instance;
	}
	
	private LagTimeLibrary() {
		lagTimeInstance = LagTimeTable.getInstance();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(lagTimeDef);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(fileIn.hasNext()) {
			double audioDelay, peacockDelay, voiceDelay, bazookaDelay,
			ringDelay, candelabraDelay, sweepDelay, ftCurtDelay, bkCurtDelay;
			
		}
	}
}
