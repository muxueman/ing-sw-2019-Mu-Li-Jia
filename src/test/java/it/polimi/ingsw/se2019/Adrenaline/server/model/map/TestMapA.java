package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;


import static org.junit.Assert.*;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import java.util.ArrayList;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapA {
    private MapA mapA;
    ArrayList<Cell> cellA;
    Cell cellX;
    Cell cellY;
    Cell cellZ;

    @Before
    public void setUp() {
        mapA = new MapA();
        cellA = mapA.getAllCells();
        cellX = cellA.get(5);
        cellY = cellA.get(1);
        cellZ = cellA.get(2);
    }

    @Test

    public void test() {

        cellX.getCellColor();
        mapA.checkDoor(cellX, cellY);
        mapA.getMapInfo();
        assertTrue(mapA.checkWithinRoom(cellY, cellZ));
        assertTrue(!mapA.checkWithinRoom(cellX, cellZ));
        assertTrue(mapA.checkDoor(cellY,cellX));
        mapA.getCellsWithinRoom(cellX);
        assertTrue(mapA.getCellsWithinRoom(Color.RED).contains(cellX));
        assertTrue(!mapA.getCellsWithinRoom(Color.RED).contains(cellY));
        assertTrue(mapA.getAllVisibleCells(cellX).contains(cellY));
        mapA.getAllCells();
        mapA.getCellsWithinRoom(Color.BLUE);
        mapA.getCellsWithinRoom(Color.GREEN);
        mapA.getCellsWithinRoom(Color.WHITE);
        mapA.getAllVisibleCells(cellX);
        mapA.getVisibleTwoAwayCells(cellX);
        mapA.getVisibleRoomWithoutYourRoom(cellX);
        mapA.getDirectionFromCellToCell(cellX,cellY);
        mapA.checkAllCardinalCells(cellX,cellY,0);
        mapA.checkAvailableOneWalkCell(cellX,cellY);
        
        assertTrue(mapA.getCardinalTwoCells(cellX,0).contains(cellY));
        assertTrue(mapA.checkWithinRoom(mapA.getAllCells().get(1),mapA.getAllCells().get(2)));

    }

    @After
    public void tearDown() {
        mapA = null;
        cellA = null;
        cellX = null;
        cellY = null;
    }

}
