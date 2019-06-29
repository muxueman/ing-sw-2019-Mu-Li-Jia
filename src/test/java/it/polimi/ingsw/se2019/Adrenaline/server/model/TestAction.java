package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import org.junit.Test;

public class TestAction {

    Player player = new Player("jia");
    Map map = new MapD();
    Board board = new Board(map, 5);
    ActionRun run = new ActionRun();

    public void setInfo(){
        board.addPlayers(player);
        player.setPlayBoard(board);
        player.setEnterCellByColor("blue");
    }

    @Test
    public void testRun(){
        setInfo();
        System.out.println(player.getCurrentCell().getCellID());
        run.ActionRun(player, 2);
        System.out.println(player.getCurrentCell().getCellID());
        System.out.println(run.ActionRun(player, 0));
        System.out.println(player.getCurrentCell().getCellID());
        System.out.println(run.ActionRun(player, 0));
        System.out.println(player.getCurrentCell().getCellID());
        System.out.println(run.ActionRun(player, 1));
        System.out.println(run.ActionRun(player, 1));
        System.out.println(run.ActionRun(player, 1));
        System.out.println(player.getCurrentCell().getCellID());
    }

    




}