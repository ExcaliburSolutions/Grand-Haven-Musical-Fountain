package customChannel;

import choreography.io.FCWLib;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

public class CustomChannel extends Application{
	private static final ListView<String> moduleListView = new ListView<String>();
	private static final ObservableList<String> moduleList = FXCollections.observableArrayList();
	private static final ListView<String> selectedListView = new ListView<String>();
	private static final GridPane rootPane = new GridPane();

	public static void main(String[] args) {
		launch(args);
	}

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

	private void initalizeListeners() {
		moduleListView.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				
				Dragboard dragBoard = moduleListView.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(moduleListView.getSelectionModel().getSelectedItem()); // may be an issue here the original had .getName from the addresses class
				dragBoard.setContent(content);
			}
		});
		
		moduleListView.setOnDragDone(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent){
				
			}
		});
		
		selectedListView.setOnDragEntered(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent) {
				selectedListView.setBlendMode(BlendMode.DIFFERENCE);
			}
		});
		
		selectedListView.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent dragEvent) {
				selectedListView.setBlendMode(null);
			}
		});
		
		selectedListView.setOnDragOver(new EventHandler<DragEvent>(){
			public void handle(DragEvent dragEvent){
				dragEvent.acceptTransferModes(TransferMode.MOVE);
			}
		});
		
		selectedListView.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent dragEvent) {
				String module = dragEvent.getDragboard().getString();
				selectedListView.getItems().addAll(module);
				moduleList.remove(module);
				dragEvent.setDropCompleted(true);
			}
		});
	}
	
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
	}
	
	private void populateData() {
		moduleList.addAll(FCWLib.getInstance().getLightTable());
		moduleListView.setItems(moduleList);
	}

	private void initializeComponenents() {
		initializeListView(moduleListView);
		initializeListView(selectedListView);
	}

	private void initializeListView(ListView<String> listView) {
		listView.setPrefSize(250, 290);
		listView.setEditable(false);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setCellFactory((Callback<ListView<String>, ListCell<String>>) new StringListCellFactory());
	}
	
	class StringListCellFactory implements Callback<ListView<String>, ListCell<String>>{
		@Override
		public ListCell<String> call(ListView<String> playerListView){
			return new StringListCell();
		}

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