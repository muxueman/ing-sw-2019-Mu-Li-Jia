package it.polimi.ingsw.se2019.Adrenaline.client.view;


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
     *
     * The initialize method is used to initialize the
     * initial view scene.
     *
     */
    


}
