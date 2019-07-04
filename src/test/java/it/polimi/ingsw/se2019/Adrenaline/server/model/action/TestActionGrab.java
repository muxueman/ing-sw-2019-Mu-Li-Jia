package it.polimi.ingsw.se2019.Adrenaline.server.model.action;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;
import org.junit.Test;

public class TestActionGrab {

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
    public void testGrab() {
        setInfo();
        ActionGrab grab = new ActionGrab();
        System.out.println(grab.checkPlayerAmmoAvailable(((GenerationCell) player.getCurrentCell()).getWeaponCard().get(0), player));
        grab.pickWeaponCrad(player, 2);
        System.out.println(player.getAmmoOwned()[0]);
        System.out.println(player.getAmmoOwned()[1]);
        System.out.println(player.getAmmoOwned()[2]);
        System.out.println(player.getWeaponsOwned());

        int[] am = {2,2,2};
        player.setAmmoOwned(am);
        try {
            run.ActionRun( 2);
        } catch (InvalidRunException e) {
            System.out.println(e);
        }
        grab.pickAmmoTile(player);
        System.out.println(player.getAmmoOwned()[0]);
        System.out.println(player.getAmmoOwned()[1]);
        System.out.println(player.getAmmoOwned()[2]);
        System.out.println(board.getPickedCell().size());
        board.reloadCardOnBoard();
        System.out.println(board.getPickedCell().size());
        //how to grab?;
        //you should check the player is in common or generation cell
        //common then grab.pickammotile
        //generation then if(checkplayerAmmoAvailable) grab.pickweaponcard else message"you dont have enough ammo"
    }
}
