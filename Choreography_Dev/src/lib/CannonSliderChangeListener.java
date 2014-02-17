package lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;
import lib.cannons.Ring;

public class CannonSliderChangeListener implements ChangeListener<Number>{
	Cannon[] cannons;
	
	public CannonSliderChangeListener(Cannon[] cannons) {
		this.cannons = cannons;
	}

	public CannonSliderChangeListener(Ring ring) {
		cannons= new Cannon[1];
		cannons[0]=ring;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
		for(Cannon c : cannons){
			c.setLevel(newValue.intValue());
			System.out.println(c);
		}
	}
}
