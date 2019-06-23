package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;

import java.util.ArrayList;

public class CellStatus implements Status {
    protected int cellID;
    protected Color cellColor;
    //all the players in this cell
    protected ArrayList<Player> cellPlayers;
    //store the adjacent cells in a sequence of up, right, down, left.
    protected Cell[] adjacentCells;
    //to identify the cell is which kind;
    protected int type;

    public CellStatus(int cellID) {
        this.cellID = cellID;
        this.cellPlayers = new ArrayList<Player>();
    }

}
