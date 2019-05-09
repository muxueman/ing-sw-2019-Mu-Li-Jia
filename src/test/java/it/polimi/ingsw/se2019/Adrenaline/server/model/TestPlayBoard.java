package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayBoard {

    PlayBoard testPlayBoard = new PlayBoard(5);


    Player tesetShooter = new Player();
    Player testPlayer = new Player();
    KillShootTrack testKillShootTrack = testPlayer.getKillShootTrack();
    @Test
    public void testPlayerOnBoard() {
        testPlayBoard.addPlayers(tesetShooter);
        testPlayBoard.addPlayers(testPlayer);
        testPlayBoard.setCurrentPlayer();
        System.out.println(testPlayBoard.getCurrentPlayer());
        testPlayBoard.nextPlayer(testPlayBoard.getCurrentPlayer());
        System.out.println(testPlayBoard.getAllPlayers());
        testPlayBoard.nextPlayer(tesetShooter);
        System.out.println(testPlayBoard.nextPlayer(tesetShooter));
    }


    @Test
    public void testfirenzyMode() {
        testPlayBoard.setKillTurn(4);
        System.out.println(testPlayBoard.triggerFirenzy());
        testPlayBoard.setKillTurn(5);
        System.out.println(testPlayBoard.triggerFirenzy());
        testPlayBoard.changefirenzyMode();
    }


}