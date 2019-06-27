package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

public class CellStatus implements Status {
    protected int cellID;

    //all the players in this cell
    protected ArrayList<Player> cellPlayers;
    //store the adjacent cells in a sequence of up, right, down, left.

    //to identify the cell is which kind;


    public CellStatus(int cellID) {
        this.cellID = cellID;
        this.cellPlayers = new ArrayList<Player>();
    }
    public void addPlayer(Player player){
        this.cellPlayers.add(player);
    }
    public void removePlayer(Player player){
        this.cellPlayers.remove(player);
    }
    public Ansi toAnsi(){
        return ansi().a("cell players:" + cellPlayers);
    }
}
