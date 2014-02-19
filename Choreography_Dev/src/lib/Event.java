package lib;

import java.util.ArrayList;

public class Event{
	protected double startTime;
	protected ArrayList<FCW> commands;
	
	public Event(double startTime, ArrayList<FCW> commands){
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

	
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

}
