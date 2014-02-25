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
            double seconds = startTime / 10;
            double minutes = seconds / 60;
            String secondsPoint = new DecimalFormat("##.#").format(minutes % 60);
            System.out.println(minutes + ":" + secondsPoint + commands);
            return minutes + ":" + secondsPoint + commands;
        }
}
