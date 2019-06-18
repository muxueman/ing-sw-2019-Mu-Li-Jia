package it.polimi.ingsw.se2019.Adrenaline.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The ModelUpdate class represents an update of the model (the state of a match).
 *
 * @author Li xuejing
 *
 */
public class ModelUpdate {

    private String message;


    /**
     *
     * The getMessage method is used to get the message of the update.
     * @return the string message.
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * The setMessage method is used to set the message associated to the update.
     * @param message is the string message to be set.
     *
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
