package lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;

public class CannonSliderChangeListener implements ChangeListener<Number>{
	Cannon cannon;
	public CannonSliderChangeListener(Cannon cannon) {
		this.cannon = cannon;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
		System.out.println(newValue.toString());
		cannon.setLevel(newValue.intValue());
		System.out.println(cannon.getLevel());
	}
}
