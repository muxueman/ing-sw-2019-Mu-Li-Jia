package it.polimi.ingsw.se2019.Adrenaline.server.model;


import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
//import sun.jvm.hotspot.debugger.win32.coff.TestParser;


public class TestPlayer {
/*
    Player testPlayer = new Player("JIA");
    PlayerBoard testKillShootTrack = new PlayerBoard(testPlayer);
    it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map map = new MapD();
    Board testBoard = new Board(map, 5);
    int testMode = 2;

    public void testName() {
        testPlayer.setUserName("jiamoxin");
        System.out.println("test nickname: " + testPlayer.getUserName());
    }

    public void testPlayerColor() {
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
        testPlayer.setPlayBoard(testBoard);
        testPlayer.setCurrentCell(testBoard.getMap().getAllCells().get(0));
        testPlayer.getCurrentCell();
    }
    @Test
    public void testPlayerInfo(){
       testName();
       testPlayerColor();
       System.out.println(testPlayer.toString());
       testActionMode();
       testAmmoOwned();
       testCardsOwned();
       testPlayerSet();
       testPlayer.getMyScore();
    }

    @Test
    public void testScore() {
        testPlayerColor();
        Player killedPlayer = new Player("JIA");
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
    @Test
    public void testPlayerAlive(){
       System.out.println(testPlayer.isAlive());
    }

    @Test
    public void setTestKillShootTrack(){
       System.out.println("get kill shoot track owner name: "  + testKillShootTrack.getPlayerStatus().getUsername());
    }
    @Test
    public void testAction(){
        testBoard.initialCardsOnBoard();
        //generation cell
        testPlayer.setPlayBoard(testBoard);
        testBoard.getMap().getAllCells().get(2).getWeaponCard(2).getBasicammoCost();
        testPlayer.setEnterCellByColor("yellow");
        testPlayer.setCurrentCell(testPlayer.getPlayBoard().getMap().getAllCells().get(2));
        System.out.println(testPlayer.getCurrentCell().getWeaponCard(1).getCardName());
//        testPlayer.getCurrentCell().getWeaponCard(1).getBasicammoCost();
        ActionGrab action = new ActionGrab();
//        shoot.payAmmoForOtherMode(testPlayer, );
        action.pickWeaponCrad(testPlayer, 1);
        System.out.println(testPlayer.getAmmoOwned()[0]);
        System.out.println(testPlayer.getAmmoOwned()[1]);
        System.out.println(testPlayer.getAmmoOwned()[2]);
    }

 */
}
