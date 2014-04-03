package choreography.io;

import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;
import choreography.view.colorPalette.ColorPaletteModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    private boolean isTimeCompensated;
    
    private CtlLib(){

    }

    /**
     *
     */
    public synchronized void openCtl() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open CTL File");
        fc.setInitialFileName(System.getProperty("user.dir"));
        fc.getExtensionFilters().add(new ExtensionFilter("CTL Files", "*.ctl"));
        File ctlFile = fc.showOpenDialog(null);
        openCtl(ctlFile);
    }
    
    public void openCtl(File file) {
        ChoreographyController.getInstance().setEventTimeline(parseCTL(readFile(file)));
        
    }

    /**
     *
     * @param file
     * @return
     */
    public synchronized String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String version = bufferedReader.readLine();
            switch(version) {
                case "ct0-382":
                    ColorPaletteModel.getInstance().setClassicColors(true);
                    break;
                case "gvsuCapstone2014B":
                    isTimeCompensated = true;
                case "gvsuCapstone2014A":
                    ChoreographyController.getInstance().setAdvanced(true);
                    break;
            }
            String text = null;
            
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
                stringBuffer.append(System.getProperty("line.separator"));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        }

        return stringBuffer.toString();
    }

    /**
     *
     * @param input
     * @return
     */
    public synchronized ConcurrentSkipListMap<Integer, ArrayList<FCW>> parseCTL(String input){
        //Split file into tokens of lines
        String[] lines = input.split(System.getProperty("line.separator"));
        //Create an Event[] to hold all events
        ConcurrentSkipListMap<Integer, ArrayList<FCW>> events = new ConcurrentSkipListMap<>();
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
            if(isTimeCompensated) {
                    reversePostDate(events);
            }
        }
//        System.out.println(events.toString());
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
                fileWriter.write("gvsuCapstone2014A\n");
            }
            else {
                fileWriter.write("gvsuCapstone2014B\n");
            }
            
            for(Integer timeIndex: content.keySet()) {
                Iterator<FCW> it = content.get(timeIndex).iterator();
                while(it.hasNext()){
                    FCW f = it.next();
                    if(f.getIsWater()) {
//                        System.out.println(f + " " + timeIndex);
                        if(postDateSingleFcw(f, content, timeIndex)){ 
                            it.remove();
                        }
                    }
                    if(content.get(timeIndex).isEmpty()) {
                        content.remove(timeIndex);
                    }
                }
            }
            StringBuilder commandsOutput = new StringBuilder();
            for(Integer i: content.keySet()) {
                String totTime = "";
                int timeIndex = i;
                if(i < 0) {
                    totTime = "-";
                    timeIndex = Math.abs(i);
                }
                int tenths = Math.abs(timeIndex % 10);
                int seconds = Math.abs(timeIndex / 10 % 60);
                int minutes = Math.abs(((timeIndex/10)-seconds) /60);
//                seconds = seconds - (minutes * 10);
//                 totTime = minutes + ":" + seconds + "." + tenths;
                totTime += String.format("%1$02d:%2$02d.%3$01d", minutes, seconds, tenths);
//                System.out.println(totTime);
                commandsOutput.append(totTime);
                for(FCW f: content.get(i)) {
                    commandsOutput.append(f);
                    commandsOutput.append(" ");
                }
                commandsOutput.append("\n");
            }
            fileWriter.write(commandsOutput.toString());
            ChoreographyController.getInstance().setfcwOutput("Finished saving CTL!");
        return true;    
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private synchronized void postDate(SortedMap<Integer, ArrayList<FCW>> content) {
        for(Integer timeIndex: content.keySet()) {
            Iterator<FCW> it = content.get(timeIndex).iterator();
            while(it.hasNext()){
                FCW f = it.next();
                if(f.getIsWater()) {
                    if(postDateSingleFcw(f, content, timeIndex))
                        it.remove();
                }
            }
            if(content.get(timeIndex).isEmpty()) {
                content.remove(timeIndex);
            }
        }
    }

    public synchronized boolean postDateSingleFcw(FCW f, SortedMap<Integer, ArrayList<FCW>> content, Integer timeIndex) {
        int lag = LagTimeLibrary.getInstance().getLagTimeInTenths(f);
        if(lag != 0){
            if(content.containsKey(timeIndex - lag)) {
                content.get(timeIndex - lag).add(f);
            }
            else {
                content.put(timeIndex - lag, new ArrayList(10));
                content.get(timeIndex - lag).add(f);
            }
            return true;
        }
        return false;
    }

    private boolean reversePostDate(SortedMap<Integer, ArrayList<FCW>> content) {
        for(Integer timeIndex: content.keySet()) {
            Iterator<FCW> it = content.get(timeIndex).iterator();
            while(it.hasNext()){
                FCW f = it.next();
                int lag = LagTimeLibrary.getInstance().getLagTimeInTenths(f);
                if(lag != 0){
                    if(content.containsKey(timeIndex + lag)) {
                        content.get(timeIndex + lag).add(f);
                    }
                    else {
                        content.put(timeIndex + lag, new ArrayList(10));
                        content.get(timeIndex + lag).add(f);
                    }
                    return true;
                }
        
            }
        }
        return false;
    }
}
