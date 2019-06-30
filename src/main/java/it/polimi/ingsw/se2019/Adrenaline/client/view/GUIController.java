package it.polimi.ingsw.se2019.Adrenaline.client.view;


import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.*;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GUIController {

    protected GUIView guiView = null;
    private double initialX = 0;
    private double initialY = 0;

    protected BoardStatus boardStatus;


    public void addDraggableNode(final Node node) {

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



    /**
     * The switchSceneSameStage method is used to switch the scene using the
     * same stage.
     *
     * @param root         the root of the scene.
     * @param fxmlFileName the path of the fxml file.
     *
     */




    protected void switchSceneSameStage(AnchorPane root, String fxmlFileName, GUIController controller) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            loader.setController(controller);
            Parent secondView = loader.load();
            Scene newScene = new Scene(secondView);
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
        // only used by MatchViewController
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



    private String getCardText(String name, String description) {
        return "Name: " + name + "\nDescription: \n" + description;
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

    protected void close(AnchorPane anchorPane) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        guiView.notify("CANCEL");
        guiView.notify("PASS");
        System.exit(0);
    }

    public void update(ModelUpdate message) {
        this.boardStatus = message.getBoardStatus();
    }

    public void guiValue() {
        // only used by MatchViewController
    }










}
