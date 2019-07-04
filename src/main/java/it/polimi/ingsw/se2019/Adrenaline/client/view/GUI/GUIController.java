package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.*;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * The GUIController is an abstract class that have all the
 * common methods of all the javafx views
 *
 * @author li xuejing
 *
 */


public abstract class GUIController {

    protected GUIView guiView = null;
    private double initialX = 0;
    private double initialY = 0;
    protected ClientController client;
    protected BoardStatus boardStatus;

    /**
     * The addFrameNode method is used to permit
     * a node to be draggable.
     *
     * @param node chosen node to be draggable.
     */

    public void addFrameNode(final Node node) {

        node.setOnMousePressed(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });

        node.setOnMouseDragged(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                node.getScene().getWindow().setX(me.getScreenX() - initialX);
                node.getScene().getWindow().setY(me.getScreenY() - initialY);
            }
        });
    }

    public void setClientController(ClientController client){
        this.client = client;
    }


    /**
     * The changeToNextView method is used to switch the scene using the
     * same stage.
     *
     * @param root         the root of the scene.
     * @param fxmlFileName the path of the fxml file.
     *
     */




    protected void changeToNextView(AnchorPane root, String fxmlFileName, GUIController controller) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            loader.setController(controller);
            Parent newView = loader.load();
            Scene newScene = new Scene(newView);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setWidth(1300);
            stage.setHeight(800);
            stage.centerOnScreen();
            stage.setScene(newScene);

            guiView.setGuiController(controller);
        } catch (IOException e1) {
            e1.printStackTrace();
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
    public void guiSetCancelImage() {
        // only used by GameMapController
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
    }


    /**
     * The guiLaunchTimer method is used to launch the timer on the gui.
     */

    public void guiLaunchTimer() {
    }


    /**
     *
     * The nextView method is used to pass to the next view.
     *
     */

    public void nextView(boolean next) {

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

    /**
     * The close method is used to close the stage.
     *
     * @param anchorPane root.
     */

    protected void close(AnchorPane anchorPane) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        guiView.notify("CANCEL");
        guiView.notify("PASS");
        System.exit(0);
    }

    /**
     * The update method is used to update the gui.
     *
     * @param message part of the model to be updated.
     */


    public void update(ModelUpdate message) {
        this.boardStatus = message.getBoardStatus();
    }

    /**
     * The guiValue method is used to get the value needed for a toolcard.
     */


    public void guiValue() {
    }

    /**
     * The showScore method is used to show the ranking.
     *
     * @param myscore ranking to display.
     */

    public void showScore(Player myscore) {
    }










}
