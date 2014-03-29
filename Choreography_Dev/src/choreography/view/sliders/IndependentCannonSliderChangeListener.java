/**
 * 
 */
package choreography.view.sliders;

import choreography.io.FCWLib;
import choreography.model.cannon.IndependentCannon;
import choreography.model.fcw.FCW;
import choreography.view.ChoreographyController;
import choreography.view.music.MusicPaneController;
import choreography.view.timeline.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @author madridf
 *
 */
public class IndependentCannonSliderChangeListener implements ChangeListener<Number>{

	private IndependentCannon cannon;
	private String name;
        private int lastNumber;

	/**
	 * @param cannon
	 */
	public IndependentCannonSliderChangeListener(IndependentCannon cannon) {
		this.cannon = cannon;
		name = cannon.getName();
	}

    /**
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    @Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue,
			Number newValue) {
            int level = newValue.intValue();
            lastNumber = level;
            cannon.setLevel(level);
            String[] actions = new String[]{Integer.toString(level), name};
            FCW f = FCWLib.getInstance().getFCW(name, actions);
            ChoreographyController.getInstance().setfcwOutput(
                f.toString());
            Timeline.getInstance().setWaterFcwAtPoint(
                MusicPaneController.getInstance().getTenthsTime(), f);
	}

    public int getLastNumber() {
        return lastNumber;
    }

}
