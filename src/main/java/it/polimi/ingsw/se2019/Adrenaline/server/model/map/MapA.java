package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import java.util.ArrayList;

/**
 *MapA better for 3 or 4 players
 *@author Xueman Mu
 */

public class MapA extends Map{

    //constructor
    public MapA (){
        super();
    }

    //inital information for Map
    //@override
    public void initialMap(){
        this.totalCellNum = 10;
        this.mapInfo = "Great for 3 or 4 players!";
        this.allCells = new ArrayList<Cell>();
                
        Cell cell1 = new CommonCell(1);
        cell1.setCellColor(Color.BLUE);
        allCells.add(cell1);
        BLUECells.add(cell1);

        Cell cell2 = new CommonCell(2);
        cell2.setCellColor(Color.BLUE);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new GenerationCell(3);
        cell3.setCellColor(Color.BLUE);
        allCells.add(cell3);
        BLUECells.add(cell3);

        Cell cell4 = new GenerationCell(4);
        cell4.setCellColor(Color.RED);
        allCells.add(cell4);
        REDCells.add(cell4);

        Cell cell5 = new CommonCell(5);
        cell5.setCellColor(Color.RED);
        allCells.add(cell5);
        REDCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor(Color.RED);
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

        Cell cell10 = new GenerationCell(10);
        cell10.setCellColor(Color.YELLOW);
        allCells.add(cell10);
        YELLOWCells.add(cell10);


        setAdjacentCells(0, 2, 4, 0, cell1);
        setAdjacentCells(0, 3, 5, 1, cell2);
        setAdjacentCells(0, 0, 6, 2, cell3);
        setAdjacentCells(1, 5, 0, 0, cell4);
        setAdjacentCells(2, 6, 8, 4, cell5);
        setAdjacentCells(3, 7, 9, 5, cell6);
        setAdjacentCells(0, 0, 10, 6, cell7);
        setAdjacentCells(5, 9, 0, 0, cell8);
        setAdjacentCells(6, 10, 0, 8, cell9);
        setAdjacentCells(7, 0, 0, 9, cell10);

        this.notDoor = new int[][]{{2,5}, {6,9}};
    }

}