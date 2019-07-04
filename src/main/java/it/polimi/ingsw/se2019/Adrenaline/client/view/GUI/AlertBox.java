package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

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

/**
 *
 * The alertbox is to setup some alertdisplay use for to get the user can get some
 * alert when the user put some special button or make some error action
 *
 * @author  li xuejing
 *
 */

public class AlertBox extends GUIController {

    private static final String REQUEST_ALERT = "/fxml/requestAlert.fxml";
    private boolean next;
    /**
     *
     * This is to throw the illegal messege
     *
     *
     */
    private AlertBox() {

        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * The setWindow is to initial the window
     *
     *
     */


    private static void setWindow(Stage window, Scene scene) {
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.showAndWait();

    }

    /**
     *
     * The displayCloseRequest is use for when the user want to
     * close the display right now collect to multidisplay
     *
     *
     */

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

    /**
     *
     * The displayEndGame is use for get the Gamerisult to the user
     * information about every user and win or loss and the final score
     *
     *
     */

    public static void displayEndGame(GUIController guiController, AnchorPane anchorPane, Player score, String username) {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(AlertBox.class.getResource("/fxml/endGameAlert.fxml"));
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


