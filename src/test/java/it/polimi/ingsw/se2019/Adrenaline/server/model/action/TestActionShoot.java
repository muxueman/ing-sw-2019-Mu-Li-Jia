package it.polimi.ingsw.se2019.Adrenaline.server.model.action;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.GenerationCell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.MapD;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidGrabException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidReloadException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidRunException;
import org.junit.Test;

public class TestActionShoot {
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
        try{
            player.addPowerupCard(board.extractPowerupcard());
            player.addPowerupCard(board.extractPowerupcard());
        }
        catch (InvalidGrabException e){
            System.out.println(e.toString());
        }

        player.dropPowerupAndGoNewCell(player.getPowerupsOwned().get(0).getCardName());
        System.out.println(player.getPowerupsOwned().size());
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
    @Test
    public void testPlayerReload() {
        setInfo();
        ActionGrab grab = new ActionGrab();
        grab.pickWeaponCrad(player, 2);
        int[] am = {2,2,2};
        player.setAmmoOwned(am);

        try{

            System.out.println( player.reloadWeapon("FLAMETHROWER"));
        }
        catch (InvalidReloadException e){
            System.out.println(e.toString());
        }


    }
    @Test
    public void testShoot() {
        setInfo();
        ActionGrab grab = new ActionGrab();
        grab.pickWeaponCrad(player, 2);
        //
        player.useWeapon("FLAMETHROWER");
//        player.useWeapon(player.getAvailableWeapon().get(0));
        ActionShoot shoot = new ActionShoot(player);
        System.out.println(player.getWeaponInUse().getCardName());
        System.out.println(shoot.getTargetNameBasic());
//        System.out.println(shoot.getTargetBasic().get(0).getUserName());

        System.out.println("shooter cell" + player.getCurrentCell().getCellID());
        System.out.println("target cell" + target.getCurrentCell().getCellID());
        System.out.println("target2 cell" + target2.getCurrentCell().getCellID());

        try{

            shoot.checkIfInputValid("mo xin");
            System.out.println(shoot.getTargetAttacked().size());
            System.out.println("xin:" + target2.getKillShootTrack().getDamageColorOnTrack());
            System.out.println("mo" + target.getKillShootTrack().getDamageColorOnTrack());
        }
        catch (InvalidNameException e){
            System.out.println(e);
        }

//        System.out.println(shoot.getTargetNameWithSideEffect());
//        try{
//            shoot.checkIfInputValidSecond("xin");
//            System.out.println(target2.getKillShootTrack().getDamageColorOnTrack());
//        }
//        catch (InvalidNameException e){
//            System.out.println(e);
//        }
//        System.out.println("red:"+player.getAmmoOwned()[0]);
//        System.out.println("blue:"+player.getAmmoOwned()[1]);
//        System.out.println("yellow:"+player.getAmmoOwned()[2]);
//        try {
//            shoot.payAmmoForThirdSideEffect();
//             System.out.println("have paid," + "\n" + shoot.getTargetNameWithThirdSideEffect()); // send to cli
//        } catch (NotEnoughAmmosException e) {
//            System.out.println(e);
//        }
//        try{
//            shoot.checkIfInputValidThird("xin");
//            System.out.println(target2.getKillShootTrack().getDamageColorOnTrack());
//        }catch (InvalidNameException e) {
//            System.out.println(e);
//        }


    }

//
//@Test
//public void shootTest(){
//        setInfo();
//}
//    public void testShootwithweapon() {
//
//         class shootWithLOCKRIFLE{
//            shootWithLOCKRIFLE(ActionShoot shoot){
//                String inpuFromCli = "";
//                String messageSendToCli= "";
//
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inpuFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect?  input continue or end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//                // if continue
//                if(inpuFromCli == "continue"){ //3.
//                    try {
//                        shoot.payAmmoForOtherMode();
//                        messageSendToCli = "have paid" + "\n" + shoot.getTargetNameWithSideEffect();
//                    } catch (NotEnoughAmmosException e) {
//                        messageSendToCli = e.toString();
//                        // keep in the same state, try again.
//                    }
//                    try {
//                        if (shoot.checkIfInputValidSecond(inpuFromCli)) {
//                            messageSendToCli = "shoot done, finish your shoot";
//                        }
//                    } catch (InvalidNameException e) {
//                        messageSendToCli = e.toString();
//                        // keep in the same state, try again.
//                    }
//                }
//                else shoot.endShoot();
//            }
//
//        }
//       class shootWithELECTROSCHE{
//            shootWithELECTROSCHE(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//
//                messageSendToCli = "basic mode or mode advance? ";
//                if (inputFromCli == "yes") {  // optehr mode
//                    messageSendToCli = "other mode";
//                    try {
//                        shoot.payAmmoForOtherMode();
//                        messageSendToCli = "have paid" + "\n" + shoot.getTargetNameWithSideEffect(); //controller-> view // this can be use before pay;
//                    } catch (NotEnoughAmmosException e) {
//                        messageSendToCli = e.toString();
//                    }
//
//                    try {
//                        if (shoot.checkIfInputValidSecond(inputFromCli)) {
//                            messageSendToCli = "shoot done, finish your shoot";
//                        }
//                    } catch (InvalidNameException e) {
//                        messageSendToCli = e.toString();
//                    }
//
//                } else {  //basic mode
//                    messageSendToCli = "basic mode";
//                    messageSendToCli = shoot.getTargetNameBasic(); //controller to view
//                    try {                //controller
//                        if (shoot.checkIfInputValid(inputFromCli)) {        //view->controller
//                            messageSendToCli = "shoot done, do you pay for side effect?";   // controller ask if he use side effect
//                            // shoot done ask if they use side effect
//                        }
//                    } catch (InvalidNameException e) {
//                        messageSendToCli = e.toString();
//                        // keep in the same state, try again.
//                    }
//                }
//            }
//        }
//       class shootWithMACHINEGUN{
//             shootWithMACHINEGUN(ActionShoot shoot){
//                 String inputFromCli = "";
//                 String messageSendToCli= "";
//
//                 messageSendToCli = shoot.getTargetNameBasic(); // send to cli
//                 try {                //controller
//                     if (shoot.checkIfInputValid(inputFromCli)) {        //view->controller
//                         messageSendToCli = "shoot done, do you want to continue with side effect 1, or 2 or end?";
//                     }
//                 } catch (InvalidNameException e) {
//                     messageSendToCli = e.toString();
//                     // keep in the same state, try again.
//                 }
//                 if(inputFromCli == "1"){
//                     try {
//                         shoot.payAmmoForOtherMode();
//                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to cli
//                     } catch (NotEnoughAmmosException e) {
//                         messageSendToCli = e.toString();
//                     }
//
//
//                     try {
//                         if (shoot.checkIfInputValidSecond(inputFromCli)) {
//                             messageSendToCli = "shoot done, finish your shoot";
//                         }
//                     } catch (InvalidNameException e) {
//                         messageSendToCli = e.toString();
//                     }
//                     messageSendToCli = "continue?";
//                     if(inputFromCli == "yes"){
//                         try {
//                             shoot.payAmmoForThirdSideEffect();
//                             messageSendToCli ="have paid," + "\n" + shoot.getTargetNameWithThirdSideEffect(); // send to cli
//                         } catch (NotEnoughAmmosException e) {
//                             System.out.println(e);
//                         }
//
//                         try {
//                             if (shoot.checkIfInputValidThird(inputFromCli)) {
//                                 messageSendToCli = "shoot done, finish your shoot";
//                             }
//                         } catch (InvalidNameException e) {
//                             messageSendToCli = e.toString();
//                         }
//
//                     }
//
//                 }
//
//
//                 else if(inputFromCli == "2"){
//                     try {
//                         shoot.payAmmoForThirdSideEffect();
//                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithThirdSideEffect(); // send to cli
//                     } catch (NotEnoughAmmosException e) {
//                         messageSendToCli = e.toString();
//                     }
//
//                     try {
//                         if (shoot.checkIfInputValidSecond(inputFromCli)) {
//                             messageSendToCli = "shoot done, finish your shoot";
//                         }
//                     } catch (InvalidNameException e) {
//                         messageSendToCli = e.toString();
//                     }
//                 }
//             }
//
//        }
//        class shootWithTRACTORBEAM{
//             shootWithTRACTORBEAM(ActionShoot shoot){
//                 String inputFromCli = "";
//                 String messageSendToCli= "";
//                 messageSendToCli = "basic mode or in punish mode?";
//                 if(inputFromCli == "basic"){
//                     messageSendToCli = "input ONE target name, and move direction like: 1,2 means move rigt then move down";
//                     try{
//                         int[] direction = {1,0}; // get direction from input
//                         shoot.moveTargetToSquareYouCanSeeMaxi2tep(inputFromCli,direction);
//                     }
//                     catch (InvalidNameException e){
//                         messageSendToCli = e + "try again";
//                     }
//                 }
//                 else{
//                     try {
//                         shoot.payAmmoForOtherMode();
//                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to
//                         messageSendToCli = "input ONE target name which is in cell at most two cells away from you";
//                     } catch (NotEnoughAmmosException e) {
//                         messageSendToCli = e.toString();
//                     }
//                     try{
//                         shoot.moveATargetToYourCell(inputFromCli);
//                         messageSendToCli = "shoot done, finish your shoot";
//                     }
//                     catch (InvalidNameException e){
//                         System.out.println(e);
//                         // try again
//                     }
//
//                 }
//             }
//        }
//        class shootWithTHOR{
//             shootWithTHOR(ActionShoot shoot){
//                 String inputFromCli = "";
//                 String messageSendToCli= "";
//                 messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                 try {                //2. controller
//                     if (shoot.checkIfInputValid(inputFromCli)) {
//                         messageSendToCli = "shoot done, do you want to continue with side effect?  input continue or end";
//                     }
//                 } catch (InvalidNameException e) {
//                     messageSendToCli = e.toString();
//                     // keep in the same state, try again.
//                 }
//                 if(inputFromCli == "continue") {
//                     try {
//                         shoot.payAmmoForOtherMode();
//                         messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithSideEffect(); // send to cli
//                     } catch (NotEnoughAmmosException e) {
//                         messageSendToCli = e.toString();
//                     }
//
//                     try {
//                         shoot.checkIfInputValidSecond(inputFromCli);
//                         messageSendToCli = "shoot done, do you want to continue with side effect?  input continue or end";
//                     } catch (InvalidNameException e) {
//                         messageSendToCli = e.toString();
//                     }
//
//                     if(inputFromCli == "continue"){
//                         try {
//                             shoot.payAmmoForThirdSideEffect();
//                             messageSendToCli = "have paid," + "\n" + shoot.getTargetNameWithThirdSideEffect(); // send to cli
//                         } catch (NotEnoughAmmosException e) {
//                             messageSendToCli = e.toString();
//                         }
//                         try{
//                             shoot.checkIfInputValidThird(inputFromCli);
//                             messageSendToCli = "shoot done, end your shoot";
//
//                         }catch (InvalidNameException e) {
//                             messageSendToCli = e.toString();
//                         }
//
//                     }
//                 }
//
//
//             }
//        }
//        class shootWithVORTEXCANNON{
//             shootWithVORTEXCANNON(ActionShoot shoot){
//                 String inputFromCli = "";
//                 String messageSendToCli= "";
//                 messageSendToCli = shoot.getTargetNameBasic();
//                 try {                //2. controller
//                     if (shoot.checkIfInputValid(inputFromCli)) {
//                         messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                     }
//                 } catch (InvalidNameException e) {
//                     messageSendToCli = e.toString();
//                     // keep in the same state, try again.
//                 }
//
//             }
//        }
//        class shootWithPLASMAGUN{
//            shootWithPLASMAGUN(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithFURANCE{
//            shootWithFURANCE(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithWHISPER{
//            shootWithWHISPER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//
//        class shootWithHEATSEEKER{
//            shootWithHEATSEEKER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//
//        class shootWithHELLION{
//            shootWithHELLION(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//
//        class shootWithFLAMETHROWER{
//            shootWithFLAMETHROWER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithZX2{
//            shootWithZX2(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, do you want to continue with side effect? end";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithGRENADELAUNCHER{
//            shootWithGRENADELAUNCHER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, type in the direction you want the target to move, no move with stay";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//                try{
//                    shoot.grenadeMove(inputFromCli);
//                    messageSendToCli = "move done, end your shoot or continue with side effect";
//                }
//                catch (InvalidRunException e){
//                    messageSendToCli = e.toString();
//                }
//
//            }
//        }
//
//        class shootWithSHOTGUN{
//             shootWithSHOTGUN(ActionShoot shoot){
//                 String inputFromCli = "";
//                 String messageSendToCli= "";
//                 messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                 try {                //2. controller
//                     if (shoot.checkIfInputValid(inputFromCli)) {
//                         messageSendToCli = "shoot done, type in the direction you want the target to move, no move with stay";
//                     }
//                 } catch (InvalidNameException e) {
//                     messageSendToCli = e.toString();
//                     // keep in the same state, try again.
//                 }
//                 try{
//                     shoot.grenadeMove(inputFromCli);
//                     messageSendToCli = "move done, end your shoot or continue with side effect";
//                 }
//                 catch (InvalidRunException e){
//                     messageSendToCli = e.toString();
//                 }
//
//             }
//         }
//
//        class shootWithROCKETLAUNCHER{
//            shootWithROCKETLAUNCHER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, type in the direction you want the target to move, no move with stay";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//                try{
//                    shoot.grenadeMove(inputFromCli);
//                    messageSendToCli = "move done, end your shoot or continue with side effect";
//                }
//                catch (InvalidRunException e){
//                    messageSendToCli = e.toString();
//                }
//
//            }
//        }
//        class shootWithPOWERGLOVE{
//            shootWithPOWERGLOVE(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, type in the direction you want the target to move, no move with stay";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithRAILGUN{
//            shootWithRAILGUN(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, continue with side effect or end your shoot?";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithSHOCKWAVE{
//            shootWithSHOCKWAVE(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, continue with side effect or end your shoot?";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithCYBERBLADE{
//            shootWithCYBERBLADE(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, continue with side effect or end your shoot?";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//        class shootWithSLEDGEAMMER{
//            shootWithSLEDGEAMMER(ActionShoot shoot){
//                String inputFromCli = "";
//                String messageSendToCli= "";
//                messageSendToCli = shoot.getTargetNameBasic(); // 1.send to cli
//                try {                //2. controller
//                    if (shoot.checkIfInputValid(inputFromCli)) {
//                        messageSendToCli = "shoot done, continue with side effect or end your shoot?";
//                    }
//                } catch (InvalidNameException e) {
//                    messageSendToCli = e.toString();
//                    // keep in the same state, try again.
//                }
//            }
//        }
//
//





//    }




}