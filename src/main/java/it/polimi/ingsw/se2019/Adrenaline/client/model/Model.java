package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

import java.util.List;

public class Model extends Observable<ModelUpdate> implements UpdatableModel{

    private BoardStatus boardStatus;
    private ModelUpdate nextUpdate;

    public Model() {
        boardStatus = null;
        nextUpdate = new ModelUpdate();
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }


    public void initModel(int map, int numkillShoot) {
        boardStatus = new BoardStatus(map,numkillShoot);
        nextUpdate = new ModelUpdate(boardStatus);
    }

    /**
     *
     * The pingUpdate method notifies the View telling that nothing
     * has changed from the previous state (possibly useful for GUI).
     *
     */
    private void pingUpdate() {
        notify(new ModelUpdate(boardStatus));
    }

    /**
     *
     * The pingUpdate method notifies the View telling that something
     * has changed from the previous state.
     * @param message is the string message to elaborate.
     *
     */
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


//    @Override
//    public void updatePlayer(PlayerStatus playerStatus) {
//        if (boardStatus.updatePlayer(playerStatus)) {
//            nextUpdate.addStatusUpdate(playerStatus);
//            nextUpdate.setBoardStatus(boardStatus);
//        }
//    }

    @Override
    public void updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {
        if (boardStatus.updateMap(mapStatus)) {
            nextUpdate.addStatusUpdate(mapStatus);
            nextUpdate.setBoardStatus(boardStatus);
        }
    }

//    /**
//     *
//     * The updatePlayer method is used to update the local model.
//     * @param additionalStatus is a new additional status to update.
//     *
//     */
//    @Override
//    public void updateAdditional(AdditionalStatus additionalStatus) {
//        boardStatus.updateAdditional(additionalStatus);
//        nextUpdate.addStatusUpdate(additionalStatus);
//    }
//
//
//
//    @Override
//    public void updateReconnectionToken(TokenStatus token) {
//        boardStatus.setReconnectionToken(token.getToken());
//        nextUpdate.addStatusUpdate(token);
//    }
}
