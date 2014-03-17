package choreography.view.colorPalette;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * @author Nick Van Kuiken
 * @author Frank Madrid
 * Controls the colorPalette
 */
public class ColorPaletteController implements Initializable {

    @FXML private static ColorPaletteController cpc;
//    @FXML private static boolean isAdvanced;
    // HBox used to hold the colorPalette
    @FXML private HBox colorPalette;
    // Color picker used for the custom colors. 
    @FXML ColorPicker colorPicker;
    @FXML private Rectangle[] rectangles;
    @FXML private Paint selectedColor;
    @FXML private int selectedColorIndex;
    
    /**
     * @return cpc the current colorPalette
     * Returns the colorPalette that has been fully built. 
     */
    public static ColorPaletteController getInstance() {
            if(cpc == null)
                    cpc = new ColorPaletteController();
            return cpc;
    }

//    private ColorPaletteController() {
//        isAdvanced = false;
//    }
    // The current instance of the selected color
    
    /**
    * @return selectedColor current instance of the selected color
    * Returns the current instance of the selected color.
    */
   public Paint getSelectedColor() {
       return selectedColor;
   }

   /**
    * @param c The passes color that has been selected
    * Sets the global variable selectedColor to the color that has been chosen
    */
   public void setSelectedColor(int c) {
        setSelectedColor(ColorPaletteModel.getInstance().getColors()[c]);
   }

   public void setSelectedColorIndex(int c) {
        ColorPaletteModel.getInstance().setSelectedIndex(c);
   }

    /**
    * @param arg0
    * @param arg1
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     * Initializes the colorPalette. 
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        /**
         * @param args the command line arguments
         */

        rectangles = new Rectangle[32];

        // sets the selected color to the a default to avoid errors. 
        setSelectedColor(ColorPaletteModel.getInstance().getColors()[13]);

        // Sets the first color to red and creates the event handler
        for(int index = 0; index < 16; index++) {
            final int index2 = index;
            rectangles[index] = new Rectangle(25, 25, ColorPaletteModel.getInstance().getColors()[index]);
            rectangles[index].setOnMousePressed((MouseEvent me) -> {
                ColorPaletteModel.getInstance().setSelectedIndex(index2);
            });
            colorPalette.getChildren().add(rectangles[index]);
            
        }
        
        rectangles[16] = new Rectangle(25, 25, ColorPaletteModel.getInstance().getColor(1));
        rectangles[16].setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    rectangles[16].setFill(colorPicker.getValue());
                    rectangles[17].setWidth(25);
                    rectangles[17].setHeight(25);
                }
                else {
                    ColorPaletteModel.getInstance().setSelectedIndex(16);
                }
            }
        });
        
        for(int index = 17; index < 32; index++) {
            final int index2 = index;
            rectangles[index] = new Rectangle(0,0, Color.WHITE);
            rectangles[index].setOnMousePressed((MouseEvent me) -> {
                if(me.getButton() == MouseButton.SECONDARY) {
                    rectangles[index2].setFill(colorPicker.getValue());
                    rectangles[index2 + 1].setWidth(25);
                    rectangles[index2 + 1].setHeight(25);
                }
                else {
                    ColorPaletteModel.getInstance().setSelectedIndex(index2);
                }
            });
            colorPalette.getChildren().add(colorPalette.getChildren().size(), rectangles[index]);
            
        }
        cpc = this;
    }
    
    /**
     * @return the selectedColorIndex
     */
    public int getSelectedColorIndex() {
        return selectedColorIndex;
    }

    /**
     * @param selectedColor the selectedColor to set
     */
    public void setSelectedColor(Paint selectedColor) {
        this.selectedColor = selectedColor;
    }

}
