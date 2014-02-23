/**
 * 
 */
package choreography.model.fcw;

/**
 * @author elementsking
 *
 */
public class LagTimeTable {
	private static LagTimeTable instance;
	private final int closeValve = 0;
	private double level1;
	private double level2;
	private double level3;
	private double level4;
	private double level5;
	private double audioDelay, peacockDelay, voiceDelay, bazookaDelay,
	ringDelay, candelabraDelay, sweepDelay, ftCurtDelay, bkCurtDelay;
	
	public static LagTimeTable getInstance() {
		if (instance == null)
			instance = new LagTimeTable();
		return instance;
	}
	
	public void setLagTimes(double audioDelay, double peacockDelay,
			double voiceDelay, double bazookaDelay, double ringDelay,
			double candelabraDelay, double sweepDelay, 
			double ftCurtDelay, double bkCurtdelay) {
		this.audioDelay = audioDelay;
		this.peacockDelay = peacockDelay;
		this.voiceDelay = voiceDelay;
		this.bazookaDelay = bazookaDelay;
		this.ringDelay = ringDelay;
		this.candelabraDelay = candelabraDelay;
		this.sweepDelay = sweepDelay;
		this.ftCurtDelay = ftCurtDelay;
		this.bkCurtDelay = bkCurtdelay;
	}

	/**
	 * @return the closeValve
	 */
	public synchronized int getCloseValve() {
		return closeValve;
	}

	/**
	 * @return the level1
	 */
	public synchronized double getLevel1() {
		return level1;
	}

	/**
	 * @return the level2
	 */
	public synchronized double getLevel2() {
		return level2;
	}

	/**
	 * @return the level3
	 */
	public synchronized double getLevel3() {
		return level3;
	}

	/**
	 * @return the level4
	 */
	public synchronized double getLevel4() {
		return level4;
	}

	/**
	 * @return the level5
	 */
	public synchronized double getLevel5() {
		return level5;
	}

	/**
	 * @return the audioDelay
	 */
	public synchronized double getAudioDelay() {
		return audioDelay;
	}

	/**
	 * @return the peacockDelay
	 */
	public synchronized double getPeacockDelay() {
		return peacockDelay;
	}

	/**
	 * @return the voiceDelay
	 */
	public synchronized double getVoiceDelay() {
		return voiceDelay;
	}

	/**
	 * @return the bazookaDelay
	 */
	public synchronized double getBazookaDelay() {
		return bazookaDelay;
	}

	/**
	 * @return the ringDelay
	 */
	public synchronized double getRingDelay() {
		return ringDelay;
	}

	/**
	 * @return the candelabraDelay
	 */
	public synchronized double getCandelabraDelay() {
		return candelabraDelay;
	}

	/**
	 * @return the sweepDelay
	 */
	public synchronized double getSweepDelay() {
		return sweepDelay;
	}

	/**
	 * @return the ftCurtDelay
	 */
	public synchronized double getFtCurtDelay() {
		return ftCurtDelay;
	}

	/**
	 * @return the bkCurtDelay
	 */
	public synchronized double getBkCurtDelay() {
		return bkCurtDelay;
	}
}
