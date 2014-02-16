package lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;

public class CannonSliderChangeListener implements ChangeListener<Number>{
	Cannon[] cannons;
	
	public CannonSliderChangeListener(Cannon[] cannons) {
		this.cannons = cannons;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
		for(Cannon c : cannons){
			System.out.print(c.setLevel(newValue.intValue()));
		}
	}
}
