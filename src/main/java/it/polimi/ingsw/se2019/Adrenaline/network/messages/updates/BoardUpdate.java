package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;

public class BoardUpdate implements StatusUpdate {

    Board board;

    public BoardUpdate(Board board){
        this.board = board;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.setBoardStatus(board);
        model.updateAllPlayers(board);
    }
}
