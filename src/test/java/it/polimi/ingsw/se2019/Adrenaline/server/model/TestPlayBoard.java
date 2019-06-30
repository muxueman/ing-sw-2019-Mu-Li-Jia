package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapB;
import org.junit.Test;

import java.util.ArrayList;

public class TestPlayBoard {

    Map testMap = new MapB();
    int skull = 5;
    Board testBoard = new Board(testMap, skull);
    Player testShooter = new Player("jia");
    Player testPlayer = new Player("li");
    Player testPlayer2 = new Player("mu");

    public void setTestInfo() {
        testBoard.setKillTurn(1);
        testBoard.setNumKillShoot(5);
//        Board testBoard2 = new Board(testMap, allPlayer);
//        testBoard2.setNumKillShoot(6);
//        testBoard.getNumKillShoot();
//        testBoard.getKillTurn();
    }

    @Test
    public void addPlayerTest(){
        testBoard.addPlayers(testShooter);
        testBoard.addPlayers(testPlayer);
        testBoard.addPlayers(testPlayer2);
        System.out.println("currenttest"+testBoard.getCurrentPlayer());
        testBoard.setPlayers(testBoard.getAllPlayers());

        testBoard.setAllPlayerColor();
        testBoard.setCurrentPlayer(testShooter);
        testBoard.turnNextPlayer();
        testBoard.turnNextPlayer();
        testBoard.changefirenzyMode();
        System.out.println(testBoard.getCurrentPlayer());
        testBoard.setCurrentPlayer(testBoard.nextPlayer(testBoard.getCurrentPlayer()));
        System.out.println(testBoard.getCurrentPlayer());
        for(Player p : testBoard.getAllPlayers()){
            System.out.println(p.getUserName());
        }



    }

    @Test
    public void testPlayerOnBoard() {
        addPlayerTest();
        System.out.println(testBoard.getMap());

//        testMap.initialMap();
//        Board testBoard2 = new Board(testMap);
//        testBoard2.setMap(testMap);
//        System.out.println(testBoard2.getMap().getAllCells());
//        System.out.println(testBoard.getAllPlayers());
//        testBoard.setCurrentPlayer(testShooter);
////        System.out.println(testBoard.getCurrentPlayer());
//        testBoard.nextPlayer(testBoard.getCurrentPlayer());
//        System.out.println(testBoard.getAllPlayers());
//        testBoard.nextPlayer(testShooter);
//        System.out.println(testBoard.nextPlayer(testShooter));
//        testBoard.findPlayerByColor(Color.RED);
//        testBoard.findPlayerByColor(Color.GREEN);
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
        testPlayer.beAttacked(testShooter, 3, 2);
        testPlayer2.beAttacked(testShooter, 4,3);
        testPlayer.beAttacked(testShooter, 3, 2);
        testPlayer.beAttacked(testPlayer2, 4,3);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());


//        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
//        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
//        testPlayer.beAttacked(testShooter, 4, 2);
//        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
//        testBoard.checkIfAnyPlayerDie(testShooter);
//        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);
//        System.out.println("all players num " + testBoard.getAllPlayers().size());
//        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
//        testBoard.checkIfAnyPlayerDie(testShooter);
//        testBoard.addScoreFromKST(testPlayer);
//        testBoard.addScoreFromPB();
//        testBoard.setCurrentPlayer(testShooter);
//        testBoard.changefirenzyMode();
//        testBoard.setCurrentPlayer(testPlayer);
//        testBoard.changefirenzyMode();
//        testBoard.setCurrentPlayer(testPlayer2);
//        testBoard.changefirenzyMode();
//        testBoard.getNumDamageOnSkullBoard();
//        testBoard.getColorDamageOnSkullBoard();
////        testBoard.getWeaponCardsOnBoard();
////        System.out.println(testBoard.getCurrentPlayer().getPlayerColor().toString());
//        System.out.println(testBoard.getAllPlayers().get(testBoard.getFirstPlayer()).getPlayerColor().toString());
//        System.out.println(testPlayer.getActionMode());
//        System.out.println(testPlayer2.getActionMode());
//        System.out.println(testShooter.getActionMode());
    }


}