package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 *
 * The ChoiceMapController is to let the user choose the map he like
 * and choose a killshootnum for next game map
 *
 * @author  li xuejing
 *
 */

public class ChoiceMapController extends GUIController{


    @FXML
    private AnchorPane root;
    @FXML
    private Button mapAbutton;
    @FXML
    private Button mapBbutton;
    @FXML
    private Button mapCbutton;
    @FXML
    private Button mapDbutton;
    @FXML
    private Button Button5;
    @FXML
    private Button Button6;
    @FXML
    private Button Button7;
    @FXML
    private Button Button8;
    @FXML
    private Button closeButton;

    private GameMapController gameMapController;
    private boolean close = false;


    public ChoiceMapController(BoardStatus boardStatus, GameMapController gameMapController){
        this.boardStatus = boardStatus;
        this.gameMapController = gameMapController;
    }

    /**
     *
     * The initialize is to setup the choicemap scenes
     *and the button is for the killshootnum
     *
     *
     */
    public void initialize(){

        addFrameNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
        checkMap(gameMapController);
        Button5.setOnMouseClicked(event -> checkChoice(5));
        Button6.setOnMouseClicked(event -> checkChoice(6));
        Button7.setOnMouseClicked(event -> checkChoice(7));
        Button8.setOnMouseClicked(event -> checkChoice(8));



    }

    /**
     *
     *choose the map you want to choose and through the matchviewcontroller to
     * notify the update messege
     *
     */

    public void checkMap(GameMapController gameMapController) {

        mapAbutton.setOnAction(event -> gameMapController.notify("1"));
        mapBbutton.setOnAction(event -> gameMapController.notify("2"));
        mapCbutton.setOnAction(event -> gameMapController.notify("3"));
        mapDbutton.setOnAction(event -> gameMapController.notify("4"));


    }

    /**
     *
     * checkChoice use for initial methods and give the killshootnum
     * and then close the scene
     *
     * @param killshootnum
     */
    private void checkChoice(int killshootnum) {
        if (killshootnum >=5 && killshootnum <= 8) {
            gameMapController.notify(Integer.toString(killshootnum));
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {
            Logger.getGlobal().warning("It's null!");
        }
    }



















}
