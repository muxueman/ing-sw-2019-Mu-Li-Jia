package it.polimi.ingsw.se2019.Adrenaline.server.controller;

/**
 * The ServerController interface describes the interface of the controller of a player
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.utils.network.GameServerInterface;

public interface ServerController {

    boolean isActive();

    void setActive(boolean active);

    void nextState(GameServerInterface nextState);

    void setMatch(MatchController currentMatch);

    MatchController getMatch();
}