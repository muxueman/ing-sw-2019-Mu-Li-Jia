package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.utils.network.UpdatableModel;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;

public class BoardUpdate implements StatusUpdate {

    private Board board;

    public BoardUpdate(Board board){
        this.board = board;
    }

    @Override
    public void updateStatus(UpdatableModel model) {
        model.setBoardStatus(board);
        model.updateAllPlayers(board);
    }
}
