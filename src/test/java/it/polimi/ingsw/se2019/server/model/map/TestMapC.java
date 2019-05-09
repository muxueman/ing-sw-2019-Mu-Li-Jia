package it.polimi.ingsw.se2019.server.model.map;

import org.junit.Test;
import static org.junit.Assert.*;


import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMapC {
    @Test
    public void test(){
        Map testMapC = new MapC();
        testMapC.initialMap();
        System.out.println(testMapC.getMapInfo());

    }
}
