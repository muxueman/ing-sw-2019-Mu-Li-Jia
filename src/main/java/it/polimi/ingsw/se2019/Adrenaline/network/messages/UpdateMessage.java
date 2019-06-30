package it.polimi.ingsw.se2019.Adrenaline.network.messages;

public class UpdateMessage  extends ServerMessage {

    public UpdateMessage(String message) {
        super(false, message);
    }
}
