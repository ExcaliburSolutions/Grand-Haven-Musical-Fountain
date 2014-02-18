/**
 * 
 */
package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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
public class FCWLib {
	private final File fcwInfo = new File("src/lib/FCW_DEF.txt");
	private final HashMap<String, Integer> address;
	private final HashMap<String, HashSet<Integer>> addressCommand;
	private final HashMap<String, HashMap<String, Integer>> tableCommands;
	
	public FCWLib(){
		address = new HashMap<>();
		addressCommand = new HashMap<>();
		tableCommands = new HashMap<>();
		
//		if(fcwInfo.exists()){
		readFCWInfoFromFile(fcwInfo);
		System.out.println(address);
		System.out.println(addressCommand);
		System.out.println(tableCommands);
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
				fileIn.findWithinHorizon("|Tables|", 100);
				fileIn.nextLine();
				readAddressTableFromFile(fileIn);
				fileIn.findWithinHorizon("|Commands|", 100);
				fileIn.nextLine();
				readTableCommandsFromFile(fileIn);
//			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
				HashSet<Integer> addresses = new HashSet<Integer>();
				for(int i = 1; i < tokens.length; i++) {
					addresses.add(new Integer(tokens[i]));
				}
				addressCommand.put(tokens[0].trim(), addresses);
			}
		}
	}

	public void readTableCommandsFromFile(Scanner fileIn) {
		
		while(fileIn.hasNextLine()){
			String line = fileIn.nextLine(); //pull in the first line
			if(line.startsWith("| ")){ //if the line is a table...
				HashMap<String, Integer> commands = new HashMap<String, Integer>(); //create something to store them in...
				String[] tokens= line.split(" "); //break it into pieces
				String table = tokens[1].trim(); //take the table name and store it
				while(fileIn.hasNextLine()) { //while I have commands...
					String command = fileIn.nextLine().trim(); //read the next in
					if(command.equals("|EndTable|")) {
						break;
					} else {
						String[] commandTokens = command.split(", "); //split into command and number
						commands.put(commandTokens[0].trim(), new Integer(commandTokens[1].trim()));
						tableCommands.put(table, commands);
					}
				}
			} else if (line.equals("|EndCommands|")) {
				break;
			}
				
		}
	}


	
	
}
