package it.polimi.ingsw.se2019.Adrenaline.client.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.logging.Logger;

public class ChoiceMapController extends GUIController{

    @FXML
    private AnchorPane root;
    @FXML
    private Button closeButton;
    @FXML
    private Button mapAbutton;
    @FXML
    private Button mapBbutton;
    @FXML
    private Button mapCbutton;
    @FXML
    private Button mapDbutton;
    @FXML
    private Button confirmButton;
    @FXML
    private TextArea textkillshootnum;
    @FXML
    private ImageView mapA;




    public void initialize(){
        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
        mapAbutton.setOnAction(event -> MatchViewController.newView(this));

    }

    public void setMapA(EventHandler<MouseEvent> value)
    {
        mapA.setOnMouseClicked(value);
    }






















}
