/**
 * 
 */
package lib;

import lib.cannons.*;


/**
 * 
 * 
 * @author madridf
 *
 */
public class Module {
	private int number;
	private Ring r1;
	private Ring r2;
	private Ring r3;
	private Ring r4;
	private Ring r5;
	private Sweeps sw;
	private Multi mx;
	private Candalabra candle;
	

	/**
	 * @param number A number that identifies this Module
	 * @param r1
	 * @param r2
	 * @param r3
	 * @param r4
	 * @param sw
	 * @param mx
	 * @param candle
	 */
	public Module(int number, Ring r1, Ring r2, Ring r3,
			Ring r4, Ring r5, Multi mx, Candalabra candle, Sweeps sw) {
		super();
		this.number = number;
		this.r1 = r1;
		this.r2 = r2;
		this.r3 = r3;
		this.r4 = r4;
		this.r5 = r5;
		this.sw = sw;
		this.mx = mx;
		this.candle = candle;
	}

	public Module(int number, Ring[] rings, Multi m1, Candalabra c1, Sweeps s1){
		this.mx = m1;
		this.candle = c1;
		this.sw = s1;
		r1 = rings[0];
		r2 = rings[1];
		r3 = rings[2];
		r4 = rings[3];
		r5 = rings[4];
	}
	/**
	 * @return the number
	 */
	protected int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	protected void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the r1
	 */
	public Ring getR1() {
		return r1;
	}

	/**
	 * @param r1 the r1 to set
	 */
	protected void setR1(Ring r1) {
		this.r1 = r1;
	}

	/**
	 * @return the r2
	 */
	public Ring getR2() {
		return r2;
	}

	/**
	 * @param r2 the r2 to set
	 */
	protected void setR2(Ring r2) {
		this.r2 = r2;
	}

	/**
	 * @return the r3
	 */
	public Ring getR3() {
		return r3;
	}

	/**
	 * @param r3 the r3 to set
	 */
	protected void setR3(Ring r3) {
		this.r3 = r3;
	}

	/**
	 * @return the r4
	 */
	public Ring getR4() {
		return r4;
	}

	/**
	 * @param r4 the r4 to set
	 */
	protected void setR4(Ring r4) {
		this.r4 = r4;
	}
	
	/**
	 * @return the r4
	 */
	public Ring getR5() {
		return r5;
	}

	/**
	 * @param r4 the r4 to set
	 */
	protected void setR5(Ring r5) {
		this.r5 = r5;
	}

	/**
	 * @return the sw
	 */
	public Sweeps getSw() {
		return sw;
	}

	/**
	 * @param sw the sw to set
	 */
	protected void setSw(Sweeps sw) {
		this.sw = sw;
	}

	/**
	 * @return the mx
	 */
	public Multi getMx() {
		return mx;
	}

	/**
	 * @param mx the mx to set
	 */
	protected void setMx(Multi mx) {
		this.mx = mx;
	}

	/**
	 * @return the candle
	 */
	public Candalabra getCandle() {
		return candle;
	}

	/**
	 * @param candle the candle to set
	 */
	protected void setCandle(Candalabra candle) {
		this.candle = candle;
	}

}
