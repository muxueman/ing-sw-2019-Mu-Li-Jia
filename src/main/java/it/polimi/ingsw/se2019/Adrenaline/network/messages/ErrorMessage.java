package it.polimi.ingsw.se2019.Adrenaline.network.messages;

/**
 * A ErrorMessage object is the message sent from the client to the server.
 * It contains an error in a textual form.
 * @author Xueman Mu
 */

import java.util.List;

public class ErrorMessage extends ServerMessage {

    //constructor
    public ErrorMessage(String error) {
        super(error);
    }
    public ErrorMessage(String error, List<StatusUpdate> statusUpdates) {
        super(error, statusUpdates);
    }
}
