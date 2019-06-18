package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPlayBoard {

    Map testMap = new MapA();
    PlayBoard testPlayBoard = new PlayBoard(5);
    Player testShooter = new Player("jia");
    Player testPlayer = new Player("li");
    Player testPlayer2 = new Player("mu");
    KillShootTrack testKillShootTrack = testPlayer.getKillShootTrack();
    ArrayList<Player> allPlayer = new ArrayList<>();




    public void setTestInfo() {
        testShooter.setPlayerColor(Color.RED);
        testPlayer.setPlayerColor(Color.WHITE);
        testPlayer2.setPlayerColor(Color.GREEN);
        testPlayer.setAlive(true);
        testPlayBoard.addPlayers(testShooter);
        testPlayBoard.addPlayers(testPlayer);
        testPlayBoard.addPlayers(testPlayer2);
        testPlayBoard.getAllPlayers();
        testPlayBoard.setKillTurn(3);
        testPlayBoard.setNumKillShoot(5);
        testPlayBoard.setCurrentPlayer(testShooter);
        allPlayer.add(testShooter);
        allPlayer.add(testPlayer);
        PlayBoard testPlayBoard2 = new PlayBoard(testMap, allPlayer);
        testPlayBoard2.setNumKillShoot(6);
        testPlayBoard.getNumKillShoot();
        testPlayBoard.getKillTurn();
    }
    @Test
    public void testPlayerOnBoard() {
        setTestInfo();
        testPlayBoard.addPlayers(testShooter);
        testPlayBoard.addPlayers(testPlayer);
        testPlayBoard.setCurrentPlayer();
        testPlayBoard.setCurrentPlayer(testShooter);
        System.out.println(testPlayBoard.getCurrentPlayer());
        testPlayBoard.nextPlayer(testPlayBoard.getCurrentPlayer());
        System.out.println(testPlayBoard.getAllPlayers());
        testPlayBoard.nextPlayer(testShooter);
        System.out.println(testPlayBoard.nextPlayer(testShooter));
        testPlayBoard.findPlayerByColor(Color.RED);
        testPlayBoard.findPlayerByColor(Color.GREEN);
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
        System.out.println("someone died? " + testPlayBoard.checkIfAnyPlayerDie());
        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        System.out.println("someone died? " + testPlayBoard.checkIfAnyPlayerDie());
        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);
        System.out.println("someone died? " + testPlayBoard.checkIfAnyPlayerDie());
        testPlayBoard.checkIfAnyPlayerDie(testShooter);
        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);
        System.out.println("all players num " + testPlayBoard.getAllPlayers().size());
        System.out.println("someone died? " + testPlayBoard.checkIfAnyPlayerDie());
        testPlayBoard.checkIfAnyPlayerDie(testShooter);
        testPlayBoard.addScoreFromKST(testPlayer);
        testPlayBoard.addScoreFromPB();
        testPlayBoard.setCurrentPlayer(testShooter);
        testPlayBoard.changefirenzyMode();
        testPlayBoard.setCurrentPlayer(testPlayer);
        testPlayBoard.changefirenzyMode();
        testPlayBoard.setCurrentPlayer(testPlayer2);
        testPlayBoard.changefirenzyMode();
        testPlayBoard.getNumDamageOnSkullBoard();
        testPlayBoard.getColorDamageOnSkullBoard();
//        testPlayBoard.getWeaponCardsOnBoard();
        System.out.println(testPlayBoard.getCurrentPlayer().getPlayerColor().toString());
        System.out.println(testPlayBoard.getAllPlayers().get(testPlayBoard.getFirstPlayer()).getPlayerColor().toString());
        System.out.println(testPlayer.getActionMode());
        System.out.println(testPlayer2.getActionMode());
        System.out.println(testShooter.getActionMode());
    }


}