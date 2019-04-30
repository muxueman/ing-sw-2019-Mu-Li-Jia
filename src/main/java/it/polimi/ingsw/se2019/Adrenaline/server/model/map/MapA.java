package it.polimi.ingsw.se2019.Adrenaline.server.model.Map;
import java.util.ArrayList;
import
/**
 *4 types of maps, a map contains several rooms, a room contains several cells
 *@author Xueman Mu
 */
public class MapA extends Map{




    //inital information for Map1
    //better for 3 or 4 players
    private void initialMap(){
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

}