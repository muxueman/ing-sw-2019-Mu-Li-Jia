package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;

/**
 *
 * The Model class is used to keep an updatable copy of the game model
 * (the state of the match) on the client.
 *
 * @author Riccardo Nembrini
 *
 */
public class Model extends Observable<ModelUpdate> implements UpdatableModel {

    private BoardStatus boardStatus;
    private ModelUpdate nextUpdate;

    /**
     *
     * The constructor creates a model that is a updatable copy of the game model.
     *
     */


    public Model() {
        boardStatus = null;
        nextUpdate = new ModelUpdate();
    }

    /**
     *
     * The getBoardStatus method is used to get the actual status of the board.
     * @return the status of the board.
     *
     */
    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    /**
     *
     * set the board according to boardstatus
     *
     * @param board
     */
    @Override
    //used for initial the board at the beginning
    public void setBoardStatus(Board board) {
        boardStatus = new BoardStatus(board);
        nextUpdate = new ModelUpdate(boardStatus);
    }

    /**
     *
     * The updatePlayer method is used to update the local model.
     * @param player is the new status of the player.
     *
     */
    @Override
    public void updatePlayer(Player player) {
        if (boardStatus.updatePlayer(player)) {
            nextUpdate.addStatusUpdate(player);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updatePlayer(player);
    }


    @Override
    public void updateSpawnLocation(Board board, it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {

        if (boardStatus.updateMap(mapStatus)) {
            nextUpdate.addStatusUpdate(mapStatus);
        }
        updateAllPlayers(board);
    }

    /**
     * update the map information
     * @param mapStatus
     */
    @Override
    public void updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {
        if (boardStatus.updateMap(mapStatus)) {
            nextUpdate.addStatusUpdate(mapStatus);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updateMap(mapStatus);
    }

    /**
     *
     * update the all player information
     *
     * @param board
     */
    @Override
    public void updateAllPlayers(Board board) {
        if (boardStatus.updatePlayers(board)) {
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updatePlayers(board);
    }
    /**
     *
     * The updateSkull method use to update the skull number and the position
     *
     */
    @Override
    public void updateSkull(Board board) {
        if (boardStatus.updateDamageSkullBoard(board)) {
            nextUpdate.addStatusUpdate(board);
            nextUpdate.setBoardStatus(boardStatus);
        }
        boardStatus.updateDamageSkullBoard(board);
    }

    /**
     *
     * The updatePlayer method is used to update the local model.
     * @param additionalStatus is a new additional status to update.
     *
     */

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
}