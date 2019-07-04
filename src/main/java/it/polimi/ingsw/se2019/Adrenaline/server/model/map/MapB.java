package it.polimi.ingsw.se2019.Adrenaline.server.model.map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

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
    @Override
    public void initialMap(){
        this.totalCellNum = 11;
        this.mapInfo = "Great for 3~5 players!";
        this.allCells = new ArrayList<Cell>();

        Cell cell0 = new CommonCell(0);
        allCells.add(cell0);
        REDCells.add(cell0);
        
        Cell cell1 = new CommonCell(1);
        cell1.setCellColor(Color.RED);
        allCells.add(cell1);
        REDCells.add(cell1);

        Cell cell2 = new CommonCell(2);
        cell2.setCellColor(Color.BLUE);
        allCells.add(cell2);
        BLUECells.add(cell2);

        Cell cell3 = new GenerationCell(3);
        cell3.setCellColor(Color.BLUE);
        allCells.add(cell3);
        BLUECells.add(cell3);

        Cell cell4 = new CommonCell(0);
        allCells.add(cell4);

        Cell cell5 = new GenerationCell(5);
        cell5.setCellColor(Color.RED);
        allCells.add(cell5);
        REDCells.add(cell5);

        Cell cell6 = new CommonCell(6);
        cell6.setCellColor(Color.PINK);
        allCells.add(cell6);
        PINKCells.add(cell6);

        Cell cell7 = new CommonCell(7);
        cell7.setCellColor(Color.PINK);
        allCells.add(cell7);
        PINKCells.add(cell7);

        Cell cell8 = new CommonCell(8);
        cell8.setCellColor(Color.YELLOW);
        allCells.add(cell8);
        YELLOWCells.add(cell8);

        Cell cell9 = new CommonCell(9);
        cell9.setCellColor(Color.WHITE);
        allCells.add(cell9);
        WHITECells.add(cell9);

        Cell cell10 = new CommonCell(10);
        cell10.setCellColor(Color.WHITE);
        allCells.add(cell10);
        WHITECells.add(cell10);

        Cell cell11 = new CommonCell(11);
        cell11.setCellColor(Color.WHITE);
        allCells.add(cell11);
        WHITECells.add(cell11);

        Cell cell12 = new GenerationCell(12);
        cell12.setCellColor(Color.YELLOW);
        allCells.add(cell12);
        YELLOWCells.add(cell12);

        setAdjacentCells(0, 0, 0, 0, cell0);
        setAdjacentCells(0, 2, 5, 0, cell1);
        setAdjacentCells(0, 3, 6, 1, cell2);
        setAdjacentCells(0, 0, 7, 2, cell3);
        setAdjacentCells(1, 6, 9, 0, cell5);
        setAdjacentCells(2, 7, 10, 5, cell6);
        setAdjacentCells(3, 8, 11, 6, cell7);
        setAdjacentCells(0, 0, 12, 7, cell8);
        setAdjacentCells(5, 10, 0, 0, cell9);
        setAdjacentCells(6, 11, 0, 9, cell10);
        setAdjacentCells(7, 12, 0, 10, cell11);
        setAdjacentCells(8, 0, 0, 11, cell12);

        this.notDoor = new int[][] {{5,6},{7,11}};
    }
    @Override
    public Ansi toAnsi(){
        return ansi().a("Map cells:" + allCells.toString());
    }
    @Override
    public String toString(){
        return "map cells:" + allCells.toString() ;
    }
}