package it.polimi.ingsw.se2019.Adrenaline.server.model;


import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapC;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
//import sun.jvm.hotspot.debugger.win32.coff.TestParser;


public class TestPlayer {

    Player testPlayer;
    PlayerBoard testKillShootTrack;
    it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map map;
    Board testBoard;
    int testMode;

    public void setTestInfo(){
        testPlayer = new Player("JIA");
        testKillShootTrack = new PlayerBoard(testPlayer);
        testPlayer.setPlayerColor(Color.RED);
        map = new MapC();
        testBoard = new Board(map, 5, 1);
        testMode = 2;
        testBoard.addPlayers(testPlayer);
    }

    public void testName() {
        testPlayer.setUserName("jiamoxin");
        System.out.println("test nickname: " + testPlayer.getUserName());
    }

    public void testPlayerColor() {
        setTestInfo();
        System.out.println("test player ID: " + testPlayer.getPlayerID());
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
       testPlayer.getWeaponsOwned();
    }

    public void testAmmoOwned(){
        testPlayer.fillAmmo(AmmoColor.RED);
        testPlayer.fillAmmo(AmmoColor.BLUE);
        testPlayer.fillAmmo(AmmoColor.YELLOW);
        testPlayer.consumeAmmo(AmmoColor.RED);
        testPlayer.consumeAmmo(AmmoColor.BLUE);
        testPlayer.consumeAmmo(AmmoColor.YELLOW);
        System.out.println("initial ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.consumeAmmo(AmmoColor.RED, 2);
        testPlayer.consumeAmmo(AmmoColor.RED, 2);
        System.out.println("after comsume ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.consumeAmmo(AmmoColor.RED, 2);
        System.out.println("after comsume ammo owned: \n" + testPlayer.ammoOwnedToString());
        testPlayer.fillAmmo(AmmoColor.RED, 1);
        System.out.println("after fill ammo owned: \n" + testPlayer.ammoOwnedToString());

    }
    @Test
    public void testPlayerSet(){
        setTestInfo();
        testPlayer.setEnterCellByColor("red");
        testPlayer.setPlayBoard(testBoard);
        testPlayer.setCurrentCell(testBoard.getMap().getAllCells().get(0));
        testPlayer.getCurrentCell();
    }
    @Test
    public void testPlayerInfo(){
        setTestInfo();
       System.out.println(testPlayer.toString());
       testActionMode();
       testAmmoOwned();
       testCardsOwned();
       testPlayerSet();
       testPlayer.getMyScore();
    }

    @Test
    public void testScore() {
        setTestInfo();
        Player killedPlayer = new Player("JIA");
        testBoard.addPlayers(killedPlayer);
        testPlayer.addToMyScore(8);
        killedPlayer.setPlayerColor(Color.BLUE);
        Map<Color, Integer> testScore = new HashMap();
        testScore.put(Color.RED, 6);
        testScore.put(Color.YELLOW, 4);
        testScore.put(Color.GREEN, 2);
        testScore.put(Color.WHITE, 4);
        killedPlayer.getKillShootTrack().setPlayerScore(testScore);
        testScore.get(testPlayer.getPlayerColor());
        testPlayer.countMyScore(killedPlayer);
    }





}
