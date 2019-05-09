package it.polimi.ingsw.se2019.server.model.map;

import org.junit.Test;
import static org.junit.Assert.*;


import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapD {
    @Test
    public void test(){
        Map testMapD = new MapD();
        testMapD.initialMap();
        System.out.println(testMapD.getMapInfo());

    }
}
