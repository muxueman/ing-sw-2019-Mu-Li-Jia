package it.polimi.ingsw.se2019.Adrenaline.client.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.*;

public class Model {

    private BoardStatus boardStatus;

    public Model() {
        boardStatus = null;
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    //used for initial the board at the beginning
    public void setBoardStatus(Board board){
        boardStatus = new BoardStatus(board);
    }

    //from client-side to client-side
    //public void setPosition(Map map){
    //    boardStatus.updatePlayerPosition(map);
    //}

    public void updatePlayer(Player player) {
        boardStatus.updatePlayer(player);
    }

    public void updateSpawnLocation(Board board, Map map){
        boardStatus.updatePosition(map);
        updateAllPlayers(board);
    }

    public void updateMap(it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map mapStatus) {
        boardStatus.updateMap(mapStatus);
    }

    public void updateAllPlayers(Board board){
        boardStatus.updatePlayers(board);
    }

    public void updateSkull(Board board){
        boardStatus.updateDamageSkullBoard(board);
    }

    public void updateAdditional(AdditionalStatus additionalStatus) {
        boardStatus.updateAdditional(additionalStatus);

    }

    public void updateReconnectionToken(TokenStatus token) {
        boardStatus.setReconnectionToken(token.getToken());
    }

/*

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
*/
}
