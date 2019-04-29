package it.polimi.ingsw.se2019.Adrenaline.Model;
import java.util.ArrayList;
/**
 *super class Cell
 *@author Xueman Mu
 */
public class Cell {

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
    public void setAdjacentCells(int up, int right, int down, int left){
        this.adjacentCells = new Cell[4];
        this.adjacentCells[0] = map.allCells.get(up);
        this.adjacentCells[1] = map.allCells.get(right);
        this.adjacentCells[2] = map.allCells.get(down);
        this.adjacentCells[3] = map.allCells.get(left);
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
        return this.color;
    }
    public Player getCellPlayers(){
        return this.cellPlayers;
    }
    public Cell getAdjacentCells(){
        return this.adjacentCells;
    }
    
    //get the cell from a specific direction
    public Cell getNextCell(String direction) {
        switch(direction){
            case "up": 
               return this.adjacentCells[0];
            case "right":
               return this.adjacentCells[1];
            case "down": 
               return this.adjacentCells[2];
            case "left": 
               return this.adjacentCells[2];
        }
    }

}