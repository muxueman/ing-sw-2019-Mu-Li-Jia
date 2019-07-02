package it.polimi.ingsw.se2019.Adrenaline.network.messages;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

import java.io.Serializable;

public interface StatusUpdate extends Serializable {
    void updateStatus(UpdatableModel model);
}
