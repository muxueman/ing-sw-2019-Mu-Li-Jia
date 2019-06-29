package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.TokenStatus;

public class Model extends Observable<ModelUpdate> implements UpdatableModel {

    private BoardStatus boardStatus;
    private ModelUpdate nextUpdate;

    public Model() {
        boardStatus = null;
        nextUpdate = new ModelUpdate();
    }
    public void initModel() {
        boardStatus = new BoardStatus(5);
        nextUpdate = new ModelUpdate(boardStatus);
    }


    private void pingUpdate() {
        notify(new ModelUpdate(boardStatus));
    }


    @Override
    public void pingUpdate(String message) {
        if (nextUpdate.isEmpty() && !message.equals("")) {
            nextUpdate.setMessage("Nothing has changed!");
            pingUpdate();
        } else {
            nextUpdate.setMessage(message);
            notify(nextUpdate);
            nextUpdate = new ModelUpdate(boardStatus);
        }
    }


    @Override
    public void updateMap(Map selectedMap) {
        nextUpdate.addStatusUpdate(boardStatus);
    }
    @Override
    public void updatePlayboard(BoardStatus boardStatus){

    };

    @Override
    public void updatePlayer(PlayerStatus playerStatus){

    };

    @Override
    public void updateReconnectionToken(TokenStatus token) {
        boardStatus.setReconnectionToken(token.getToken());
        nextUpdate.addStatusUpdate(token);
    }

}
