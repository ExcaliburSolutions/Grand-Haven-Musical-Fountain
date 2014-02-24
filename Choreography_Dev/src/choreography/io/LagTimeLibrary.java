/**
 * 
 */
package choreography.io;

import choreography.model.lagtime.LagTimeTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author elementsking
 *
 */
public class LagTimeLibrary {
	private static LagTimeLibrary instance;
	private static LagTimeTable lagTimeInstance;
	private final File lagTimeDef = new File("LagTimeDef.txt");
	
	public LagTimeLibrary getInstance() {
		if(instance == null) {
                    try {
                        instance = new LagTimeLibrary();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(LagTimeLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
		return instance;
	}
	
	private LagTimeLibrary() throws FileNotFoundException {
		lagTimeInstance = LagTimeTable.getInstance();
		Scanner fileIn = null;
		try {
                    fileIn = new Scanner(lagTimeDef);
		} catch (FileNotFoundException e) {
                    throw new FileNotFoundException("Cannot find lagTimeDef file");
		}
		
		HashMap<String, Double> delayTimes = new HashMap<>();
		while(fileIn.hasNext()) {
			String line = fileIn.nextLine();
			String[] tokens = line.split("=");
			delayTimes.put(tokens[0], Double.parseDouble(tokens[1]));
		}
		lagTimeInstance.setLagTimes(delayTimes);
	}
}
