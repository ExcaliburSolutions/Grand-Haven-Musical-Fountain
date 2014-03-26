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
	private static final int closeValve = 0;
	private static final double level1 = .2;
	private static final double level2 = .4;
	private static final double level3 = .6;
	private static final double level4 = .8;
	private static final double level5 = 1.0;
	private static ArrayList<LagTime> delays;

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
    public static ArrayList<LagTime> getDelays() {
            return delays;
        }

    /**
     *
     * @param delayTimes
     */
    public static void setLagTimes(ArrayList<LagTime> delayTimes) {
            LagTimeTable.delays = delayTimes;
//            System.out.println(delays);
	}

	/**
	 * @return the closeValve
	 */
	public static synchronized int getCloseValve() {
		return closeValve;
	}

	/**
	 * @return the level1
	 */
	public static synchronized double getLevel1() {
		return level1;
	}

	/**
	 * @return the level2
	 */
	public static synchronized double getLevel2() {
		return level2;
	}

	/**
	 * @return the level3
	 */
	public static synchronized double getLevel3() {
		return level3;
	}

	/**
	 * @return the level4
	 */
	public static synchronized double getLevel4() {
		return level4;
	}

	/**
	 * @return the level5
	 */
	public static synchronized double getLevel5() {
		return level5;
	}

    /**
     *
     * @param f
     * @return
     */
    public static synchronized double getLagTime(FCW f) {
        String[] actions = FCWLib.getInstance().reverseLookupData(f);
        String cannon = FCWLib.getInstance().reverseLookupAddress(f.getAddr());
        double lagTime = -99.0; 
        for(LagTime lt: delays) {
            if(lt.getDelayName().equalsIgnoreCase(cannon)) {
                lagTime = lt.getDelayTime();
                break;
            }
        }
        for(String action: actions) {
            switch(action) {
                case "1": lagTime *= level1; return lagTime;
                case "2": lagTime *= level2; return lagTime;
                case "3": lagTime *= level3; return lagTime;
                case "4": lagTime *= level4; return lagTime;
                case "5": lagTime *= level5; return lagTime;
            }
        }
        throw new IllegalArgumentException("Invalid lag time! " + f);
    }
}
