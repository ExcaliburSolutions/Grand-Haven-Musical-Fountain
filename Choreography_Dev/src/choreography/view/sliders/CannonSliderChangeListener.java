package choreography.view.sliders;

import choreography.view.ChoreographyController;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import choreography.model.fcw.FCWLib;
import choreography.model.cannon.Cannon;

/**
 *
 * @author elementsking
 */
class CannonSliderChangeListener<T extends Cannon> implements ChangeListener<Number> {

    ArrayList<T> cannons;
    String name;
    String module;
    
    public CannonSliderChangeListener(ArrayList<T> list, String module) {
        this.module = module;
        cannons = new ArrayList<>();
        for(T c : list) {
            cannons.add(c);
        }
        
        name = list.get(0).getName();
    }
    
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        int level = t1.intValue();
        for(T c : cannons){
            c.setLevel(level);
        } 
        System.out.println();
        String[] actions = new String[]{module, Integer.toString(level)};
        ChoreographyController.getInstance().setfcwOutput(
                FCWLib.getInstance().getFCW(name.toString(), actions).toString());
    }
}
