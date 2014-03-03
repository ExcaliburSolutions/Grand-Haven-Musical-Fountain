package customChannel;

import java.sql.Array;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import choreography.io.FCWLib;

/**
 * @author Nick Van Kuiken
 * Allows user to add the different individual channels to choreography timeline
 */
public class CustomChannel extends Application{
	// Used to show the user the available modules that can be selected.
	private static final ListView<String> moduleListView = new ListView<String>();
	
	// Holds the modules available for the moduleListView
	private static final ObservableList<String> moduleList = FXCollections.observableArrayList();
	
	// Used to show the user the selected modules the chose from dragging and dropping
	private static final ListView<String> selectedListView = new ListView<String>();
	
	// Holds the ListViews 
	private static final GridPane rootPane = new GridPane();
	
	// Finish button
	private Button finish = new Button("Finish");

	/**
	 * @param args
	 * Main method
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
     * @param primaryStage
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Drag to select channels desired");
		initializeComponenents();
		initalizeListeners();
		buildGUI();
		populateData();

		primaryStage.setScene(new Scene(rootPane, 400, 325));
		primaryStage.show();
	}

	/**
	 * Initializes the listeners for the ListViews for dragging and dropping
	 */
	private void initalizeListeners() {
		
		/** 
		 * Handles when drag had started in the moduleListView
		 * Creates a Dragboard. Creates a clipboard and adds the selected element
		 * to the clipboard. Adds the clipboard to the Dragboard.
		 */
		moduleListView.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				
				Dragboard dragBoard = moduleListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(moduleListView.getSelectionModel().getSelectedItem());
				dragBoard.setContent(content);
			}
		});
		
		/**
		 * Required handler, not used.
		 */
		moduleListView.setOnDragDone(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent){
				
			}
		});
		
		/**
		 * Handles when drag enters selectedListView.
		 * Blendmode is used to signify to teh user they have entered the 
		 * ListView and can now drop the selection. 
		 */
		selectedListView.setOnDragEntered(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent) {
				selectedListView.setBlendMode(BlendMode.DIFFERENCE);
			}
		});
		
		/**
		 * Handles when the drag leaves the ListView.
		 * Sets the Blendmode back to default.
		 */
		selectedListView.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent dragEvent) {
				selectedListView.setBlendMode(null);
			}
		});
		
		/**
		 * Handles when the drag is over the selectedListView.
		 */
		selectedListView.setOnDragOver(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent){
				dragEvent.acceptTransferModes(TransferMode.MOVE);
			}
		});
		
		/**
		 * Handles when the drag is dropped in the selectedListView.
		 * Creates a string called module to hold the clipboard.
		 * Adds the string to the ListView.
		 * Removes the selected content from the moduleList.
		 * Completes the dragEvent.
		 */
		selectedListView.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent dragEvent) {
				String module = dragEvent.getDragboard().getString();
				selectedListView.getItems().addAll(module);
				moduleList.remove(module);
				dragEvent.setDropCompleted(true);
			}
		});
		
		finish.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				// @todo need to add handler in timeline to add selectedModules and exit the dialog
				getSelected();
				try {
					stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Object getSelected() {
		Object[] selectedArray;
		selectedArray = selectedListView.getItems().toArray();
		return selectedArray;
	}
	
	/**
	 * Builds the GUI of the application. Sets all the 
	 * default values for the GUI.
	 */
	private void buildGUI() {
		rootPane.setPadding(new Insets(10));
		rootPane.setPrefHeight(30);
		rootPane.setPrefWidth(100);
		rootPane.setVgap(10);
		rootPane.setHgap(20);

		Label modules = new Label("Players");
		Label selectedModules = new Label("Team");

		rootPane.add(modules, 0, 0);
		rootPane.add(moduleListView, 0, 1);
		rootPane.add(selectedModules, 1, 0);
		rootPane.add(selectedListView, 1, 1);
		rootPane.add(finish, 1, 2);
	}
	
	/**
	 * Populates data from the FCW_DEF.txt file. 
	 * Calls pre-fetched data from the FCWLib class, then adds
	 * the data to the moduleListView.
	 */
	private void populateData() {
		moduleList.addAll(FCWLib.getInstance().getLightTable());
		moduleListView.setItems(moduleList);
	}

	/**
	 * Initializes the ListView components
	 */
	private void initializeComponenents() {
		initializeListView(moduleListView);
		initializeListView(selectedListView);
	}

	/**
	 * @param listView 
	 * Initializes the inidividual ListVIew components when send
	 * a ListView from the initializeComponents() method. 
	 */
	private void initializeListView(ListView<String> listView) {
		listView.setPrefSize(250, 290);
		listView.setEditable(false);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setCellFactory((Callback<ListView<String>, ListCell<String>>) new StringListCellFactory());
	}
	
	/**
	 * @author Nick Van Kuiken
	 * These classes help to make sure the data is updated correctly after dragging. 
	 */
	class StringListCellFactory implements Callback<ListView<String>, ListCell<String>>{
		@Override
		public ListCell<String> call(ListView<String> playerListView){
			return new StringListCell();
		}

		/**
		 * @author Nick Van Kuiken
		 *
		 */
		class StringListCell extends ListCell<String>{
			protected void updateItem(String module, boolean b){
				super.updateItem(module, b);

				if (module != null){
					setText(module);
				}
			}
		}
	}
}