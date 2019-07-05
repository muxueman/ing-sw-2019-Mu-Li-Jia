package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages;

/**
 *
 * A PlayMessage object is the message sent from the client to the server.
 * It contains an message to change the client's status in PlayingState.
 *
 * @author li xuejing
 *
 */


public class PlayMessage extends ServerMessage {

    /**
     *
     * Constructs a message with a signal to change into a new PlayingState.
     *
     */

    public PlayMessage() {
        super(true);
    }
}
