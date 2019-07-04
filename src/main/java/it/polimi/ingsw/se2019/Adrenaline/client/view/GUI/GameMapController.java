package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * The GameMapController class represents the controller
 * of the matchView.fxml file.
 *
 * @author li xuejing
 */


public class GameMapController extends GUIController{

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
    private boolean visible = false;

    private Timeline timeline;
    private Integer timeSeconds;
    private PlayerBoardController playerBoardController;
    private GUIController guiController;

    /**
     * The constructor of the GameMapController class.
     *
     * @param boardStatus boardStatus from the server.
     * @param client clientController
     *
     */

    public GameMapController(ClientController client, BoardStatus boardStatus, boolean next){
        this.boardStatus = boardStatus;
        this.next = next;
        this.client = client;
    }

    public static final String chioceMap = "/fxml/choiceMap.fxml";
    protected static final String WeaponR_Path = "/weapons_red/";
    protected static final String WeaponB_Path = "/weapons_blue/";
    protected static final String WeaponY_Path = "/weapons_yellow/";
    protected static final String Ammotile_Path = "/ammo/";
    protected static final String PowerupCard_Path = "/powerups/";



    /**
     * The initialize method is used to initialize the game map
     */


    @FXML
    public void initialize() {
        if (next) {
            Platform.runLater(() -> choiceMapView(this));
        }
        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            Window.displayCloseRequest(this,root);
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

    /**
     * setPlayerInfo is to set everyPlayer who play in game some information at scene
     * @param boardStatus
     */

    @FXML
    public void setPlayerInfo(BoardStatus boardStatus){
        List<PlayerStatus> playerList = new ArrayList<>();
        playerList = boardStatus.getAllPlayers();
        int size = playerList.size();
        if(size < 4){
            player3Name.setVisible(false);
            player3Score.setVisible(false);
            player3.setVisible(false);
            if(size < 5){
                player4Name.setVisible(false);
                player4Score.setVisible(false);
                player4.setVisible(false);
            }
        }

        for(int i = 0; i < size; i++){
            switch(i){
                case 0:
                    selfPlayerName.setText(playerList.get(0).getUsername());
                    Logger.getGlobal().info(playerList.get(0).getPlayerColor().getColor());
                    setPlayerBoard(selfPlayer,playerList.get(0).getPlayerColor().getColor());
                    break;
                case 1:
                    player1Name.setText(playerList.get(1).getUsername());
                    Logger.getGlobal().info(playerList.get(1).getPlayerColor().getColor());
                    setPlayerBoard(player1,playerList.get(1).getPlayerColor().getColor());
                    break;
                case 2:
                    player2Name.setText(playerList.get(2).getUsername());
                    setPlayerBoard(player2,playerList.get(2).getPlayerColor().getColor());
                    break;
                case 3:
                    player3Name.setText(playerList.get(3).getUsername());
                    setPlayerBoard(player3,playerList.get(3).getPlayerColor().getColor());
                    break;
                case 4:
                    player4Name.setText(playerList.get(4).getUsername());
                    setPlayerBoard(player4,playerList.get(4).getPlayerColor().getColor());
                    break;
            }
        }
    }

    /**
     * the methods set PlayerBoard is use to collect the playerboardcontroller
     *
     * @param anchorPaneNeed
     * @param color
     */

    @FXML//need to collect the playerBoardController
    public void setPlayerBoard(AnchorPane anchorPaneNeed,String color){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playerBoard.fxml"));
            AnchorPane anchorPane = loader.load();
            anchorPaneNeed.getChildren().add(anchorPane);
            this.playerBoardController = new PlayerBoardController(client,boardStatus,next);
            playerBoardController.setPlayerImage(color);
            Logger.getGlobal().info(color);

        }catch (IOException e){
            Logger.getGlobal().warning(e.getCause().toString());
        }

    }

