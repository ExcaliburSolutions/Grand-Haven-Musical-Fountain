/**
 * 
 */
package choreography.model.fcw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
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
	private final File fcwInfo = new File("src/choreography/model/fcw/FCW_DEF.txt");
	private final HashMap<String, Integer> address;
	private final HashMap<HashSet<Integer>, String> functionTable;
	private final HashMap<String, HashMap<String, Integer>> tableCommands;
	
	private FCWLib(){
		address = new HashMap<>();
		functionTable = new HashMap<>();
		tableCommands = new HashMap<>();
		
		readFCWInfoFromFile(fcwInfo);
//		System.out.println(address);
//		System.out.println(functionTable);
//		System.out.println(tableCommands);
//		}
	}
	
	public void readFCWInfoFromFile(File fcwInfo){
		try {
			Scanner fileIn = new Scanner(new FileReader(fcwInfo));
			fileIn.useDelimiter("|");
//			String line = "";
//			while(fileIn.hasNextLine()){
				fileIn.findWithinHorizon("|Addresses|", 1);
				fileIn.nextLine();
				readAddressesFromFile(fileIn);
				fileIn.nextLine();
				fileIn.findWithinHorizon("|Tables|", 0);
				readAddressTableFromFile(fileIn);
				fileIn.findWithinHorizon("|Commands|", 0);
				fileIn.nextLine();
				readTableCommandsFromFile(fileIn);
//			}

		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "FCW_DEF.txt Not Found!");
		}
	}


	public void readAddressesFromFile(Scanner fileIn) {
		while(fileIn.hasNextLine()){
			String line = fileIn.nextLine();
			if(line.equals("|EndAddresses|")) {
				return;
			} else {
				String[] tokens= line.split(", ");
					address.put(tokens[0].trim(), new Integer(tokens[1].trim()));
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
		
            do{
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

            } while(fileIn.hasNextLine());
	}
	
	public String[] getLightTable() {
		throw new UnsupportedOperationException();
	}
	
	public String[] getWaterTable() {
		throw new UnsupportedOperationException();
	}

        public FCW getFCW(String cannon, String[] actions) {
            
            int addr = 0;
            String table = null;
            int data = 0;
            
            addr = searchAddresses(cannon);
            table = searchFunctionTables(addr);
            
            for (String action : actions) {
                int value = tableCommands.get(table).get(action);
                data += value;
            }

            return new FCW(addr, data); //get rid of this crap!
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

    private int searchAddresses(String cannon) throws IllegalArgumentException {
        
        int addr = 0;
        if(address.containsKey(cannon)){ //if we have a cannon
            addr = address.get(cannon); //get it!
        } else throw new IllegalArgumentException(cannon + " does not have "
                + "an associated address in FCW_DEF.txt");
        return addr;
        
    }
}
