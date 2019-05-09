package it.polimi.ingsw.se2019.Adrenaline.server.model;


import it.polimi.ingsw.se2019.Adrenaline.server.model.AmmoColor;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;
//import sun.jvm.hotspot.debugger.win32.coff.TestParser;

import static org.junit.Assert.*;

public class TestPlayer {
    Player testPlayer = new Player();
    KillShootTrack testKillShootTrack = new KillShootTrack(testPlayer);
    int testMode = 2;
   @Test
    public void testActionMode() {
       System.out.println("initial actionmode: " + testPlayer.getActionMode());
        testPlayer.setActionMode(testMode);
        System.out.println("test action mode：" + testPlayer.getActionMode());
    }


    public void testName() {
        testPlayer.setName("jiamoxin");
        System.out.println("test nickname: " + testPlayer.getNickName());
    }


    public void testPlayerColor() {
       testPlayer.setPlayerColor(Color.RED);
       System.out.println("test player color: " + testPlayer.getPlayerColor());
    }
    @Test
    public void testPlayerInfo(){
       testName();
       testPlayerColor();
       System.out.println(testPlayer.toString());
    }
    @Test
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
    public void testPlayerAlive(){
       System.out.println(testPlayer.isAlive());
    }
    @Test
    public void setTestKillShootTrack(){
       System.out.println("get kill shoot track owner name: "  + testKillShootTrack.getPlayer().getNickName());
    }


}
