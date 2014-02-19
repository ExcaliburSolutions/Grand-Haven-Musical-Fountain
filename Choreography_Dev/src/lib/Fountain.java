/**
 * 
 */
package lib;

import lib.cannons.*;

/**
 * @author madridf
 *
 */
public class Fountain {

        private static Fountain instance;
	private Peacock peacock; private Spout spout; private Bazooka bazooka; private FtCurt ftCurt; private BkCurt bkCurt;
	private ModuleGroup A; private ModuleGroup B;

    /**
     *
     * @return 
     */
    public static synchronized Fountain getInstance() {
            if (instance == null) {
                instance = new Fountain();
            }
            return instance;
        }
	
	private Fountain(){
            A = (CannonFactory.createModuleGroup("A"));
            B = (CannonFactory.createModuleGroup("B"));
		
            initIndependentCannons();
	}

	/**
	 * 
	 */
	private void initIndependentCannons() {
		peacock = new Peacock(0); spout = new Spout(0); bazooka = new Bazooka(0); ftCurt = new FtCurt(0); bkCurt = new BkCurt(0);
	}

	/**
	 * @return the peacock
	 */
	public Peacock getPeacock() {
		return peacock;
	}

	/**
	 * @param peacock the peacock to set
	 */
	protected void setPeacock(Peacock peacock) {
		this.peacock = peacock;
	}

	/**
	 * @return the spout
	 */
	public Spout getSpout() {
		return spout;
	}

	/**
	 * @param spout the spout to set
	 */
	protected void setSpout(Spout spout) {
		this.spout = spout;
	}

	/**
	 * @return the bazooka
	 */
	public Bazooka getBazooka() {
		return bazooka;
	}

	/**
	 * @param bazooka the bazooka to set
	 */
	protected void setBazooka(Bazooka bazooka) {
		this.bazooka = bazooka;
	}

	/**
	 * @return the ftCurt
	 */
	public FtCurt getFtCurt() {
		return ftCurt;
	}

	/**
	 * @param ftCurt the ftCurt to set
	 */
	protected void setFtCurt(FtCurt ftCurt) {
		this.ftCurt = ftCurt;
	}

	/**
	 * @return the bkCurt
	 */
	public BkCurt getBkCurt() {
		return bkCurt;
	}

	/**
	 * @param bkCurt the bkCurt to set
	 */
	protected void setBkCurt(BkCurt bkCurt) {
		this.bkCurt = bkCurt;
	}

	/**
	 * @return the module1
	 */
	public Module getModule1() {
		return A.getModules()[0];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule1(Module module1) {
		A.getModules()[0] = module1;
	}
	
	/**
	 * @return the module1
	 */
	public Module getModule3() {
		return A.getModules()[1];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule3(Module module1) {
		A.getModules()[1] = module1;
	}
	
	/**
	 * @return the module1
	 */
	public Module getModule5() {
		return A.getModules()[2];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule5(Module module1) {
		A.getModules()[2] = module1;
	}
	
	/**
	 * @return the module1
	 */
	public Module getModule7() {
		return A.getModules()[3];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule7(Module module1) {
		A.getModules()[3] = module1;
	}
	/**
	 * @return the module1
	 */
	public Module getModule2() {
		return B.getModules()[0];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule2(Module module2) {
		B.getModules()[0] = module2;
	}

	/**
	 * @return the module1
	 */
	public Module getModule4() {
		return B.getModules()[1];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule4(Module module1) {
		B.getModules()[1] = module1;
	}

	/**
	 * @return the module1
	 */
	public Module getModule6() {
		return B.getModules()[2];
	}

	/**
	 * @param module1 the module1 to set
	 */
	public void setModule6(Module module1) {
		B.getModules()[2] = module1;
	}

	/**
	 * @return the a
	 */
	public ModuleGroup getA() {
		return A;
	}

	/**
	 * @param a the a to set
	 */
	protected void setA(ModuleGroup a) {
		A = a;
	}

	/**
	 * @return the b
	 */
	public ModuleGroup getB() {
		return B;
	}

	/**
	 * @param b the b to set
	 */
	protected void setB(ModuleGroup b) {
		B = b;
	}
}
