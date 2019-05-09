package it.polimi.ingsw.se2019.Adrenaline.server.model;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//import sun.jvm.hotspot.debugger.win32.coff.TestParser;

import static org.junit.Assert.*;

public class TestPlayer {

    Player testPlayer = new Player();
    KillShootTrack testKillShootTrack = new KillShootTrack(testPlayer);
    PlayBoard testPlayBoard = new PlayBoard(5);
    int testMode = 2;

    public void testName() {
        testPlayer.setName("jiamoxin");
        System.out.println("test nickname: " + testPlayer.getNickName());
    }

    public void testPlayerColor() {
       testPlayer.setPlayerColor(Color.RED);
       System.out.println("test player color: " + testPlayer.getPlayerColor());
    }

    public void testActionMode() {
        System.out.println("initial actionmode: " + testPlayer.getActionMode());
        testPlayer.setActionMode(testMode);
        System.out.println("test action mode：" + testPlayer.getActionMode());
        testPlayer.changeActionMode();
    }

    public void testCardsOwned(){
       testPlayer.getPowerupsOwned();
       testPlayer.getAmmoOwned();
       testPlayer.getPowerupsOwned();
    }

    public void testAmmoOwned(){
        System.out.println("initial ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.consumeAmmo(AmmoColor.RED, 2);
        System.out.println("after comsume ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.consumeAmmo(AmmoColor.RED, 2);
        System.out.println("after comsume ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.fillAmmo(AmmoColor.RED, 1);
        System.out.println("after fill ammo owned: \n" + testPlayer.ammoOwnedToString());

    }

    @Test
    public void testPlayerInfo(){
       testName();
       testPlayerColor();
       System.out.println(testPlayer.toString());
       testActionMode();
       testAmmoOwned();
       testCardsOwned();
    }

    @Test
    public void testScore(){
        testPlayerColor();
        Player killedPlayer = new Player();
        testPlayer.addToMyScore(8);
        killedPlayer.setPlayerColor(Color.BLUE);
        Map<Color, Integer> testScore = new HashMap();
        testScore.put(Color.RED, 6);
        testScore.put(Color.YELLOW, 4);
        testScore.put(Color.GREEN, 2);
        testScore.put(Color.WHITE, 4);
        killedPlayer.getKillShootTrack().setPlayerScore(testScore);
        testScore.get(testPlayer.playerColor);
        testPlayer.countMyScore(killedPlayer);
    }

    @Test
    public void testPlayerAlive(){
       System.out.println(testPlayer.isAlive());
    }

    @Test
    public void setTestKillShootTrack(){
       System.out.println("get kill shoot track owner name: "  + testKillShootTrack.getPlayer().getNickName());
    }


}
