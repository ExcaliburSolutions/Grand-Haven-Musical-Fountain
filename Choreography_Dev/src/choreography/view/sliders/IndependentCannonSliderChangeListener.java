/**
 * 
 */
package choreography.view.sliders;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import choreography.model.cannon.Cannon;
import choreography.model.cannon.IndependentCannon;
import choreography.model.fcw.FCWLib;
import choreography.view.ChoreographyController;

/**
 * @author madridf
 *
 */
public class IndependentCannonSliderChangeListener implements ChangeListener<Number>{

	private IndependentCannon cannon;
	private String name;

	/**
	 * @param cannon
	 */
	public IndependentCannonSliderChangeListener(IndependentCannon cannon) {
		this.cannon = cannon;
		name = cannon.getName();
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
        int level = newValue.intValue();
            cannon.setLevel(level);
        String[] actions = new String[]{Integer.toString(level)};
        ChoreographyController.getInstance().setfcwOutput(
                FCWLib.getInstance().getFCW(name.toString(), actions).toString());

	}

}
