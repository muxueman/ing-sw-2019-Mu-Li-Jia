package it.polimi.ingsw.se2019.Adrenaline.server.model;
import java.util.ArrayList;
/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */
public class MapD extends Map{

    
    //inital information for Map4
    //better for 4 or 5 players
    private void initialMap(){
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
}