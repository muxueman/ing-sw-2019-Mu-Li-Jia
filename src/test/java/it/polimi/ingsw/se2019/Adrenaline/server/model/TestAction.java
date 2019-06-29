package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import org.junit.Test;

import java.util.ArrayList;

public class TestAction {

    Player player = new Player("jia");
    Player target = new Player("mo");
    Map map = new MapD();
    Board board = new Board(map, 5);
    ActionRun run = new ActionRun();

    public void setInfo(){
        board.addPlayers(player);
        board.addPlayers(target);
        player.setPlayBoard(board);
        target.setPlayBoard(board);
        player.setEnterCellByColor("blue");
        target.setEnterCellByColor("yellow");
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

    @Test
    public void testGrab(){
        setInfo();
        ActionGrab grab = new ActionGrab();
       System.out.println(grab.checkPlayerAmmoAvailable( ((GenerationCell)player.getCurrentCell()).getWeaponCard(0), player));
        grab.pickWeaponCrad(player,2);
        System.out.println(player.getAmmoOwned()[0]);
        System.out.println(player.getAmmoOwned()[1]);
        System.out.println(player.getAmmoOwned()[2]);
        System.out.println(player.getWeaponsOwned());
        run.ActionRun(player, 2);
        grab.pickAmmoTile(player);
        System.out.println(player.getAmmoOwned()[0]);
        System.out.println(player.getAmmoOwned()[1]);
        System.out.println(player.getAmmoOwned()[2]);

        //how to grab?;
        //you should check the player is in common or generation cell
        //common then grab.pickammotile
        //generation then if(checkplayerAmmoAvailable) grab.pickweaponcard else message"you dont have enough ammo"
    }

    @Test
    public void testShoot(){
        setInfo();
        ActionGrab grab = new ActionGrab();
        grab.pickWeaponCrad(player,2);
        System.out.println(player.getWeaponsOwned().entrySet());
        player.useWeapon(player.getAvailableWeapon().get(0));
        System.out.println(player.getWeaponsOwned().entrySet());

    }





}