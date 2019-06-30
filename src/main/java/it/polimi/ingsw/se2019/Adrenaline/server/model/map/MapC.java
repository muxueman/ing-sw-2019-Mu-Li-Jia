package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

import java.util.ArrayList;

/**
 *MapC better for 3, 4 or 5 players
 *@author Xueman Mu
 */
public class MapC extends Map{

    //constructor
    public MapC (){
        super();
    }

    //initial information for Map
    @Override
    public void initialMap(){
        this.totalCellNum = 11;
        this.mapInfo = "Great for 3~5 players!";
        this.allCells = new ArrayList<Cell>();

        Cell cell0 = new CommonCell(0);
        allCells.add(cell0);
        REDCells.add(cell0);

        Cell cell1 = new CommonCell(1);
        cell1.setCellColor(Color.BLUE);
        allCells.add(cell1);
        BLUECells.add(cell1);

        Cell cell2 = new CommonCell(2);
        cell2.setCellColor(Color.BLUE);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3= new GenerationCell(3);
        cell3.setCellColor(Color.BLUE);
        allCells.add(cell3);
        BLUECells.add(cell3);

        Cell cell4 = new CommonCell(4);
        cell4.setCellColor(Color.GREEN);
        allCells.add(cell4);
        GREENCells.add(cell4);

        Cell cell5 = new GenerationCell(5);
        cell5.setCellColor(Color.RED);
        allCells.add(cell5);
        REDCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor(Color.RED);
        allCells.add(cell6);
        REDCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor(Color.YELLOW);
        allCells.add(cell7);
        YELLOWCells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor(Color.YELLOW);
        allCells.add(cell8);
        YELLOWCells.add(cell8);

        Cell cell9 = new CommonCell(0);
        allCells.add(cell9);

        Cell cell10 = new CommonCell(10);
        cell10.setCellColor(Color.WHITE);
        allCells.add(cell10);
        WHITECells.add(cell10);

        Cell cell11 = new CommonCell(11);
        cell11.setCellColor(Color.YELLOW);
        allCells.add(cell10);
        YELLOWCells.add(cell10);

        Cell cell12 = new GenerationCell(12);
        cell12.setCellColor(Color.YELLOW);
        allCells.add(cell12);
        YELLOWCells.add(cell12);

        setAdjacentCells(0, 0, 0, 0, cell0);
        setAdjacentCells(0, 2, 5, 0, cell1);
        setAdjacentCells(0, 3, 6, 1, cell2);
        setAdjacentCells(0, 4, 7, 2, cell3);
        setAdjacentCells(0, 0, 8, 3, cell4);
        setAdjacentCells(1, 6, 0, 0, cell5);
        setAdjacentCells(2, 7, 10, 5, cell6);
        setAdjacentCells(3, 8, 11, 6, cell7);
        setAdjacentCells(4, 0, 12, 7, cell8);
        setAdjacentCells(6, 11, 0, 0, cell10);
        setAdjacentCells(7, 12, 0, 10, cell11);
        setAdjacentCells(8, 0, 0, 11, cell12);

        this.notDoor = new int[][]{{2,6}, {6,7}};
    } 

}