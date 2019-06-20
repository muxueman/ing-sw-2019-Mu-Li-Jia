package it.polimi.ingsw.se2019.Adrenaline.client.view;


import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * The InitialViewController class represents the controller
 * of the initialView.fxml file.
 *
 * @author Li xuejing
 *
 */


public class InitialViewController extends GUIController {

    @FXML
    private AnchorPane root;
    @FXML
    private Text messageText;
    @FXML
    private Text title;
    @FXML
    private TextField nameText;
    @FXML
    private Button socketButton;
    @FXML
    private Button rmiButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField hostText;
    @FXML
    private TextField portText;
    @FXML
    private Label errorText;

    private boolean errorMessage = false;
    private boolean player = false;


    /**
     * The initialize method is used to initialize the
     * initial view scene.
     */

    public void initialize() {
        final String textEffect = "textEffect";
        final String networkButtonEffect = "networkButton";
        final String searchButtonEffect = "searchButton";
        hostText.getStyleClass().add(textEffect);
        portText.getStyleClass().add(textEffect);
        nameText.getStyleClass().add(textEffect);
        rmiButton.getStyleClass().add(networkButtonEffect);
        socketButton.getStyleClass().add(networkButtonEffect);
        searchButton.getStyleClass().add(searchButtonEffect);
        nameText.setVisible(false);
        searchButton.setVisible(false);
        closeButton.setOnAction(event -> AlertBox.displayCloseRequest(this,root));
        addDraggableNode(root);
        boardStatus = null;
    }


    /**
     * The getTable method is used to take the player's name and
     * get into a room.
     */

    public void getTable() {
        if (!nameText.getText().equals("")) {
            notify(nameText.getText());
        } else {
            messageText.setText("Please enter a name");
        }

    }

    /**
     * The getConnection method is used to connect to the server.
     *
     * @param event event from the user interface.
     */

    @FXML
    public void getConnection(ActionEvent event) {
        if (hostText.getText().equals("") || portText.getText().equals("")) {
            messageText.setText("Insert: " + (hostText.getText().equals("") ? "Host" : "")   + " " + (portText.getText().equals("") ? "Port" : "") );
        } else {
            try {
                messageText.setText("");
                Integer.parseInt(portText.getText());
                if(errorMessage){
                    notify("cancel");
                    errorMessage = false;
                }

                if (event.getSource() == socketButton) {
                    notify("2");
                    notify(hostText.getText());
                    notify(portText.getText());
                } else if (event.getSource() == rmiButton) {
                    notify("1");
                    notify(hostText.getText());
                    notify(portText.getText());
                }
            } catch (NumberFormatException e) {
                messageText.setText("Invalid port");
            }
        }
    }

    /**
     * The reportError method is used to set an error text in
     * the user's interface.
     *
     * @param error error's string.
     */

    @Override
    public void reportError(String error) {
        Platform.runLater(() -> errorText.setText(error));
        errorMessage = true;
    }

    /**
     * The nextView method is used to
     * ask the graphic user interface to change view.
     */

    @Override
    public void nextView(boolean wpc) {
        if(!player) {
            Platform.runLater(() -> {
                errorText.setText("");
                title.setText("Insert name \nor reconnection token");
                socketButton.setVisible(false);
                rmiButton.setVisible(false);
                hostText.setVisible(false);
                portText.setVisible(false);
                nameText.setVisible(true);
                searchButton.setVisible(true);
                player = true;
            });
        } else {
            Platform.runLater( () -> {
                switchSceneSameStage(root, "/scenes/matchView.fxml", "/scenes/matchViewStyle.css",
                        new MatchViewController(boardStatus, wpc));
                Logger.getGlobal().log(Level.INFO, "{0} joins the table...", nameText.getText());
            });
        }
    }



    @Override
    protected void close(AnchorPane anchorPane) {

    }

    public void update(ModelUpdate message) {
        Platform.runLater(() -> guiView.getGuiController().update(message));


    }
}




