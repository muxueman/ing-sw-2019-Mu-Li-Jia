package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.updates;
/**
 * Update the client-side status of the map. containing update the cells, weapon cards, ammotiles, player positions.
 * It's sent from the server to the client.
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.utils.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;

public class MapUpdate implements StatusUpdate {

    private Map map;

    public MapUpdate(Map map){
        this.map = map;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateMap(map);
    }


}
