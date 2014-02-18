package lib.events;

import org.bushe.swing.event.EventBus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;
import lib.cannons.CannonGroup;

public class CannonSliderChangeListener<T extends Cannon> implements ChangeListener<Number>{
	CannonGroup<T> cannons;
	
	public CannonSliderChangeListener(CannonGroup<T> cannons) {
		this.cannons = cannons;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
		for(Cannon c : cannons.getCannons()){
			c.setLevel(newValue.intValue());
			EventBus.publish(new StatusText(c.getClass().getSimpleName() + " set to " + c.getLevel()));
		}
	}
}
