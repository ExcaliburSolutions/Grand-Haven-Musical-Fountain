package choreography.model;

import choreography.model.fcw.FCW;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author elementsking
 */
public class Event{

    /**
     *
     */
    protected int startTime;

    /**
     *
     */
    protected ArrayList<FCW> commands;

    /**
     *
     * @param startTime
     * @param commands
     */
    public Event(int startTime, ArrayList<FCW> commands){
		this.setStartTime(startTime);
		this.setCommands(commands);
	}

    /**
     *
     * @return
     */
    public int getStartTime() {
		return startTime;
	}

    /**
     *
     * @param delayTime
     */
    public void transformStartTime(double delayTime) {
            startTime += convertToTenthSeconds(delayTime);
        }
        
        private int convertToTenthSeconds(double inputSeconds) {
            return (int) (inputSeconds * 10);
        }

    /**
     *
     * @return
     */
    public ArrayList<FCW> getCommands() {
		return commands;
	}

    /**
     *
     * @param commands
     */
    public void setCommands(ArrayList<FCW> commands) {
		this.commands = commands;
	}

    /**
     *
     * @param startTime
     */
    public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

    /**
     *
     * @return
     */
    @Override
        public String toString() {
//            double tenthseconds = startTime / 10;
            int minutes = (int) (startTime / 60);
            StringBuilder commandsOutput = new StringBuilder();
            for(FCW f: commands){
                commandsOutput.append(" ").append(f);
            }
            String eventString = new DecimalFormat("00").format(minutes) + ":" 
                    + new DecimalFormat("00.0").format(startTime/10) + commandsOutput;
            return eventString;
        }
}
