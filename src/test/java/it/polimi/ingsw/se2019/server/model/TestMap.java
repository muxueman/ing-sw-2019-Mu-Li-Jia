package it.polimi.ingsw.se2019.server.model;

import org.junit.Test;
import static org.junit.Assert.*;


import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapB;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.CommonCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;

/**
 *  test 4 types of maps,and the cells inside them
 *  @author Xueman Mu
 */

public class TestMap {
    @Test
    public void test(){
        Map testMapA = new MapA();
        Map testMapB = new MapB();
        Map testMapC = new MapC();
        Map testMapD = new MapD();
    }
}
