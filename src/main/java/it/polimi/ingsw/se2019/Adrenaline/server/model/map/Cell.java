package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import java.util.ArrayList;

/**
 *super class Cell
 *@author Xueman Mu
 */

public abstract class Cell {

    private int cellID;
    private Color cellColor;

    //all the players in this cell
    private ArrayList<Player> cellPlayers;
    //store the adjacent cells in a sequence of up, right, down, left.
    private Cell[] adjacentCells;
    //store adjacent cells through doors
    private Cell[] CellsThroDoor;

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
   
    public void setCellsThroDoor(){

    }
    
    //change players inside a cell
    public void addPlayer(Player player){
        this.cellPlayers.add(player);
    }
    public void removePlayer(Player player){
        this.cellPlayers.remove(player);
    }

    //get values of cell

    public Color getCellColor() {
        return this.cellColor;
    }
    public ArrayList<Player> getCellPlayers(){
        return this.cellPlayers;
    }
    public Cell[] getAdjacentCells(){
        return this.adjacentCells;
    }
    
    //get the cell from a specific direction
    public Cell getNextCell(String direction) {
        switch(direction) {
            case "up":
                return this.adjacentCells[0];
            case "right":
                return this.adjacentCells[1];
            case "down":
                return this.adjacentCells[2];
            case "left":
                return this.adjacentCells[3];
            default:
                return null; //better solution??
        }
    }
}