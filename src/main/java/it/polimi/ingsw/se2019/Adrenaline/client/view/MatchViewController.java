package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private Pane root;
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
    private Button endRound;
    @FXML
    private Button endTurn;


    private boolean wpc;

    public MatchViewController(BoardStatus boardStatus,boolean wpc){
        this.boardStatus = boardStatus;
        this.wpc = wpc;

    }




    @Override
    protected void close(AnchorPane anchorPane) {

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




}
