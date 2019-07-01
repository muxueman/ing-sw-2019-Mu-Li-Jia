package it.polimi.ingsw.se2019.Adrenaline.utils;

import it.polimi.ingsw.se2019.Adrenaline.server.Server;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestBoardStatus {
    @Test
    public void MapPutTest() {
        Map<Integer, Integer> maptest = new HashMap<>();
        maptest.put(1, 2);
        maptest.put(1, 3);
        maptest.put(2, 5);
        System.out.println(maptest);
    }
}