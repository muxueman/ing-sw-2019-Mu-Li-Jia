package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;

public class Model extends Observable<ModelUpdate> implements UpdatableModel {

    private BoardStatus boardStatus;
    private ModelUpdate nextUpdate;

    public Model() {
        boardStatus = null;
        nextUpdate = new ModelUpdate();
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    //init model with
    public void initModel(Map map, int skull ) {
        boardStatus = new BoardStatus(map, skull);
        nextUpdate = new ModelUpdate(boardStatus);
    }


    //notifies the View telling that nothing has changed from the previous state (possibly useful for GUI)
    private void pingUpdate() {
        notify(new ModelUpdate(boardStatus));
    }

    //notifies the View
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
    public void updatePlayboard(BoardStatus boardStatus){
        nextUpdate.addStatusUpdate(boardStatus);
    };

    @Override
    //update the local model.
    public void updatePlayer(PlayerStatus playerStatus){
        if (boardStatus.updatePlayer(playerStatus)) {
            nextUpdate.addStatusUpdate(playerStatus);
            nextUpdate.setBoardStatus(boardStatus);
        }
    };

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
}
