/**
 * 
 */
package lib.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.IndependentCannon;

/**
 * @author madridf
 *
 */
public class IndependentCannonSliderChangeListener implements ChangeListener<Number>{

	IndependentCannon cannon;

	/**
	 * @param cannon
	 */
	public IndependentCannonSliderChangeListener(IndependentCannon cannon) {
		this.cannon = cannon;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
		cannon.setLevel(newValue.intValue());
	}

}
