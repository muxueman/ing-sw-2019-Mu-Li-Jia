package it.polimi.ingsw.se2019.Adrenaline.client.view;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    private static final String REQUEST_ALERT = "/alertBox/requestAlert.fxml";
    private AlertBox() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    private static void setWindow(Stage window, Scene scene){
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.showAndWait();

    }


}
