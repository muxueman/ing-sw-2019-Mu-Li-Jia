package it.polimi.ingsw.se2019.Adrenaline.client.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.awt.*;
import java.util.Scanner;
import java.util.logging.Logger;


public class MatchViewController extends GUIController{

    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView cancelImage;
    @FXML
    private ImageView switchImage;
    @FXML
    private ImageView imageOne;
    @FXML
    private ImageView imageTwo;
    @FXML
    private ImageView imageThree;
    @FXML
    private ImageView imagePrivateOC;
    @FXML
    private ImageView chosenDie;
    @FXML
    private javafx.scene.control.Button settingsButton;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Button endTurnButton;
    @FXML
    private AnchorPane root;
    @FXML
    private GridPane playerOneGrid;
    @FXML
    private javafx.scene.control.Label playerOneName;
    @FXML
    private javafx.scene.control.Label playerOneWPC;
    @FXML
    private javafx.scene.control.Label playerOneFT;
    @FXML
    private javafx.scene.control.Label messageLabel;
    @FXML
    private GridPane playerTwoGrid;
    @FXML
    private javafx.scene.control.Label playerTwoName;
    @FXML
    private javafx.scene.control.Label playerTwoWPC;
    @FXML
    private javafx.scene.control.Label playerTwoFT;
    @FXML
    private GridPane playerThreeGrid;
    @FXML
    private javafx.scene.control.Label playerThreeName;
    @FXML
    private javafx.scene.control.Label playerThreeWPC;
    @FXML
    private javafx.scene.control.Label playerThreeFT;
    @FXML
    private GridPane playerActualGrid;
    @FXML
    private javafx.scene.control.Label playerActualName;
    @FXML
    private javafx.scene.control.Label playerActualWPC;
    @FXML
    private javafx.scene.control.Label playerActualFT;
    @FXML
    private VBox draftPoolSpace;
    @FXML
    private HBox roundTrackSpace;
    @FXML
    private HBox diceStorage;
    @FXML
    private javafx.scene.control.Label timerLabel;
    @FXML
    private javafx.scene.control.Label errorLabel;

    private boolean wpc;
    private boolean isToolCard = true;
    private boolean nextIsPrivateOC = true;
    private boolean toolCardUsed = false;
    private boolean toolCard = false;

    private Timeline timeline;
    private Integer timeSeconds;



}
