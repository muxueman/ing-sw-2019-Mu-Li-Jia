package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.logging.Level;
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
    }






    public void setMapAbutton(EventHandler<MouseEvent> value) {
        mapA.setOnMouseClicked(value);

        mapAbutton.setOnMouseClicked(value);
    }

    public void setMapBbutton(EventHandler<MouseEvent> value) {

        mapBbutton.setOnMouseClicked(value);
    }
    public void setMapCbutton(EventHandler<MouseEvent> value) {

        mapCbutton.setOnMouseClicked(value);
    }
    public void setMapDbutton(EventHandler<MouseEvent> value) {
        mapDbutton.setOnMouseClicked(value);
    }


    public static void displaychioceMap(MatchViewController matchViewController) {

        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(ChoiceMapController.class.getResource("/scenes/choiceMap.fxml"));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            ChoiceMapController controller = loader.getController();
            controller.setMapAbutton(event ->
                    Platform.runLater(() -> {
                        //matchViewController.notify("1");
                        Logger.getGlobal().info("1");
                        window.close();
                    })
            );

            controller.setMapBbutton(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("2");
                        window.close();
                    })
            );
            controller.setMapCbutton(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("3");
                        window.close();
                    })
            );
            controller.setMapDbutton(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("4");
                        window.close();
                    })
            );

            setWindow(window, initialScene);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }
    private static void setWindow(Stage window, Scene scene) {
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.showAndWait();

    }















}
