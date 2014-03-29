/**
 * 
 */
package choreography.model.cannon;

import choreography.model.fcw.FCW;
import choreography.view.colorPalette.ColorPaletteModel;
import choreography.view.sim.FountainSimController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * @author madridf
 *
 */
public class FtCurt extends IndependentCannon {

    * @param level
     */
    public FtCurt(int level) {
            super(level);
    }

    public FtCurt(int level, String name) {
        super(level, name);
    }

}
