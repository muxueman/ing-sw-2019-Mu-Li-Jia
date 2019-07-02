package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import org.fusesource.jansi.Ansi;

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

    @Override
    //used for initial the board at the beginning
    public void setBoardStatus(Board board){
        boardStatus = new BoardStatus(board);
        nextUpdate = new ModelUpdate(boardStatus);
    }

//    public void initModel(int map, int numkillShoot) {
//        boardStatus = new BoardStatus(map,numkillShoot);
//        nextUpdate = new ModelUpdate(boardStatus);
//    }

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


    @Override
    public void updatePlayer(Player player) {
        if (boardStatus.updatePlayer(player)) {
            nextUpdate.addStatusUpdate(player);
            nextUpdate.setBoardStatus(boardStatus);
        }
    }

    @Override
    public void updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {
        if (boardStatus.updateMap(mapStatus)) {
            nextUpdate.addStatusUpdate(mapStatus);
            nextUpdate.setBoardStatus(boardStatus);
        }
    }

    @Override
    public void updateAllPlayers(Board board){
        if(boardStatus.updatePlayers(board)){
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
    }

    @Override
    public void updateSkull(Board board){
        if(boardStatus.updateDamageSkullBoard(board)){
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
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


}
