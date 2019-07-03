package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;


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

    @Override
    //used for initial the board at the beginning
    public void setBoardStatus(Board board) {
        boardStatus = new BoardStatus(board);
        nextUpdate = new ModelUpdate(boardStatus);
    }

    @Override
    public void updatePlayer(Player player) {
        if (boardStatus.updatePlayer(player)) {
            nextUpdate.addStatusUpdate(player);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updatePlayer(player);
    }

    @Override
    public void updateSpawnLocation(Board board, Map map) {

        if (boardStatus.updatePosition(map)) {
            nextUpdate.addStatusUpdate(map);
        }
        boardStatus.updatePosition(map);
        updateAllPlayers(board);
    }

    @Override
    public void updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {
        if (boardStatus.updateMap(mapStatus)) {
            nextUpdate.addStatusUpdate(mapStatus);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updateMap(mapStatus);
    }

    @Override
    public void updateAllPlayers(Board board) {
        if (boardStatus.updatePlayers(board)) {
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updatePlayers(board);
    }

    @Override
    public void updateSkull(Board board) {
        if (boardStatus.updateDamageSkullBoard(board)) {
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updateDamageSkullBoard(board);
    }

    @Override
    public void updateAdditional(AdditionalStatus additionalStatus) {
        boardStatus.updateAdditional(additionalStatus);
        nextUpdate.addStatusUpdate(additionalStatus);

    }

    @Override
    public void updateReconnectionToken(TokenStatus token) {
        boardStatus.setReconnectionToken(token.getToken());
        nextUpdate.addStatusUpdate(token);
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






    }
}