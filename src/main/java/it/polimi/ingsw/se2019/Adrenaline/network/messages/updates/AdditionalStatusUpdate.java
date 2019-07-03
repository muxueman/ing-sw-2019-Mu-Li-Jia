package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.AdditionalStatus;

public class AdditionalStatusUpdate implements StatusUpdate {

    private AdditionalStatus additionalStatus;

    public AdditionalStatusUpdate(AdditionalStatus additionalStatus) {
        this.additionalStatus = additionalStatus;
    }

    @Override
    public void updateStatus(Model model) {
        model.updateAdditional(additionalStatus);
    }
}