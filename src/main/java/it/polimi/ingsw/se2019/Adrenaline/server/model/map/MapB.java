package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

import java.util.ArrayList;

/**
 *MapA better for 3, 4 or 5 players
 *@author Xueman Mu
 */

public class MapB extends Map{

    //constructor
    public MapB (){
        super();
    }

    //inital information for Map
    public void initialMap(){
        this.totalCellNum = 11;
        this.mapInfo = "Great for 3~5 players!";
        this.allCells = new ArrayList<Cell>();
        
        Cell cell1 = new CommonCell(1);
        cell1.setCellColor(Color.RED);
        allCells.add(cell1);
        REDCells.add(cell1);

        Cell cell2 = new CommonCell(2);
        cell2.setCellColor(Color.BLUE);
        setAdjacentCells(0, 1, 5, 1, cell2);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new GenerationCell(3);
        cell3.setCellColor(Color.BLUE);
        allCells.add(cell3);
        BLUECells.add(cell3);

        Cell cell4 = new GenerationCell(4);
        cell4.setCellColor(Color.BLUE);
        allCells.add(cell4);
        REDCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor(Color.PINK);
        allCells.add(cell5);
        PINKCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor(Color.PINK);
        allCells.add(cell6);
        PINKCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor(Color.YELLOW);
        allCells.add(cell7);
        YELLOWCells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor(Color.WHITE);
        allCells.add(cell8);
        WHITECells.add(cell8);

        Cell cell9 = new CommonCell(9);
        cell9.setCellColor(Color.WHITE);
        allCells.add(cell9);
        WHITECells.add(cell9);

        Cell cell10 = new CommonCell(10);
        cell10.setCellColor(Color.WHITE);
        allCells.add(cell10);
        WHITECells.add(cell10);

        Cell cell11 = new GenerationCell(11);
        cell11.setCellColor(Color.YELLOW);
        allCells.add(cell11);
        YELLOWCells.add(cell11);

        setAdjacentCells(0, 2, 4, 0, cell1);
        setAdjacentCells(0, 0, 6, 2, cell3);
        setAdjacentCells(1, 5, 8, 0, cell4);
        setAdjacentCells(2, 6, 9, 4, cell5);
        setAdjacentCells(3, 7, 10, 5, cell6);
        setAdjacentCells(0, 0, 11, 6, cell7);
        setAdjacentCells(4, 9, 0, 0, cell8);
        setAdjacentCells(5, 10, 0, 8, cell9);
        setAdjacentCells(6, 11, 0, 9, cell10);
        setAdjacentCells(7, 0, 0, 10, cell11);

        this.notDoor = new int[][] {{4,5},{6,10}};
    }    

}