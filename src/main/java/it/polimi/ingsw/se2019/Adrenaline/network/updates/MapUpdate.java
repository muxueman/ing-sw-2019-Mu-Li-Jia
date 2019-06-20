package it.polimi.ingsw.se2019.Adrenaline.network.updates;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

public class MapUpdate implements StatusUpdate {

    Map map;
    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateMap(map);
    }

}
