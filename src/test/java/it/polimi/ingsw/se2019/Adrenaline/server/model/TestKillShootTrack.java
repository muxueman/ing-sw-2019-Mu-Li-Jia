package it.polimi.ingsw.se2019.Adrenaline.server.model;

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
    public void valueOfMapDownSort() {
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
