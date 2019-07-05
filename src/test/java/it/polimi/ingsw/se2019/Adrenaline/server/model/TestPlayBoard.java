package it.polimi.ingsw.se2019.Adrenaline.server.model;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPlayBoard {

    private Map testmapa;
    private Board testBoard;
    private Player testShooter;
    private Player testPlayer;
    private Player testPlayer2;
    private TurnHandler turnHandler;
    @Before
    public void setTestInfo() {
        testmapa = new MapB();
        int skull = 5;
        testBoard = new Board(testmapa, skull, 2);
        testShooter= new Player("jia");
        testPlayer = new Player("li");
        testPlayer2 = new Player("mu");
        testBoard.addPlayers(testShooter);
        testBoard.addPlayers(testPlayer);
        testBoard.addPlayers(testPlayer2);
        testBoard.setKillTurn(3);
        turnHandler = new TurnHandler(testBoard);
        testShooter.setPlayerColor(Color.RED);
        testPlayer2.setPlayerColor(Color.YELLOW);
        testPlayer.setPlayerColor(Color.BLUE);
        testBoard.setCurrentPlayer(testShooter);
    }

    @Test
    public void addPlayerTest(){

        assertTrue(!testBoard.checkIfAnyPlayerDie());
        assertTrue(!testBoard.triggerFirenzy());
        System.out.println(testBoard.getCurrentPlayer());
        //testBoard.setCurrentPlayer(testBoard.nextPlayer(testBoard.getCurrentPlayer()));
        System.out.println(testBoard.getCurrentPlayer());
        for(Player p : testBoard.getAllPlayers()){
            System.out.println(p.getUserName());
        }
        for(Cell c : testBoard.getMap().getAllCells()){
            c.getCellID();
        }



    }

    @Test
    public void testPlayerOnBoard() {
        setTestInfo();
        addPlayerTest();
        System.out.println(testBoard.getMap());

        System.out.println(testBoard.getAllPlayers());
        testBoard.setCurrentPlayer(testShooter);
//        System.out.println(testBoard.getCurrentPlayer());
        testBoard.nextPlayer(testBoard.getCurrentPlayer());
        System.out.println(testBoard.getAllPlayers());
        testBoard.nextPlayer(testShooter);
        System.out.println(testBoard.nextPlayer(testShooter));
        testBoard.findPlayerByColor(Color.RED);
        testBoard.findPlayerByColor(Color.GREEN);
    }


    @Test
    public void testfirenzyMode() {
        setTestInfo();
        testBoard.setKillTurn(4);
        System.out.println(testBoard.triggerFirenzy());
        testBoard.setKillTurn(5);
        System.out.println(testBoard.triggerFirenzy());
        assertTrue(testBoard.triggerFirenzy());
    }

    @Test
    public void testSomeoneDie(){
        setTestInfo();
        testPlayer.beAttacked(testShooter, 3, 2);
        testPlayer2.beAttacked(testShooter, 4,3);
        testPlayer.beAttacked(testShooter, 3, 2);
        testPlayer.beAttacked(testPlayer2, 4,3);

        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());


        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testPlayer.beAttacked(testShooter, 4, 2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testBoard.checkIfAnyPlayerDie(testShooter);
        testPlayer.getKillShootTrack().beAttacked(testShooter, 4, 2);

        System.out.println("all players num " + testBoard.getAllPlayers().size());
        testPlayer.getKillShootTrack().beAttacked(testShooter,2,2);
        System.out.println("someone died? " + testBoard.checkIfAnyPlayerDie());
        testBoard.checkIfAnyPlayerDie(testShooter);
        testBoard.findPlayerByColor(Color.RED);
        testBoard.setCurrentPlayer(testShooter);
        testBoard.setCurrentPlayer(testPlayer);
        assertTrue(testBoard.triggerFirenzy());
        testBoard.setCurrentPlayer(testPlayer2);
        assertTrue(testBoard.triggerFirenzy());
        testBoard.getNumDamageOnSkullBoard();
        testBoard.getColorDamageOnSkullBoard();
//        testBoard.getWeaponCardsOnBoard();
//        System.out.println(testBoard.getCurrentPlayer().getPlayerColor().toString());
        System.out.println(testBoard.getAllPlayers().get(0).getPlayerColor().toString());
        System.out.println(testPlayer.getActionMode());
        System.out.println(testPlayer2.getActionMode());
        System.out.println(testShooter.getActionMode());
    }

    @After
    public void tearDown() {
        testmapa = null;
        testBoard = null;
        testShooter = null;
        testPlayer = null;
        testPlayer2 = null;
    }


}