    /**
     * The choiceMapView method is used to show a new window
     * with the list of window pattern card.
     *
     * @param gameMapController actual controller.
     */
    @FXML
    private void choiceMapView(GameMapController gameMapController) {
        try {
            ChoiceMapController choiceMapController = new ChoiceMapController(boardStatus, gameMapController);

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



//    @FXML
//    public void setInit() {
//
//    }

    /**
     * set the weaponcard red blue and yellow
     *
     * @param boardStatus
     * @param gridPane
     */

    //initial every weapon gridpane three weaponCard
    @FXML
    public void setWeaponRCard(BoardStatus boardStatus,GridPane gridPane){
        ((ImageView)gridPane.getChildren().get(0)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(0).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(1)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(1).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(2)).setImage(new Image(WeaponR_Path + boardStatus.getWeaponsInCell().get(5).get(2).getImage() + ".png"));

    }
    @FXML
    public void setWeaponBCard(BoardStatus boardStatus,GridPane gridPane){
        ((ImageView)gridPane.getChildren().get(0)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(0).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(1)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(1).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(2)).setImage(new Image(WeaponB_Path + boardStatus.getWeaponsInCell().get(3).get(2).getImage() + ".png"));

    }
    @FXML
    public void setWeaponYCard(BoardStatus boardStatus,GridPane gridPane){
        ((ImageView)gridPane.getChildren().get(0)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(0).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(1)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(1).getImage() + ".png"));
        ((ImageView)gridPane.getChildren().get(2)).setImage(new Image(WeaponY_Path + boardStatus.getWeaponsInCell().get(12).get(2).getImage() + ".png"));

    }

    /**
     * initial the game map give every cell which need ammotilecard
     *
     * @param boardStatus
     * @param gridPane
     */

    @FXML
    public void setAmmoCardInMap(BoardStatus boardStatus,GridPane gridPane){
        int count = 1;
        for (int cellID : boardStatus.getAmmotilesInCell().keySet()) {
            while (count < cellID){
                ((ImageView) gridPane.getChildren().get(count-1)).setImage(new Image(Ammotile_Path +
                        boardStatus.getAmmotilesInCell().get(cellID).getImage() + ".png"));
                gridPane.getChildren().get(count-1).setVisible(false);
                count++;
            }
            if(count == cellID){
                ((ImageView) gridPane.getChildren().get(count-1)).setImage(new Image(Ammotile_Path +
                        boardStatus.getAmmotilesInCell().get(cellID).getImage() + ".png"));
                count++;
            }

            }

    }

    /**
     *
     * to set the player collect to the player image to show in gui
     *
     * @param imageView
     * @param color
     */
    @FXML//用颜色代表每一个player 与 playerBoard 连接
    private void setPlayerColor(ImageView imageView,String color){
        switch(color){
            case "YELLOW":
                imageView.setImage(new Image("/player/小黄（d-struct-or）.jpg"));
            case "PINK":
                imageView.setImage(new Image("/player/小紫（violet）.jpg"));
            case "GREEN":
                imageView.setImage(new Image("/player/小绿（sprog）.jpg"));
            case "BLUE":
                imageView.setImage(new Image("/player/小蓝（banshee）.jpg"));
            case "white":
                imageView.setImage(new Image("/player/小黑（dozer）.jpg"));
        }
    }



    //about initial the game give two powerupcard to choose one discard and relive in that card's color cell
    @FXML
    public void setPlayerRelivePosition(BoardStatus boardStatus,GridPane gridPane){
        for(int i = 0;i < 3;i++ ){
        }
    }

    /**
     *
     * to give two powerupcard to player let them choose which card want drop
     * and make the card unvisible
     *
     * @param boardStatus
     * @param gridPane
     */

    @FXML
    public void setPlayerOwnPowerupCard(BoardStatus boardStatus,GridPane gridPane){

        ArrayList<PowerupCard> powerupsOwn = boardStatus.getPlayer(client.getPlayerID()).getPowerupsOwned();
        int size = powerupsOwn.size();
        for (int i = 0; i < size; i++){
        ((ImageView) gridPane.getChildren().get(i)).setImage(new Image(PowerupCard_Path+ powerupsOwn.get(i).getImage() + ".png"));
        int chooseI = i;
        (gridPane.getChildren().get(i)).setOnMouseClicked(
            event -> Platform.runLater(
                    () ->{// set the choose one powerupcard unvisible and put the card show in the waitforreload GridPane
                        textMessege.setText("");
                        errorArea.setText("");
                        notify(powerupsOwn.get(chooseI).getCardName());
                        (gridPane.getChildren().get(chooseI)).setVisible(visible);
                        disable();
                    }
                )
            );
        }


    }

    private void disable() {
        visible = true;

    }


    @FXML
    public void setMap(BoardStatus boardStatus){
        if(boardStatus != null){
            for(int i = 0; i < boardStatus.getAllCells().size(); i++){

            }

        }
    }

    /**
     *
     * use for to update the messege
     *
     * @param message part of the model to be updated.
     */

    @Override
    public void update(ModelUpdate message) {
        boardStatus = message.getBoardStatus();
        if (boardStatus!= null) {
            Platform.runLater( () -> {
                setPlayerInfo(boardStatus);
                setWeaponRCard(boardStatus,weaponR);
                setWeaponYCard(boardStatus,weaponY);
                setWeaponBCard(boardStatus,weaponB);
                setAmmoCardInMap(boardStatus,pickAmmoCard);
                setPlayerOwnPowerupCard(boardStatus,currentPowerupCard);

            });
        }

    }


    /**
     *
     * to showMessage to the client controller
     *
     * @param message message to display.
     */

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

    /**
     *
     *show the errorArea the error message
     * @param error error to display.
     */

    @Override
    public void reportError(String error) {
        Platform.runLater(() -> {
            errorArea.setText(error);
        });
    }






}
