package it.polimi.ingsw.se2019.Adrenaline.utils;

import it.polimi.ingsw.se2019.Adrenaline.server.Server;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapA;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.BoardStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestBoardStatus {
    @Test
    public void MapPutTest() {
        Map<Integer, Integer> maptest = new HashMap<>();
        maptest.put(1, 2);
        maptest.put(1, 3);
        maptest.put(2, 5);
        System.out.println(maptest);
    }
    public void setTestInfo(){
        MapA a = new MapA();
        Player player1 = new Player("id1");
        player1.setPlayerColor(Color.RED);
        player1.setUserName("345");
        Player player2 = new Player("id2");
        player2.setPlayerColor(Color.GREEN);
        player2.setUserName("675");
        Board board = new Board(a,1,5);
        BoardStatus boardStatus = new BoardStatus(board);
        a.getAllCells().get(3).getCellPlayers().add(player1);
        boardStatus.updateMap(a);
    }
    @Test
    public void MapTest(){
        MapA a = new MapA();
        Player player1 = new Player("id1");
        player1.setPlayerColor(Color.RED);
        player1.setUserName("345");
        Player player2 = new Player("id2");
        player2.setPlayerColor(Color.GREEN);
        player2.setUserName("675");
        Board board = new Board(a,1,5);
        BoardStatus boardStatus = new BoardStatus(board);
        a.getAllCells().get(3).getCellPlayers().add(player1);
        boardStatus.updateMap(a);


        System.out.println(boardStatus.getAllCells().size());
        System.out.println(boardStatus.getAllCells().get(3).getCellPlayers());
        System.out.println(boardStatus.getAllCells().get(3).getCellPlayers().size());
        System.out.println(boardStatus.getPositions().values());
        System.out.println(boardStatus.getPositions().get("1"));

        a.getAllCells().get(3).getCellPlayers().remove(player1);
        a.getAllCells().get(5).getCellPlayers().add(player1);
        a.getAllCells().get(3).getCellPlayers().add(player2);

//        boardStatus.updateMap(a);
        boardStatus.updatePosition(a);


        System.out.println(boardStatus.getAllCells().size());
        System.out.println("cell3:" + boardStatus.getAllCells().get(3).getCellPlayers());
        System.out.println("cell5" + boardStatus.getAllCells().get(5).getCellPlayers());
        System.out.println(boardStatus.getAllCells().get(3).getCellPlayers().size());
        System.out.println(boardStatus.getPositions().keySet());
        System.out.println(boardStatus.getPositions().values());

        System.out.println(boardStatus.getPositions().get("id1"));
        System.out.println(boardStatus.getPositions().get("id2"));


    }

    @Test
    public void testPlayer(){
        MapA a = new MapA();
        Player player1 = new Player("id1");
        player1.setPlayerColor(Color.RED);
        player1.setUserName("345");
        Player player2 = new Player("id2");
        player2.setPlayerColor(Color.GREEN);
        player2.setUserName("675");
        Board board = new Board(a,1,5);
        BoardStatus boardStatus = new BoardStatus(board);
        a.getAllCells().get(3).getCellPlayers().add(player1);
        boardStatus.updateMap(a);

        player1.setPlayerColor(Color.YELLOW);
        boardStatus.updatePlayer(player1);
        System.out.println(boardStatus.getPlayer("id1").getPlayerColor());


    }
}