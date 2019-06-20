package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.*;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.IOException;
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

    /**
     * The constructor of the MatchViewController class.
     *
     * @param boardStatus boardStatus from the server.
     */

    public MatchViewController(BoardStatus boardStatus, boolean wpc) {
        this.boardStatus = boardStatus;
        this.wpc = wpc;
    }

    @FXML
    @Override
    public void guiLaunchTimer() {
        try (Scanner input = new Scanner(MatchViewController.class.getResourceAsStream("/file/config.json"))){
            //Read the content of the file
            StringBuilder jsonIn = new StringBuilder();
            while(input.hasNextLine()) {
                jsonIn.append(input.nextLine());
            }
            JSONParser parser = new JSONParser();
            JSONObject rootFile = (JSONObject) parser.parse(jsonIn.toString());
            JSONArray jsonArray = (JSONArray) rootFile.get("timer");
            JSONObject cell = (JSONObject) jsonArray.get(0);
            final int seconds = Integer.parseInt((String) cell.get("seconds"));
            Platform.runLater(()->{
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds = seconds;
                timerLabel.setText(timeSeconds.toString());
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                event -> {
                                    timeSeconds--;
                                    timerLabel.setText(
                                            timeSeconds.toString());
                                    if (timeSeconds <= 0) {
                                        notify("PASS");
                                        messageLabel.setText("");
                                        errorLabel.setText("");
                                        endTurnButton.setVisible(false);
                                        timeline.stop();
                                    }
                                }));
                timeline.playFromStart();
            });
        } catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    @Override
    protected void close(AnchorPane anchorPane) {

    }

    @FXML
    private void setImageDisable(Boolean bool) {
        imageOne.setDisable(bool);
        imageTwo.setDisable(bool);
        imageThree.setDisable(bool);
    }






}
