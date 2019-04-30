package it.polimi.ingsw.se2019.Adrenaline.server.model;
import java.util.ArrayList;
/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */
public class MapB extends Map{
    
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

}