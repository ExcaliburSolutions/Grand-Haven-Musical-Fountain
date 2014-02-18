/**
 * 
 */
/**
 * @author Max
 *
 */
import javax.swing.*;  
import javax.swing.event.*;  
  
public class TimeSlider extends JSlider implements ChangeListener {  
  
    private final static int INITIAL_ORIENTATION = SwingConstants.HORIZONTAL;  
    private final static int INITIAL_MIN = 0;  
    private final static int INITIAL_MAX = 10000;  
    private final static int INITIAL_VALUE = 0;  
      
    public TimeSlider() {  
        super(INITIAL_ORIENTATION, INITIAL_MIN, INITIAL_MAX, INITIAL_VALUE);  
          
        setPaintTicks(true);  
        setPaintLabels(true);  
        setMajorTickSpacing((INITIAL_MAX - INITIAL_MIN) / 5);  
        setMinorTickSpacing((INITIAL_MAX - INITIAL_MIN) / 10);  
  
        addChangeListener(this);  
    }  
      
    @Override  
    public void stateChanged(ChangeEvent e) {  
        JSlider source = (JSlider) e.getSource();  
        int currentValue = source.getValue();  
        if (!source.getValueIsAdjusting()) {  
            System.out.println("VAL: " + currentValue + ", MAX: " + source.getMaximum());  
            if(currentValue == source.getMaximum()) {  
                // Extend to the future  
                source.setMinimum(source.getMinimum() + (int) ((INITIAL_MAX - INITIAL_MIN) * 0.5));  
                source.setMaximum(source.getMaximum() + (int) ((INITIAL_MAX - INITIAL_MIN) * 0.5));  
                // Re-set current value, because it changes when bounds change  
                source.setValue(currentValue);  
            } else if(currentValue == source.getMinimum()) {  
                // Extend to the past  
                source.setMinimum(source.getMinimum() - (int) ((INITIAL_MAX - INITIAL_MIN) * 0.5));  
                source.setMaximum(source.getMaximum() - (int) ((INITIAL_MAX - INITIAL_MIN) * 0.5));  
                if(source.getMinimum() < 0) {  
                    source.setMinimum(INITIAL_MIN);  
                    source.setMaximum(INITIAL_MAX);  
                }  
                // Re-set current value, because it changes when bounds change  
                source.setValue(currentValue);  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        JFrame frame = new JFrame("TimeSlider test");  
        frame.add(new TimeSlider());  
        frame.setBounds(100, 100, 400, 100);  
        frame.setVisible(true);  
    }  
}  
package lightcontrol;