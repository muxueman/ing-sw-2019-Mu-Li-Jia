package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import org.junit.Test;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;


import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

import java.util.ArrayList;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapA {

    Map testMapA = new MapA();

    @Test
    public void test() {
        testMapA.initialMap();
        ArrayList<Cell> cellA = testMapA.getAllCells();
        Cell cellX = cellA.get(4);
        Cell cellY = cellA.get(1);
        Cell cellZ = cellA.get(2);

        testMapA.checkDoor(cellX, cellY);
        testMapA.getCellsWithinRoom(cellX);
        testMapA.getAllVisibleCells(cellX);
        testMapA.getAllCells();
        testMapA.getCellsWithinRoom(Color.RED);
        testMapA.getCellsWithinRoom(Color.YELLOW);
        testMapA.getCellsWithinRoom(Color.BLUE);
        testMapA.getCellsWithinRoom(Color.GREEN);
        testMapA.getCellsWithinRoom(Color.WHITE);
    }
}
