package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import org.junit.Test;

import java.util.ArrayList;

public class TestPlayBoard {

    Map testMap = new MapA();
    Board testBoard = new Board(5);
    Player testShooter = new Player("jia");
    Player testPlayer = new Player("li");
    Player testPlayer2 = new Player("mu");
    PlayerBoard testKillShootTrack = testPlayer.getKillShootTrack();
    ArrayList<Player> allPlayer = new ArrayList<>();




    public void setTestInfo() {
        testShooter.setPlayerColor(Color.RED);
        testPlayer.setPlayerColor(Color.WHITE);
        testPlayer2.setPlayerColor(Color.GREEN);
        testPlayer.setAlive(true);
        testBoard.addPlayers(testShooter);
        testBoard.addPlayers(testPlayer);
        testBoard.addPlayers(testPlayer2);
        testBoard.getAllPlayers();
        testBoard.setKillTurn(3);
        testBoard.setNumKillShoot(5);
        testBoard.setCurrentPlayer(testShooter);
        allPlayer.add(testShooter);
        allPlayer.add(testPlayer);
//        Board testBoard2 = new Board(testMap, allPlayer);
//        testBoard2.setNumKillShoot(6);
        testBoard.getNumKillShoot();
        testBoard.getKillTurn();
    }
    @Test
    public void testPlayerOnBoard() {
        setTestInfo();
        testBoard.addPlayers(testShooter);
        testBoard.addPlayers(testPlayer);
//        testBoard.setCurrentPlayer();
        testBoard.setCurrentPlayer(testShooter);
//        System.out.println(testBoard.getCurrentPlayer());
//        testBoard.nextPlayer(testBoard.getCurrentPlayer());
        System.out.println(testBoard.getAllPlayers());
        testBoard.nextPlayer(testShooter);
        System.out.println(testBoard.nextPlayer(testShooter));
        testBoard.findPlayerByColor(Color.RED);
        testBoard.findPlayerByColor(Color.GREEN);
    }


    @Test
    public void testfirenzyMode() {
        testBoard.setKillTurn(4);
        System.out.println(testBoard.triggerFirenzy());
        testBoard.setKillTurn(5);
        System.out.println(testBoard.triggerFirenzy());
        testBoard.changefirenzyMode();
    }

    @Test
    public void testSomeoneDie(){
        setTestInfo();
        testPlayer.getKillShootTrack().beAttacked(testShooter, 3, 2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testBoard.checkIfAnyPlayerDie(testShooter);
        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);
        System.out.println("all players num " + testBoard.getAllPlayers().size());
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testBoard.checkIfAnyPlayerDie(testShooter);
        testBoard.addScoreFromKST(testPlayer);
        testBoard.addScoreFromPB();
        testBoard.setCurrentPlayer(testShooter);
        testBoard.changefirenzyMode();
        testBoard.setCurrentPlayer(testPlayer);
        testBoard.changefirenzyMode();
        testBoard.setCurrentPlayer(testPlayer2);
        testBoard.changefirenzyMode();
        testBoard.getNumDamageOnSkullBoard();
        testBoard.getColorDamageOnSkullBoard();
//        testBoard.getWeaponCardsOnBoard();
//        System.out.println(testBoard.getCurrentPlayer().getPlayerColor().toString());
        System.out.println(testBoard.getAllPlayers().get(testBoard.getFirstPlayer()).getPlayerColor().toString());
        System.out.println(testPlayer.getActionMode());
        System.out.println(testPlayer2.getActionMode());
        System.out.println(testShooter.getActionMode());
    }


}