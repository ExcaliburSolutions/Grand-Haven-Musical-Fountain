package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JavaFXDnDApplication extends Application
{
	private final File fcwInfo = new File("/Users/vankuikn/Grand-Haven-Musical-Fountain/Choreography_Dev/src/choreography/model/fcw/FCW_DEF.txt");
	
	private static final ListView<Addresses> playersListView = new ListView<Addresses>();

	private static final ObservableList<Addresses> playersList = FXCollections.observableArrayList();

	private static final ListView<Addresses> teamListView = new ListView<Addresses>();

	private static final GridPane rootPane = new GridPane();

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Drag over channels desired");

		initializeComponents();

		initializeListeners();

		buildGUI();

		populateData();

		primaryStage.setScene(new Scene(rootPane, 400, 325));
		primaryStage.show();
	}

	private void initializeListeners()
	{
		playersListView.setOnDragDetected(new EventHandler<MouseEvent>()
				{
			@Override
			public void handle(MouseEvent event)
			{
				System.out.println("setOnDragDetected");

				Dragboard dragBoard = playersListView.startDragAndDrop(TransferMode.MOVE);

				ClipboardContent content = new ClipboardContent();

				content.putString(playersListView.getSelectionModel().getSelectedItem().getName());

				dragBoard.setContent(content);
			}
				});

		playersListView.setOnDragDone(new EventHandler<DragEvent>()
				{
			@Override
			public void handle(DragEvent dragEvent)
			{
				System.out.println("setOnDragDone");

				// This is not the ideal place to remove the player because the drag might not have been exited on the target.
				// String player = dragEvent.getDragboard().getString();
				// playersList.remove(new Player(player));
			}
				});

		teamListView.setOnDragEntered(new EventHandler<DragEvent>()
				{
			@Override
			public void handle(DragEvent dragEvent)
			{
				System.out.println("setOnDragEntered");

				teamListView.setBlendMode(BlendMode.DIFFERENCE);
			}
				});

		teamListView.setOnDragExited(new EventHandler<DragEvent>()
				{
			@Override
			public void handle(DragEvent dragEvent)
			{
				System.out.println("setOnDragExited");

				teamListView.setBlendMode(null);
			}
				});

		teamListView.setOnDragOver(new EventHandler<DragEvent>()
				{
			@Override
			public void handle(DragEvent dragEvent)
			{
				System.out.println("setOnDragOver");

				dragEvent.acceptTransferModes(TransferMode.MOVE);
			}
				});

		teamListView.setOnDragDropped(new EventHandler<DragEvent>()
				{
			@Override
			public void handle(DragEvent dragEvent)
			{
				System.out.println("setOnDragDropped");

				String player = dragEvent.getDragboard().getString();

				teamListView.getItems().addAll(new Addresses(player));

				playersList.remove(new Addresses(player));

				dragEvent.setDropCompleted(true);
			}
				});
	}

	private void buildGUI()
	{
		rootPane.setPadding(new Insets(10));
		rootPane.setPrefHeight(30);
		rootPane.setPrefWidth(100);
		rootPane.setVgap(10);
		rootPane.setHgap(20);

		Label playersLabel = new Label("Players");
		Label teamLabel = new Label("Team");

		rootPane.add(playersLabel, 0, 0);
		rootPane.add(playersListView, 0, 1);
		rootPane.add(teamLabel, 1, 0);
		rootPane.add(teamListView, 1, 1);
	}

	private void populateData()
	{
		ArrayList<String> info = new ArrayList<String>();
		info = readFile(fcwInfo);
		
		for (int i=0; i < 300; i++){
//			playersList.add(i,info.get(i));
		}
//		playersList.add(readFile(fcwInfo));

		playersListView.setItems(playersList);
	}

	private void initializeComponents()
	{
		initializeListView(playersListView);

		initializeListView(teamListView);
	}

	private void initializeListView(ListView<Addresses> listView)
	{
		listView.setPrefSize(250, 290);
		listView.setEditable(false);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setCellFactory(new StringListCellFactory());
	}

	class StringListCellFactory implements Callback<ListView<Addresses>, ListCell<Addresses>>
	{
		@Override
		public ListCell<Addresses> call(ListView<Addresses> playerListView)
		{
			return new StringListCell();
		}

		class StringListCell extends ListCell<Addresses>
		{
			@Override
			protected void updateItem(Addresses player, boolean b)
			{
				super.updateItem(player, b);

				if (player != null)
				{
					setText(player.getName());
				}
			}
		}
	}
	
	private ArrayList<String> readFile(File file){
		ArrayList<String> output = new ArrayList<String>();

		StringBuilder stringBuffer = new StringBuilder();
		Scanner scanner = null;

		try {

			scanner = new Scanner(new FileReader(file));
			scanner.findWithinHorizon("|LightAddresses|", 1);
			boolean n;
			while (n = scanner.hasNextLine()) {
				String line = scanner.nextLine();
				stringBuffer.append(line);
				if(line.equals("|EndLightAddresses|")) {
					return output;
				} else {
					String[] tokens= line.split(", ");
					output.add(tokens[0].trim());
				}
//				stringBuffer.append(System.getProperty("line.separator"));
//				output.add(stringBuffer.toString());
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(JavaFXDnDApplication.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(JavaFXDnDApplication.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			scanner.close();
		}
		System.out.println(stringBuffer.toString());
		return output;
	}
}
