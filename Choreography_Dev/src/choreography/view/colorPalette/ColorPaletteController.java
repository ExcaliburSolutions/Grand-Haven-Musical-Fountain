package choreography.view.colorPalette;

import java.net.URL;
import java.util.ArrayList;
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
 * @author vankuikn
 * Controls the colorPalette
 */
public class ColorPaletteController implements Initializable{

	private static ColorPaletteController cpc;
        private static boolean isAdvanced;

	/**
	 * @return cpc the current colorPalette
	 * Returns the colorPalette that has been fully built. 
	 */
	public static ColorPaletteController getInstance() {
		if(cpc == null)
			cpc = new ColorPaletteController();
		return cpc;
	}

	// HBox used to hold the colorPalette

    /**
     *
     */
    	@FXML public HBox colorPalette;
	// Color picker used for the custom colors. 
	@FXML ColorPicker colorPicker;
	// HBox used to hold the colorPalette
	@FXML HBox colorPaletteHbox;
	// Color 0-15 are default colors that have been pre-defined
	@FXML Rectangle color0;
	@FXML Rectangle color1;
	@FXML Rectangle color2;
	@FXML Rectangle color3;
	@FXML Rectangle color4;
	@FXML Rectangle color5;
	@FXML Rectangle color6;
	@FXML Rectangle color7;
	@FXML Rectangle color8;
	@FXML Rectangle color9;
	@FXML Rectangle color10;
	@FXML Rectangle color11;
	@FXML Rectangle color12;
	@FXML Rectangle color13;
	@FXML Rectangle color14;
	@FXML Rectangle color15;
	// Color 16-31 are custom colors that are picked by the user.
	@FXML Rectangle color16;
	@FXML Rectangle color17;
	@FXML Rectangle color18;
	@FXML Rectangle color19;
	@FXML Rectangle color20;
	@FXML Rectangle color21;
	@FXML Rectangle color22;
	@FXML Rectangle color23;
	@FXML Rectangle color24;
	@FXML Rectangle color25;
	@FXML Rectangle color26;
	@FXML Rectangle color27;
	@FXML Rectangle color28;
	@FXML Rectangle color29;
	@FXML Rectangle color30;
	@FXML Rectangle color31;

	// The current instance of the selected color

    /**
     *
     */
    	public Paint selectedColor;

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
	private void setSelectedColor(Paint c) {
		selectedColor = c;
	}
	
