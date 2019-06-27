package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MatchViewController extends GUIController{

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView background;
    @FXML
    private ImageView map;
    @FXML
    private GridPane weaponR;
    @FXML
    private GridPane weaponB;
    @FXML
    private GridPane weaponY;
    @FXML
    private AnchorPane selfPlayer;
    @FXML
    private AnchorPane player1;
    @FXML
    private AnchorPane player2;
    @FXML
    private AnchorPane player3;
    @FXML
    private AnchorPane player4;
    @FXML
    private Button nextButton;
    @FXML
    private TextArea textMessege;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextArea selfScore;
    @FXML
    private TextArea player1Score;
    @FXML
    private TextArea player2Score;
    @FXML
    private TextArea player3Score;
    @FXML
    private TextArea player4Score;
    @FXML
    private HBox ammotile;
    @FXML
    private GridPane skull;
    @FXML
    private GridPane selfBlood;
    @FXML
    private GridPane action;
    @FXML
    private Button endRoundButton;
    @FXML
    private Button endTurnButton;
    @FXML
    private Button closeButton;

    private boolean next;

    @FXML
    public void initialize() {
        if (next) {
            Platform.runLater(() -> newView(this));
        }
        addDraggableNode(root);
        closeButton.setOnAction(event -> AlertBox.displayCloseRequest(this, root));
        endTurnButton.setOnAction(event -> {
            notify("PASS");
            textMessege.setText("");
        });
        endRoundButton.setOnAction(event -> {
            notify("PASS");
            textMessege.setText("");
        });

    }

    @FXML
    private void newView(MatchViewController matchViewController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/gameMap.fxml"));
            Stage window = new Stage();
            Parent newRoot = loader.load();
            Scene initialScene = new Scene(newRoot);
            window.setScene(initialScene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setResizable(false);
            window.showAndWait();
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }




    public void showPlayer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/playerBoard.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();


            /*
            selfPlayer.getChildren().add(anchorPane):
             */

        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }


    }


    @Override
    public void guiValue() {
        Platform.runLater(() -> ChoiceMapController.displaychioceMap(this));
    }

    public void setMap(){

    }










}
