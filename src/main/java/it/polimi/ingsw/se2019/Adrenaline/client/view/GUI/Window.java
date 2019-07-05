package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
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

public class Window extends GUIController {

    private static final String REQUEST_WINDOW = "/fxml/requestWindow.fxml";
    private boolean next;
    /**
     *
     * This is to throw the illegal messege
     *
     *
     */
    private Window() {

        throw new IllegalStateException("");
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
            FXMLLoader loader = new FXMLLoader(Window.class.getResource(REQUEST_WINDOW));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            WindowController controller = loader.getController();
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
     * The displayEndGame is use for get the game result to the user
     * information about every user and win or loss and the final score
     *
     *
     */

    public static void displayEndGame(GUIController guiController, AnchorPane anchorPane, Player score, String username) {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(Window.class.getResource("/fxml/finalWindow.fxml"));
            Parent root = loader.load();
            Scene initialScene = new Scene(root);
            WindowController controller = loader.getController();
            controller.setCloseButton(guiController, anchorPane);

            controller.setNewGameButton(anchorPane);
            controller.setLabelOne("Score: " + username);

        } catch (IOException e) {
            Logger.getGlobal().warning(e.getCause().toString());
        }
    }




}


