package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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

    private MatchViewController matchViewController;

    private String playerID;


    private void initialize(){
        addDraggableNode(playerChild);


    }

    @FXML
    public void setPlayerImg(PlayerStatus playerStatus) {
        for(int i = 0;i < playerStatus.getPlayerColor().getColor().length();i++){
            switch (playerStatus.getPlayerColor().getColor()){
                case "YELLOW":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_yellow.png")); break;
                case "RED":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_red.png")); break;
                case "GREEN":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_green.png")); break;
                case "BLUE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_blue.png")); break;
                case "WHITE":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_white.png")); break;
            }
        }

    }

    @FXML
    public void setPlayerBlood(PlayerStatus playerStatus,GridPane gridPane){

        for (int i = 0;i < 12;i++){


        }

    }

    @FXML
    public void setPlayerSkull(BoardStatus boardStatus){

    }

}
