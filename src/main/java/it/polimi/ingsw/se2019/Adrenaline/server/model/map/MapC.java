package it.polimi.ingsw.se2019.Adrenaline.server.model;
import java.util.ArrayList;
/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */
public class MapC extends Map{

    

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

}