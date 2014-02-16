/**
 * 
 */
package lib;

/**
 * @author madridf
 *
 */
public class ModuleGroup {
	String AB;
	Module[] modules;
	
//	public ModuleGroup(Module[] modules){
//		this.modules = modules;
//	}
	
	public ModuleGroup(Module ... modulesIn) {
		if(modulesIn.length == 4){
			this.modules = new Module[4];
			this.modules = modulesIn;
			AB = "A";
		} else if(modulesIn.length == 3){
			this.modules = new Module[3];
			this.modules = modulesIn;
			AB = "B";
		}
	}

	/**
	 * @return the aB
	 */
	protected String getAB() {
		return AB;
	}

	/**
	 * @param aB the aB to set
	 */
	protected void setAB(String aB) {
		AB = aB;
	}

	/**
	 * @return the modules
	 */
	public Module[] getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	protected void setModules(Module[] modules) {
		this.modules = modules;
	}
}
