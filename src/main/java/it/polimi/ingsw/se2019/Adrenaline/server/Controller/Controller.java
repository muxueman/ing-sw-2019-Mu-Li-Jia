package it.polimi.ingsw.se2019.Adrenaline.server.controller;


import it.polimi.ingsw.se2019.Adrenaline.network.PlayServerInterface;

public interface Controller{
    boolean isActive();

    void setActive(boolean active);

    void nextState(PlayServerInterface nextState);

    void setMatch(MatchController currentMatch);

    MatchController getMatch();


}
