package it.polimi.ingsw.se2019.Adrenaline.server.model;

import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionGrab;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionShoot;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.NotEnoughAmmosException;
import org.junit.Test;

import java.util.ArrayList;

public class TestAction {

    Player player = new Player("jia");
    Player target = new Player("mo");
    Player target2 = new Player("xin");
    Map map = new MapD();
    Board board = new Board(map, 5);
    ActionRun run = new ActionRun();

    public void setInfo(){
        board.addPlayers(player);
        board.addPlayers(target);
        board.addPlayers(target2);
//        player.setPlayBoard(board);
//        target.setPlayBoard(board);
        board.setAllPlayerColor();
        player.setEnterCellByColor("blue");
        target.setEnterCellByColor("blue");
        target2.setEnterCellByColor("yellow");
        target2.setCurrentCell(player.getCurrentCell().getNextCell(2));
    }

    @Test
    public void testRun(){
        setInfo();
        System.out.println(player.getCurrentCell().getCellID());
        try{
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
        catch (InvalidRunException e){
            System.out.println(e);
        }

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
        try {
            run.ActionRun(player, 2);
        }
        catch (InvalidRunException e){
            System.out.println(e);
        }
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


        //
        player.useWeapon(player.getAvailableWeapon().get(0));
        ActionShoot shoot = new ActionShoot(player);
        System.out.println(player.getWeaponInUse().getCardName());

        //
        System.out.println(shoot.checkWeaponType()); //controller
        System.out.println(shoot.getTargetNameBasic()); //controller to view

        try{                //controller
            if(shoot.checkIfInputValid("mo")) {        //view->controller
                System.out.println("shoot done, do you pay for side effect?");   // controller ask if he use side effect
                // shoot done ask if they use side effect
            }
        }
        catch (InvalidNameException e){
            System.out.println(e);
            // keep in the same state, try again.
        }



        try {
            shoot.payAmmoForOtherMode();
        }
        catch (NotEnoughAmmosException e){
            System.out.println(e);
        }

        System.out.println(shoot.getTargetNameWithSideEffect()); //controller-> view // this can be use before pay
//       //view -> controller
        try{
            if(shoot.checkIfInputValidSecond("xin")) {
                System.out.println("shoot done");
            }
        }
        catch (InvalidNameException e){
            System.out.println(e);
        }






//        shoot.dealBasicDamageToTarget("mo");
        System.out.println(target.getKillShootTrack().getDamageColorOnTrack());
        System.out.println(target.getKillShootTrack().getMarkColorOnTrack());

//        System.out.println(shoot.getLockrifleSecondTarget());

//        System.out.println(shoot.getTargetAttacked());


//        System.out.println(shoot.getTargetNameBasic());

    }





}