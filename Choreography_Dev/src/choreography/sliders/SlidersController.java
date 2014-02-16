package choreography.sliders;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import lib.CannonSliderChangeListener;
import lib.Fountain;
import lib.IndependentCannonSliderChangeListener;
import lib.Module;
import lib.ModuleGroup;
import lib.cannons.Bazooka;
import lib.cannons.BkCurt;
import lib.cannons.Candalabra;
import lib.cannons.FtCurt;
import lib.cannons.Multi;
import lib.cannons.Peacock;
import lib.cannons.Ring;
import lib.cannons.Spout;
import lib.cannons.Sweeps;

public class SlidersController {
	
	@FXML private Slider ring1ASlider, ring1BSlider, ring2ASlider, ring2BSlider, ring3ASlider, ring3BSlider, ring4ASlider, ring4BSlider, ring5ASlider, ring5BSlider;
	@FXML private Slider multiASlider, multiBSlider;
	@FXML private Slider candleabraASlider, candalabraBSlider;
	@FXML private Slider sweepsASlider, sweepsBSlider;			//fx:id
	@FXML private Slider ftCurtSlider, bkCurtSlider;
	@FXML private Slider peacockSlider, bazookaSlider, spoutSlider;

	private static FtCurt ftCurt;
	private static BkCurt bkCurt;
	private static Peacock peacock;
	private static Bazooka bazooka;
	private static Spout spout;
	
	private static ModuleGroup A;
	private static ModuleGroup B;
	private static Ring[] rings1A, rings2A, rings3A, rings4A, rings5A;
	private static Ring[] rings1B, rings2B, rings3B, rings4B, rings5B;
	private static Multi[] multisA, multisB;
	private static Candalabra[] candlesA, candlesB;
	private static Sweeps[] sweepssA, sweepssB;
	
	public static void setupSlidersController(Fountain fountain){
		A = fountain.getA();
		B = fountain.getB();
		setupAModule();
		setupBModule();
		
		
	}

	/**
	 * 
	 */
	private static void setupAModule() {
		Module[] aModules = A.getModules();
		rings1A = new Ring[4];
		rings2A = new Ring[4];
		rings3A = new Ring[4];
		rings4A = new Ring[4];
		rings5A = new Ring[4];
		multisA = new Multi[4];
		candlesA = new Candalabra[4];
		sweepssA = new Sweeps[4];
		for (Module m: aModules){
			for(int c = 0; c <= 3; c++){
				rings1A[c] = m.getR1();
				rings2A[c] = m.getR2();
				rings3A[c] = m.getR3();
				rings4A[c] = m.getR4();
				rings5A[c] = m.getR5();
			}
			for(int c = 0; c <=3; c++){
				multisA[c] = m.getMx();
				candlesA[c] = m.getCandle();
				sweepssA[c] = m.getSw();
			}
		}
	}
	
	/**
	 * 
	 */
	private static void setupBModule() {
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
			}
			for(int c = 0; c <=2; c++){
				multisB[c] = m.getMx();
				candlesB[c] = m.getCandle();
				sweepssB[c] = m.getSw();
			}
		}
	}	
	
	public void ring1ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(rings1A));
	}
	
	public void ring1BSliderHandler(MouseEvent event) {
		ring1BSlider.valueProperty().addListener(new CannonSliderChangeListener(rings1B));
	}
	
	public void ring2ASliderHandler(MouseEvent event) {
		ring2ASlider.valueProperty().addListener(new CannonSliderChangeListener(rings2A));
	}
	
	public void ring2BSliderHandler(MouseEvent event) {
		ring2BSlider.valueProperty().addListener(new CannonSliderChangeListener(rings2B));
	}
	
	public void ring3ASliderHandler(MouseEvent event) {
		ring3ASlider.valueProperty().addListener(new CannonSliderChangeListener(rings3A));
	}
	
	public void ring3BSliderHandler(MouseEvent event) {
		ring3BSlider.valueProperty().addListener(new CannonSliderChangeListener(rings3B));
	}
	
	public void ring4ASliderHandler(MouseEvent event) {
		ring4ASlider.valueProperty().addListener(new CannonSliderChangeListener(rings4A));
	}
	
	public void ring4BSliderHandler(MouseEvent event) {
		ring4BSlider.valueProperty().addListener(new CannonSliderChangeListener(rings4B));
	}
	
	public void ring5ASliderHandler(MouseEvent event) {
		ring5ASlider.valueProperty().addListener(new CannonSliderChangeListener(rings5A));
	}
	
	public void ring5BSliderHandler(MouseEvent event) {
		ring5BSlider.valueProperty().addListener(new CannonSliderChangeListener(rings5B));
	}
	
	public void multiASliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(multisA));
	}
	
	public void multiBSliderHandler(MouseEvent event) {
		multiBSlider.valueProperty().addListener(new CannonSliderChangeListener(multisB));
	}
	
	public void candleabraASliderHandler(MouseEvent event) {
		candleabraASlider.valueProperty().addListener(new CannonSliderChangeListener(candlesA));
	}
	
	public void candleabraBSliderHandler(MouseEvent event) {
		candalabraBSlider.valueProperty().addListener(new CannonSliderChangeListener(candlesB));
	}
	
	public void sweepsASliderHandler(MouseEvent event) {
		sweepsASlider.valueProperty().addListener(new CannonSliderChangeListener(sweepssA));
	}
	
	public void sweepsBSliderHandler(MouseEvent event) {
		sweepsBSlider.valueProperty().addListener(new CannonSliderChangeListener(sweepssB));
	}
	
	public void ftCurtSliderHandler(MouseEvent event) {
		ftCurtSlider.valueProperty().addListener(new IndependentCannonSliderChangeListener(ftCurt));
	}
	
	public void bkCurtSliderHandler(MouseEvent event) {
		bkCurtSlider.valueProperty().addListener(new IndependentCannonSliderChangeListener(bkCurt));
	}
	
	public void peacockSliderHandler(MouseEvent event) {
		peacockSlider.valueProperty().addListener(new IndependentCannonSliderChangeListener(peacock));
	}
	
	public void bazookaSliderHandler(MouseEvent event) {
		bazookaSlider.valueProperty().addListener(new IndependentCannonSliderChangeListener(bazooka));
	}
	
	public void spoutSliderHandler(MouseEvent event) {
		spoutSlider.valueProperty().addListener(new IndependentCannonSliderChangeListener(spout));
	}
}
	