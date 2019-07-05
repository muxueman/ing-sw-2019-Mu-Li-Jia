package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private Button endGameButton;
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
    private Label redAmmoN;
    @FXML
    private Label blueAmmoN;
    @FXML
    private Label yellowAmmoN;
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
    protected static final String Player_Path = "/player/";





    /**
     * The initialize method is used to initialize the game map
     */


    @FXML
    public void initialize() {
        if (next) {
            Platform.runLater(() -> choiceMapView(this));
        }
        addFrameNode(root);
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
                    showPlayerBoard(selfPlayer,playerList.get(0).getPlayerColor().getColor());
                    break;
                case 1:
                    player1Name.setText(playerList.get(1).getUsername());
                    Logger.getGlobal().info(playerList.get(1).getPlayerColor().getColor());
                    showPlayerBoard(player1,playerList.get(1).getPlayerColor().getColor());
                    break;
                case 2:
                    player2Name.setText(playerList.get(2).getUsername());
                    showPlayerBoard(player2,playerList.get(2).getPlayerColor().getColor());
                    break;
                case 3:
                    player3Name.setText(playerList.get(3).getUsername());
                    showPlayerBoard(player3,playerList.get(3).getPlayerColor().getColor());
                    break;
                case 4:
                    player4Name.setText(playerList.get(4).getUsername());
                    showPlayerBoard(player4,playerList.get(4).getPlayerColor().getColor());
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
    public void showPlayerBoard(AnchorPane anchorPaneNeed,String color){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playerBoard.fxml"));
            AnchorPane anchorPane = loader.load();
            anchorPaneNeed.getChildren().add(anchorPane);
            PlayerBoardController playerBoardController =loader.getController();
            playerBoardController.setPlayerImage(boardStatus,color);
//            playerBoardController.setPlayerBlood(boardStatus);
//            playerBoardController.setPlayerSkull(boardStatus);

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
    private void setAmmoCardInMap(BoardStatus boardStatus,GridPane gridPane){
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
     * The setAmmoOwned  set the current ammotile number
     *
     * @param boardStatus
     */

    @FXML
    private void setAmmoOwned(BoardStatus boardStatus){
        redAmmoN.setText(String.valueOf(boardStatus.getPlayer(client.getPlayerID()).getAmmoOwned()[0]));
        blueAmmoN.setText(String.valueOf(boardStatus.getPlayer(client.getPlayerID()).getAmmoOwned()[1]));
        yellowAmmoN.setText(String.valueOf(boardStatus.getPlayer(client.getPlayerID()).getAmmoOwned()[3]));

    }

    /**
     *The setDamageOnSkull is use to get the blood on the skull board
     *
     */
    @FXML
    private void setDamageOnSkull(BoardStatus boardStatus){
        int size = boardStatus.getNumDamageOnSkullBoard().length;
        for(int i = 0; i < 9 ;i ++){
            if(i < size){
                ((ImageView)skull.getChildren().get(i)).setImage(new Image("/blood/" + boardStatus.getColorDamageOnSkullBoard().toString()+".png"));
            }
        }


    }



    /**
     *
     * The setPlayerPosition is use to get the player's position
     * at the beginning use forEach methods set the image visible false
     * and collect to playerstatus get the cellID and put the player's image
     * into the GridPane,if the position image is false put into the player's
     * image into it,if the position image is true pass it to next position
     *
     * @param boardStatus
     * @param gridPane
     */


    @FXML
    public void setPlayerPosition(BoardStatus boardStatus,GridPane gridPane){
        playerPosition.getChildren().stream().map(node->(GridPane)node).forEach(grid -> grid.getChildren().forEach(n -> n.setVisible(false)));
        boardStatus.getAllPlayers().forEach(playerStatus ->
                {
                    int playerPos = boardStatus.getPositions().get(playerStatus.getPlayerID());
                    GridPane playerGrid = (GridPane) playerPosition.getChildren().get(playerPos-1);
                    ImageView iv = (ImageView) playerGrid.getChildren().stream().filter(node -> !node.isVisible()).findFirst().orElse(null);
                    if (iv != null) {
                        Logger.getGlobal().info("Path = " + Player_Path+playerStatus.getPlayerColor().getColor()+"jpg");
                        iv.setImage(new Image(Player_Path+playerStatus.getPlayerColor().getColor()+".jpg"));
                        iv.setVisible(true);
                    }
                });

        //用stream（）把所以的player位置的图片 设置成不可见
        //那所有的playstatus PLAYERID去找它的 位置 （cellID）
        //在那个cellID里面加那个玩家的图片
        //如果位置的图片不可见（false） 就添加 如果是true 就不考虑（filter的用法就是为了去判断不考虑）
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
                        visible = true;
                    }
                )
            );
        }


    }

    /**
     *
     *
     *
     * @param
     */

//    @FXML
//    private void setEndGameButton(BoardStatus boardStatus,AnchorPane anchorPane){
//        endGameButton.setOnMouseClicked(
//                event -> {
//                });
//
//
//    }



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
                setPlayerPosition(boardStatus,playerPosition);
                setAmmoOwned(boardStatus);

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
