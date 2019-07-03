package it.polimi.ingsw.se2019.Adrenaline.network.messages;

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import java.io.Serializable;

public interface StatusUpdate extends Serializable {
    void updateStatus(Model model);
}
