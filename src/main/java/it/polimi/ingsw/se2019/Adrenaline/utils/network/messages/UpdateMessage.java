package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages;

/**
 *
 * The UpdateMessage object represents an update of the client.
 * It's sent from the server to the client.
 *
 * @author Mu xueman
 *
 */

public class UpdateMessage extends ServerMessage {


    /**
     *
     * The updateMessage method is used to update clients。
     *
     * @param message the message to send.
     *
     */

    public UpdateMessage(String message) {
        super(false, message);
    }
}
