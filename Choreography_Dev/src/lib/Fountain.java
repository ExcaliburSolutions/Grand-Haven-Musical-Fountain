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
//	private Ring r11, r21, r31, r41, r51;
//	private Multi m1; private Candalabra c1; private Sweeps s1; //Module 1
//	private Ring r12, r22, r32, r42, r52;
//	private Multi m2; private Candalabra c2; private Sweeps s2; //Module 2
//	private Ring r13, r23, r33, r43, r53;
//	private Multi m3; private Candalabra c3; private Sweeps s3; //Module 3
//	private Ring r14, r24, r34, r44, r54;
//	private Multi m4; private Candalabra c4; private Sweeps s4; //Module 4
//	private Ring r15, r25, r35, r45, r55;
//	private Multi m5; private Candalabra c5; private Sweeps s5; //Module 5
//	private Ring r16, r26, r36, r46, r56;
//	private Multi m6; private Candalabra c6; private Sweeps s6; //module 6
//	private Ring r17, r27, r37, r47, r57;
//	private Multi m7; private Candalabra c7; private Sweeps s7; //Module 7
//	private Ring[][] rings;
//	private Multi[] multis;
//	private Candalabra[] candles;
//	private Sweeps[] sweeps;
	
//	private Module module1, module2, module3, module4, module5, module6, module7;
	private Peacock peacock; private Spout spout; private Bazooka bazooka; private FtCurt ftCurt; private BkCurt bkCurt;
	private ModuleGroup A; private ModuleGroup B;
	
	public Fountain(){
		Module[] modules = new Module[7];
		
		for(int n = 0; n <= 6; n++){
				Ring[] rings = new Ring[]{new Ring(1), new Ring(2), new Ring(3), new Ring(4), new Ring(5)}; 
				modules[n] = new Module(n+1, rings, new Multi(0), new Candalabra(0), new Sweeps(0));
		}
		
		
		A = new ModuleGroup(modules[0], modules[2], modules[4],modules[6]);
		B = new ModuleGroup(modules[1], modules[3], modules[5]);
		
		
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
	public void setModule4(Module module2) {
		B.getModules()[1] = module2;
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
	public void setModule6(Module module2) {
		B.getModules()[2] = module2;
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
