package application;

import java.util.Stack;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenWizard extends Application {
  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {

    stage.setScene(new Scene(new SurveyWizard(stage), 400, 250));
    stage.show();
  }
}

class Wizard extends StackPane {
  private static final int UNDEFINED = -1;
  private ObservableList<WizardPage> pages = FXCollections.observableArrayList();
  private Stack<Integer> history = new Stack<Integer>();
  private int curPageIdx = UNDEFINED;

  Wizard(WizardPage... nodes) {
    pages.addAll(nodes);
    navTo(0);
    setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
  }

  void nextPage() {
    if (hasNextPage()) {
      navTo(curPageIdx + 1);
    }
  }

  void priorPage() {
    if (hasPriorPage()) {
      navTo(history.pop(), false);
    }
  }

  boolean hasNextPage() {
    return (curPageIdx < pages.size() - 1);
  }

  boolean hasPriorPage() {
    return !history.isEmpty();
  }

  void navTo(int nextPageIdx, boolean pushHistory) {
    if (nextPageIdx < 0 || nextPageIdx >= pages.size())
      return;
    if (curPageIdx != UNDEFINED) {
      if (pushHistory) {
        history.push(curPageIdx);
      }
    }

    WizardPage nextPage = pages.get(nextPageIdx);
    curPageIdx = nextPageIdx;
    getChildren().clear();
    getChildren().add(nextPage);
    nextPage.manageButtons();
  }

  void navTo(int nextPageIdx) {
    navTo(nextPageIdx, true);
  }

  void navTo(String id) {
    Node page = lookup("#" + id);
    if (page != null) {
      int nextPageIdx = pages.indexOf(page);
      if (nextPageIdx != UNDEFINED) {
        navTo(nextPageIdx);
      }
    }
  }

  public void finish() {
  }

  public void cancel() {
  }
}

/** basic wizard page class */
abstract class WizardPage extends VBox {
  Button priorButton = new Button("_Previous");
  Button nextButton = new Button("N_ext");
  Button cancelButton = new Button("Cancel");
  Button finishButton = new Button("_Finish");

  WizardPage(String title) {
    getChildren().add(
        LabelBuilder.create().text(title)
            .style("-fx-font-weight: bold; -fx-padding: 0 0 5 0;").build());
    setId(title);
    setSpacing(5);
    setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

    Region spring = new Region();
    VBox.setVgrow(spring, Priority.ALWAYS);
    getChildren().addAll(getContent(), spring, getButtons());

    priorButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        priorPage();
      }
    });
    nextButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        nextPage();
      }
    });
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        getWizard().cancel();
      }
    });
    finishButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        getWizard().finish();
      }
    });
  }

  HBox getButtons() {
    Region spring = new Region();
    HBox.setHgrow(spring, Priority.ALWAYS);
    HBox buttonBar = new HBox(5);
    cancelButton.setCancelButton(true);
    finishButton.setDefaultButton(true);
    buttonBar.getChildren().addAll(spring, priorButton, nextButton,
        cancelButton, finishButton);
    return buttonBar;
  }

  abstract Parent getContent();

  boolean hasNextPage() {
    return getWizard().hasNextPage();
  }

  boolean hasPriorPage() {
    return getWizard().hasPriorPage();
  }

  void nextPage() {
    getWizard().nextPage();
  }

  void priorPage() {
    getWizard().priorPage();
  }

  void navTo(String id) {
    getWizard().navTo(id);
  }

  Wizard getWizard() {
    return (Wizard) getParent();
  }

  public void manageButtons() {
    if (!hasPriorPage()) {
      priorButton.setDisable(true);
    }

    if (!hasNextPage()) {
      nextButton.setDisable(true);
    }
  }
}

/** This class shows a satisfaction survey */
class SurveyWizard extends Wizard {
  Stage owner;

  public SurveyWizard(Stage owner) {
    super(new MusicPage(), new CtlPage());//, new ThanksPage());
    this.owner = owner;
  }

  public void finish() {
    System.out.println("Have .ctl file? "
        + WizardData.instance.hasCTL.get());
    if (WizardData.instance.hasCTL.get()) {
      System.out.println(".ctl file location: "
          + (WizardData.instance.ctlFile.get().isEmpty() ? "No Details"
              : "\n" + WizardData.instance.ctlFile.get()));
    }
    owner.close();
  }

  public void cancel() {
    System.out.println("Cancelled");
    owner.close();
  }
}

/** Simple placeholder class for the customer entered survey response. */
class WizardData {
  BooleanProperty hasCTL = new SimpleBooleanProperty();
  StringProperty ctlFile = new SimpleStringProperty();
  static WizardData instance = new WizardData();
}

/**
 * This class determines if the user has a .ctl file. If not, it enables the 
 * finish button. Also loads .wav file.
 */
class MusicPage extends WizardPage {
  private RadioButton yes;
  private RadioButton no;
  private ToggleGroup options = new ToggleGroup();
  private FileChooser chooser = new FileChooser();

  public MusicPage() {
    super("Choose .wav File");
    chooser.setTitle("Choose .wav file");
    nextButton.setDisable(true);
    finishButton.setDisable(true);
    yes.setToggleGroup(options);
    no.setToggleGroup(options);
    options.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> observableValue,
          Toggle oldToggle, Toggle newToggle) {
        nextButton.setDisable(false);
        finishButton.setDisable(false);
      }
    });
  }

  Parent getContent() {
    yes = new RadioButton("Yes");
    no = new RadioButton("No");
    WizardData.instance.hasCTL.bind(yes.selectedProperty());
    return VBoxBuilder.create().spacing(5)
        .children(new Label("Do you have a .ctl file?"), yes, no).build();
  }

  void nextPage() {
    // If they have complaints, go to the normal next page
    if (options.getSelectedToggle().equals(yes)) {
      super.nextPage();
    } else {
      // No complaints? Short-circuit the rest of the pages
      //navTo("Thanks");
    	finishButton.setDisable(false); 
    }
  }
}

/** This page gathers more information about the complaint */
class CtlPage extends WizardPage {
  public CtlPage() {
    super("Choose .ctl File");
  }

  Parent getContent() {
    TextArea textArea = TextAreaBuilder.create().wrapText(true).build();
    nextButton.setDisable(true);
    textArea.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue,
          String oldValue, String newValue) {
        nextButton.setDisable(newValue.isEmpty());
      }
    });
    WizardData.instance.ctlFile.bind(textArea.textProperty());
    return VBoxBuilder.create().spacing(5)
        .children(new Label("Please enter the location of the .ctl file."), textArea).build();
  }
}

///** This page thanks the user for taking the survey */
//class ThanksPage extends WizardPage {
//  public ThanksPage() {
//    super("Thanks");
//  }
//
//  Parent getContent() {
//    StackPane stack = StackPaneBuilder.create().children(new Label("Thanks!"))
//        .build();
//    VBox.setVgrow(stack, Priority.ALWAYS);
//    return stack;
//  }
//}

