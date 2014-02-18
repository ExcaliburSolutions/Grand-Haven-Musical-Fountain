package choreography.sliders;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import lib.Fountain;
import lib.Module;
import lib.ModuleGroup;
import lib.cannons.Bazooka;
import lib.cannons.BkCurt;
import lib.cannons.Candelabra;
import lib.cannons.CannonGroup;
import lib.cannons.FtCurt;
import lib.cannons.Multi;
import lib.cannons.Peacock;
import lib.cannons.Ring;
import lib.cannons.Spout;
import lib.cannons.Sweep;
import lib.events.CannonSliderChangeListener;
import lib.events.ResourceAvailable;

import org.bushe.swing.event.annotation.EventSubscriber;

import choreography.Main;
import lib.CannonEnum;

public class SlidersController {
	
	//All Sliders found on Sliders.fxml GUI
	@FXML private Slider r1A, r1B;
	@FXML private Slider r2A, r2B;
	@FXML private Slider r3A, r3B;
	@FXML private Slider r4A, r4B;
	@FXML private Slider r5A, r5B;
	@FXML private Slider mxA, mxB;
	@FXML private Slider candleA, candleB;
	@FXML private Slider swA, swB;
	@FXML private Slider ftC, bkC;
	@FXML private Slider pk, bz, sp;
	//Represents all cannons available on the fountain
	private Fountain fountain;
	private CannonGroup<Ring> rings1A, rings2A, rings3A, rings4A, rings5A, rings1B, rings2B, rings3B, rings4B, rings5B;
	private CannonGroup<Multi> multisA, multisB;
	private CannonGroup<Candelabra> candlesA, candlesB;
	private CannonGroup<Sweep> sweepssA, sweepssB;
	private FtCurt ftCurt;
	private BkCurt bkCurt;
	private Peacock peacock;
	private Bazooka bazooka;
	private Spout spout;
	//Represents the two groups of modules
	private ModuleGroup A;
	private ModuleGroup B;
	
	
	public SlidersController(){
		Main.getFountain();
		configureModules(fountain);
	}
	
	public void configureModules(Fountain fountain){
		fountain = Main.getFountain();
		A = fountain.getA();
		B = fountain.getB();
		setupAModule();
		setupBModule();
		peacock = fountain.getPeacock();
		bazooka = fountain.getBazooka();
		spout = fountain.getSpout();
		bkCurt = fountain.getBkCurt();
		ftCurt = fountain.getFtCurt();
	}

        private void setupAModule() {
            rings1A = A.getCannonGroup(CannonEnum.RING1);
        }
  
	
	/**
	 * 
	 */
	private void setupBModule() {

	}
	
	public void cannonSliderChangeListener() {
		r1A.valueProperty().addListener(new CannonSliderChangeListener<>(rings1A));
	}

	/**
	 * @return the fountain
	 */
	public Fountain getFountain() {
		return fountain;
	}

	/**
	 * @param fountain the fountain to set
	 */
	public void setFountain(Fountain fountain) {
		this.fountain = fountain;
	}
	
	@EventSubscriber(eventClass=ResourceAvailable.class)
	public void resourceAvailableEventHandler(ResourceAvailable res) {
		if(res.getResource() instanceof Fountain){
			this.setFountain(fountain);
			configureModules(fountain);
		}
	}

	/**
	 * @return the r1A
	 */
	public Slider getR1A() {
		return r1A;
	}

	/**
	 * @param r1a the r1A to set
	 */
	public void setR1A(Slider r1a) {
		r1A = r1a;
	}

	/**
	 * @return the r1B
	 */
	public Slider getR1B() {
		return r1B;
	}

	/**
	 * @param r1b the r1B to set
	 */
	public void setR1B(Slider r1b) {
		r1B = r1b;
	}

	/**
	 * @return the r2A
	 */
	public Slider getR2A() {
		return r2A;
	}

	/**
	 * @param r2a the r2A to set
	 */
	public void setR2A(Slider r2a) {
		r2A = r2a;
	}

	/**
	 * @return the r2B
	 */
	public Slider getR2B() {
		return r2B;
	}

	/**
	 * @param r2b the r2B to set
	 */
	public void setR2B(Slider r2b) {
		r2B = r2b;
	}

	/**
	 * @return the r3A
	 */
	public Slider getR3A() {
		return r3A;
	}

	/**
	 * @param r3a the r3A to set
	 */
	public void setR3A(Slider r3a) {
		r3A = r3a;
	}

