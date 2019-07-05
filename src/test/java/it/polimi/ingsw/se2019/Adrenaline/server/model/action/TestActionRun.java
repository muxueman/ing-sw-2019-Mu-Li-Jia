package it.polimi.ingsw.se2019.Adrenaline.server.model.action;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidGrabException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidRunException;
import org.junit.Test;

public class TestActionRun {
    Player player = new Player("player", "jia", 2);
    Player target = new Player("target", "mo", 1);
    Player target2 = new Player("target2", "xin", 3);
    Map map = new MapD();
    Board board = new Board(map, 5, 1);
    ActionRun run = new ActionRun(player);

    public void setInfo() {
        board.addPlayers(player);
        board.addPlayers(target);
        board.addPlayers(target2);
//        player.setPlayBoard(board);
//        target.setPlayBoard(board);
        player.setPlayerColor(Color.YELLOW);
        target2.setPlayerColor(Color.BLUE);
        target.setPlayerColor(Color.RED);
        player.setEnterCellByColor("red");
        target.setEnterCellByColor("yellow");
        target2.setEnterCellByColor("yellow");
        target2.setCurrentCell(target2.getCurrentCell().getNextCell(0));
        target.setCurrentCell(player.getCurrentCell());
        target.setCurrentCell(board.getCellFromID(7));
        target2.setCurrentCell(board.getCellFromID(6));
    }

    @Test
    public void testRun() {
        setInfo();
        try {
            player.addPowerupCard(board.extractPowerupcard());
            player.addPowerupCard(board.extractPowerupcard());
        } catch (InvalidGrabException e) {
            System.out.println(e.toString());
        }

        player.dropPowerupAndGoNewCell(player.getPowerupsOwned().get(0).getCardName());
        System.out.println(player.getPowerupsOwned().size());
        System.out.println(player.getCurrentCell().getCellID());
        try {
            run.ActionRun(0);
            System.out.println(player.getCurrentCell().getCellID());
//            System.out.println(run.ActionRun(0));
//            System.out.println(player.getCurrentCell().getCellID());
//            System.out.println(run.ActionRun(0));
//            System.out.println(player.getCurrentCell().getCellID());
//            System.out.println(run.ActionRun(1));
//            System.out.println(run.ActionRun(1));
//            System.out.println(run.ActionRun(1));
            System.out.println(player.getCurrentCell().getCellID());
            System.out.println(run.ActionRunToCell(3));
            System.out.println(player.getCurrentCell().getCellID());
        } catch (InvalidRunException e) {
            System.out.println(e);
        }

    }
}
