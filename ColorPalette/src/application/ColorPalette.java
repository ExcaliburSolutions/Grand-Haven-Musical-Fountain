package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class ColorPalette extends Application {
	public Paint selectedColor;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Color Palette");
		Group root = new Group();
		final Scene scene = new Scene(root, 1100, 100);

		final ColorPicker colorPicker = new ColorPicker();

		colorPicker.setLayoutX(834); //834 ends the color boxes
		colorPicker.setLayoutY(0);

		colorPicker.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Color c = colorPicker.getValue();
				System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
			}
		});

		final GridPane gridpaneColor = new GridPane();
		gridpaneColor.setPrefSize(900, 300);

		gridpaneColor.setGridLinesVisible(true);

		final List<Rectangle> recColorArray = new ArrayList<Rectangle>();

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

		for(int i=0;i<32; i++){

			final int testI = i;

			if (i < 16){
				recColorArray.add(new Rectangle(25,25, Color.LIGHTGREY));
				gridpaneColor.getColumnConstraints().add(new ColumnConstraints(26));

				gridpaneColor.add(recColorArray.get(i), i, 0);

				recColorArray.get(i).setOnMousePressed(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent me) {
						selectedColor = recColorArray.get(testI).getFill();
						System.out.println("Col " + (testI) );
					}
				});
				// preset colors
				if (i == 0) {
					recColorArray.get(testI).setFill(c0);
				} else if (i == 1) {
					recColorArray.get(testI).setFill(c1);
				} else if (i == 2) {
					recColorArray.get(testI).setFill(c2);
				} else if (i == 3) {
					recColorArray.get(testI).setFill(c3);
				} else if (i == 4) {
					recColorArray.get(testI).setFill(c4);
				} else if (i == 5) { 
					recColorArray.get(testI).setFill(c5);
				} else if (i == 6) { 
					recColorArray.get(testI).setFill(c6);
				} else if (i == 7) { 
					recColorArray.get(testI).setFill(c7);
				} else if (i == 8) { 
					recColorArray.get(testI).setFill(c8);
				} else if (i == 9) { 
					recColorArray.get(testI).setFill(c9);
				} else if (i == 10) { 
					recColorArray.get(testI).setFill(c10);
				} else if (i == 11) { 
					recColorArray.get(testI).setFill(c11);
				} else if (i == 12) { 
					recColorArray.get(testI).setFill(c12);
				} else if (i == 13) { 
					recColorArray.get(testI).setFill(c13);
				} else if (i == 14) { 
					recColorArray.get(testI).setFill(c14);
				} else if (i == 15) { 
					recColorArray.get(testI).setFill(c15);
				}
			}
		}
		
		final Button addCustomColor = new Button("Add Custom Color");
		final int c = 16;
			//else{
		//gridpaneColor.add(addCustomColor,c,1);
		addCustomColor.setLayoutX(836);
		addCustomColor.setLayoutY(20);
		addCustomColor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				
				gridpaneColor.getChildren().remove(c);
				recColorArray.add(new Rectangle(25,25, Color.LIGHTGRAY));
				gridpaneColor.getColumnConstraints().add(new ColumnConstraints(26));
				gridpaneColor.add(recColorArray.get(c), c, 0);

				(recColorArray.get(c)).setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						if (me.getButton() == MouseButton.SECONDARY) {
							recColorArray.get(c).setFill(colorPicker.getValue());
							System.out.println("Col " + (c) + "Worked!!!");
						} else {
							System.out.println(recColorArray.get(c).getFill());
						}
					}
				});
				//gridpaneColor.add(addCustomColor,c,1);
			}
		});

			//}
		//}
		root.getChildren().addAll(gridpaneColor, colorPicker, addCustomColor);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}