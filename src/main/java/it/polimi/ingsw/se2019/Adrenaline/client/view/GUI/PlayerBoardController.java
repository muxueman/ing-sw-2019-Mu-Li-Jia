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
    private PlayerStatus playerStatus;

    private String playerID;
    protected static final String Blood_Path = "/blood/";


    public void initialize(){
        addDraggableNode(playerChild);
        setPlayerImg(playerStatus);



    }

    @FXML
    public void setPlayerImg(PlayerStatus playerStatus) {
        int size = boardStatus.getAllPlayers().size();
        for(int i = 0;i < size;i++){
            switch (playerStatus.getPlayerColor().getColor()){
                case "YELLOW":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_yellow.png"));
                    notify("YELLOW");break;
                case "RED":
                    playerImg.setImage(new Image("/playerBoard/playerBoard_red.png"));
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
            }
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
            notify(boardStatus.getColorDamageOnSkullBoard().toString());
        }

    }



}
