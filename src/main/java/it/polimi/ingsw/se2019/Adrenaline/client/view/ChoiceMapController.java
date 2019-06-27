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
    private ImageView imgOne;
    @FXML
    private ImageView imgTwo;
    @FXML
    private ImageView imgThree;
    @FXML
    private ImageView imgFour;

    private boolean next;

    public ChoiceMapController(BoardStatus boardStatus, boolean next) {
        this.boardStatus = boardStatus;
        this.next = next;
    }



    public void initialize(){
        addDraggableNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
    }






    public void setImgOne(EventHandler<MouseEvent> value) {

        imgOne.setOnMouseClicked(value);
    }

    public void setImgTwo(EventHandler<MouseEvent> value) {

        imgTwo.setOnMouseClicked(value);
    }
    public void setImgThree(EventHandler<MouseEvent> value) {

        imgThree.setOnMouseClicked(value);
    }
    public void setImgFour(EventHandler<MouseEvent> value) {
        imgFour.setOnMouseClicked(value);
    }


    public static void displaychioceMap(MatchViewController matchViewController) {

        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(ChoiceMapController.class.getResource("/scenes/choiceMap.fxml"));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            ChoiceMapController controller = loader.getController();
            controller.setImgOne(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("1");
                        window.close();
                    })
            );

            controller.setImgTwo(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("2");
                        window.close();
                    })
            );
            controller.setImgThree(event ->
                    Platform.runLater(() -> {
                        matchViewController.notify("3");
                        window.close();
                    })
            );
            controller.setImgFour(event ->
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
