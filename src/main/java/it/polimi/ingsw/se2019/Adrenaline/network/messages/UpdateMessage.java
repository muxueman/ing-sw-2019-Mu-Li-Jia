package it.polimi.ingsw.se2019.Adrenaline.network.messages;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

public class UpdateMessage  extends ServerMessage {
    public UpdateMessage(String message) {
        super(false, message);
    }
}
