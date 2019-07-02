package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
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
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MatchViewController extends GUIController{

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView map;
    @FXML
    private GridPane visibleCell;
    @FXML
    private GridPane playerPosition;
    @FXML
    private GridPane pickAmmoCard;
    @FXML
    private GridPane weaponR;
    @FXML
    private GridPane weaponB;
    @FXML
    private GridPane weaponY;
    @FXML
    private ImageView selfPlayer;
    @FXML
    private VBox vboxPlayer;
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
    private Label selfPlayerScore;
    @FXML
    private Label selfPlayerName;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    @FXML
    private Label player3Name;
    @FXML
    private Label player4Name;
    @FXML
    private Label player1Score;
    @FXML
    private Label player2Score;
    @FXML
    private Label player3Score;
    @FXML
    private Label player4Score;
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
    @FXML
    private Label timerLabel;
    @FXML
    private TextArea killshootnum;
    @FXML
    private TextArea errorArea;


    private boolean next;
    private boolean position = false;

    private Timeline timeline;
    private Integer timeSeconds;

    public MatchViewController(BoardStatus boardStatus,boolean next){
        this.boardStatus = boardStatus;
        this.next = next;
    }

    public static final String chioceMap = "/fxml/choiceMap.fxml";
    protected static final String WeaponR_Path = "/weapons_red/";
    protected static final String WeaponB_Path = "/weapons_blue/";
    protected static final String WeaponY_Path = "/weapons_yellow/";


    @FXML
    @Override
    public void guiLaunchTimer() {
        try (Scanner input = new Scanner(MatchViewController.class.getResourceAsStream("/json/config.json"))){
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
                                        textMessege.setText("");
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




    @FXML
    public void initialize() {
        if (next) {
            Platform.runLater(() -> choiceMapView(this));
        }
        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            AlertBox.displayCloseRequest(this,root);
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
        endTurnButton.setOnAction(event -> {
            notify("PASS");
            if(timeline!=null) timeline.stop();
            timerLabel.setText("00");
            endTurnButton.setVisible(false);
            textMessege.setText("");
        });
        endRoundButton.setOnAction(event -> {
            notify("PASS");
            if(timeline!=null) timeline.stop();
            timerLabel.setText("00");
            endRoundButton.setVisible(false);
            textMessege.setText("");
        });

    }
    //add selfPlayerBlood every blood have their own color represente the damage
    @FXML
    public void setSelfBlood(PlayerStatus player){
        for (int i = 0;i < player.getDamageColorOnTrack().size();i++ ){
            switch (player.getDamageColorOnTrack().get(i).getColor()){
                case "yellow" :


            }
        }
    }

    @FXML
    public void setPlayerInfo(){


    }

    private void setPowerupCards(BoardStatus boardStatus){

    }

    @FXML
    private void choiceMapView(MatchViewController matchViewController) {
        try {
            ChoiceMapController choiceMapController = new ChoiceMapController(boardStatus, matchViewController);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choiceMap.fxml"));
            loader.setController(choiceMapController);
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

    @FXML
    public void setInit() {

    }

    @FXML
    public void setGameMapTable(BoardStatus boardStatus){
        List<PlayerStatus> playerList = new ArrayList<>(boardStatus.getAllPlayers());
        playerList.remove(boardStatus.getCurrentPlayer());
        for (int i = 0; i < playerList.size(); i++){
            switch (i){
                case 0:
                    selfPlayer.setImage(new Image("/playerBoard/playerBoard_yellow"));
            }

        }
    }

    @FXML
    private void choosePlayerPosition(int i,GridPane playerPosition){
        if(!position){

        }
    }

    @FXML
    public void setWeaponRCard(PlayerStatus weaponRCard){
        for (int i = 0; i < weaponR.getChildren().size(); i++){
            ((ImageView)weaponR.getChildren().get(i)).setImage(new Image(WeaponR_Path + weaponRCard.getWeaponsOwned() + ".png"));
        }
    }
    @FXML
    public void setWeaponBCard(PlayerStatus weaponBCard,GridPane weaponB){
        for (int i = 0; i < weaponB.getChildren().size(); i++){
            ((ImageView)weaponB.getChildren().get(i)).setImage(new Image(WeaponB_Path + weaponBCard.getWeaponsOwned() + ".png"));
        }
    }
    @FXML
    public void setWeaponYCard(PlayerStatus weaponYCard){
        for (int i = 0; i < weaponY.getChildren().size(); i++){
            ((ImageView)weaponY.getChildren().get(i)).setImage(new Image(WeaponY_Path + weaponYCard.getWeaponsOwned() + ".png"));
        }
    }

    @FXML
    public void setAmmoCardInMap(){

    }







    public void showPlayer(int i ,VBox vboxPlayer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playerBoard.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            player1.getChildren().add(anchorPane);
            player2.getChildren().add(anchorPane);
            player3.getChildren().add(anchorPane);
            player4.getChildren().add(anchorPane);


        } catch (IOException e) {
            Logger.getGlobal().warning(e.toString());
        }


    }


    @Override
    public void update(ModelUpdate message) {
        if (boardStatus!= null) {
            Platform.runLater( () -> {
//                setWeaponBCard(boardStatus.getWeaponsInCell().get(3),weaponB);

            });
        }
    }




    @Override
    public void showMessage(String message) {
        Platform.runLater(() -> {
            textMessege.setText(message.split("\n")[0]);
        });

        switch(message) {
            case "1" :
                map.setImage(new Image("/map/map_A.png")); break;
            case "2" :
                map.setImage(new Image("/map/map_B.png")); break;
            case "3" :
                map.setImage(new Image("/map/map_C.png")); break;
            case "4" :
                map.setImage(new Image("/map/map_D.png")); break;
            case "5" :
                killshootnum.setText("Killshootnum:5"); break;
            case "6" :
                killshootnum.setText("Killshootnum:6"); break;
            case "7" :
                killshootnum.setText("Killshootnum:7"); break;
            case "8" :
                killshootnum.setText("Killshootnum:8"); break;


        }


    }

    @Override
    public void reportError(String error) {
        Platform.runLater(() -> {
            errorArea.setText(error);
        });
    }



















}
