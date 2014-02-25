package choreography.view.colorPalette;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

		colorPicker.setLayoutX(834); //834 ends the color swatches 
		colorPicker.setLayoutY(0);

		colorPicker.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Color c = colorPicker.getValue();
				System.out.println("New Color's RGB = "+c.getRed()+" "+c.getGreen()+" "+c.getBlue());
			}
		});

		GridPane gridpaneColor = new GridPane();
		gridpaneColor.setPrefSize(900, 300);

		gridpaneColor.setGridLinesVisible(true);

		final ArrayList recColorArray1 = new ArrayList();
		
		//final Rectangle[] recColorArray = new Rectangle[32];

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
				recColorArray1.add(new Rectangle(25,25, Color.LIGHTGREY));
				//recColorArray[i] = new Rectangle(25,25, Color.LIGHTGREY);
				gridpaneColor.getColumnConstraints().add(new ColumnConstraints(26));

				//gridpaneColor.add(recColorArray[i], i, 0);
				gridpaneColor.add((Node) recColorArray1.get(i), i, 0);
				
				//recColorArray[i].setOnMousePressed(new EventHandler<MouseEvent>() {
				((Node) recColorArray1.get(i)).setOnMousePressed(new EventHandler<MouseEvent>() {
					
					public void handle(MouseEvent me) {
						//selectedColor = recColorArray[testI].getFill();
						selectedColor = ((Scene) recColorArray1.get(testI)).getFill();
						System.out.println("Col " + (testI) );
					}
				});
				// preset colors
				if (i == 0) {
					//recColorArray[testI].setFill(c0);
					((Scene) recColorArray1.get(testI)).setFill(c0);
				} else if (i == 1) {
					//recColorArray[testI].setFill(c1);
					((Scene) recColorArray1.get(testI)).setFill(c1);
				} else if (i == 2) {
					//recColorArray[testI].setFill(c2);
					((Scene) recColorArray1.get(testI)).setFill(c2);
				} else if (i == 3) {
					//recColorArray[testI].setFill(c3);
					((Scene) recColorArray1.get(testI)).setFill(c3);
				} else if (i == 4) {
					//recColorArray[testI].setFill(c4);
					((Scene) recColorArray1.get(testI)).setFill(c4);
				} else if (i == 5) { 
					//recColorArray[testI].setFill(c5);
					((Scene) recColorArray1.get(testI)).setFill(c5);
				} else if (i == 6) { 
					//recColorArray[testI].setFill(c6);
					((Scene) recColorArray1.get(testI)).setFill(c6);
				} else if (i == 7) { 
					//recColorArray[testI].setFill(c7);
					((Scene) recColorArray1.get(testI)).setFill(c7);
				} else if (i == 8) { 
					//recColorArray[testI].setFill(c8);
					((Scene) recColorArray1.get(testI)).setFill(c8);
				} else if (i == 9) { 
					//recColorArray[testI].setFill(c9);
					((Scene) recColorArray1.get(testI)).setFill(c9);
				} else if (i == 10) { 
					//recColorArray[testI].setFill(c10);
					((Scene) recColorArray1.get(testI)).setFill(c10);
				} else if (i == 11) { 
					//recColorArray[testI].setFill(c11);
					((Scene) recColorArray1.get(testI)).setFill(c11);
				} else if (i == 12) { 
					//recColorArray[testI].setFill(c12);
					((Scene) recColorArray1.get(testI)).setFill(c12);
				} else if (i == 13) { 
					//recColorArray[testI].setFill(c13);
					((Scene) recColorArray1.get(testI)).setFill(c13);
				} else if (i == 14) { 
					//recColorArray[testI].setFill(c14);
					((Scene) recColorArray1.get(testI)).setFill(c14);
				} else if (i == 15) { 
					//recColorArray[testI].setFill(c15);
					((Scene) recColorArray1.get(testI)).setFill(c15);
				}
			}
			else{
				//recColorArray[i] = new Rectangle(25,25, Color.LIGHTGREY);
				recColorArray1.add(new Rectangle(25,25, Color.LIGHTGRAY));
				gridpaneColor.getColumnConstraints().add(new ColumnConstraints(26));

				//gridpaneColor.add(recColorArray[i], i, 0);
				gridpaneColor.add((Node) recColorArray1.get(i), i, 0);
				//recColorArray[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
				((Node) recColorArray1.get(i)).setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						if (me.getButton() == MouseButton.SECONDARY) {
							//recColorArray[testI].setFill(colorPicker.getValue());
							((Scene) recColorArray1.get(testI)).setFill(colorPicker.getValue());
							System.out.println("Col " + (testI) + "Worked!!!");
						} else {
							//System.out.println(recColorArray[testI].getFill());
							System.out.println(((Scene) recColorArray1.get(testI)).getFill());
						}
					}
				});

			}


		}


		root.getChildren().addAll(gridpaneColor, colorPicker);
		primaryStage.setScene(scene);
		primaryStage.show();
	}}
