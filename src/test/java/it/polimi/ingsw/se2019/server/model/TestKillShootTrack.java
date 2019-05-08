package it.polimi.ingsw.se2019.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestKillShootTrack {

    KillShootTrack testKillShootTrack = new KillShootTrack();
    Player testShooter = new Player();


    @Test
    public void getNumKillShoot() {
        testKillShootTrack.getNumKillShoot();
    }
    @Test
    public void getBeKilled() {

    }

    @Test
    public void addMarkToDamage() {
    }

    @Test
    public void beAttacked() {
        testKillShootTrack.beAttacked(testShooter, 3,2);
        testKillShootTrack.beAttacked(testShooter, 1,2);
        testKillShootTrack.beAttacked(testShooter, 2,1);
        testKillShootTrack.beAttacked(testShooter, 1,2);
        testKillShootTrack.beAttacked(testShooter, 3,2);
    }

    @Test
    public void overkillMark() {
        System.out.println(testShooter.getKillShootTrack().getMarkColorOnTrack());
    }

    @Test
    public void valueOfMapDownSort() {
    }

    @Test
    public void checkHighestScore() {
        testKillShootTrack.checkHighestScore();
    }

    @Test
    public void countPlayerScore() {
    }

    @Test
    public void recover() {
        testKillShootTrack.recover();
    }

    @Test
    public void addPlayerScore() {
    }

    @Test
    public void getDamageColorOnTrack() {
    }

    @Test
    public void clearkillShootTrack() {
    }
    @Test
    public void test(){
        beAttacked();
        getBeKilled();
        beAttacked();
        getDamageColorOnTrack();
        getBeKilled();
        getNumKillShoot();
        recover();
        getDamageColorOnTrack();
        overkillMark();
    }
}