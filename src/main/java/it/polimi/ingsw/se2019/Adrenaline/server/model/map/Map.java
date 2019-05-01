package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import java.util.ArrayList;

/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */

public abstract class Map {

    protected ArrayList<Cell> allCells;
    protected int totalCellNum;
    protected String mapInfo;
    protected ArrayList<Cell> YELLOWCells;
    protected ArrayList<Cell> REDCells;
    protected ArrayList<Cell> GREENCells;
    protected ArrayList<Cell> BLUECells;
    protected ArrayList<Cell> WHITECells;
    protected ArrayList<Cell> PINKCells;

    //constructor
    public Map(){
        initialMap();
    }

    /**
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
     */

    //@override
    //abstract method to initial different kind of map
    public abstract void initialMap();

    //@overload
    //set adjacent cells for a specific cell
    public void setAdjacentCells(int up, int right, int down, int left, Cell cell){
        cell.setAdjacentCells(allCells.get(up), allCells.get(right), allCells.get(down), allCells.get(left));
    }

    //get map info
    public String getMapInfo(){
        return this.mapInfo;
    }   

    //comparison of cells, same room/color
    public Boolean checkWithinRoom(Cell cella, Cell cellb){
        return (cella.getCellColor() == cellb.getCellColor());
    }
    //@requires not the same color
    public Boolean checkDoor(Cell cella, Cell cellb){

    }

    //get cells within rooms, which means all cells from same color
    public ArrayList<Cell> getCellsWithinRoom(Cell cellx){
        switch(cellx.getCellColor()){
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
            default:
                return null; // better solution??
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
