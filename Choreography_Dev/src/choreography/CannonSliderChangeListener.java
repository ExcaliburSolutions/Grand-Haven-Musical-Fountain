package choreography;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;

public class CannonSliderChangeListener implements ChangeListener<Number>{
	Cannon cannon;
	public CannonSliderChangeListener(Cannon cannon) {
		this.cannon = cannon;
	}

	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1,
			Number arg2) {
		// TODO Auto-generated method stub
		
	}
}
