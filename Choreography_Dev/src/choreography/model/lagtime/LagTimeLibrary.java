/**
 * 
 */
package choreography.model.lagtime;

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
		double audioDelay = 0, peacockDelay = 0, voiceDelay = 0, bazookaDelay = 0,
		ringDelay = 0, candelabraDelay = 0, sweepDelay = 0, ftCurtDelay = 0, 
		bkCurtDelay = 0;
		
		double[] delayTimes = new double[9];
		
		lagTimeInstance = LagTimeTable.getInstance();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(lagTimeDef);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int index = 0;
		while(fileIn.hasNext()) {
			String line = fileIn.nextLine();
			String[] tokens = line.split("=");
			delayTimes[index] = Double.parseDouble(tokens[1]);
		}
		lagTimeInstance.setLagTimes(delayTimes);
	}
}
