/**
 * 
 */
package choreography.io;

import choreography.model.fcw.FCW;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * This class represents a dictionary which uses a String to identify the fixture 
 * and returns the address of that fixture. Then, it looks up the appropriate table
 * using an intermediate table. Then, looks in that table and retrieves the 
 * appropriate command.
 * 
 * @author madridf
 *
 */
public final class FCWLib {
	
    private static FCWLib fcwLib;
    private static final Logger LOG = Logger.getLogger(FCWLib.class.getName());
    private final File fcwInfo = new File("src/choreography/model/fcw/FCW_DEF.txt");
	private HashMap<String, Integer> waterAddress;
	private HashMap<String, Integer> lightAddress;
	private HashMap<String, Integer> functionAddress;
	private Set<String> lightNames;
	private Set<String> waterNames;
	private String[] functionNames;
	private HashMap<HashSet<Integer>, String> functionTable;
	private HashMap<String, HashMap<String, Integer>> tableCommands;
	
	private FCWLib(){
		waterAddress = new HashMap<>();
		lightAddress = new HashMap<>();
		functionTable = new HashMap<>();
		tableCommands = new HashMap<>();
		functionAddress = new HashMap<>();
		
		readFCWInfoFromFile(fcwInfo);
		
		lightNames = new HashSet<String>();
		waterNames = new HashSet<String>();
		lightNames = lightAddress.keySet();
		waterNames = waterAddress.keySet();
	}	
	
    /**
     * @return the fcwLib
     */
    public static synchronized FCWLib getInstance() {
        if(fcwLib == null)
           fcwLib = new FCWLib();
        return fcwLib;
    }

    /**
     * @param aFcwLib the fcwLib to set
     */
    public static void setFcwLib(FCWLib aFcwLib) {
        fcwLib = aFcwLib;
    }
	
	public void readFCWInfoFromFile(File fcwInfo){
		try {
			Scanner fileIn = new Scanner(new FileReader(fcwInfo));
			fileIn.useDelimiter("|");
			
			fileIn.findWithinHorizon("|WaterAddresses|", 0);
			fileIn.nextLine();
			readWaterAddressesFromFile(fileIn);
			
			fileIn.findWithinHorizon("|LightAddresses|", 0);
			fileIn.nextLine();
			readLightAddressesFromFile(fileIn);
			
			fileIn.findWithinHorizon("|Functions|", 0);
			fileIn.nextLine();
			readFunctionsFromFile(fileIn);
			
			fileIn.findWithinHorizon("|Tables|", 0);
			fileIn.nextLine();
			readAddressTableFromFile(fileIn);
			
			fileIn.findWithinHorizon("|Commands|", 0);
			fileIn.nextLine();
			readTableCommandsFromFile(fileIn);
		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "FCW_DEF.txt Not Found!");
		}
	}

	private void readFunctionsFromFile(Scanner fileIn) {
		String line = null;
		while(fileIn.hasNextLine()) {
			line = fileIn.nextLine();
			if(line.equals("|EndFunctions|")) {
				return;
			} else {
				String[] tokens = line.split(", ");
				functionAddress.put(tokens[0].trim(), new Integer(tokens[1].trim()));
			}
		}
		
	}

	public void readWaterAddressesFromFile(Scanner fileIn) {
		String line = null;
		while(fileIn.hasNextLine()){ //while we aren't EOF
			line = fileIn.nextLine(); //get the next legitmate line
			if(line.equals("|EndWaterAddresses|")) { //If we hit end of table
				return; //GTFO
			} else { 
				String[] tokens= line.split(", "); //split into components
				waterAddress.put(tokens[0].trim(), new Integer(tokens[1].trim())); //add them
			}
		}
	}
	
	public void readLightAddressesFromFile(Scanner fileIn) {
		while(fileIn.hasNextLine()){
			String line = fileIn.nextLine();
			if(line.equals("|EndLightAddresses|")) {
				return;
			} else {
				String[] tokens= line.split(", ");
					lightAddress.put(tokens[0].trim(), new Integer(tokens[1].trim()));
			}
		}
	}
	
	public void readAddressTableFromFile(Scanner fileIn) {
		while(fileIn.hasNextLine()){
            String line = fileIn.nextLine();
			if(line.equals("|EndTables|")) {
				return;
			} else {
				String[] tokens= line.split(", ");
				HashSet<Integer> addresses = new HashSet<>();
				for(int i = 1; i < tokens.length; i++) {
					addresses.add(new Integer(tokens[i]));
				}
				functionTable.put(addresses, tokens[0].trim());
			}
		}
	}

	public void readTableCommandsFromFile(Scanner fileIn) {
		while(fileIn.hasNextLine()){
	        String line = fileIn.nextLine(); //pull in the first line
	        if(line.startsWith("| ")){ //if the line is a new table...
	            HashMap<String, Integer> commands = new HashMap<>(); //create something to store them in...
	            String[] tokens= line.split(" "); //break it into pieces
	            String table = tokens[1].trim(); //take the table name and store it
	
	                do { //while I have commands...
	                   String command = fileIn.nextLine().trim(); //read the next in
	                   if(!command.equals("|EndTable|")) {
	                       String[] commandTokens = command.split(", "); //split into command and number
	                       commands.put(commandTokens[0].trim(), new Integer(commandTokens[1].trim()));
	                   } else {
	                       tableCommands.put(table, commands);
	                       break;
	                   }
	
	               } while(fileIn.hasNextLine()); 
	            } else if (line.equals("|EndCommands|")) {
	                    break;
	            }

            }
	}
	
	public Set<String> getLightTable() {
		return lightNames;
	}
	
	public Set<String> getWaterTable() {
		return waterNames;
	}

        public FCW getFCW(String cannon, String[] actions) {
            
            int addr = 0;
            String table = null;
            int data = 0;
            
            addr = searchAddresses(cannon);
            table = searchFunctionTables(addr);
            
            for (String action : actions) {
            	action = action.toUpperCase();
                int value = tableCommands.get(table).get(action);
                data += value;
            }

            return new FCW(addr, data); //get rid of this crap!
        }

    private int searchAddresses(String cannon) {
			if(waterAddress.containsKey(cannon)) {
				return searchWaterAddresses(cannon);
			}
			else if(lightAddress.containsKey(cannon)){
				return searchLightAddresses(cannon);
			}
			else throw new IllegalArgumentException(cannon + " isn't associated with "
					+ "a water or light address");
		}

	private String searchFunctionTables(int addr) {
        String table = null;
        for(HashSet<Integer> hs : functionTable.keySet()){
            if(hs.contains(addr)){
                table = functionTable.get(hs);
                break;
            }
        }
        return table;
    }

    private int searchWaterAddresses(String cannon) throws IllegalArgumentException {
		return waterAddress.get(cannon); //get it!
    }
    
    private int searchLightAddresses(String cannon) throws IllegalArgumentException {
        return lightAddress.get(cannon); //get it!
    }

	/**
	 * @return the functionAddress
	 */
	public HashMap<String, Integer> getFunctionAddress() {
		return functionAddress;
	}

	/**
	 * @param functionAddress the functionAddress to set
	 */
	public void setFunctionAddress(HashMap<String, Integer> functionAddress) {
		this.functionAddress = functionAddress;
	}

	/**
	 * @return the functionNames
	 */
	public String[] getFunctionNames() {
		return functionNames;
	}

	/**
	 * @param functionNames the functionNames to set
	 */
	public void setFunctionNames(String[] functionNames) {
		this.functionNames = functionNames;
	}
}
