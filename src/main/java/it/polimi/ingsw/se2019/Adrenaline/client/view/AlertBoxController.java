package it.polimi.ingsw.se2019.Adrenaline.client.view;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class AlertBoxController extends GUIController {

    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane root;
    @FXML
    private Button closeButton;
    @FXML
    private Label labelOne;
    @FXML
    private Label firstP;
    @FXML
    private Label secondP;
    @FXML
    private Label thirdP;
    @FXML
    private Label fifthP;
    @FXML
    private Label firstScore;
    @FXML
    private Label secondScore;
    @FXML
    private Label thirdScore;
    @FXML
    private Label fourthScore;
    @FXML
    private Label fifthScore;
    @FXML
    private Button buttonOne;
    @FXML
    private Button buttonTwo;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button newGameButton;
    @FXML
    private ImageView mapA;
    @FXML
    private ImageView mapB;
    @FXML
    private ImageView mapC;
    @FXML
    private ImageView mapD;

    public void initialize() {
        addDraggableNode(root);
        closeButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });
    }

    public void setCloseButton(GUIController guiController, AnchorPane anchorPane) {
        closeButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            guiController.close(anchorPane);
            stage.close();
        });
    }

    public void setNewGameButton(AnchorPane anchorPane) {
        newGameButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
            Stage secondStage = (Stage) anchorPane.getScene().getWindow();
            secondStage.close();
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/initialView.fxml"));
                    Stage primaryStage = new Stage();
                    Parent newRoot = loader.load();
                    primaryStage.setTitle("Sagrada");
                    Scene initialScene = new Scene(newRoot);
                    primaryStage.setScene(initialScene);
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    GUIController newGuiController = loader.getController();
                    new ClientController(new GUIView(newGuiController));
                } catch (IOException e) {
                    Logger.getGlobal().warning(e.toString());
                }
            });
        });
    }

    public void setLabelOne(String s) {
        labelOne.setText(s);
    }

    public void setButtonOneText(String string) {
        buttonOne.setText(string);
    }

    public void setButtonTwoText(String string) {
        buttonOne.setText(string);
    }


    public void setButtonOne(EventHandler<ActionEvent> value) {
        buttonOne.setOnAction(value);
    }

    public void setButtonTwo(EventHandler<ActionEvent> value) {
        buttonOne.setOnAction(value);
    }

    public void setChoiceBox() {
        choiceBox.getItems().add("violet");
        choiceBox.getItems().add("sprog");
        choiceBox.getItems().add("banshee");
        choiceBox.getItems().add("d-struct-or");
        choiceBox.getItems().add("dozer");
        choiceBox.setValue("violet");
    }

    public String getChoiceBoxValue(){
        return choiceBox.getValue();
    }


    @Override
    protected void close(AnchorPane anchorPane) {

    }
}




