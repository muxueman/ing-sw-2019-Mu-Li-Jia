package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;


import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Logger;
/**
 *
 * The WindowController is collect with javafx and initial the fxml scene and
 * use to collect other controller
 *
 * @author li xuejing
 *
 */
public class WindowController extends GUIController {

    @FXML
    private TextArea textArea;
    @FXML
    private AnchorPane root;
    @FXML
    private Button closeButton;
    @FXML
    private Label labelOne;
    @FXML
    private Label firstP;
    @FXML
    private Label secondP;
    @FXML
    private Label thirdP;
    @FXML
    private Label fourthP;
    @FXML
    private Label fifthP;
    @FXML
    private Label firstScore;
    @FXML
    private Label secondScore;
    @FXML
    private Label thirdScore;
    @FXML
    private Label fourthScore;
    @FXML
    private Label fifthScore;
    @FXML
    private Button buttonOne;
    @FXML
    private Button buttonTwo;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button newGameButton;


    private boolean next;

    /**
     *
     * The initialize is use to initial the scene
     * and you can choose the closeButton to out the game any time
     *
     *
     */


    public void initialize(){
        addFrameNode(root);
        closeButton.setOnAction( event ->  {
            Stage stage = (Stage)root.getScene().getWindow();
            stage.close();
        });
    }

    /**
     *
     * The setCloseButton is use to close the scene you want to close
     *
     */

    void setCloseButton(GUIController guiController, AnchorPane anchorPane) {
        closeButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            guiController.close(anchorPane);
            stage.close();
        });
    }
    /**
     *
     * The setNewGameButton is use to start other new game collect to the new initialView.fxml
     *
     */
    void setNewGameButton(AnchorPane anchorPane) {
        newGameButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
            Stage secondStage = (Stage) anchorPane.getScene().getWindow();
            secondStage.close();
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/initialView.fxml"));
                    Stage primaryStage = new Stage();
                    Parent newRoot = loader.load();
                    primaryStage.setTitle("Adrenalina");
                    Scene initialScene = new Scene(newRoot);
                    primaryStage.setScene(initialScene);
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    GUIController newGuiController = loader.getController();
                    new ClientController(new GUIView(newGuiController));
                } catch (IOException e) {
                    Logger.getGlobal().warning(e.toString());
                }
            });
        });
    }

    void setLabelOne(String s) {

        labelOne.setText(s);
    }

    public void setButtonOneText(String string) {

        buttonOne.setText(string);
    }

    public void setButtonTwoText(String string) {

        buttonOne.setText(string);
    }


    void setButtonOne(EventHandler<ActionEvent> value) {

        buttonOne.setOnAction(value);
    }

    void setButtonTwo(EventHandler<ActionEvent> value) {

        buttonTwo.setOnAction(value);
    }


//    public String getChoiceBoxValue(){
//        return choiceBox.getValue();
//    }

    private void setTextArea(Player myScore,String username){
        textArea.setVisible(true);
        textArea.clear();
        textArea.appendText("Player's name: "+ username +"\n");
        textArea.appendText("Player's score" + myScore + "\n");

    }

    public void setFirstP(Player player,Player score){
        firstP.setText(player.getUserName());
        firstScore.setText(Integer.toString(player.getMyScore()));
        firstScore.setOnMouseClicked(event -> setTextArea(score,player.getUserName()));
    }

    public void setSecondP(Player player,Player score){
        secondP.setText(player.getUserName());
        secondScore.setText(Integer.toString(player.getMyScore()));
        secondScore.setOnMouseClicked(event -> setTextArea(score,player.getUserName()));
    }

    public void setThirdP(Player player,Player score){
        thirdP.setText(player.getUserName());
        thirdScore.setText(Integer.toString(player.getMyScore()));
        thirdScore.setOnMouseClicked(event -> setTextArea(score,player.getUserName()));
    }

    public void setFourthP(Player player,Player score){
        fourthP.setText(player.getUserName());
        fourthScore.setText(Integer.toString(player.getMyScore()));
        fourthScore.setOnMouseClicked(event -> setTextArea(score,player.getUserName()));
    }

    public void setFifthP(Player player,Player score){
        fifthP.setText(player.getUserName());
        fifthScore.setText(Integer.toString(player.getMyScore()));
        fifthScore.setOnMouseClicked(event -> setTextArea(score,player.getUserName()));
    }
}




