package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestKillShootTrack {

    PlayBoard testPlayBoard = new PlayBoard(5);
    Player testShooter = new Player();
    Player testPlayer = new Player();
    Player testPlayer2 = new Player();
    KillShootTrack testKillShootTrack = testPlayer.getKillShootTrack();

    public void setTestInfo() {
        testShooter.setPlayerColor(Color.RED);
        testPlayer.setPlayerColor(Color.WHITE);
        testPlayer2.setPlayerColor(Color.GREEN);
        testPlayer.setAlive(false);
    }
    @Test
    public void testBeAttacked(){
        setTestInfo();
        testPlayer.getKillShootTrack().beAttacked(testShooter, 3, 2);
        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        int beAttacked = testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        System.out.println(beAttacked);
        System.out.println(testPlayer.getKillShootTrack().getDamageColorOnTrack().toString());
        System.out.println(testPlayer.getKillShootTrack().getMarkColorOnTrack().toString());
        //testPlayer.getKillShootTrack().beAttacked(testShooter,4,2);
        testPlayer.getKillShootTrack().recover();
        testPlayer.getKillShootTrack().getNumKillShoot();
    }


    @Test
    public void getNumKillShoot() {
        testKillShootTrack.getNumKillShoot();
    }
    @Test
    public void getBeKilled() {
        testKillShootTrack.getBeKilled();
    }

    @Test
    public void addMarkToDamage() {
        testKillShootTrack.addMarkToDamage(testShooter);
    }

    @Test
    public void overkillMark() {
        System.out.println(testShooter.getKillShootTrack().getMarkColorOnTrack());
    }


    @Test
    public void checkHighestScore() {
        testKillShootTrack.checkHighestScore();
    }
    @Test
    public void getDamageColorOnTrack() {
        testKillShootTrack.getDamageColorOnTrack();
        testKillShootTrack.getMarkColorOnTrack();
    }
    @Test
    public void clearkillShootTrack() {
        testKillShootTrack.clearKillShootTrack();
    }


}
