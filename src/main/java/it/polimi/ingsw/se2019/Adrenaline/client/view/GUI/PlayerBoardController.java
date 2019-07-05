package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * The PlayerBoardController class represents the controller
 * of the PlayerBoard.fxml file.
 *
 * @author li xuejing
 */

public class PlayerBoardController extends GUIController {

    @FXML
    private AnchorPane playerChild;
    @FXML
    private GridPane playerBlood;
    @FXML
    private GridPane playerSkull;
    @FXML
    private GridPane playerAction;
    @FXML
    private ImageView playerImg;
    @FXML
    private GridPane markDamage;
    @FXML
    private GridPane markDamageNum;

    private GameMapController gameMapController;
    private String playerID;
    private boolean next;
    private static final String Blood_Path = "/blood/";

    public PlayerBoardController(ClientController client, BoardStatus boardStatus,boolean next){
        this.boardStatus = boardStatus;
        this.next = next;
        this.client = client;
    }

    public void initialize(){
        addFrameNode(playerChild);
        setPlayerImage(boardStatus.getPlayer(client.getPlayerID()).getPlayerColor().getColor());
    }

    /**
     *
     * set the playerboard every color collect a image
     *
     * @param color of a player
     */

    @FXML
    void setPlayerImage(String color) {

            switch (color){
                case "YELLOW":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_yellow.png"));
                    notify("YELLOW");break;
                case "PINK":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_pink.png"));
                    notify("RED");break;
                case "GREEN":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_green.png"));
                    notify("GREEN");break;
                case "BLUE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_blue.png"));
                    notify("BLUE");break;
                case "WHITE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_white.png"));
                    notify("WHITE");break;
                    default:break;
            }


    }

    //
    @FXML
    public void setPlayerBlood(PlayerStatus playerStatus,GridPane gridPane){

        for (int i = 0;i < 12;i++){
            ((ImageView)gridPane.getChildren().get(i)).setImage(new Image(Blood_Path + playerStatus.getPlayerColor().getColor()+".png"));//怎么调用不一样的血滴？
            notify(playerStatus.getPlayerColor().getColor());

        }

    }

    @FXML
    public void setPlayerSkull(BoardStatus boardStatus,GridPane gridPane){

        for (int i = 0;i < 6;i++ ){
            ((ImageView)gridPane.getChildren().get(i)).setImage(new Image("/blood/redSkull.png"));
            //notify(boardStatus.getColorDamageOnSkullBoard());
        }

    }



}
