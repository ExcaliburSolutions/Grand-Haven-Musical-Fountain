package choreography.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import choreography.model.Event;
import choreography.model.fcw.FCW;

public class CtlLib {
    
	public CtlLib(){
            
	}
	
	public String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
         
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
             
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append(System.getProperty("line.separator"));
            }
 
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
         
        return stringBuffer.toString();
    }
	
	public ArrayList<Event> parseCTL(String input){
		//Split file into tokens of lines
		String[] lines = input.split(System.getProperty("line.separator"));
		//Create an Event[] to hold all events
		ArrayList<Event> events = new ArrayList<>();
		// For each line,
                for(String line : lines){
                    //Get the time signature
                    String totalTime = line.substring(0, 7);
                    //Get the minutes
                    int minutes = (Integer.parseInt(totalTime.substring(0, 1)));
                    //get the seconds
                    int seconds = Integer.parseInt(totalTime.substring(3, 4));
                    //get the tenths
                    int tenths = Integer.parseInt(totalTime.substring(5, 6));
                    //find the total time in seconds
                    int totalTimeinSecs = (tenths * 10) + seconds + (minutes * 60);
                    //get the commands section on the line
                    String commands = line.substring(8, line.length());
                    //break the commands into tokens
                    String[] commandTokens = commands.split(" ");
                    //create a new FCW for the command token
                    FCW fcw = new FCW(Integer.parseInt(commandTokens[1]), 
                            Integer.parseInt(commandTokens[2]));
                    ArrayList<FCW> fcws = new ArrayList<>();
                    events.add(new Event(totalTimeinSecs, fcws));
                }
		System.out.println(events);
		return events;
	}
	
	public void saveFile(File file, String content){
		
            try {
                FileWriter fileWriter = null;

                fileWriter = new FileWriter(file);
                fileWriter.write(content);
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
		         
	}
}
