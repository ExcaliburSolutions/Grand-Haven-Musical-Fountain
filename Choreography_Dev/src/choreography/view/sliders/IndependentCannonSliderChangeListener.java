/**
 * 
 */
package choreography.view.sliders;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import choreography.model.cannon.IndependentCannon;

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
