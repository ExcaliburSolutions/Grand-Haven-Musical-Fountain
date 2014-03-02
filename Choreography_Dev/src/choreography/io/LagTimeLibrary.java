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
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * @author elementsking
 *
 */
public class LagTimeLibrary {
	private static LagTimeLibrary instance;
	private LagTimeTable lagTimeInstance;
	private final File lagTimeDef = new File("LagTimeDef.txt");
	
	public static LagTimeLibrary getInstance() {
		if(instance == null) {
                    try {
                        instance = new LagTimeLibrary();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(LagTimeLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
		return instance;
	}
        
        public LagTimeTable getLagTimes() {
            return lagTimeInstance;
        }
	
	private LagTimeLibrary() throws FileNotFoundException {
		lagTimeInstance = LagTimeTable.getInstance();
		try (Scanner fileIn = new Scanner(lagTimeDef)){
                    HashMap<String, Double> delayTimes = new HashMap<>();
                    while(fileIn.hasNext()) {
                            String line = fileIn.nextLine();
                            String[] tokens = line.split("=");
                            delayTimes.put(tokens[0], Double.parseDouble(tokens[1]));
                    }
		lagTimeInstance.setLagTimes(delayTimes);
		} catch (FileNotFoundException e) {
                    throw new FileNotFoundException("Cannot find lagTimeDef file");
		} catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Your LagTimeDef.txt file may be corrupted.");
                }
		
		
	}

    public void saveLagTimes(String[] dataTable) {
        for(Object object: dataTable) {
            
        }
    }
}
