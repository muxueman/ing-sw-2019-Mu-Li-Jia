package it.polimi.ingsw.se2019.server.model;

import org.junit.Test;
import static org.junit.Assert.*;


import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

import java.util.ArrayList;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapA {
    @Test
    public void test(){
        Map testMapA = new MapA();
        testMapA.initialMap();
        System.out.println(testMapA.getMapInfo());
        System.out.println(testMapA.getCellsWithinRoom(Color.RED));
        System.out.println(testMapA.getCellsWithinRoom(Color.YELLOW));
        System.out.println(testMapA.getCellsWithinRoom(Color.BLUE));
        System.out.println(testMapA.getCellsWithinRoom(Color.PINK));
        System.out.println(testMapA.getCellsWithinRoom(Color.GREEN));
        System.out.println(testMapA.getCellsWithinRoom(Color.WHITE));
        ArrayList<Cell> cellA = testMapA.getAllCells();
        Cell cellX = cellA.get(4);
        Cell cellY = cellA.get(1);
        System.out.println(testMapA.checkWithinRoom(testMapA.getAllCells().get(1),testMapA.getAllCells().get(2)));
        System.out.println(testMapA.checkWithinRoom(testMapA.getAllCells().get(3),testMapA.getAllCells().get(4)));
        System.out.println(testMapA.getCellsWithinRoom(cellX));
        System.out.println((testMapA.getAllVisibleCells(cellY)));




    }
}
