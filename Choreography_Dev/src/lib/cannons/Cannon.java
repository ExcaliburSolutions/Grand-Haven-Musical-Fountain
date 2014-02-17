package lib.cannons;

public class Cannon {
	protected String AB;
	protected int level;
	
	public Cannon(String AB, int level) {
		this.setModule(AB);
		this.setLevel(level);
	}

	public Cannon(int level) {
		this.level = level;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 * 
	 * @return Whether the operation succeeded or not.
	 */
	public int setLevel(int level) {
		return this.level = level;
	}
	
	/**
	 * @return the module
	 */
	protected String getAB() {
		return AB;
	}

	/**
	 * @param AB the module to set
	 */
	protected void setModule(String AB) {
		this.AB = AB;
	}
	
	public String toString(){
		return this.getClass().getSimpleName() + " " + level;
	}
	
	
}
