package lib.cannons;

public abstract class Cannon {
	protected int level;
	
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
	
        @Override
	public String toString(){
		return this.getClass().getSimpleName() + " " + level;
	}
}
