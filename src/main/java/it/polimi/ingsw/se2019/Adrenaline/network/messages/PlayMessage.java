package it.polimi.ingsw.se2019.Adrenaline.network.messages;




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
