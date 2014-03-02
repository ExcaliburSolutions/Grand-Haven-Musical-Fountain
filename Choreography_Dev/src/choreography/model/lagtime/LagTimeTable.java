/**
 * 
 */
package choreography.model.lagtime;

import java.util.HashMap;

/**
 * @author elementsking
 *
 */
public class LagTimeTable {
	private static LagTimeTable instance;
	private final int closeValve = 0;
	private final double level1 = .2;
	private final double level2 = .4;
	private final double level3 = .6;
	private final double level4 = .8;
	private final double level5 = 1.0;
	private HashMap<String, Double> delays;
	
	public static LagTimeTable getInstance() {
		if (instance == null)
			instance = new LagTimeTable();
		return instance;
	}
	
	private LagTimeTable() {
		delays = new HashMap<>();
	}
        
        public HashMap<String, Double> getDelays() {
            return delays;
        }
	
	public void setLagTimes(HashMap<String, Double> delayTimes) {
            this.delays = delayTimes;
            System.out.println(delays);
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
}
