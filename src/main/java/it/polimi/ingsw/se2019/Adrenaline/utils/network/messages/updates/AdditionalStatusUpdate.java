package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.updates;


import it.polimi.ingsw.se2019.Adrenaline.utils.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.AdditionalStatus;

public class AdditionalStatusUpdate implements StatusUpdate {

    private AdditionalStatus additionalStatus;

    public AdditionalStatusUpdate(AdditionalStatus additionalStatus) {
        this.additionalStatus = additionalStatus;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateAdditional(additionalStatus);
    }
}