package it.polimi.ingsw.se2019.Adrenaline.server.controller.controllerState;

import it.polimi.ingsw.se2019.Adrenaline.network.ClientInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.GameServerInterface;
import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;
import it.polimi.ingsw.se2019.Adrenaline.server.controller.MatchController;

import java.util.List;

public class PlayingState implements GameServerInterface {

    private MatchController matchController;

    public PlayingState(MatchController matchController) {
        this.matchController = matchController;
    }

    @Override
    public GameServerInterface update(ClientMessage message, ClientInterface client) {
        String textMove = message.getTextMove();
        if (textMove.equalsIgnoreCase("SELECT")) {
            //matchController.selectDie(message.getMainParam()-1);
            return this;
        } else if (textMove.equalsIgnoreCase("PLACE")) {
            List<Integer> additionalParams = message.getAdditionalParams();
            if (additionalParams.size() == 2) {
                //Position position = new Position(additionalParams.get(0)-1, additionalParams.get(1)-1);
                //if (matchController.placeDie(position)) {
                //    return this;
                //}
            }
            return this;
        } else if (textMove.equalsIgnoreCase("TOOL")) {
            //matchController.useToolCard(message.getMainParam() - 1, message.getAdditionalParams());
            return this;
        } else if (textMove.equalsIgnoreCase("CANCEL")) {
            //matchController.resetToolCard(message.getMainParam() - 1);
            return this;
        } else if (textMove.equalsIgnoreCase("PASS")) {
            matchController.nextTurn();
            //return matchController.isPlaying(client) ? this : new NonPlayingState();
        }
        return null;
    }
}