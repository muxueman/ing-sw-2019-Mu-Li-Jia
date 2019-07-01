package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;
/**
 * Update the client-side status of the map.
 * It's sent from the server to the client.
 * @author Xueman Mu
 */
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.MapStatus;

public class MapUpdate implements StatusUpdate {

    MapStatus map;

    public MapUpdate(MapStatus map){
        this.map = map;
    }
    @Override
    public void updateStatus(UpdatableModel model) {
        model.updateMap(map);
    }


}
