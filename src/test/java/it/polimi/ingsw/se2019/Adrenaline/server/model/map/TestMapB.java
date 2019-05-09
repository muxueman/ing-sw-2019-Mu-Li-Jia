package it.polimi.ingsw.se2019.Adrenaline.server.model.map;

import org.junit.Test;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapB {
    @Test
    public void test(){
        Map testMapB = new MapB();
        testMapB.initialMap();
        System.out.println(testMapB.getMapInfo());

    }
}
