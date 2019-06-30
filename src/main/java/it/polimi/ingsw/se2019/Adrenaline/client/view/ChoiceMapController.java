package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public ChoiceMapController(BoardStatus boardStatus,MatchViewController matchViewController){
        this.boardStatus = boardStatus;
        this.matchViewController = matchViewController;
    }

    public void initialize(){

        checkMap(matchViewController);
        getKillshootnum(matchViewController);
        boardStatus = null;
        matchViewController.setInit();
        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });

    }

    public void getKillshootnum(MatchViewController matchViewController){

        Button5.setOnAction(event -> matchViewController.notify("5"));
        Button6.setOnAction(event -> matchViewController.notify("6"));
        Button7.setOnAction(event -> matchViewController.notify("7"));
        Button8.setOnAction(event -> matchViewController.notify("8"));



    }

    public void checkMap(MatchViewController matchViewController){

        mapAbutton.setOnAction(event -> matchViewController.notify("1"));
        mapBbutton.setOnAction(event -> matchViewController.notify("2"));
        mapCbutton.setOnAction(event -> matchViewController.notify("3"));
        mapDbutton.setOnAction(event -> matchViewController.notify("4"));


    }

//    public void setCloseButton(GUIController guiController, AnchorPane anchorPane) {
//        closeButton.setOnAction(event -> {
//            Stage stage = (Stage) root.getScene().getWindow();
//            guiController.close(anchorPane);
//            stage.close();
//        });
//    }















}