	/**
	 * @return the r3B
	 */
	public Slider getR3B() {
		return r3B;
	}

	/**
	 * @param r3b the r3B to set
	 */
	public void setR3B(Slider r3b) {
		r3B = r3b;
	}

	/**
	 * @return the r4A
	 */
	public Slider getR4A() {
		return r4A;
	}

	/**
	 * @param r4a the r4A to set
	 */
	public void setR4A(Slider r4a) {
		r4A = r4a;
	}

	/**
	 * @return the r4B
	 */
	public Slider getR4B() {
		return r4B;
	}

	/**
	 * @param r4b the r4B to set
	 */
	public void setR4B(Slider r4b) {
		r4B = r4b;
	}

	/**
	 * @return the r5A
	 */
	public Slider getR5A() {
		return r5A;
	}

	/**
	 * @param r5a the r5A to set
	 */
	public void setR5A(Slider r5a) {
		r5A = r5a;
	}

	/**
	 * @return the r5B
	 */
	public Slider getR5B() {
		return r5B;
	}

	/**
	 * @param r5b the r5B to set
	 */
	public void setR5B(Slider r5b) {
		r5B = r5b;
	}

	/**
	 * @return the mxA
	 */
	public Slider getMxA() {
		return mxA;
	}

	/**
	 * @param mxA the mxA to set
	 */
	public void setMxA(Slider mxA) {
		this.mxA = mxA;
	}

	/**
	 * @return the mxB
	 */
	public Slider getMxB() {
		return mxB;
	}

	/**
	 * @param mxB the mxB to set
	 */
	public void setMxB(Slider mxB) {
		this.mxB = mxB;
	}

	/**
	 * @return the candleA
	 */
	public Slider getCandleA() {
		return candleA;
	}

	/**
	 * @param candleA the candleA to set
	 */
	public void setCandleA(Slider candleA) {
		this.candleA = candleA;
	}

	/**
	 * @return the candleB
	 */
	public Slider getCandleB() {
		return candleB;
	}

	/**
	 * @param candleB the candleB to set
	 */
	public void setCandleB(Slider candleB) {
		this.candleB = candleB;
	}

	/**
	 * @return the swA
	 */
	public Slider getSwA() {
		return swA;
	}

	/**
	 * @param swA the swA to set
	 */
	public void setSwA(Slider swA) {
		this.swA = swA;
	}

	/**
	 * @return the swB
	 */
	public Slider getSwB() {
		return swB;
	}

	/**
	 * @param swB the swB to set
	 */
	public void setSwB(Slider swB) {
		this.swB = swB;
	}

	/**
	 * @return the ftC
	 */
	public Slider getFtC() {
		return ftC;
	}

	/**
	 * @param ftC the ftC to set
	 */
	public void setFtC(Slider ftC) {
		this.ftC = ftC;
	}

	/**
	 * @return the bkC
	 */
	public Slider getBkC() {
		return bkC;
	}

	/**
	 * @param bkC the bkC to set
	 */
	public void setBkC(Slider bkC) {
		this.bkC = bkC;
	}

	/**
	 * @return the pk
	 */
	public Slider getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(Slider pk) {
		this.pk = pk;
	}

	/**
	 * @return the bz
	 */
	public Slider getBz() {
		return bz;
	}

	/**
	 * @param bz the bz to set
	 */
	public void setBz(Slider bz) {
		this.bz = bz;
	}

	/**
	 * @return the sp
	 */
	public Slider getSp() {
		return sp;
	}

	/**
	 * @param sp the sp to set
	 */
	public void setSp(Slider sp) {
		this.sp = sp;
	}

	/**
	 * @return the rings1A
	 */
	public CannonGroup<Ring> getRings1A() {
		return rings1A;
	}

	/**
	 * @param rings1a the rings1A to set
	 */
	public void setRings1A(CannonGroup<Ring> rings1a) {
		rings1A = rings1a;
	}

	/**
	 * @return the rings2A
	 */
	public CannonGroup<Ring> getRings2A() {
		return rings2A;
	}

	/**
	 * @param rings2a the rings2A to set
	 */
	public void setRings2A(CannonGroup<Ring> rings2a) {
		rings2A = rings2a;
	}

	/**
	 * @return the rings3A
	 */
	public CannonGroup<Ring> getRings3A() {
		return rings3A;
	}

	/**
	 * @param rings3a the rings3A to set
	 */
	public void setRings3A(CannonGroup<Ring> rings3a) {
		rings3A = rings3a;
	}

