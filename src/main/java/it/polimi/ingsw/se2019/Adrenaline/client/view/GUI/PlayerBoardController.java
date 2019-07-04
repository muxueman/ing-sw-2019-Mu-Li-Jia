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
    protected static final String Blood_Path = "/blood/";




    public void initialize(){
        addFrameNode(playerChild);
    }

    /**
     *
     * set the playerboard every color collect a image
     *
     * @param color
     */

    @FXML
    public void setPlayerImage(BoardStatus boardStatus,String color) {

            switch (color) {
                case "YELLOW":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_yellow.png"));
                    break;
                case "PINK":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_pink.png"));
                    break;
                case "GREEN":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_green.png"));
                    break;
                case "BLUE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_blue.png"));
                    break;
                case "WHITE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_white.png"));
                    break;
            }
    }

//    //
//    @FXML
//    public void setPlayerBlood(BoardStatus boardStatus){
//
//        for (int i = 0;i < 12;i++){
//
//            ((ImageView)playerBlood.getChildren().get(i)).setImage(new Image(Blood_Path + boardStatus.getPlayer(client.getPlayerID()).getDamageColorOnTrack().get(i).getColor()+".png"));
//        }
//
//    }
//
//    @FXML
//    public void setPlayerSkull(BoardStatus boardStatus){
//
//        for (int i = 0;i < 6;i++ ){
//            ((ImageView)playerSkull.getChildren().get(i)).setImage(new Image("/blood/redSkull.png"));
//        }
//
//    }



}