	/**
	 * @return savedColorPalette is an array that contains all of the colors 
	 * currently defined in the color palette. 
	 */
	public Object saveColorPalette () {
            ArrayList<Color> savedColorPalette = new ArrayList<Color>();
            for (int i = 0 ; i < 32 ; i++){
                if (i == 0) {
                        savedColorPalette.add((Color) color0.getFill());
                } else if (i == 1) { 
                        savedColorPalette.add((Color) color1.getFill());
                } else if (i == 2) { 
                        savedColorPalette.add((Color) color2.getFill());
                } else if (i == 3) {
                        savedColorPalette.add((Color) color3.getFill());
                } else if (i == 4) {
                        savedColorPalette.add((Color) color4.getFill());
                } else if (i == 5) {
                        savedColorPalette.add((Color) color5.getFill());
                } else if (i == 6) {
                        savedColorPalette.add((Color) color6.getFill());
                } else if (i == 7) {
                        savedColorPalette.add((Color) color7.getFill());
                } else if (i == 8) {
                        savedColorPalette.add((Color) color8.getFill());
                } else if (i == 9) {
                        savedColorPalette.add((Color) color9.getFill());
                } else if (i == 10) {
                        savedColorPalette.add((Color) color10.getFill());
                } else if (i == 11) {
                        savedColorPalette.add((Color) color11.getFill());
                } else if (i == 12) {
                        savedColorPalette.add((Color) color12.getFill());
                } else if (i == 13) {
                        savedColorPalette.add((Color) color13.getFill());
                } else if (i == 14) {
                        savedColorPalette.add((Color) color14.getFill());
                } else if (i == 15) {
                        savedColorPalette.add((Color) color15.getFill());
                } else if (i == 16) {
                        savedColorPalette.add((Color) color16.getFill());
                } else if (i == 17) {
                        savedColorPalette.add((Color) color17.getFill());
                } else if (i == 18) {
                        savedColorPalette.add((Color) color18.getFill());
                } else if (i == 19) {
                        savedColorPalette.add((Color) color19.getFill());
                } else if (i == 20) {
                        savedColorPalette.add((Color) color20.getFill());
                } else if (i == 21) {
                        savedColorPalette.add((Color) color21.getFill());
                } else if (i == 22) {
                        savedColorPalette.add((Color) color22.getFill());
                } else if (i == 23) {
                        savedColorPalette.add((Color) color23.getFill());
                } else if (i == 24) {
                        savedColorPalette.add((Color) color24.getFill());
                } else if (i == 25) {
                        savedColorPalette.add((Color) color25.getFill());
                } else if (i == 26) {
                        savedColorPalette.add((Color) color26.getFill());
                } else if (i == 27) {
                        savedColorPalette.add((Color) color27.getFill());
                } else if (i == 28) {
                        savedColorPalette.add((Color) color28.getFill());
                } else if (i == 29) {
                        savedColorPalette.add((Color) color29.getFill());
                } else if (i == 30) {
                        savedColorPalette.add((Color) color30.getFill());
                } else if (i == 31) {
                        savedColorPalette.add((Color) color31.getFill());
                }
            }

            return null;
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

            Color c0 = Color.web("FF0000"); // red
            Color c1 = Color.web("FF1900"); // orange
            Color c2 = Color.web("FFC800"); // yellow
            Color c3 = Color.web("00F000"); // green
            Color c4 = Color.web("0000FF"); // blue
            Color c5 = Color.web("3B11FF"); // violet
            Color c6 = Color.web("FF0037"); // lightRed
            Color c7 = Color.web("FF3205"); // lightOrange
            Color c8 = Color.web("FFFF08"); // lightYellow
            Color c9 = Color.web("3BFF00"); // lightGreen
            Color c10 = Color.web("00FFFF"); // lightBlue
            Color c11 = Color.web("9646FF"); // lightViolet
            Color c12 = Color.web("D100C5"); // magenta
            Color c13 = Color.web("0096EF"); // cyan
            Color c14 = Color.web("000000"); // black/Off
            Color c15 = Color.web("FFFFFF"); // white/On

            // sets the selected color to the a default to avoid errors. 
            selectedColor = c0;

            // Sets the first color to red and creates the event handler
            color0.setFill(c0);
            color0.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color0.getFill());
                    }
            });
            // Sets the second color to orange and creates the event handler
            color1.setFill(c1);
            color1.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color1.getFill());
                    }
            });
            // Sets the third color to yellow and creates the event handler
            color2.setFill(c2);
            color2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color2.getFill());
                    }
            });
            // Sets the third color to green and creates the event handler
            color3.setFill(c3);
            color3.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color3.getFill());
                    }
            });
            // Sets the third color to blue and creates the event handler
            color4.setFill(c4);
            color4.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color4.getFill());
                    }
            });
            // Sets the third color to violet and creates the event handler
            color5.setFill(c5);
            color5.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color5.getFill());
                    }
            });
            // Sets the third color to lightRed and creates the event handler
            color6.setFill(c6);
            color6.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color6.getFill());
                    }
            });
            // Sets the third color to lightOrange and creates the event handler
            color7.setFill(c7);
            color7.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color7.getFill());
                    }
            });
            // Sets the third color to lightYellow and creates the event handler
            color8.setFill(c8);
            color8.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color8.getFill());
                    }
            });
            // Sets the third color to lightGreen and creates the event handler
            color9.setFill(c9);
            color9.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color9.getFill());
                    }
            });
            // Sets the third color to lightBlue and creates the event handler
            color10.setFill(c10);
            color10.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color10.getFill());
                    }
            });
            // Sets the third color to lightViolet and creates the envent handler
            color11.setFill(c11);
            color11.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color11.getFill());
                    }
            });
            // Sets the third color to magenta and creates the event handler
            color12.setFill(c12);
            color12.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color12.getFill());
                    }
            });
            // Sets the third color to cyan and creates the event handler
            color13.setFill(c13);
            color13.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color13.getFill());
                    }
            });
            // Sets the third color to black (off) and creates the event handler
            color14.setFill(c14);
            color14.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color14.getFill());
                    }
            });
            // Sets the third color to white (on) and creates the event handler
            color15.setFill(c15);
            color15.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                            setSelectedColor(color15.getFill());
                    }
            });
            if(isAdvanced) {
            // sets the first custom color visible and hides the rest
            color16.setVisible(true);
            color17.setVisible(false);
            color18.setVisible(false);
            color19.setVisible(false);
            color20.setVisible(false);
            color21.setVisible(false);
            color22.setVisible(false);
            color23.setVisible(false);
            color24.setVisible(false);
            color25.setVisible(false);
            color26.setVisible(false);
            color27.setVisible(false);
            color28.setVisible(false);
            color29.setVisible(false);
            color30.setVisible(false);
            color31.setVisible(false);

            // Sets all the custom colors to a default white
            color16.setFill(c15);
            color17.setFill(c15);
            color18.setFill(c15);
            color19.setFill(c15);
            color20.setFill(c15);
            color21.setFill(c15);
            color22.setFill(c15);
            color23.setFill(c15);
            color24.setFill(c15);
            color25.setFill(c15);
            color26.setFill(c15);
            color27.setFill(c15);
            color28.setFill(c15);
            color29.setFill(c15);
            color30.setFill(c15);
            color31.setFill(c15);

            // Creates the event handler for the custom color squares. 
            color16.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color16.setFill(colorPicker.getValue());
                                    // Sets the next custom color square to visible 
                                    color17.setVisible(true);
                            } else {
                                    setSelectedColor(color16.getFill());
                            }
                    }
            });
            color17.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color17.setFill(colorPicker.getValue());
                                    color18.setVisible(true);
                            } else {
                                    setSelectedColor(color17.getFill());
                            }
                    }
            });
            color18.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color18.setFill(colorPicker.getValue());
                                    color19.setVisible(true);
                            } else {
                                    setSelectedColor(color18.getFill());
                            }
                    }
            });
            color19.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color19.setFill(colorPicker.getValue());
                                    color20.setVisible(true);
                            } else {
                                    setSelectedColor(color19.getFill());
                            }
                    }
            });
            color20.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color20.setFill(colorPicker.getValue());
                                    color21.setVisible(true);
                            } else {
                                    setSelectedColor(color20.getFill());
                            }
                    }
            });
            color21.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color21.setFill(colorPicker.getValue());
                                    color22.setVisible(true);
                            } else {
                                    setSelectedColor(color21.getFill());
                            }
                    }
            });
            color22.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color22.setFill(colorPicker.getValue());
                                    color23.setVisible(true);
                            } else {
                                    setSelectedColor(color22.getFill());
                            }
                    }
            });
            color23.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color23.setFill(colorPicker.getValue());
                                    color24.setVisible(true);
                            } else {
                                    setSelectedColor(color23.getFill());
                            }
                    }
            });
            color24.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color24.setFill(colorPicker.getValue());
                                    color25.setVisible(true);
                            } else {
                                    setSelectedColor(color24.getFill());
                            }
                    }
            });
            color25.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color25.setFill(colorPicker.getValue());
                                    color26.setVisible(true);
                            } else {
                                    setSelectedColor(color25.getFill());
                            }
                    }
            });
            color26.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color26.setFill(colorPicker.getValue());
                                    color27.setVisible(true);
                            } else {
                                    setSelectedColor(color26.getFill());
                            }
                    }
            });
            color27.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color27.setFill(colorPicker.getValue());
                                    color28.setVisible(true);
                            } else {
                                    setSelectedColor(color27.getFill());
                            }
                    }
            });
            color28.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color28.setFill(colorPicker.getValue());
                                    color29.setVisible(true);
                            } else {
                                    setSelectedColor(color28.getFill());
                            }
                    }
            });
            color29.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color29.setFill(colorPicker.getValue());
                                    color30.setVisible(true);
                            } else {
                                    setSelectedColor(color29.getFill());
                            }
                    }
            });
            color30.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color30.setFill(colorPicker.getValue());
                                    color31.setVisible(true);
                            } else {
                                    setSelectedColor(color30.getFill());
                            }
                    }
            });
            color31.setOnMousePressed(new EventHandler <MouseEvent>() {
                    public void handle(MouseEvent me) {
                            if (me.getButton() == MouseButton.SECONDARY) {
                                    color31.setFill(colorPicker.getValue());
                            } else {
                                    setSelectedColor(color31.getFill());
                            }
                    }
            });
            }
            cpc = this;
	}

    public void setAdvancedFunction(boolean b) {
        isAdvanced = b;
    }
}
