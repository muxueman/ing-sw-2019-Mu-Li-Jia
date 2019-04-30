package it.polimi.ingsw.se2019.Adrenaline.server.model;
import java.util.ArrayList;

/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */

public abstract class Map {

    private ArrayList<Cell> allCells;
    private int totalCellNum;
    private String mapInfo;
    private ArrayList<Cell> YELLOWCells;
    private ArrayList<Cell> REDCells;
    private ArrayList<Cell> GREENCells;
    private ArrayList<Cell> BLUECells;
    private ArrayList<Cell> WHITECells;
    private ArrayList<Cell> PINKCells;

    //constructor
    public Map(int mapType) {
        switch (mapType) {
            case 1:
                initialMap1();
                break;
            case 2:
                initialMap2();
                break;
            case 3:
                initialMap3();
                break;
            case 4:
                initialMap4();
                break;
            default:
                System.out.println("error");
        }
    }


    //abstract method to initial different kind of map
    public abstract void initialMap();


    //set adjacent cells for a specific cell
    public void setAdjacentCells(int up, int right, int down, int left, Cell cellx){
        cellx.adjacentCells = new Cell[4];
        cellx.adjacentCells[0] = this.allCells.get(up);
        cellx.adjacentCells[1] = this.allCells.get(right);
        cellx.adjacentCells[2] = this.allCells.get(down);
        cellx.adjacentCells[3] = this.allCells.get(left);
    }


    //get map info
    public String getMapInfo(){
        return this.mapInfo;
    }   

    //comparison of cells, same room/color
    public Boolean checkWithinRoom(Cell cella, Cell cellb){
        return (cella.color == cellb.color);           
    }
    //@requires not the same color
    public Boolean checkDoor(Cell cella, Cell cellb){

    }

    //get cells within rooms, which means all cells from same color
    public Cell getCellsWithinRoom(Cell cellx){
        switch(cellx.color){
            case YELLOW:
               return YELLOWCells;
            case GREEN:
               return GREENCells;
            case BLUE:
               return BLUECells;
            case PINK: 
               return PINKCells;
            case RED:
               return REDCells;
            case WHITE:
               return WHITECells;               
        }
   }

   //get cells from door
   public Cell getCellsThroDoor(Cell cellx){
       return cellx;
   }

   //get all visible cells from one cell
   public ArrayList<Cell> getAllVisibleCells(Cell cellx){
       ArrayList<Cell> allVisibleCells = new ArrayList<Cell>();
       allVisibleCells.add(getCellsWithinRoom(cellx));
       allVisibleCells.add(getCellsThroDoor(cellx));
       //not finished
       return allVisibleCells;


   }

}
