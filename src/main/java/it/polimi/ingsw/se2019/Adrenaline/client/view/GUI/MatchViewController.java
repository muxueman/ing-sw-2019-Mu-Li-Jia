package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
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
import java.util.Map;
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
    private GridPane relivePosition;
    @FXML
    private GridPane weaponR;
    @FXML
    private GridPane weaponB;
    @FXML
    private GridPane weaponY;
    @FXML
    private AnchorPane selfPlayer;
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
    @FXML
    private GridPane currentPowerupCard;
    @FXML
    private GridPane currentWeaponCard;
    @FXML
    private GridPane waitForReload;


    private boolean next;
    private boolean position = false;
    private boolean powerup = false;
    private boolean cell = false;

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
    protected static final String Ammotile_Path = "/ammo/";
    protected static final String PowerupCard_Path = "/powerups/";



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


    @FXML
    public void setPlayerInfo(BoardStatus boardStatus){
        List<PlayerStatus> playerList = new ArrayList<>();
        playerList = boardStatus.getAllPlayers();
        for(int i = 0; i < playerList.size(); i++){
            switch(i){
                case 0:
                    selfPlayerName.setText(playerList.get(0).getUsername());break;
                case 1:
                    player1Name.setText(playerList.get(1).getUsername()); break;
                case 2:
                    player1Name.setText(playerList.get(2).getUsername()); break;
                case 3:
                    player1Name.setText(playerList.get(3).getUsername()); break;
                case 4:
                    player1Name.setText(playerList.get(4).getUsername()); break;
            }
        }
    }




    protected void setPlayerBoard(BoardStatus boardStatus ,AnchorPane anchorPane){
        PlayerBoardController playerBoardController = new PlayerBoardController();

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
    private void choosePlayerPosition(int i,GridPane playerPosition){
        if(!position){

        }
    }

    @FXML
    public void setWeaponRCard(BoardStatus boardStatus){
        ((ImageView)weaponR.getChildren().get(0)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(0).getCardName() + ".png"));
        ((ImageView)weaponR.getChildren().get(1)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(1).getCardName() + ".png"));
        ((ImageView)weaponR.getChildren().get(2)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(2).getCardName() + ".png"));

    }
    @FXML
    public void setWeaponBCard(BoardStatus boardStatus){
        ((ImageView)weaponB.getChildren().get(0)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(0).getCardName() + ".png"));
        ((ImageView)weaponB.getChildren().get(1)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(1).getCardName() + ".png"));
        ((ImageView)weaponB.getChildren().get(2)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(2).getCardName() + ".png"));

    }
    @FXML
    public void setWeaponYCard(BoardStatus boardStatus){
        ((ImageView)weaponY.getChildren().get(0)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(0).getCardName() + ".png"));
        ((ImageView)weaponY.getChildren().get(1)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(1).getCardName() + ".png"));
        ((ImageView)weaponY.getChildren().get(2)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(2).getCardName() + ".png"));

    }

    @FXML
    public void setAmmoCardInMap(BoardStatus boardStatus,GridPane gridPane){
        if(boardStatus != null){
            Map<Integer,Cell> allCell = boardStatus.getAllCells();
            for(int i = 0;i < 12; i++) {
                ((ImageView) gridPane.getChildren().get(i)).setImage(new Image(Ammotile_Path+boardStatus.getAmmotilesInCell().get(i).getNumAmmotileCard()+".png"));
                notify(String.valueOf(boardStatus.getAmmotilesInCell().get(i).getNumAmmotileCard()));
            }
        }

    }

    @FXML
    public void setPlayer(PlayerStatus playerStatus){

    }

    //setPlayer should use this function
    @FXML
    public void setPlayerOwnCard(PlayerStatus playerStatus,GridPane gridPane){
        ArrayList<PowerupCard> powerupsOwn = playerStatus.getPowerupsOwned();
        int size = powerupsOwn.size();
        for (int i = 0; i < 3; i++){
            if(i < size){
                ((ImageView) gridPane.getChildren().get(i)).setImage(new Image(PowerupCard_Path+playerStatus.getPowerupsOwned().get(i).getCardName()+".png"));
                int chooseI = i;
                (gridPane.getChildren().get(i)).setOnMouseClicked(
                        event -> Platform.runLater(
                                () ->{// set the choose one powerupcard unvisible and put the card show in the waitforreload GridPane
                                    textMessege.setText("");
                                    errorArea.setText("");
                                    notify(powerupsOwn.get(chooseI).getCardName());
                                    (gridPane.getChildren().get(chooseI)).setVisible(false);
                                    disable();
                                }
                        )
                );
            }
        }
    }

//    @FXML
//    public void chooseCard(int i,GridPane gridPane){
//        if(!powerup){
//            notify("");//notify messege to clientController which card dont wanted
//        }
//        notify(Integer.toString(i));
//        setGridClickable(currentPowerupCard);
//    }

    //can use to set map clickable
    @FXML
    private void setGridClickable(GridPane gridPane) {
        int count = 0;
        for(int rows=0; rows<4; rows++) {
            for(int columns=0; columns<5; columns++) {
                int row = rows;
                int column = columns;
                gridPane.getChildren().get(count).setOnMouseClicked(
                        event -> Platform.runLater(
                                () -> {
                                    textMessege.setText("");
                                    errorArea.setText("");
                                    if (!cell) {
                                        notify("");
                                    }
                                    notify(Integer.toString(row + 1));
                                    notify(Integer.toString(column + 1));
                                    disable();
                                }
                        )
                );
                count++;
            }
        }
    }

    private void disable() {
        powerup = false;
        cell = false;
    }

    @FXML
    public void setMap(BoardStatus boardStatus){
        if(boardStatus != null){
            for(int i = 0; i < boardStatus.getAllCells().size(); i++){

            }

        }
    }


    @Override
    public void update(ModelUpdate message) {
        boardStatus = message.getBoardStatus();
        if (boardStatus!= null) {
            Platform.runLater( () -> {
                setPlayerInfo(boardStatus);
                setWeaponRCard(boardStatus);
                setWeaponYCard(boardStatus);
                setWeaponBCard(boardStatus);
                setAmmoCardInMap(boardStatus,pickAmmoCard);

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
