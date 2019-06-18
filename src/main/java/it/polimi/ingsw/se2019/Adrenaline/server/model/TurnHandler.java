package it.polimi.ingsw.se2019.Adrenaline.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnHandler {
    private List<Player> players;
    private List<Player> orderedPlayers;


    public TurnHandler(List<Player> players) {
        this.players = new ArrayList<>(players);
        orderedPlayers = new ArrayList<>();
        createOrderedPlayers();
    }

    private void createOrderedPlayers() {
        List<Player> invertedList = new ArrayList<>(players);
        Collections.reverse(invertedList);
        orderedPlayers.addAll(players);
        orderedPlayers.addAll(invertedList);
    }


}
