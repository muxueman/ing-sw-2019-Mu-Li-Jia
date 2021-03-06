package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import org.junit.Test;

import java.util.*;

import java.util.HashMap;

public class TestPlayerBoard {
it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map map;
    Board testBoard;
    Player testShooter;
    Player testPlayer;
    Player testPlayer2;
    PlayerBoard testKillShootTrack;
    PlayerBoard testKillShootTrack2;

    public void setTestInfo() {
        map = new MapA();
        testShooter = new Player("JIA");
        testPlayer = new Player("MO");
        testPlayer2 = new Player("XIN");
        testBoard = new Board(map, 5,2);
        testKillShootTrack = testPlayer.getKillShootTrack();
        testKillShootTrack2 = testPlayer2.getKillShootTrack();
        testShooter.setPlayerColor(Color.RED);
        testPlayer.setPlayerColor(Color.WHITE);
        testPlayer2.setPlayerColor(Color.GREEN);
        testPlayer2.setAlive(false);
    }

    @Test
    public void testBeAttacked() {
        setTestInfo();
        testPlayer.beAttacked(testShooter, 3, 2);
        testPlayer.getKillShootTrack().beAttacked(testPlayer2, 3, 2);
        System.out.println(testPlayer.getKillShootTrack().getDamageColorOnTrack().toString());
        testPlayer.changeActionMode();
        testPlayer.getKillShootTrack().beAttacked(testShooter, 2, 2);
        System.out.println(testPlayer.getKillShootTrack().getDamageColorOnTrack().toString());
        testPlayer.changeActionMode();
        System.out.println(testPlayer.getActionMode());
        int beAttacked = testPlayer.getKillShootTrack().beAttacked(testShooter, 2, 2);
        testPlayer.getKillShootTrack();
        testPlayer.getKillShootTrack().addPlayerScore(testShooter);
        System.out.println("beAttacked" + beAttacked);
        System.out.println(testPlayer.getKillShootTrack().getDamageColorOnTrack().toString());
        System.out.println(testPlayer.getKillShootTrack().getMarkColorOnTrack().toString());
        //testPlayer.getKillShootTrack().beAttacked(testShooter,4,2);
        testPlayer.getKillShootTrack().recover();
        testPlayer.getKillShootTrack().getNumKillShoot();
    }

    @Test
    public void testKilled() {
        setTestInfo();
//        System.out.println("highest score"+testPlayer.getKillShootTrack().checkHighestScore());
//        System.out.println("countPlayerScore"+testPlayer.getKillShootTrack().countPlayerScore());
        testPlayer.beAttacked(testShooter, 4, 2);
        testPlayer.beAttacked(testPlayer2, 2, 2);
        testPlayer.beAttacked(testShooter, 3, 2);
        System.out.println(testPlayer.getKillShootTrack().getPlayerScore());
        testPlayer.getKillShootTrack().recover();
        testPlayer.beAttacked(testPlayer2, 3, 2);
//        System.out.println("highest score"+testPlayer.getKillShootTrack().checkHighestScore());
//        System.out.println("countPlayerScore"+testPlayer.getKillShootTrack().countPlayerScore());
//        System.out.println(testPlayer.getKillShootTrack().getNumKillShoot());
        System.out.println(testPlayer.getKillShootTrack().getPlayerScore());
//        System.out.println(testPlayer.getKillShootTrack().toString());
//        System.out.println(testPlayer.toString());
    }


    public void getNumKillShoot() {
        testKillShootTrack.getNumKillShoot();
    }

    public void getBeKilled() {
        testKillShootTrack.getBeKilled();
    }

    public void addMarkToDamage() {
        testKillShootTrack.addMarkToDamage(testShooter);
    }

//    @Test
//    public void testSet() {
////        testKillShootTrack.setPlayerScore(testKillShootTrack.getPlayerScore());
////        testKillShootTrack.setPlayer(testPlayer);
//        testKillShootTrack.setBeKilled(2);
//        testKillShootTrack.setDamageColorOnTrack(testKillShootTrack.getDamageColorOnTrack());
//        testKillShootTrack.setMarkColorOnTrack(testKillShootTrack.getMarkColorOnTrack());
//        testKillShootTrack.setTurn(3);
//
//    }

    public Map valueOfMapDownSort(Map inputMap) {
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(inputMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (-(o1.getValue().compareTo(o2.getValue())));
            }
        });
        inputMap.clear();
        LinkedHashMap outMap = new LinkedHashMap();
        System.out.println(infoIds);
        for (Map.Entry<String, Integer> mapping : infoIds) {
            outMap.put(mapping.getKey(), mapping.getValue());
        }
        return outMap;
    }

    @Test
    public void testSort() {
        Map testSortMap = new HashMap<>();
        testSortMap.put(Color.WHITE, 20);
        testSortMap.put(Color.RED, 10);
        testSortMap.put(Color.YELLOW, 30);
        testSortMap.put(Color.GREEN, 60);
        System.out.println(testSortMap);
        System.out.println(valueOfMapDownSort(testSortMap));
//        System.out.println(testPlayer.getKillShootTrack().valueOfMapDownSort(testSortMap));

    }

    @Test
    public void getDamageColorOnTrack() {
//        testKillShootTrack.toString();
    }


}
