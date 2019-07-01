package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

import java.util.List;

public class Model extends Observable<ModelUpdate> {

    private ClientStatus clientStatus;
    private ModelUpdate nextUpdate;

    public Model() {
        clientStatus = null;
        nextUpdate = new ModelUpdate();
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    //init model with
    public void initModel(Map map, int skull ) {
        clientStatus = new ClientStatus();
        nextUpdate = new ModelUpdate(clientStatus);
    }


    //notifies the View telling that nothing has changed from the previous state (possibly useful for GUI)
    private void pingUpdate() {
        notify(new ModelUpdate(clientStatus));
    }

    //notifies the View
    
    public void pingUpdate(String message) {
        if (nextUpdate.isEmpty() && !message.equals("")) {
            nextUpdate.setMessage("Nothing has changed!");
            pingUpdate();
        } else {
            nextUpdate.setMessage(message);
            notify(nextUpdate);
            nextUpdate = new ModelUpdate(clientStatus);
        }
    }

/*

    @Override
    public void updateClientStatus(ClientStatus clientStatus){
        nextUpdate.addStatusUpdate(clientStatus);
    };

    @Override
    public void updateMap(Map map){
        clientStatus.setMap(map);
        nextUpdate.addStatusUpdate(map);
        nextUpdate.setClientStatus(clientStatus);
    }

    @Override
    public void updateBoard(Board board){
        clientStatus.setBoard(board);
        nextUpdate.addStatusUpdate(board);
        nextUpdate.setClientStatus(clientStatus);
    }
    @Override
    //update the local model.
    public void updatePlayer(Player player){
        if (clientStatus.updatePlayer(player)) {
            nextUpdate.addStatusUpdate(player);
            nextUpdate.setClientStatus(clientStatus);
        }
    };

    @Override
    public void updateOptionalPowerupCards(List<PowerupCard> powerupCards) {
        boardStatus.setOptionalPowerupCards(powerupCards);
        for (PowerupCard p : powerupCards) {
            nextUpdate.addStatusUpdate(p);
        }
    }

    @Override
    public void updateOptionalWeaponCards(List<WeaponCard> weaponCards) {
        boardStatus.setOptionalWeaponCards(weaponCards);
        for (WeaponCard w : weaponCards) {
            nextUpdate.addStatusUpdate(w);
        }
    }

    @Override
    public void updateReconnectionToken(TokenStatus token) {
        boardStatus.setReconnectionToken(token.getToken());
        nextUpdate.addStatusUpdate(token);
    }

    @Override
    public void updateAdditional(AdditionalStatus additionalStatus) {
        boardStatus.updateAdditional(additionalStatus);
        nextUpdate.addStatusUpdate(additionalStatus);
    }
    */
}
