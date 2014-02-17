package choreography.sliders;

import java.util.ArrayList;

import org.bushe.swing.event.annotation.EventSubscriber;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import lib.CannonGroup;
import lib.Fountain;
import lib.Module;
import lib.ModuleGroup;
import lib.ResourceAvailable;
import lib.cannons.Bazooka;
import lib.cannons.BkCurt;
import lib.cannons.Candalabra;
import lib.cannons.Cannon;
import lib.cannons.FtCurt;
import lib.cannons.Multi;
import lib.cannons.Peacock;
import lib.cannons.Ring;
import lib.cannons.Spout;
import lib.cannons.Sweeps;
import choreography.Main;

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
	private CannonGroup<Candalabra> candlesA, candlesB;
	private CannonGroup<Sweeps> sweepssA, sweepssB;
	private FtCurt ftCurt;
	private BkCurt bkCurt;
	private Peacock peacock;
	private Bazooka bazooka;
	private Spout spout;
	//Represents the two groups of modules
	private ModuleGroup A;
	private ModuleGroup B;
	
	
	public SlidersController(){
		
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

	/**
	 * 
	 */
	private void setupAModule() {
		//get all modules in A
		Module[] aModules = A.getModules();
		ArrayList<Ring> cannons = null;
		//isolate all rings 1-5
		for(Module m : aModules) {
			cannons = new ArrayList<>();
			cannons.add(m.getR1());
			
		}
		rings1A = new CannonGroup<Ring>(cannons);
	}

	private <T> void setupCannonGroup(ModuleGroup AB, ArrayList<T> list) {
		for(Module m : AB) {
			list = new ArrayList<>();
			list.add(m.getR1());
			
		}
		rings1A = new CannonGroup<T>(list);
	}
		
		//store rings 1-5 in rings groups
		//isolate all multis
		//isolate all candles
		//isolate all sweeps
		
//		Module[] aModules = A.getModules();
//		rings1A = new Ring[4];
//		rings2A = new Ring[4];
//		rings3A = new Ring[4];
//		rings4A = new Ring[4];
//		rings5A = new Ring[4];
//		multisA = new Multi[4];
//		candlesA = new Candalabra[4];
//		sweepssA = new Sweeps[4];
//		for (Module m: aModules){
//			for(int c = 0; c <= 3; c++){
//				rings1A[c] = m.getR1();
//				rings2A[c] = m.getR2();
//				rings3A[c] = m.getR3();
//				rings4A[c] = m.getR4();
//				rings5A[c] = m.getR5();
//				multisA[c] = m.getMx();
//				candlesA[c] = m.getCandle();
//				sweepssA[c] = m.getSw();
//			}
//		}
	}
	
	/**
	 * 
	 */
	private void setupBModule() {
		Module[] aModules = B.getModules();
		rings1B = new Ring[3];
		rings2B = new Ring[3];
		rings3B = new Ring[3];
		rings4B = new Ring[3];
		rings5B = new Ring[3];
		multisB = new Multi[3];
		candlesB = new Candalabra[3];
		sweepssB = new Sweeps[3];
		for (Module m: aModules){
			for(int c = 0; c <= 2; c++){
				rings1B[c] = m.getR1();
				rings2B[c] = m.getR2();
				rings3B[c] = m.getR3();
				rings4B[c] = m.getR4();
				rings5B[c] = m.getR5();
				multisB[c] = m.getMx();
				candlesB[c] = m.getCandle();
				sweepssB[c] = m.getSw();
			}
		}
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
	
}
	