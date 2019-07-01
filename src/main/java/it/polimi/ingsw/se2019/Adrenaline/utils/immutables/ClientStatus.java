package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import org.fusesource.jansi.Ansi;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class ClientStatus{


    private Board board;
    private Map map;
    private Player player;
    private List<Player> players;

    public ClientStatus(){
        this.board = null;
        this.map = null;
    }

    //getter and setter
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updatePlayer(List<Player> players){
        int i = 0;
        for(Player player: players){
            players.remove(i);
            players.add(player);
        }
    }
/*
    public boolean updatePlayer(Player newPlayer) {
        Player player = getPlayer(newPlayer.getPlayerID());
        if (player == null) {
            players.add(newPlayer);
            if (currentPlayer == null) {
                currentPlayer = newPlayer;
            }
        } else {
            int playerIndex = players.indexOf(player);
            players.remove(playerIndex);
            players.add(playerIndex, newPlayerStatus);
            if (newPlayerStatus.equals(currentPlayer)) {
                currentPlayer = newPlayerStatus;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return "";
    }
    public Ansi toAnsi(){
        return ansi().a("");
    }

 */
}
