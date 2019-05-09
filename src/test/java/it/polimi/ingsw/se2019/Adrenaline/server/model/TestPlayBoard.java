package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayBoard {

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
        testPlayBoard.addPlayers(testShooter);
        testPlayBoard.addPlayers(testPlayer);
        testPlayBoard.addPlayers(testPlayer2);
        testPlayBoard.getAllPlayers();
        testPlayBoard.setKillTurn(5);
        testPlayBoard.setNumKillShoot(5);
        testPlayBoard.setCurrentPlayer();

    }
    @Test
    public void testPlayerOnBoard() {
        setTestInfo();
        testPlayBoard.addPlayers(testShooter);
        testPlayBoard.addPlayers(testPlayer);
        testPlayBoard.setCurrentPlayer();
        System.out.println(testPlayBoard.getCurrentPlayer());
        testPlayBoard.nextPlayer(testPlayBoard.getCurrentPlayer());
        System.out.println(testPlayBoard.getAllPlayers());
        testPlayBoard.nextPlayer(testShooter);
        System.out.println(testPlayBoard.nextPlayer(testShooter));
        testPlayBoard.findPlayerByColor(Color.RED);
    }


    @Test
    public void testfirenzyMode() {
        testPlayBoard.setKillTurn(4);
        System.out.println(testPlayBoard.triggerFirenzy());
        testPlayBoard.setKillTurn(5);
        System.out.println(testPlayBoard.triggerFirenzy());
        testPlayBoard.changefirenzyMode();
    }
    @Test
    public void testSomeoneDie(){
        setTestInfo();
        testPlayer.getKillShootTrack().beAttacked(testShooter, 3, 2);
        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        testPlayer.getKillShootTrack().beAttacked(testShooter, 3, 2);

        System.out.println("all players num " + testPlayBoard.getAllPlayers().size());
        System.out.println("someone died? " + testPlayBoard.checkIfAnyPlayerDie());

    }



}