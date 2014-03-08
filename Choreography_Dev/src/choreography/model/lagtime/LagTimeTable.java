/**
 * 
 */
package choreography.model.lagtime;

import choreography.io.FCWLib;
import choreography.model.cannon.CannonEnum;
import choreography.model.fcw.FCW;
import java.util.ArrayList;

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
	private ArrayList<LagTime> delays;

    /**
     *
     * @return
     */
    public static LagTimeTable getInstance() {
		if (instance == null)
			instance = new LagTimeTable();
		return instance;
	}
	
	private LagTimeTable() {
		delays = new ArrayList<>();
	}

    /**
     *
     * @return
     */
    public ArrayList<LagTime> getDelays() {
            return delays;
        }

    /**
     *
     * @param delayTimes
     */
    public void setLagTimes(ArrayList<LagTime> delayTimes) {
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

    /**
     *
     * @param f
     * @return
     */
    public synchronized double getLagTime(FCW f) {
            String[] actions = FCWLib.getInstance().reverseLookupData(f);
            double lagTime = 0.0;
            for(String action: actions) {
                for(CannonEnum ce : CannonEnum.values()) {
                    if(action.contains(ce.toString())) {
                        for(LagTime lt: delays) {
                            if(lt.getDelayName().equalsIgnoreCase(action)) {
                                lagTime = lt.getDelayTime();
                            }
                        }
                    }
                }
                switch(action) {
                    case "1": lagTime *= level1; break;
                    case "2": lagTime *= level2; break;
                    case "3": lagTime *= level3; break;
                    case "4": lagTime *= level4; break;
                    case "5": lagTime *= level5; break;
                }
            }
            return lagTime;
        }
}
