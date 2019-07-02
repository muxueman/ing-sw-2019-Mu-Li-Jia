package it.polimi.ingsw.se2019.Adrenaline.server.model;

import com.sun.deploy.association.AssociationNotRegisteredException;
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
import java.util.Scanner;

public class TestAction {

    Player player = new Player("jia");
    Player target = new Player("mo");
    Player target2 = new Player("xin");
    Map map = new MapD();
    Board board = new Board(map, 5);
    ActionRun run = new ActionRun(player);

    public void setInfo() {
        board.addPlayers(player);
        board.addPlayers(target);
        board.addPlayers(target2);
//        player.setPlayBoard(board);
//        target.setPlayBoard(board);
        board.setAllPlayerColor();
        player.setEnterCellByColor("blue");
        target.setEnterCellByColor("blue");
        target2.setEnterCellByColor("yellow");

        target2.setCurrentCell(target2.getCurrentCell().getNextCell(0));
    }

    @Test
    public void testRun() {
        setInfo();
        System.out.println(player.getCurrentCell().getCellID());
        try {
            run.ActionRun( 0);
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

    @Test
    public void testGrab() {
        setInfo();
        ActionGrab grab = new ActionGrab();
        System.out.println(grab.checkPlayerAmmoAvailable(((GenerationCell) player.getCurrentCell()).getWeaponCard(0), player));
        grab.pickWeaponCrad(player, 2);
        System.out.println(player.getAmmoOwned()[0]);
        System.out.println(player.getAmmoOwned()[1]);
        System.out.println(player.getAmmoOwned()[2]);
        System.out.println(player.getWeaponsOwned());
        try {
            run.ActionRun( 2);
        } catch (InvalidRunException e) {
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
    public void testShoot() {
        setInfo();
        ActionGrab grab = new ActionGrab();
        grab.pickWeaponCrad(player, 2);
        //
        player.useWeapon(player.getAvailableWeapon().get(0));
        ActionShoot shoot = new ActionShoot(player);
        System.out.println(player.getWeaponInUse().getCardName());
        System.out.println(shoot.getTargetNameBasic());
        System.out.println("shooter cell" + player.getCurrentCell().getCellID());
        System.out.println("target cell" + target.getCurrentCell().getCellID());
        System.out.println("target2 cell" + target2.getCurrentCell().getCellID());
        try{

            shoot.checkIfInputValidSecond("xin");
            System.out.println(target2.getKillShootTrack().getDamageColorOnTrack());
        }
        catch (InvalidNameException e){
            System.out.println(e);
        }

    }


@Test
public void shootTest(){
        setInfo();
}
    public void testShootwithweapon() {

         class shootWithLOCKRIFLE{
            shootWithLOCKRIFLE(ActionShoot shoot){
                String inpuFromCli = "";
                String messageSendToCli= "";

                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
                try {                //2. controller
                    if (shoot.checkIfInputValid(inpuFromCli)) {
                        messageSendToCli = "shoot done, do you want to continue with side effect?  input continue or end";
                    }
                } catch (InvalidNameException e) {
                    messageSendToCli = e.toString();
                    // keep in the same state, try again.
                }
                // if continue
                if(inpuFromCli == "continue"){ //3.
                    try {
                        shoot.payAmmoForOtherMode();
                        messageSendToCli = "have paid" + "\n" + shoot.getTargetNameWithSideEffect();
                    } catch (NotEnoughAmmosException e) {
                        messageSendToCli = e.toString();
                        // keep in the same state, try again.
                    }
                    try {
                        if (shoot.checkIfInputValidSecond(inpuFromCli)) {
                            messageSendToCli = "shoot done, finish your shoot";
                        }
                    } catch (InvalidNameException e) {
                        messageSendToCli = e.toString();
                        // keep in the same state, try again.
                    }
                }
                else shoot.endShoot();
            }

        }
       class shootWithELECTROSCHE{
            shootWithELECTROSCHE(ActionShoot shoot){
                String inputFromCli = "";
                String messageSendToCli= "";

                messageSendToCli = "basic mode or mode advance? ";
                if (inputFromCli == "yes") {  // optehr mode
                    messageSendToCli = "other mode";
                    try {
                        shoot.payAmmoForOtherMode();
                        messageSendToCli = "have paid" + "\n" + shoot.getTargetNameWithSideEffect(); //controller-> view // this can be use before pay;
                    } catch (NotEnoughAmmosException e) {
                        messageSendToCli = e.toString();
                    }

                    try {
                        if (shoot.checkIfInputValidSecond(inputFromCli)) {
                            messageSendToCli = "shoot done, finish your shoot";
                        }
                    } catch (InvalidNameException e) {
                        messageSendToCli = e.toString();
                    }

                } else {  //basic mode
                    messageSendToCli = "basic mode";
                    messageSendToCli = shoot.getTargetNameBasic(); //controller to view
                    try {                //controller
                        if (shoot.checkIfInputValid(inputFromCli)) {        //view->controller
                            messageSendToCli = "shoot done, do you pay for side effect?";   // controller ask if he use side effect
                            // shoot done ask if they use side effect
                        }
                    } catch (InvalidNameException e) {
                        messageSendToCli = e.toString();
                        // keep in the same state, try again.
                    }
                }
            }
        }
       class shootWithMACHINEGUN{
             shootWithMACHINEGUN(ActionShoot shoot){
                 String inputFromCli = "";
                 String messageSendToCli= "";

                 messageSendToCli = shoot.getTargetNameBasic(); // send to cli
                 try {                //controller
                     if (shoot.checkIfInputValid(inputFromCli)) {        //view->controller
                         messageSendToCli = "shoot done, do you want to continue with side effect 1, or 2 or end?";
                     }
                 } catch (InvalidNameException e) {
                     messageSendToCli = e.toString();
                     // keep in the same state, try again.
                 }
                 if(inputFromCli == "1"){
                     try {
                         shoot.payAmmoForOtherMode();
                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to cli
                     } catch (NotEnoughAmmosException e) {
                         messageSendToCli = e.toString();
                     }


                     try {
                         if (shoot.checkIfInputValidSecond(inputFromCli)) {
                             messageSendToCli = "shoot done, finish your shoot";
                         }
                     } catch (InvalidNameException e) {
                         messageSendToCli = e.toString();
                     }
                     messageSendToCli = "continue?";
                     if(inputFromCli == "yes"){
                         try {
                             shoot.payAmmoForThirdSideEffect();
                             messageSendToCli ="have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to cli
                         } catch (NotEnoughAmmosException e) {
                             System.out.println(e);
                         }

                         try {
                             if (shoot.checkIfInputValidSecond(inputFromCli)) {
                                 messageSendToCli = "shoot done, finish your shoot";
                             }
                         } catch (InvalidNameException e) {
                             messageSendToCli = e.toString();
                         }

                     }

                 }


                 else if(inputFromCli == "2"){
                     try {
                         shoot.payAmmoForThirdSideEffect();
                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to cli
                     } catch (NotEnoughAmmosException e) {
                         messageSendToCli = e.toString();
                     }

                     try {
                         if (shoot.checkIfInputValidSecond(inputFromCli)) {
                             messageSendToCli = "shoot done, finish your shoot";
                         }
                     } catch (InvalidNameException e) {
                         messageSendToCli = e.toString();
                     }
                 }
             }

        }
        class shootWithTRACTORBEAM{
             shootWithTRACTORBEAM(ActionShoot shoot){
                 String inputFromCli = "";
                 String messageSendToCli= "";
                 messageSendToCli = "basic mode or in punish mode?";
                 if(inputFromCli == "basic"){
                     messageSendToCli = "input ONE target name, and move direction like: 1,2 means move rigt then move down";
                     try{
                         int[] direction = {1,0}; // get direction from input
                         shoot.moveTargetToSquareYouCanSeeMaxi2tep(inputFromCli,direction);
                     }
                     catch (InvalidNameException e){
                         messageSendToCli = e + "try again";
                     }
                 }
                 else{
                     try {
                         shoot.payAmmoForOtherMode();
                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to
                         messageSendToCli = "input ONE target name which is in cell at most two cells away from you";
                     } catch (NotEnoughAmmosException e) {
                         messageSendToCli = e.toString();
                     }
                     try{
                         shoot.moveATargetToYourCell(inputFromCli);
                         messageSendToCli = "shoot done, finish your shoot";
                     }
                     catch (InvalidNameException e){
                         System.out.println(e);
                         // try again
                     }

                 }
             }
        }
        class shootWithTHOR{
             shootWithTHOR(ActionShoot shoot){
                 String inputFromCli = "";
                 String messageSendToCli= "";
                 messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
                 try {                //2. controller
                     if (shoot.checkIfInputValid(inputFromCli)) {
                         messageSendToCli = "shoot done, do you want to continue with side effect?  input continue or end";
                     }
                 } catch (InvalidNameException e) {
                     messageSendToCli = e.toString();
                     // keep in the same state, try again.
                 }


             }
        }
    }




    }