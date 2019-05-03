package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import java.util.ArrayList;

/**
 *super class Cell
 *@author Xueman Mu
 */

public abstract class Cell {

    protected int cellID;
    protected Color cellColor;
    //all the players in this cell
    protected ArrayList<Player> cellPlayers;
    //store the adjacent cells in a sequence of up, right, down, left.
    protected Cell[] adjacentCells;

    //constructor
    public Cell(int cellID) {
        this.cellID = cellID;
        this.cellPlayers = new ArrayList<Player>();
    }
    //set values of cell
    public void setCellColor(Color cellColor){
        this.cellColor = cellColor;  
    }

    //@overload
    //direction 0, 1, 2, 3 represent up, right, down, left respectively
    public void setAdjacentCells(Cell cellUp, Cell cellRight, Cell cellDown, Cell cellLeft){
        adjacentCells = new Cell[4];
        adjacentCells[0] = cellUp;
        adjacentCells[1] = cellRight;
        adjacentCells[2] = cellDown;
        adjacentCells[3] = cellLeft;
    }

    //change players inside a cell
    public void addPlayer(Player player){
        this.cellPlayers.add(player);
    }
    public void removePlayer(Player player){
        this.cellPlayers.remove(player);
    }

    //get values of cell
    public int getCellID() {
        return this.cellID;
    }
    public Color getCellColor() {
        return this.cellColor;
    }
    public ArrayList<Player> getCellPlayers(){
        return this.cellPlayers;
    }
    public Cell[] getAdjacentCells(){
        return this.adjacentCells;
    }

    //overload
    //get the cell from a specific direction
    public Cell getNextCell(String direction) {
        switch(direction) {
            case "up":
                if(adjacentCells[0].getCellID() != 0)
                    return this.adjacentCells[0];
                else return null;
            case "right":
                if(adjacentCells[1].getCellID() != 0)
                    return this.adjacentCells[1];
                else return null;
            case "down":
                if(adjacentCells[2].getCellID() != 0)
                    return this.adjacentCells[2];
                else return null;
            case "left":
                if(adjacentCells[3].getCellID() != 0)
                    return this.adjacentCells[3];
                else return null;
            default:
                return null; //better solution??? not regular
        }
    }
    //@overload
    public Cell getNextCell(int direction) {
        switch(direction) {
            case 0:
                if(adjacentCells[0].getCellID() != 0)
                    return this.adjacentCells[0];
                else return null;
            case 1:
                if(adjacentCells[1].getCellID() != 0)
                    return this.adjacentCells[1];
                else return null;
            case 2:
                if(adjacentCells[2].getCellID() != 0)
                    return this.adjacentCells[2];
                else return null;
            case 3:
                if(adjacentCells[3].getCellID() != 0)
                    return this.adjacentCells[3];
                else return null;
            default:
                return null; //better solution??? not regular
        }
    }
}