package it.polimi.ingsw.se2019.Adrenaline.network.messages;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

import java.io.Serializable;
/**
 *
 * The StatusUpdate interface represents an update of the client-side
 * status of the Model. It's sent from the server to the client, wrapped
 * in a ServerMessage object.
 *
 * @author Mu xueman
 *
 */
public interface StatusUpdate extends Serializable {

    /**
     *
     * The updateStatus function is used to update the client-side Model,
     * depending on the implementation.
     *
     * @param model the model to update.
     *
     */

    void updateStatus(UpdatableModel model);
}