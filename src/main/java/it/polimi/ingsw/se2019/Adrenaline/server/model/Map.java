package it.polimi.ingsw.se2019.Adrenaline.server.model;
import java.util.ArrayList;

/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */

public class Map {

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
}

    //inital information for Map1
    //better for 3 or 4 players
    private void initialMap1(){
        this.totalCellNum = 10;
        this.mapInfo = "Great for 3 or 4 players!";
        this.allCells = new ArrayList<Cell>();
                
        Cell cell0 = new CommonCell(0);
        cell0.setCellColor("BLUE");
        cell0.setAdjacentCells(null, 1, 3,null);
        allCells.add(cell0);
        BLUECells.add(cell0);

        Cell cell1 = new CommonCell(1);
        cell1.setCellColor("BLUE");
        cell1.setAdjacentCells(null, 2, 4, 0);
        allCells.add(cell1);
        BLUECells.add(cell1);

        Cell cell2 = new GenerationCell(2);
        cell2.setCellColor("BLUE");
        cell2.setAdjacentCells(null, null, 5, 1);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new GenerationCell(3);
        cell3.setCellColor("RED");
        cell3.setAdjacentCells(0, 4, null, null);
        allCells.add(cell3);
        REDCells.add(cell3);

        Cell cell4 = new CommonCell(4);
        cell4.setCellColor("RED");
        cell4.setAdjacentCells(1, 5, 7, 3);
        allCells.add(cell4);
        REDCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor("PINK");
        cell5.setAdjacentCells(2, 6, 8, 4);
        allCells.add(cell5);
        PINKCells.add(cell3);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor("YELLOW");
        cell6.setAdjacentCells(null, null, 9, 5);
        allCells.add(cell6);
        YELLOWCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor("WHITE");
        cell7.setAdjacentCells(4, 8, null, null);
        allCells.add(cell7);
        WHITECells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor("WHITE");
        cell8.setAdjacentCells(5, 9, null, 7);
        allCells.add(cell8);
        WHITECells.add(cell8);

        Cell cell9 = new GenerationCell(9);
        cell9.setCellColor("YELLOW");
        cell9.setAdjacentCells(6, null, null, 8);
        allCells.add(cell9);
        YELLOWCells.add(cell9);

    }

    //inital information for Map2
    //better for 3, 4 or 5 players
    private void initialMap2(){
        this.totalCellNum = 11;
        this.mapInfo = "Great for 3~5 players!";
        this.allCells = new ArrayList<Cell>();
        
        Cell cell0 = new CommonCell(0);
        cell0.setCellColor("RED");
        cell0.setAdjacentCells(null, 1, 3, null);
        allCells.add(cell0);
        REDCells.add(cell0);

        Cell cell1 = new CommonCell(1);
        cell1.setCellColor("BLUE");
        cell1.setAdjacentCells(null, 2, 4, 0);
        allCells.add(cell1);
        BLUECells.add(cell1);

        Cell cell2 = new GenerationCell(2);
        cell2.setCellColor("BLUE");
        cell2.setAdjacentCells(null, null, 5, 1);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new GenerationCell(3);
        cell3.setCellColor("RED");
        cell3.setAdjacentCells(0, 4, 7, null);
        allCells.add(cell3);
        REDCells.add(cell3);

        Cell cell4 = new CommonCell(4);
        cell4.setCellColor("PINK");
        cell4.setAdjacentCells(1, 5, 8, 3);
        allCells.add(cell4);
        PINKCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor("PINK");
        cell5.setAdjacentCells(2, 6, 9, 4);
        allCells.add(cell5);
        PINKCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor("YELLOW");
        cell6.setAdjacentCells(null, null, 10, 5);
        allCells.add(cell6);
        YELLOWCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor("WHITE");
        cell7.setAdjacentCells(3, 8, null, null);
        allCells.add(cell7);
        WHITECells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor("WHITE");
        cell8.setAdjacentCells(4, 9, null, 7);
        allCells.add(cell8);
        WHITECells.add(cell8);

        Cell cell9 = new CommonCell(9);
        cell9.setCellColor("WHITE");
        cell9.setAdjacentCells(5, 10, null, 8);
        allCells.add(cell9);
        WHITECells.add(cell9);

        Cell cell10 = new GenerationCell(10);
        cell10.setCellColor("YELLOW");
        cell10.setAdjacentCells(6, null, null, 9);
        allCells.add(cell10); 
        YELLOWCells.add(cell10);
    }    

    //inital information for Map3
    //better for 3, 4 or 5 players
    private void initialMap3(){
        this.totalCellNum = 11;
        this.mapInfo = "Great for 3~5 players!";
        this.allCells = new ArrayList<Cell>();
        
        Cell cell0 = new CommonCell(0);
        cell0.setCellColor("BLUE");
        cell0.setAdjacentCells(NULL, 1, 4, NULL);
        allCells.add(cell0);
        BLUECells.add(cell0);

        Cell cell1 = new CommonCell(1);
        cell1.setCellColor("BLUE");
        cell1.setAdjacentCells(NULL, 2, 5, 0);
        allCells.add(cell1);
        BLUECells.add(cell1);

        Cell cell2 = new GenerationCell(2);
        cell2.setCellColor("BLUE");
        cell2.setAdjacentCells(NULL, 3, 6, 1);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new CommonCell(3);
        cell3.setCellColor("GREEN");
        cell3.setAdjacentCells(null, null, 7, 2);
        allCells.add(cell3);
        GREENCells.add(cell3);

        Cell cell4 = new GenerationCell(4);
        cell4.setCellColor("RED");
        cell4.setAdjacentCells(0, 5, null, null);
        allCells.add(cell4);
        REDCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor("RED");
        cell5.setAdjacentCells(1, 6, 8, 4);
        allCells.add(cell5);
        REDCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor("YELLOW");
        cell6.setAdjacentCells(2, 7, 9, 5);
        allCells.add(cell6);
        YELLOWCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor("YELLOW");
        cell7.setAdjacentCells(3, null, 10, 6);
        allCells.add(cell7);
        YELLOWCells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor("WHITE");
        cell8.setAdjacentCells(5, 9, null, null);
        allCells.add(cell8);
        WHITECells.add(cell8);

        Cell cell9 = new CommonCell(9);
        cell9.setCellColor("YELLOW");
        cell9.setAdjacentCells(6, 10, null, 8);
        allCells.add(cell9);
        YELLOWCells.add(cell9);

        Cell cell10 = new GenerationCell(10);
        cell10.setCellColor("YELLOW");
        cell10.setAdjacentCells(7, null, null, 9);
        allCells.add(cell10);
        YELLOWCells.add(cell10);
    } 

    //inital information for Map4
    //better for 4 or 5 players
    private void initialMap4(){
        this.totalCellNum = 12;
        this.mapInfo = "Great for 4 or 5 players!";
        this.allCells = new ArrayList<Cell>();
        
        Cell cell0 = new CommonCell(0);
        cell0.setCellColor("RED");
        cell0.setAdjacentCells(null, 1, 4, null);
        allCells.add(cell0);
        REDCells.add(cell0);

        Cell cell1 = new CommonCell(1);
        cell1.setCellColor("BLUE");
        cell1.setAdjacentCells(null, 2, 5, 0);
        allCells.add(cell1);
        BLUECells.add(cell2);

        Cell cell2 = new GenerationCell(2);
        cell2.setCellColor("BLUE");
        cell2.setAdjacentCells(null, 3, 6, 1);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new CommonCell(3);
        cell3.setCellColor("RED");
        cell3.setAdjacentCells(null, null, 7, 2);
        allCells.add(cell3);
        REDCells.add(cell3);

        Cell cell4 = new GenerationCell(4);
        cell4.setCellColor("RED");
        cell4.setAdjacentCells(0, 5, 8, null);
        allCells.add(cell4);
        REDCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor("PINK");
        cell5.setAdjacentCells(1, 6, 9, 4);
        allCells.add(cell5);
        PINKCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor("YELLOW");
        cell6.setAdjacentCells(2, 7, 10, 5);
        allCells.add(cell6);
        YELLOWCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor("YELLOW");
        cell7.setAdjacentCells(3, null, 11, 6);
        allCells.add(cell7);
        YELLOWCells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor("WHITE");
        cell8.setAdjacentCells(4, 9, null, null);
        allCells.add(cell8);
        WHITECells.add(cell8);

        Cell cell9 = new CommonCell(9);
        cell9.setCellColor("WHITE");
        cell9.setAdjacentCells(5, 10, null, 8);
        allCells.add(cell9);
        WHITECells.add(cell9);

        Cell cell10 = new CommonCell(10);
        cell10.setCellColor("YELLOW");
        cell10.setAdjacentCells(6, 11, null, 9);
        allCells.add(cell10); 
        YELLOWCells.add(cell10);

        Cell cell11 = new GenerationCell(11);
        cell11.setCellColor("YELLOW");
        cell11.setAdjacentCells(7, null, null, 10);
        allCells.add(cell11); 
        YELLOWCells.add(cell11);
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
