package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

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

    private MatchViewController matchViewController;
    private boolean close = false;


    public ChoiceMapController(BoardStatus boardStatus,MatchViewController matchViewController){
        this.boardStatus = boardStatus;
        this.matchViewController = matchViewController;
    }

    public void initialize(){

        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
        checkMap(matchViewController);
        Button5.setOnMouseClicked(event -> checkChoice(5));
        Button6.setOnMouseClicked(event -> checkChoice(6));
        Button7.setOnMouseClicked(event -> checkChoice(7));
        Button8.setOnMouseClicked(event -> checkChoice(8));



    }

    public void checkMap(MatchViewController matchViewController) {

        mapAbutton.setOnAction(event -> matchViewController.notify("1"));
        mapBbutton.setOnAction(event -> matchViewController.notify("2"));
        mapCbutton.setOnAction(event -> matchViewController.notify("3"));
        mapDbutton.setOnAction(event -> matchViewController.notify("4"));


    }
    private void checkChoice(int killshootnum) {
        if (killshootnum >=5 && killshootnum <= 8) {
            matchViewController.notify(Integer.toString(killshootnum));
//            matchViewController.setInit();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {
            Logger.getGlobal().warning("It's null!");
        }
    }



















}
