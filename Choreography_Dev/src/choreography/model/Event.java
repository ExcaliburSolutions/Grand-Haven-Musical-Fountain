package choreography.model;

import choreography.model.fcw.FCW;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Event{
	protected int startTime;
	protected ArrayList<FCW> commands;
	
	public Event(int startTime, ArrayList<FCW> commands){
		this.setStartTime(startTime);
		this.setCommands(commands);
	}
	
	public double getStartTime() {
		return startTime;
	}

	public ArrayList<FCW> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<FCW> commands) {
		this.commands = commands;
	}

	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

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
