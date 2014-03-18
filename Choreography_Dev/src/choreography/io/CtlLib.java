package choreography.io;

import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;
import choreography.view.music.MusicPaneController;
import choreography.view.timeline.TimelineController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
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
        
    }
    
    public void openCtl(File file) {
        ChoreographyController.getInstance().setEventTimeline(parseCTL(readFile(file)));
        TimelineController.getInstance().setLabelGridPaneWithCtl();
    }

    /**
     *
     * @param file
     * @return
     */
    public synchronized String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            bufferedReader.readLine();
            String text = null;
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
    public synchronized SortedMap<Integer, ArrayList<FCW>> parseCTL(String input){
        //Split file into tokens of lines
        String[] lines = input.split(System.getProperty("line.separator"));
        //Create an Event[] to hold all events
        SortedMap<Integer, ArrayList<FCW>> events = new ConcurrentSkipListMap<>();
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
        System.out.println(events.toString());
        return events;
    }

    /**
     *
     * @param file
     * @param content
     * @return 
     */
    public synchronized boolean saveFile(File file, SortedMap<Integer, ArrayList<FCW>> content){
//
        try (FileWriter fileWriter = new FileWriter(file)){
            if(ChoreographyController.getInstance().getAdvanced()) {
                fileWriter.write("gvsuCapstone2014A");
            }
            else {
                fileWriter.write("gvsuCapstone2014B");
            }
            
            postDate(content);
            
            for(Integer i: content.keySet()){
                StringBuilder commandsOutput = new StringBuilder();
                int totalTenths = MusicPaneController.SONG_TIME;
                int tenths = totalTenths % 10;
                int seconds = totalTenths /10;
                int minutes = seconds / 60;
                seconds = seconds - (minutes * 10);
                String totTime = minutes + ":" + seconds + "." + tenths;
                for(FCW f: content.get(i)) {
                    
                }
            }
        return true;    
        } catch (IOException ex) {
            return false;
        }
    }

    private void postDate(SortedMap<Integer, ArrayList<FCW>> content) {
        for(Integer timeIndex: content.keySet()) {
            for(FCW f: content.get(timeIndex)) {
                int lag = LagTimeLibrary.getInstance().getLagTime(f);
                if(content.containsKey(timeIndex - lag)) {
                    content.get(timeIndex - lag).add(f);
                }
            }
        }
    }
}
