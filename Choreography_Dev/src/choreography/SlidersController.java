package choreography;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import lib.CannonSliderChangeListener;
import lib.Fountain;
import lib.Module;
import lib.ModuleGroup;
import lib.cannons.*;

public class SlidersController {
	
	@FXML private Slider ring1ASlider, ring1BSlider, ring2ASlider, ring2BSlider, ring3ASlider, ring3BSlider, ring4ASlider, ring4BSlider, ring5ASlider, ring5BSlider;
	@FXML private Slider multiASlider, multiBSlider;
	@FXML private Slider candleabraASlider, candalabraBSlider;
	@FXML private Slider sweepsASlider, sweepsBSlider;			//fx:id
	@FXML private Slider ftCurtSlider, bkCurtSlider;
	@FXML private Slider peacockSlider, bazookaSlider, spoutSlider;

	private Ring ring1A, ring1B, ring2A, ring2B, ring3A, ring3B, ring4A, ring4B, ring5A, ring5B;
	private Multi multiA, multiB;
	private Candalabra candleA, candleB;
	private Sweeps sweepsA, sweepsB;
	private FtCurt ftCurt;
	private BkCurt bkCurt;
	private Peacock peacock;
	private Bazooka bazooka;
	private Spout spout;
	
	private ModuleGroup A;
	private ModuleGroup B;
	private Ring[] ring1, ring2, ring3, ring4, ring5;
	
//	public void setupSlidersController(Module m1, Module m2, Module m3, Module m4, Module m5, Module m6, Module m7, 
//			Peacock peacock, Bazooka bazooka, BkCurt bkCurt, FtCurt ftCurt, Spout spout) {
//		A = new Module[]{m1, m3, m5, m7};
//		B = new Module[]{m2, m4, m6};
//		
//	}
	
	public void setupSlidersController(Fountain fountain){
		A = fountain.getA();
		B = fountain.getB();
		Module[] aModules = A.getModules();
		for (Module m: aModules){
			for(Cannon c: m){
				
			}
		}
	}
	
	public void ring1ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener());
		System.out.println();
	}
	
	public void ring1BSliderHandler(MouseEvent event) {
		ring1BSlider.valueProperty().addListener(new CannonSliderChangeListener(ring1B));
	}
	
	public void ring2ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring2A));
	}
	
	public void ring2BSliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring2B));
	}
	
	public void ring3ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring3A));
	}
	
	public void ring3BSliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring3B));
	}
	
	public void ring4ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring4A));
	}
	
	public void ring4BSliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring4B));
	}
	
	public void ring5ASliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring5A));
	}
	
	public void ring5BSliderHandler(MouseEvent event) {
		ring1ASlider.valueProperty().addListener(new CannonSliderChangeListener(ring5B));
	}
	
	public void multiASliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(multiA));
	}
	
	public void multiBSliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(multiB));
	}
	
	public void candleabraASliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(candleA));
	}
	
	public void candleabraBSliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(candleB));
	}
	
	public void sweepsASliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(sweepsA));
	}
	
	public void sweepsBSliderHandler(MouseEvent event) {
		multiASlider.valueProperty().addListener(new CannonSliderChangeListener(sweepsB));
	}
	
	public void ftCurtSliderHandler(MouseEvent event) {
		ftCurtSlider.valueProperty().addListener(new CannonSliderChangeListener(ftCurt));
	}
	
	public void bkCurtSliderHandler(MouseEvent event) {
		bkCurtSlider.valueProperty().addListener(new CannonSliderChangeListener(bkCurt));
	}
	
	public void peacockSliderHandler(MouseEvent event) {
		peacockSlider.valueProperty().addListener(new CannonSliderChangeListener(peacock));
	}
	
	public void bazookaSliderHandler(MouseEvent event) {
		bazookaSlider.valueProperty().addListener(new CannonSliderChangeListener(bazooka));
	}
	
	public void spoutSliderHandler(MouseEvent event) {
		spoutSlider.valueProperty().addListener(new CannonSliderChangeListener(spout));
	}
}
	