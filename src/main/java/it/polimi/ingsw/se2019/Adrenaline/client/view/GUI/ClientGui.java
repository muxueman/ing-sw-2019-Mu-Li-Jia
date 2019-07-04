package it.polimi.ingsw.se2019.Adrenaline.client.view.GUI;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * The clientGui class launch the gui.
 *
 * @author Li xuejing
 *
 */

public class ClientGui extends Application {

    /**
     *
     * The main method
     * @param args args passed.
     *
     */

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     *
     * The start method used to start the javafx window.
     *
     * @param primaryStage a new stage.
     * @throws Exception thrown if it can't open the file.
     */

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/initialView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Adrenalina");
        Scene initialScene = new Scene(root);
        primaryStage.setScene(initialScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        GUIController guiController = loader.getController();
        ClientController client = new ClientController(new GUIView(guiController));
        guiController.setClientController(client);
    }
}

