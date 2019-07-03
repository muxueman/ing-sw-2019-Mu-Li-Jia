package it.polimi.ingsw.se2019.Adrenaline.network.messages.updates;

import it.polimi.ingsw.se2019.Adrenaline.client.model.Model;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.StatusUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;

public class BoardSkullUpdate implements StatusUpdate {

    Board board;

    public BoardSkullUpdate(Board board){
        this.board = board;
    }

    @Override
    public void updateStatus(Model model) {
        model.updateSkull(board);
    }
}