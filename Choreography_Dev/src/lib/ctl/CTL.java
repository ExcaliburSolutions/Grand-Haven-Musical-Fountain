package lib.ctl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.FileChooser;
import lib.Event;
import lib.FCW;

public class CTL {

	public CTL(){
		
	}
	
	public String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
         
        try {
 
            bufferedReader = new BufferedReader(new FileReader(file));
             
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append(System.getProperty("line.separator"));
            }
 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CTL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CTL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(CTL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        return stringBuffer.toString();
    }
	
	public Event[] parseCTL(String input){
		
		String[] tokens = input.split(System.getProperty("line.separator"));
		int commandNumber = tokens.length;
		
		Event[] event = new Event[commandNumber];
		
		for(int i=0; i < commandNumber;i++){
			String totalTime = tokens[i].substring(0, 7);
			int minutes = (Integer.parseInt(totalTime.substring(0, 2)));
			double seconds = Double.parseDouble(totalTime.substring(3, 7));
			double totalTimeinSecs = seconds +(minutes *60);
			
			String commands = tokens[i].substring(8);
			
			String[] FCWs = commands.split(" ");
			ArrayList<FCW> FCWcommands = new ArrayList();
			
			for(String item : FCWs){
				FCWcommands.add(item);
			}
			
			event[i] = new Event(totalTimeinSecs, FCWcommands);
			
		}
		
		return event;
	}
	
	public void saveFile(File file, String content){
		
		        try {
		            FileWriter fileWriter = null;
		             
		            fileWriter = new FileWriter(file);
		            fileWriter.write(content);
		            fileWriter.close();
		        } catch (IOException ex) {
		            Logger.getLogger(CTL.class.getName()).log(Level.SEVERE, null, ex);
		        }
		         
	}
}