	/**
	 * @return the rings4A
	 */
	public CannonGroup<Ring> getRings4A() {
		return rings4A;
	}

	/**
	 * @param rings4a the rings4A to set
	 */
	public void setRings4A(CannonGroup<Ring> rings4a) {
		rings4A = rings4a;
	}

	/**
	 * @return the rings5A
	 */
	public CannonGroup<Ring> getRings5A() {
		return rings5A;
	}

	/**
	 * @param rings5a the rings5A to set
	 */
	public void setRings5A(CannonGroup<Ring> rings5a) {
		rings5A = rings5a;
	}

	/**
	 * @return the rings1B
	 */
	public CannonGroup<Ring> getRings1B() {
		return rings1B;
	}

	/**
	 * @param rings1b the rings1B to set
	 */
	public void setRings1B(CannonGroup<Ring> rings1b) {
		rings1B = rings1b;
	}

	/**
	 * @return the rings2B
	 */
	public CannonGroup<Ring> getRings2B() {
		return rings2B;
	}

	/**
	 * @param rings2b the rings2B to set
	 */
	public void setRings2B(CannonGroup<Ring> rings2b) {
		rings2B = rings2b;
	}

	/**
	 * @return the rings3B
	 */
	public CannonGroup<Ring> getRings3B() {
		return rings3B;
	}

	/**
	 * @param rings3b the rings3B to set
	 */
	public void setRings3B(CannonGroup<Ring> rings3b) {
		rings3B = rings3b;
	}

	/**
	 * @return the rings4B
	 */
	public CannonGroup<Ring> getRings4B() {
		return rings4B;
	}

	/**
	 * @param rings4b the rings4B to set
	 */
	public void setRings4B(CannonGroup<Ring> rings4b) {
		rings4B = rings4b;
	}

	/**
	 * @return the rings5B
	 */
	public CannonGroup<Ring> getRings5B() {
		return rings5B;
	}

	/**
	 * @param rings5b the rings5B to set
	 */
	public void setRings5B(CannonGroup<Ring> rings5b) {
		rings5B = rings5b;
	}

	/**
	 * @return the multisA
	 */
	public CannonGroup<Multi> getMultisA() {
		return multisA;
	}

	/**
	 * @param multisA the multisA to set
	 */
	public void setMultisA(CannonGroup<Multi> multisA) {
		this.multisA = multisA;
	}

	/**
	 * @return the multisB
	 */
	public CannonGroup<Multi> getMultisB() {
		return multisB;
	}

	/**
	 * @param multisB the multisB to set
	 */
	public void setMultisB(CannonGroup<Multi> multisB) {
		this.multisB = multisB;
	}

	/**
	 * @return the candlesA
	 */
	public CannonGroup<Candelabra> getCandlesA() {
		return candlesA;
	}

	/**
	 * @param candlesA the candlesA to set
	 */
	public void setCandlesA(CannonGroup<Candelabra> candlesA) {
		this.candlesA = candlesA;
	}

	/**
	 * @return the candlesB
	 */
	public CannonGroup<Candelabra> getCandlesB() {
		return candlesB;
	}

	/**
	 * @param candlesB the candlesB to set
	 */
	public void setCandlesB(CannonGroup<Candelabra> candlesB) {
		this.candlesB = candlesB;
	}

	/**
	 * @return the sweepssA
	 */
	public CannonGroup<Sweep> getSweepssA() {
		return sweepssA;
	}

	/**
	 * @param sweepssA the sweepssA to set
	 */
	public void setSweepssA(CannonGroup<Sweep> sweepssA) {
		this.sweepssA = sweepssA;
	}

	/**
	 * @return the sweepssB
	 */
	public CannonGroup<Sweep> getSweepssB() {
		return sweepssB;
	}

	/**
	 * @param sweepssB the sweepssB to set
	 */
	public void setSweepssB(CannonGroup<Sweep> sweepssB) {
		this.sweepssB = sweepssB;
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
	public void setFtCurt(FtCurt ftCurt) {
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
	public void setBkCurt(BkCurt bkCurt) {
		this.bkCurt = bkCurt;
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
	public void setPeacock(Peacock peacock) {
		this.peacock = peacock;
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
	public void setBazooka(Bazooka bazooka) {
		this.bazooka = bazooka;
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
	public void setSpout(Spout spout) {
		this.spout = spout;
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
	public void setA(ModuleGroup a) {
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
	public void setB(ModuleGroup b) {
		B = b;
	}
	
}
	