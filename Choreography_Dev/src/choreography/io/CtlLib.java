package choreography.io;

import choreography.model.Event;
import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author elementsking
 */
public class CtlLib {
    
    private static CtlLib instance;
    
    /**
     *
     * @return
     */
    public static synchronized CtlLib getInstance() {
        if(instance == null)
            instance = new CtlLib();
        return instance;
    }
    
    private CtlLib(){

    }

    /**
     *
     */
    public synchronized void openCtl() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open CTL File");
        fc.setInitialFileName(System.getProperty("user.home"));
        fc.getExtensionFilters().add(new ExtensionFilter("CTL Files", "*.ctl"));
        File ctlFile = fc.showOpenDialog(null);
        //had to cast to hashmap to get it to work, likely will have to change in the future
        ChoreographyController.getInstance().setEventTimeline((HashMap<Integer, ArrayList<FCW>>) parseCTL(readFile(ctlFile)));
    }

    /**
     *
     * @param file
     * @return
     */
    public synchronized String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append(System.getProperty("line.separator"));
            }

        } catch (Exception ex) {
        } finally {
        }

        return stringBuffer.toString();
    }

    /**
     *
     * @param input
     * @return
     */
    public synchronized Map<Integer, ArrayList<FCW>> parseCTL(String input){
        //Split file into tokens of lines
        String[] lines = input.split(System.getProperty("line.separator"));
        //Create an Event[] to hold all events
        HashMap<Integer, ArrayList<FCW>> events = new HashMap<>();
        // For each line,
        for(String line : lines){
            //Get the time signature
            String totalTime = line.substring(0, 7);
            //Get the minutes
            int minutes = (Integer.parseInt(totalTime.substring(0, 2)));
            //get the seconds
            int seconds = Integer.parseInt(totalTime.substring(3, 5));
            //get the tenths
            int tenths = Integer.parseInt(totalTime.substring(6, 7));
            //find the total time in seconds
            int totalTimeinTenthSecs = (minutes * 600) + (seconds * 10) + tenths;
            //get the commands section on the line
            String commands = line.substring(7, line.length());
            //break the commands into tokens
            String[] commandTokens = commands.split(" ");
            //create a new FCW for the command token
            FCW fcw =null; ArrayList<FCW> fcws = new ArrayList<>();
            for(String command: commandTokens){
                String[] tokens = command.split("-");
                fcw = new FCW(Integer.parseInt(tokens[0]), 
                    Integer.parseInt(tokens[1]));
                fcws.add(fcw);       
            }
            events.put(totalTimeinTenthSecs, fcws);
            
        }
        Map<Integer, ArrayList<FCW>> map = new TreeMap<Integer,ArrayList<FCW>>(events);
        System.out.println(map.toString());
        return map;
    }

    /**
     *
     * @param file
     * @param content
     */
    public synchronized void saveFile(File file, HashMap<Integer, ArrayList<FCW>> content){

        try (FileWriter fileWriter = new FileWriter(file)){
            
        } catch (IOException ex) {
        }

    }
}
