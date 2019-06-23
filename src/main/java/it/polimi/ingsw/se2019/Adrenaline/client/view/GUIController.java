package it.polimi.ingsw.se2019.Adrenaline.client.view;


import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class GUIController {
    protected GUIView guiView = null;
    private double initialX = 0;
    private double initialY = 0;

    protected BoardStatus boardStatus;




    /**
     * The switchSceneSameStage method is used to switch the scene using the
     * same stage.
     *
     * @param root         the root of the scene.
     * @param fxmlFileName the path of the fxml file.
     * @param cssFileName  the path of the css file.
     */

    protected void switchSceneSameStage(AnchorPane root, String fxmlFileName, String cssFileName, GUIController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            loader.setController(controller);
            Parent secondView = loader.load();
            Scene newScene = new Scene(secondView);
            newScene.getStylesheets().add(cssFileName);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setWidth(1024);
            stage.setHeight(624);
            stage.centerOnScreen();
            stage.setScene(newScene);
            guiView.setGuiController(controller);
        } catch (IOException e1) {
            Logger.getGlobal().warning(e1.getCause().toString());
        }
    }



    /**
     * The showMessage method is used to show a message
     *
     * @param message message to display.
     */
    public void showMessage(String message) {

    }

    /**
     * The reportError method is used to report an error.
     *
     * @param error error to display.
     */

    public void reportError(String error) {

    }



    /**
     * The setGuiView method is used to set the guiView.
     *
     * @param guiView actual guiView.
     */

    public void setGuiView(GUIView guiView) {
        this.guiView = guiView;
    }

    /**
     * The guiPlay method is used to send a playing message to the client.
     *
     * @param playing true or false corresponding to play or not.
     */

    public void guiPlay(boolean playing) {
        // only used by MatchViewController
    }


    /**
     * The guiLaunchTimer method is used to launch the timer on the gui.
     */

    public void guiLaunchTimer() {
        // only used by MatchViewController
    }

    /**
     * The guiValue method is used to get the value needed for a toolcard.
     */

    public void guiValue() {
        // only used by MatchViewController
    }

    /**
     * The guiNumber method is used to get the number needed for a toolcard.
     */

    public void guiNumber() {
        // only used by MatchViewController
    }

    /**
     * The guiChoice method is used to get a choice needed for a toolcard.
     */

    public void guiChoice() {
        // only used by MatchViewController
    }

    /**
     * The guiRoundTrack method is used active the DiceStorege.
     */

    public void guiRoundTrack() {
        // only used by MatchViewController
    }

    /**
     * The guiSetCancelImage method is used to disable or not the cancel image.
     */

    public void guiSetCancelImage() {
        // only used by MatchViewController
    }

    /**
     * The getCardText method is used to get the complete description
     * of the card.
     *
     * @param name        card's name.
     * @param description card's description.
     * @return a complete description.
     */

    private String getCardText(String name, String description) {
        return "Name: " + name + "\nDescription: \n" + description;
    }



    /**
     *
     * The nextView method is used to pass to the next view.
     *
     */

    public void nextView(boolean wpc) {

    }
    /**
     *
     * The notify method is used to notify a client.
     * @param message message needed to be notify.
     *
     */
    void notify(String message) {
        guiView.notify(message);
    }


    protected void setInit() {
    }

    protected abstract void close(AnchorPane anchorPane);

}
