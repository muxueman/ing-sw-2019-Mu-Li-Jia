package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestKillShootTrack {

    PlayBoard testPlayBoard = new PlayBoard(5);
    Player testShooter = new Player("JIA");
    Player testPlayer = new Player("MO");
    Player testPlayer2 = new Player("XIN");
    KillShootTrack testKillShootTrack = testPlayer.getKillShootTrack();
    KillShootTrack testKillShootTrack2 = new KillShootTrack();

    public void setTestInfo() {
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


    public void getNumKillShoot() {
        testKillShootTrack.getNumKillShoot();
    }

    public void getBeKilled() {
        testKillShootTrack.getBeKilled();
    }

    public void addMarkToDamage() {
        testKillShootTrack.addMarkToDamage(testShooter);
    }

    @Test
    public void testSet() {
        testKillShootTrack.setPlayerScore(testKillShootTrack.getPlayerScore());
        testKillShootTrack.setPlayer(testPlayer);
        testKillShootTrack.setBeKilled(2);
        testKillShootTrack.setDamageColorOnTrack(testKillShootTrack.getDamageColorOnTrack());
        testKillShootTrack.setMarkColorOnTrack(testKillShootTrack.getMarkColorOnTrack());
        testKillShootTrack.setTurn(3);

    }

    @Test
    public void getDamageColorOnTrack() {
        testKillShootTrack.toString();
    }
}