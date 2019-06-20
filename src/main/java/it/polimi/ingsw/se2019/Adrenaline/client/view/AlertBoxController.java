package it.polimi.ingsw.se2019.Adrenaline.client.view;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;

import java.awt.*;
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

}




