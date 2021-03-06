package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnHandler implements Serializable {
    private List<Player> players;
    private List<Player> orderedPlayers;
    private int turn;


    public TurnHandler(List<Player> players) {
        this.players = new ArrayList<>(players);
        orderedPlayers = new ArrayList<>();
        createOrderedPlayers();
    }
    public TurnHandler(Board playboard) {
        orderedPlayers = playboard.getAllPlayers();
        turn = 0;
        playboard.setCurrentPlayer(playboard.getAllPlayers().get(turn));
    }

    private void createOrderedPlayers() {
        List<Player> invertedList = new ArrayList<>(players);
        Collections.reverse(invertedList);
        orderedPlayers.addAll(players);
        orderedPlayers.addAll(invertedList);
    }

    public Player changeTurn(Board board) {
        this.turn++;
        if(turn == board.getAllPlayers().size()) turn = 0;
        board.setCurrentPlayer(board.getAllPlayers().get(turn));
        return board.getCurrentPlayer();
    }

    public List<Player> getPlayers() {
        return players;
    }


    /**
     *
     * The getCurrentPlayer return the current player.
     *
     * @return the player that is playing.
     *
     */

    public Player getCurrentPlayer() {
        return orderedPlayers.get(turn);
    }
}
