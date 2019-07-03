package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;
/**
 * Update the client-side status of the map. containing update the cells, weapon cards, ammotiles, player positions.
 * It's sent from the server to the client.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

public class MapUpdate implements StatusUpdate {

    Map map;

    public MapUpdate(Map map){
        this.map = map;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateMap(map);
    }


}
