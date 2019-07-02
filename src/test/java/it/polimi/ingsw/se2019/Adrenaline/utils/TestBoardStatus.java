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
    @Test
    public void MapTest(){
        MapA a = new MapA();
        Player player1 = new Player("1");
        player1.setPlayerColor(Color.RED);
        player1.setUserName("345");
        Board board = new Board(a,1,5);
        BoardStatus boardStatus = new BoardStatus(board);
        a.getAllCells().get(3).getCellPlayers().add(player1);
        boardStatus.updateMap(a);


        System.out.println(boardStatus.getAllCells().size());
        System.out.println(boardStatus.getAllCells().get(3).getCellPlayers());
        System.out.println(boardStatus.getAllCells().get(3).getCellPlayers().size());
        System.out.println(boardStatus.getPositions().values());
        System.out.println(boardStatus.getPositions().get("1"));
    }
}