package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class AlertBox extends GUIController {

    private static final String REQUEST_ALERT = "/alertBox/requestAlert.fxml";
    private boolean next;

    private AlertBox() {

        throw new IllegalStateException("Utility class");
    }


    private static void setWindow(Stage window, Scene scene) {
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.showAndWait();

    }


    private static void displayChoice(MatchViewController matchViewController, String buttonOne, String buttonTwo) {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource(REQUEST_ALERT));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            AlertBoxController controller = loader.getController();
            controller.setLabelOne("Make a Choice");
            controller.setButtonOneText(buttonOne);
            controller.setButtonOne(
                    event ->
                            Platform.runLater(() -> {
                                        matchViewController.notify("1");
                                        window.close();
                                    }
                            ));
            controller.setButtonTwoText(buttonTwo);
            controller.setButtonTwo(
                    event ->
                            Platform.runLater(() -> {
                                        matchViewController.notify("2");
                                        window.close();
                                    }
                            ));
            setWindow(window, initialScene);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }


    public static void displayCloseRequest(GUIController guiController, AnchorPane anchorPane) {

        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource(REQUEST_ALERT));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            AlertBoxController controller = loader.getController();
            controller.setLabelOne("Are you sure?");
            controller.setButtonOne(
                    event -> {
                        guiController.close(anchorPane);
                        window.close();
                    }
            );
            controller.setButtonTwo(event -> window.close());
            setWindow(window, initialScene);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());

        }
    }


    public static void displaySettings() {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource("/alertBox/choicePlayer.fxml"));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            AlertBoxController controller = loader.getController();
            controller.setButtonOne(event -> {
                        Logger.getGlobal().warning("Scelto: " + controller.getChoiceBoxValue());
                        window.close();
                    }
            );
            controller.setButtonTwo(event -> window.close());
            controller.setChoiceBox();
            setWindow(window, initialScene);
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }


    public static void displayEndGame(GUIController guiController, AnchorPane anchorPane, Player score, String username) {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource("/alertBox/endGameAlert.fxml"));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            AlertBoxController controller = loader.getController();
            controller.setCloseButton(guiController, anchorPane);

            controller.setNewGameButton(anchorPane);
            controller.setLabelOne("Score: " + username);

        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }



}


