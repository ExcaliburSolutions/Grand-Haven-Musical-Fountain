package choreography.sliders;

import static java.time.Clock.system;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lib.cannons.Cannon;
import lib.cannons.Multi;
import lib.cannons.Ring;
import lib.events.StatusTextEvent;
import org.bushe.swing.event.EventBus;

/**
 *
 * @author elementsking
 */
class CannonSliderChangeListener<T extends Cannon> implements ChangeListener<Number> {

    ArrayList<T> cannons;
    String name;
    
    public CannonSliderChangeListener(ArrayList<T> list) {
        cannons = new ArrayList<>();
        for(T c : list) {
            cannons.add(c);
        }
        name = cannons.get(0).getClass().getSimpleName();
    }
    
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        for(T c : cannons){
            c.setLevel(t1.intValue());
            
        } 
        EventBus.publish(new StatusTextEvent("Cannons: " + name + " updated to level " + t1.intValue())); 
    }
}
