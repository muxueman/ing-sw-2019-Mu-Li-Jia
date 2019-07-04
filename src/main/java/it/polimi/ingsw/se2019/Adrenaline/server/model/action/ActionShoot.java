package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import com.sun.org.apache.regexp.internal.RE;
import it.polimi.ingsw.se2019.Adrenaline.server.model.AmmoColor;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidRunException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.NotEnoughAmmosException;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.scene.control.SliderBuilder;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import sun.security.krb5.internal.PAData;

import java.io.Serializable;
import java.lang.management.PlatformLoggingMXBean;
import java.rmi.server.SkeletonNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 */

public class ActionShoot implements Serializable {

    private Player  shooter;
    private WeaponCard weaponCard;
    private ArrayList<Player> targetBasic;
    private ArrayList<Player> targetAttacked;
    private ArrayList<Player> targetSecond;

    public ActionShoot(Player shooter) {
        this.shooter = shooter;
        this.weaponCard = shooter.getWeaponInUse();
        targetBasic = new ArrayList<>();
        targetAttacked = new ArrayList<>();
        targetSecond = new ArrayList<>();
    }

    public int checkWeaponType(){
        if(weaponCard.getType() == 0) return 0; //check your target
        else return 1; // select you shoot mode, basic or advance?
    }

    public ArrayList<Player> getTargetBasic(){
        return targetBasic;
    }

    public ArrayList<Player> getTargetSecond() {
        return targetSecond;
    }

    protected ArrayList<Player> findTargetBasic() {
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": targetBasic.addAll(getTargetsYouCanSee(shooter));break;
            case "ELECTROSCYTHE": targetBasic.addAll(getTargetInYourCell(shooter)); break;
            case "MACHINE GUN": targetBasic.addAll(getTargetsYouCanSee(shooter));break;
            case "TRACTOR BEAM": targetBasic.addAll(shooter.getPlayBoard().getAllPlayers()); break;
            case "T.H.O.R.": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
            case "VORTEX CANNON":targetBasic.addAll(getOneCellAwayAndVortexPlayer()); break;
            case "PLASMA GUN": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
            case "FURANCE": targetBasic.addAll(getTargetsFromRoomYouCanSeeButNotYouAreIn(shooter)); break;
            case "HEATSEKER": {
                for(Player p : shooter.getBoard().getAllPlayers()){
                    if(getTargetsYouCanSee(shooter).contains(p)) continue;
                    else targetBasic.add(p);
                    }
                } break;
            case "WHISPER": targetBasic.addAll(getTargetVisibleTwoCellsAway(shooter)); break;
            case "HELLION": targetBasic.addAll(getTargetVisibleOneCellAway(shooter)); break;
            case "FLAMETHROWER": targetBasic.addAll(getTargetOneCellAway(shooter)); break;
            case "ZX-2": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
            case "GRENA DE LAUNCHER": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
            case "SHOTGUN":  targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
            case "ROCKET LAUNCHER": targetBasic.addAll(getTargetsYouCanSeeButNotOnYourSquare(shooter)); break;
            case "POWER GLOVE": targetBasic.addAll(getTargetOneCellAway(shooter)); break;
            case "RAILGUN": targetBasic.addAll(getTargetCardinal(shooter)); break;
            case "SHOCKWAVE": targetBasic.addAll(getTargetOneCellAway(shooter));break;
            case "CYBERBLADE": targetBasic.addAll(getTargetInYourCell(shooter)); break;
            case "SLEDGEHAMMER": targetBasic.addAll(getTargetInYourCell(shooter)); break;
        }
        return targetBasic;
    }


    public ArrayList<Player> removePlayerInSameCell(ArrayList<Player> players){
        ArrayList<Player> toPlayer = new ArrayList<>();
        for(Player p : players){
            if(p.getCurrentCell().getCellPlayers().size() == 1) toPlayer.add(p);
        }
        return toPlayer;
    }
//    public ArrayList<Player> getFlamethrowerTarget(ArrayList<Player> players){
//        ArrayList<Player> toPlayer = new ArrayList<>();
//        for(Player p : players){
//            if(p.getCurrentCell().getCellPlayers().size() == 1) toPlayer.add(p);
//        }
//        return toPlayer;
//    }

    protected ArrayList<Player> getTargetsArrayFromName(String names){
        ArrayList<Player> selectedPlayer = new ArrayList<>();
        for(Player p : shooter.getPlayBoard().getAllPlayers()){
            if(names.contains(p.getUserName()))
             selectedPlayer.add(p);
        }
        return selectedPlayer;
    }

    public boolean checkIfInputValid(String names) throws InvalidNameException{
        if(getTargetBasic().size() == 0 || weaponCard.getBasicDamageVision() == 0){  // case: dont need to choose target or nobody valid
            dealBasicDamageToTarget(targetBasic);
            return true; // dont do anything
        }
        if(weaponCard.getCardName().equals("FLAMETHROWER")) {
            targetAttacked.addAll(getTargetsArrayFromName(names));
            if(checkIfInputValidFlamethrower()) dealBasicDamageToTarget(targetAttacked);
            return true;
        }
        boolean check = true;
        for(Player p : getTargetsArrayFromName(names)){
            if(getTargetBasic().contains(p)) continue;
            else {
                check = false;
                throw new InvalidNameException();      // target not in vision
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getBasicDamageVision()) {
            check = false;
            throw new InvalidNameException();      //selected target more than
        }
        if(check){
            targetAttacked.addAll(getTargetsArrayFromName(names));
            dealBasicDamageToTarget(targetAttacked);
            shooter.getBoard().checkIfAnyPlayerDie();
        }
        return check;
    }
    public boolean checkIfInputValidFlamethrower(){
        switch (targetAttacked.size()){
            case 0: return true;
            case 1: if(targetBasic.contains(targetAttacked.get(0))) return true;
            case 2: if(targetBasic.contains(targetAttacked.get(0)) || targetBasic.get(0).getCurrentCell().getNextCell(
                    shooter.getBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), targetAttacked
                            .get(0).getCurrentCell())) == targetAttacked.get(1).getCurrentCell()||
                    targetBasic.contains(targetAttacked.get(1)) || targetBasic.get(1).getCurrentCell().getNextCell(
                    shooter.getBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), targetAttacked
                            .get(1).getCurrentCell())) == targetAttacked.get(0).getCurrentCell()) return true;
            }

        return false;
    }

    public boolean checkIfInputValidSecond(String names) throws InvalidNameException{
        if(targetSecond.size() == 0 || weaponCard.getEffectDamageVison() == 0) {
            dealSideEffectDamageToTarget(targetSecond);
            return true;
        }
        boolean check = true;
        for(Player p : getTargetsArrayFromName(names)){
            if(getTargetSecond().contains(p)) continue;
            else {
                check = false;
                throw new InvalidNameException();
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getEffectDamageVison()) {
            check = false;
            throw new InvalidNameException();
        }
        if(check){
            dealSideEffectDamageToTarget(getTargetsArrayFromName(names));
            targetAttacked.addAll(getTargetsArrayFromName(names));
        }
        return check;
    }
    public boolean checkIfInputValidThird(String names) throws InvalidNameException{
        if(targetSecond.size() == 0 || weaponCard.getEffectDamageVison() == 0) {
            dealSideEffectDamageToTarget(targetSecond);
            return true;
        }
        boolean check = true;
        for(Player p : getTargetsArrayFromName(names)){
            if(getTargetSecond().contains(p)) continue;
            else {
                check = false;
                throw new InvalidNameException();
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getEffectDamageVison()) {
            check = false;
            throw new InvalidNameException();
        }
        if(check){
            dealThirdSideEffectDamageToTarget(getTargetsArrayFromName(names));
            targetAttacked.addAll(getTargetsArrayFromName(names));
        }
        return check;
    }

    // consume ammo for side effect, give available target,

    public String getTargetNameBasic(){
        String targetString = "Targets: ";
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": targetString += transferFromPlayerToString(findTargetBasic());
            targetString += "\n" + "type in the name of ONE target"; break;
            case "ELECTROSCYTHE": targetString += transferFromPlayerToString(findTargetBasic());
            targetString += "\n" + "you dont have to choose a target, deal damage to them?"; break;
            case "MACHINE GUN": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE or TWO target";break;
            case "TRACTOR BEAM": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, and direction you want it to move, maxi two steps";break;
            case "T.H.O.R." : targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target";break;
            case "PLASMA GUN":  targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "VORTEX CANNON": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case"FURNACE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, ALL player within the same room will get damage"; break;
            case "WHISPER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "HEATSEEKER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "HELLION": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "FLAMETHROWER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, or possibly another target that is in the same direction one cell away from your first target"; break;
            case "ZX-2": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "GRENADE LAUNCHER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, after shoot you can move the target 1 cell"; break;
            case "SHOTGUN": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, after shoot you can move the target 1 cell"; break;
            case "ROCKET LAUNCHER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, after shoot you can move the target 1 cell"; break;
            case "POWER GLOVE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, you will move on its square and give a shot"; break;
            case "RAILGUN": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "SHOCKWAVE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "CYBERBLADE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "SLEDGEHAMMER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
        }
        if(getTargetBasic().size() == 0) targetString += "\n" + "nobody to shoot,";
        return targetString;
    }
    public String getTargetNameWithSideEffect(){
        if(weaponCard.getType() == 1){
            targetBasic = findTargetBasic();
        }
        if(targetBasic.size() == 0){
            return "nobody to shoot";
        }
        String secondTargetName = "Second Target:" + "\n";
        targetSecond.addAll(targetBasic);
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": targetSecond.remove(targetAttacked.get(0));
                secondTargetName += transferFromPlayerToString(targetSecond);
                break;
            case "ELECTROSCYTHE":  secondTargetName += transferFromPlayerToString(targetSecond);
                break;
            case "MACHINE GUN": secondTargetName += transferFromPlayerToString(targetAttacked);
                break;
            case "T.H.O.R.": targetSecond.clear(); targetSecond.addAll(getTargetsYouCanSee(targetAttacked.get(0)));
                targetSecond.remove(shooter);
                secondTargetName += transferFromPlayerToString(targetSecond);break;
        }
        return secondTargetName;

    }


    public String getTargetNameWithThirdSideEffect(){
        String thirdTargetName = "";
        switch (weaponCard.getCardName()){
            case "MACHINE GUN":targetSecond.clear();
                for(Player p : targetBasic) {
                    if (!targetAttacked.contains(p)) targetSecond.add(p);
                }
                thirdTargetName += transferFromPlayerToString(targetSecond);
                break;
            case "PLASMA GUN": break;
            case "T.H.O.R.": targetSecond.clear();targetSecond.addAll(getTargetsYouCanSee(targetAttacked.get(targetAttacked.size()-1)));
                thirdTargetName += transferFromPlayerToString(targetSecond);break;

        }
        return thirdTargetName;
    }

    public Cell getCellFromID(int CellID){
        for(Cell c : shooter.getBoard().getMap().getAllCells()){
            if(c.getCellID() == CellID) return c;
        }
        return null;
    }

//    public boolean moveAtargetMaxiTwoStepToCellVisible(String targetAndCell, int CellDestination) throws InvalidNameException{
//        boolean succ = false;
//        Player target = getTargetsArrayFromName(targetAndCell).get(0);
//        if(getTargetsArrayFromName(targetAndCell).size() > 1 || getTargetsArrayFromName(targetAndCell).size() == 0) throw new InvalidNameException();  // one target
//        for(Cell c : getCleanCellArray(shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell())))
//            if(CellDestination == c.getCellID()){
//                succ = true;
//                break;
//            } // destination true
//        if(!succ)  throw new InvalidNameException();
//        if(target.getBoard().getMap().getVisibleTwoAwayCells(target.getCurrentCell()).contains(getCellFromID(CellDestination)) ||
//                target.getBoard().getMap().getAvailableOneWalkCell(target.getCurrentCell()).contains(getCellFromID(CellDestination)))// MaxitwoCellAway
//        {
//            target.setCurrentCell(getCellFromID(CellDestination));
//            return true;
//        }
//        else throw new InvalidNameException();
//    }


    public String transferFromPlayerToString(ArrayList<Player> targetBasic){
        String targetName = "";
        for(Player p : targetBasic) {
            targetName += p.getUsername();
            targetName += "\t";
        }
        return targetName;
    }
    // this is be used to check the num of target is not more than the maximum



    public boolean checkBasicTargetNum(ArrayList<Player> target, WeaponCard weaponCard){
        boolean shootable = false;
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": if(target.size() == 1) shootable = true; break;
            case "MACHINE GUN": if(target.size() <= 2) shootable = true; break;
            case "TRACTOR BEAM": if(target.size() == 1) shootable = true; break;
            case "T.H.O.R.": if(target.size()  == 1) shootable = true; break;
            case "VORTEX CANNON": if(target.size() == 1) shootable = true; break;
            case "PLASMA GUN":  if(target.size() == 1) shootable = true; break;
            case "FURANCE": if(target.size() == 1) shootable = true; break;
            case "HEATSEKER": if(target.size() == 1) shootable = true; break;
            case "WHISPER": if(target.size() == 1) shootable = true; break;
            case "HELLION": if(target.size() ==1) shootable = true; break;
            case "FLAMETHROWER": if(target.size() <= 2) shootable = true; break;
            case "ZX-2": if(target.size() == 1) shootable = true; break;
            case "GRENA DE LAUNCHER": if(target.size() == 1)  shootable = true; break;
            case "SHOTGUN":  if(target.size() == 1) shootable = true; break;
            case "ROCKET LAUNCHER": if(target.size() == 1) shootable = true; break;
            case "POWER GLOVE": if(target.size() == 1) shootable = true; break;
            case "RAILGUN": if(target.size() == 1) shootable = true; break;
            case "SHOCKWAVE": if(target.size() <= 3) shootable = true; break;
            case "CYBERBLADE": if(target.size() == 1) shootable = true; break;
            case "SLEDGEHAMMER": if(target.size() == 1) shootable = true; break;
        }
        return shootable;
    }
// when the player click shoot， check the target is valid or not，
    public boolean checkBasicShootTarget(Player shooter, ArrayList<Player> target, WeaponCard weaponCard){
        boolean shootable = false;
        int i = 0;
        while(i < target.size()){
            if(weaponCard.getCardName() == "FLAMETHROWER"){
                if(target.size() == 1)
                    if (getTargetOneCellAway(shooter).contains(target.get(i))) shootable = true;
                else {
                    int direction = shooter.getPlayBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), target.get(i).getCurrentCell());
                    if(getTargetOneCellAway(shooter).contains(target.get(i)) && direction == shooter.getPlayBoard().getMap().getDirectionFromCellToCell(target.get(i).getCurrentCell(), target.get(i+1).getCurrentCell()))
                        shootable = true;
                }
                break;
            }
            else if(weaponCard.getCardName() == "SHOCKWAVE"){
                if(target.size() == 1){
                    if(getTargetOneCellAway(shooter).contains(target.get(i))) shootable = true;
                else if(target.size() == 2)
                    if(getTargetOneCellAway(shooter).contains(target.get(i)) && getTargetOneCellAway(shooter).contains(target.get(i+1)) && target.get(i).getCurrentCell() != target.get(i+1).getCurrentCell()) shootable = true;
                }
                else if(target.size() == 3)
                    if(getTargetOneCellAway(shooter).contains(target.get(i)) && getTargetOneCellAway(shooter).contains(target.get(i+1)) && getTargetOneCellAway(shooter).contains(target.get(i+2))
                    && target.get(i).getCurrentCell() != target.get(i+1).getCurrentCell() && target.get(i+1).getCurrentCell() != target.get(i+2).getCurrentCell() && target.get(i).getCurrentCell() != target.get(i+2).getCurrentCell())
                        shootable = true;
                break;
            }
            else if(weaponCard.getCardName() == "")
            switch (weaponCard.getCardName()){
                case "LOCK RIFLE": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "MACHINE GUN": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "TRACTOR BEAM": if(getTargetVisibleTwoCellsAway(shooter).contains(target.get(i))) {shootable = true; } break;
                case "T.H.O.R.": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "VORTEX CANNON": if(getVortexTarget().contains(target.get(i))) {shootable = true;} break;
                case "PLASMA GUN": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "FURNACE": if(getTargetsFromRoomYouCanSeeButNotYouAreIn(shooter).contains(target.get(i))) {shootable = true; } break;
                case "WHISPER": if(getTargetVisibleTwoCellsAway(shooter).contains(target.get(i))) {shootable = true; } break;
                case "HEATSEEKER": if(!getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "HELLION": if(getTargetVisibleOneCellAway(shooter).contains(target.get(i))) {shootable = true; } break;
//                case "FLAMETHROWER": if(getTargetOneCellAway(shooter).contains(target.get(i)) &&) {shootable = true; } break;
                case "ZX-2": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "GRENADE LAUNCHER": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "SHOTGUN": if(getTargetInYourCell(shooter).contains(target.get(i))) {shootable = true; } break;
                case "ROCKET LUNCHER": if(getTargetVisibleOneCellAway(shooter).contains(target.get(i))) {shootable = true; } break;
                case "POWER GLOVE": if(getTargetOneCellAway(shooter).contains(target.get(i))) {shootable = true; } break;
                case "RAILGUN": if(getTargetCardinal(shooter).contains(target.get(i))) {shootable = true; } break;
                case "CYBERBLADE": if(getTargetInYourCell(shooter).contains(target.get(i))) {shootable = true; } break;
                case "SLEDGEHAMMER": if(getTargetInYourCell(shooter).contains(target.get(i))) {shootable = true; } break;
            }
            i++;
        }
        return shootable;
    }
// after click shoot, all the target will add damage / mark on their killshoottrack


//    public void dealBasicDamageToTarget(String name){
//       Player target = getTargetFromName(name);
//       switch (weaponCard.getCardName()){
//           case "LOCK RIFLE": dealDamageMarkToTarget(shooter, 2, 1, target);
//       }
//       targetAttacked.add(target);
//    }
    protected void moveTargetOneWalkToVortex(Player target){
        Cell targetCell = target.getCurrentCell();
        for(Cell c : shooter.getBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell()))
        {   int i = 0;
            while(i < 4){
                if(c.getNextCell(i) == targetCell) {
                    target.setCurrentCell(c);
                    break;
                }
                i++;
            }
        }
    }

    protected void dealBasicDamageToTarget(ArrayList<Player> target) {
        if(target.size() == 0) return;
        boolean shoot = false;
        int i = 0;
        if (weaponCard.getCardName() == "FURANCE") {
            target.addAll(getTargetFromCell(target.get(i).getPlayBoard().getMap().getCellsWithinRoom(target.get(i).getCurrentCell())));
            target.remove(target.get(i));
        }
        while (i < target.size()) {
            switch (weaponCard.getCardName()) {
                case "LOCK RIFLE":
                    dealDamageMarkToTarget(shooter, 2, 1, target.get(i));
                    break;
                case "ELECTROSCYTHE": dealDamageMarkToTarget(shooter, 1,0, target.get(i));
                    break;
                case "MACHINE GUN":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "TRACTOR BEAM":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "T.H.O.R.":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "VORTEX CANNON":
                    if(!getVortexTarget().contains(target.get(i))){
                        moveTargetOneWalkToVortex(target.get(i));
                    }
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "PLASMA GUN":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "FURANCE":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "HEATSEKER":
                    dealDamageMarkToTarget(shooter, 3, 0, target.get(i));
                    break;
                case "WHISPER":
                    dealDamageMarkToTarget(shooter, 3, 1, target.get(i));
                    break;
                case "HELLION":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    for(Player p : target.get(i).getCurrentCell().getCellPlayers()){
                        dealDamageMarkToTarget(shooter,0,1, p);
                    }
                    break;

                case "FLAMETHROWER":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "ZX-2":
                    dealDamageMarkToTarget(shooter, 1, 2, target.get(i));
                    break;
                case "GRENADE LAUNCHER":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "SHOTGUN":
                    dealDamageMarkToTarget(shooter, 3, 1, target.get(i));
                    break;
                case "ROCKET LAUNCHER":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "POWER GLOVE":
                    shooter.setCurrentCell(target.get(i).getCurrentCell());
                    dealDamageMarkToTarget(shooter, 1, 2, target.get(i));
                case "RAILGUN":
                    dealDamageMarkToTarget(shooter, 3, 0, target.get(i));
                    break;
                case "SHOCKWAVE":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "CYBERBLADE":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "SLEDGEHAMMER":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
            }
            i++;
        }

    }

    public void dealSideEffectDamageToTarget(ArrayList<Player> target){
        int i = 0;
        while (i < target.size()) {
            switch (weaponCard.getCardName()) {
                case "LOCK RIFLE":
                    dealDamageMarkToTarget(shooter, 0, 1, target.get(i));
                    break;
                case "ELECTROSCYTHE":
                    dealDamageMarkToTarget(shooter, 2,0, target.get(i));
                    break;
                case "MACHINE GUN":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "TRACTOR BEAM":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "T.H.O.R.":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "VORTEX CANNON":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "PLASMA GUN":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "FURANCE":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "HEATSEKER":
                    dealDamageMarkToTarget(shooter, 3, 0, target.get(i));
                    break;
                case "WHISPER":
                    dealDamageMarkToTarget(shooter, 3, 1, target.get(i));
                    break;
                case "HELLION":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    int j = 0;
                    while (j < target.get(i).getCurrentCell().getCellPlayers().size()) {
                        dealDamageMarkToTarget(shooter, 0, 1, target.get(i).getCurrentCell().getCellPlayers().get(j));
                        j++;
                    }
                    break;

                case "FLAMETHROWER":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "ZX-2":
                    dealDamageMarkToTarget(shooter, 1, 2, target.get(i));
                    break;
                case "GRENA DE LAUNCHER":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "SHOTGUN":
                    dealDamageMarkToTarget(shooter, 3, 1, target.get(i));
                    break;
                case "ROCKET LAUNCHER":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "POWER GLOVE":
                    shooter.setCurrentCell(target.get(i).getCurrentCell());
                    dealDamageMarkToTarget(shooter, 1, 2, target.get(i));
                case "RAILGUN":
                    dealDamageMarkToTarget(shooter, 3, 0, target.get(i));
                    break;
                case "SHOCKWAVE":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "CYBERBLADE":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "SLEDGEHAMMER":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
            }
            i++;
        }
    }

    public void dealThirdSideEffectDamageToTarget(ArrayList<Player> target){
        int i = 0;
        while (i < target.size()) {
            switch (weaponCard.getCardName()) {
                case "MACHINE GUN":
                    dealDamageMarkToTarget(shooter, 1, 0, target.get(i));
                    break;
                case "T.H.O.R.":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "PLASMA GUN":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "ROCKET LAUNCHER":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
                case "CYBERBLADE":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
                    break;
            }
            i++;
        }
    }
// this is the special case that if the player use "ELECTROSYTHE", they dont have to choose targets.
    public void dealBasicDamageToTarget(Player shooter){
        int i = 0;
        while(i < shooter.getCurrentCell().getCellPlayers().size()){
            if(shooter != shooter.getCurrentCell().getCellPlayers().get(i))
                dealDamageMarkToTarget(shooter, 1,0, shooter.getCurrentCell().getCellPlayers().get(i));
            else {
                i++;
                continue;
            }
            i++;
        }
    }

//use ELECTROSCYTHE in rapper mode
    public void dealOtherModeDamageToTarget(Player shooter){
        int i = 0;
        while(i < shooter.getCurrentCell().getCellPlayers().size()){
            if(shooter != shooter.getCurrentCell().getCellPlayers().get(i))
                dealDamageMarkToTarget(shooter, 2,0, shooter.getCurrentCell().getCellPlayers().get(i));
            else {
                i++;
                continue;
            }
            i++;
        }
    }
// this is used to deal damage to targets when the shooter use weapon in other mode
    public void shootTargetInOtherMode(Player shooter, ArrayList<Player> target, WeaponCard weaponCard){
        int i = 0;
        if(weaponCard.getCardName().equals( "FLAMETHROWER")){
            dealDamageToEveryoneInCell(shooter, 2,0,target.get(0));
            dealDamageToEveryoneInCell(shooter, 1, 0, target.get(1));

        }
        else if(weaponCard.getCardName().equals("SHOCKWAVE")){
            dealDamageToAllPlayerOneMoveAway(shooter, 1,0);
        }
        while(i <  target.size()){
            switch (weaponCard.getCardName()){
                case "TRACTOR BEAM": dealDamageMarkToTarget(shooter, 3, 0, target.get(i)); break;
                case "FURANCE": dealDamageToEveryoneInCell(shooter, 1, 1, target.get(i));break;
                case "HELLION":  dealDamageMarkToTarget(shooter, 1,0,target.get(i));
                                 dealDamageToEveryoneInCell(shooter, 0,2,target.get(i));break;
//                case "FLAMETHROWER": dealDamageMarkToTarget(shooter, 2,0, target.get(i));break;
                case "ZX-2": dealDamageMarkToTarget(shooter, 0,1,target.get(i)); break;
                case "SHOTGUN": dealDamageMarkToTarget(shooter,2,0, target.get(i)); break;
//                case "POWER GLOVE": ; break;
                case "RAILGUN":  dealDamageMarkToTarget(shooter,2,0, target.get(i)); break;
                case "SLEDGEHAMMER": dealDamageMarkToTarget(shooter, 3, 0, target.get(i)); break;
            }
            i++;
        }
    }
//
//    public void shootTargetWithEffect(ArrayList<Player> target, WeaponCard weaponCard){
//
//        switch (weaponCard.getCardName()){
//            case "LOCK RIFLE": if(target.size() == 1 && target.get(0)!= this.targetBasic.get(0) && getTargetsYouCanSee(shooter).contains(target.get(0))){
//                shootable = true;
//                dealDamageMarkToTarget(shooter, 0,1, target.get(0));
//            } break;
//            case "MACHINE GUN": if(target.size() <= 2) {
//                shootable = true;
//            } break;
//            case "TRACTOR BEAM": if(target.size() == 1) shootable = true; break;
//            case "T.H.O.R": if(target.size()  == 1) shootable = true; break;
//            case "VORTEX CANNON": if(target.size() == 1) shootable = true; break;
//            case "PLASMA GUN":  if(target.size() == 1) shootable = true; break;
//            case "FURANCE": if(target.size() == 1) shootable = true; break;
//            case "HEATSEKER": if(target.size() == 1) shootable = true; break;
//            case "WHISPER": if(target.size() == 1) shootable = true; break;
//            case "HELLION": if(target.size() ==1) shootable = true; break;
//            case "FLAMETHROWER": if(target.size() <= 2) shootable = true; break;
//            case "ZX-2": if(target.size() == 1) shootable = true; break;
//            case "GRENA DE LAUNCHER": if(target.size() == 1)  shootable = true; break;
//            case "SHOTGUN":  if(target.size() == 1) shootable = true; break;
//            case "ROCKET LAUNCHER": if(target.size() == 1) shootable = true; break;
//            case "POWER GLOVE": if(target.size() == 1) shootable = true; break;
//            case "RAILGUN": if(target.size() == 1) shootable = true; break;
//            case "SHOCKWAVE": if(target.size() <= 3) shootable = true; break;
//            case "CYBERBLADE": if(target.size() == 1) shootable = true; break;
//            case "SLEDGEHAMMER": if(target.size() == 1) shootable = true; break;
//        }
//
//    }


//    public boolean moveTargetToVortexOneStep(Player shooter, Player target, int direction){
//        if(shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter
//        .getCurrentCell()).contains(target.getCurrentCell().getNextCell(direction))){
//            moveTarget(target, direction);
//            return true;
//        }
//        else return false;
//    }
    public boolean moveTargetToSquareYouCanSeeMaxi2tep(String targetName, int[] direction) throws InvalidNameException{
        Player target = getTargetsArrayFromName(targetName).get(0);
        if(getTargetsArrayFromName(targetName).size() != 1) throw new InvalidNameException();
        boolean done = false;
        int i = 0;
        if(direction.length > 2) throw new InvalidNameException();
        try{
            while(i < direction.length){
                moveTarget(target, direction[i]);
                i++;
            }
        }
        catch (InvalidRunException e){
            throw new InvalidNameException();
        }
        if(getTargetsYouCanSee(shooter).contains(target)) dealDamageMarkToTarget(shooter,1,0, target);
        else throw new InvalidNameException();
        return done;
    }

    public ArrayList<Player> getTargetsYouCanSee(Player shooter){
        ArrayList<Cell> cells = new ArrayList<>();
        cells= shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
        //去重
        return getTargetFromCell(getCleanCellArray(cells));
    }
    public ArrayList<Player> getTargetsYouCanSeeButNotOnYourSquare(Player shooter){
        ArrayList<Cell> cells = new ArrayList<>();
        cells= shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
        //去重
        cells = getCleanCellArray(cells);
        cells.remove(shooter.getCurrentCell());
        return getTargetFromCell(cells);
    }

    public ArrayList<Cell> getCleanCellArray(ArrayList<Cell> cells){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cells.stream().forEach(
                p ->{
                    if(!cellOfTargets.contains(p)){
                        cellOfTargets.add(p);
                    }
                }
        );
        return cellOfTargets;
    }

    private ArrayList<Player> getVortexTarget(){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    private ArrayList<Player> getOneCellAwayAndVortexPlayer(){
        ArrayList<Cell> cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
        cellOfTargets.addAll(getCellsOneWalkAwayFromCells(cellOfTargets));
        cellOfTargets = getCleanCellArray(cellOfTargets);
        cellOfTargets.remove(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    private ArrayList<Cell> getCellsOneWalkAwayFromCells(ArrayList<Cell> fromCells){
        ArrayList<Cell> toCells = new ArrayList<>();
        for(Cell c : fromCells){
            int i = 0;
            while(i < 4){
                if(c.getNextCell(i) != null) toCells.add(c.getNextCell(i));
                i++;
            }
        }
        return toCells;
    }
    protected ArrayList<Player> getTargetsFromRoomYouCanSeeButNotYouAreIn(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int cellIndex = 0;
        int playerIndex = 0;
        while(cellIndex < shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).size()){
            cellOfTargets.add(shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell()).get(cellIndex));
            cellIndex++;
        }
        cellIndex = 0;
        //从所有的可见的中再删除和自己在同一room的cell
        while(cellIndex < shooter.getPlayBoard().getMap().getCellsWithinRoom(shooter.getCurrentCell()).size()) {
            cellOfTargets.remove(shooter.getPlayBoard().getMap().getCellsWithinRoom(shooter.getCurrentCell()).get(cellIndex));
            cellIndex++;
        }
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }
    protected ArrayList<Player> getTargetVisibleTwoCellsAway(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleTwoAwayCells(shooter.getCurrentCell());
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }
    //get targets from visible cells but at least one cell away
    protected ArrayList<Player> getTargetVisibleOneCellAway(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    //hget targets from one move away cell
    protected ArrayList<Player> getTargetOneCellAway(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int i = 0;
        while(i < 4){
            if(shooter.getCurrentCell().getNextCell(i)!= null)
                cellOfTargets.add(shooter.getCurrentCell().getNextCell(i));
            i++;
        }
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }
    protected ArrayList<Player> getTargetInYourCell(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets.add(shooter.getCurrentCell());
//        ArrayList<Player> players = getTargetFromCell(cellOfTargets);
//        players.remove(shooter);
        return getTargetFromCell(cellOfTargets);
    }
    protected ArrayList<Player> getTargetCardinal(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        Player shooter1 = shooter;
        int i = 0;
        while(i < 4){
            cellOfTargets.addAll(shooter.getPlayBoard().getMap().getAllCardinalCells(shooter.getCurrentCell(), i));
            i++;
        }
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }

    public ArrayList<Player> getTargetFromCell( ArrayList<Cell> cellOfTargets){
        ArrayList<Player> targetsInVision = new ArrayList<>();
        int cellIndex = 0;
        int playerIndex = 0;
        for(Cell c : cellOfTargets){
            targetsInVision.addAll(c.getCellPlayers());
        }
        targetsInVision.remove(shooter); // 攻击对象不包括shooter.
        return  targetsInVision;
    }

    protected void dealDamageMarkToTarget(Player shooter, int damage, int mark, Player target){
        target.getKillShootTrack().beAttacked(shooter, damage, mark);
    }


    protected boolean payAmmoForAtherMode(Player shooter, AmmoColor color){
        boolean available = false;
        switch (color){
            case RED: if(shooter.getAmmoOwned()[0]>0){
                available = true;
                shooter.consumeAmmo(color);
            } break;
            case BLUE: if(shooter.getAmmoOwned()[1]>0) {
                available = true;
                shooter.consumeAmmo(color);
            } break;
            case YELLOW: if(shooter.getAmmoOwned()[2]>0) {
                available = true;
                shooter.consumeAmmo(color);
            } break;
        }
        return available;
    }


    public boolean payAmmoForOtherMode() throws NotEnoughAmmosException{
        boolean paid = false;
        switch(weaponCard.getCardName()){
            case "LOCK RIFLE": paid = payAmmoForAtherMode(shooter, AmmoColor.RED); break;
            case "ELECTROSCYTHE":paid = payAmmoForAtherMode(shooter, AmmoColor.RED); paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "MACHINE GUN": paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW); break;
            case "TRACTOR BEAM": paid = payAmmoForAtherMode(shooter, AmmoColor.RED);payAmmoForAtherMode(shooter, AmmoColor.YELLOW);
            break;
            case "T.H.O.R.": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "VORTEX CANNON": paid = payAmmoForAtherMode(shooter, AmmoColor.RED); break;
            case "PLASMA GUN": paid = true;  break;
            case "FURANCE": paid = true; break;
            case "HEATSEKER": paid = true; break;
            case "WHISPER": paid = true; break;
            case "HELLION": paid = payAmmoForAtherMode(shooter, AmmoColor.RED);break;

            case "FLAMETHROWER": paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW); paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW);break;
            case "ZX-2": paid = true; break;
            case "GRENA DE LAUNCHER": paid = payAmmoForAtherMode(shooter, AmmoColor.RED); break;
            case "SHOTGUN":  paid = true; break;
            case "ROCKET LAUNCHER": paid = payAmmoForAtherMode(shooter,  AmmoColor.BLUE); break;
            case "POWER GLOVE": paid = payAmmoForAtherMode(shooter,  AmmoColor.BLUE); break;
            case "RAILGUN":  paid = true; break;
            case "SHOCKWAVE":  paid = payAmmoForAtherMode(shooter,  AmmoColor.YELLOW); break;
            case "CYBERBLADE":  paid = true; break;
            case "SLEDGEHAMMER":  paid = payAmmoForAtherMode(shooter,  AmmoColor.RED); break;
        }
        if(!paid) throw new NotEnoughAmmosException();
        else return paid;
    }
    public boolean payAmmoForThirdSideEffect() throws NotEnoughAmmosException{
        boolean paid = false;
        switch(weaponCard.getCardName()){
            case "MACHINE GUN": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "T.H.O.R.": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "PLASMA GUN": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE);  break;
            case "ROCKET LAUNCHER": paid = payAmmoForAtherMode(shooter,  AmmoColor.YELLOW); break;
            case "CYBERBLADE":  paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW);; break;
        }
        if(!paid) throw new NotEnoughAmmosException();
        return paid;
    }
    public void moveATargetToYourCell(String targetname) throws InvalidNameException{
        Player target = getTargetsArrayFromName(targetname).get(0);
        if(getTargetsArrayFromName(targetname).size() != 1) throw new InvalidNameException();
        if(shooter.getCurrentCell().getCellID() == target.getCurrentCell().getCellID()) return;
        for(Cell c : shooter.getCurrentCell().getAdjacentCells()){
            if(c == target.getCurrentCell()|| c.getAdjacentCells()[0] == target.getCurrentCell() ||
                c.getAdjacentCells()[1] == target.getCurrentCell() ||
                    c.getAdjacentCells()[2] == target.getCurrentCell() ||
                    c.getAdjacentCells()[3] == target.getCurrentCell())
                target.setCurrentCell(shooter.getCurrentCell());
        }
        if(target.getCurrentCell().getCellID() != shooter.getCurrentCell().getCellID()) throw new InvalidNameException();
        dealDamageMarkToTarget(shooter, 3,0,target);
    }
    public void moveTarget(Player target, int direction) throws InvalidRunException{
        if(target.getCurrentCell().getNextCell(direction) == null)
            throw new InvalidRunException();
        target.setCurrentCell(target.getCurrentCell().getNextCell(direction));
        // catch cannotMoveException;
    }

    public void  grenadeMove(String direction) throws InvalidRunException{
        if(direction.equals("stay")) return;
        int direc = Integer.valueOf(direction);
        if(targetBasic.get(0).getCurrentCell().getNextCell(direction) == null)
            throw new InvalidRunException();
        targetBasic.get(0).setCurrentCell(targetBasic.get(0).getCurrentCell().getNextCell(direction));
    }

    protected void dealDamageToEveryoneInCell(Player shooter, int damage, int mark, Player target){
        ArrayList<Player> targets = new ArrayList<>();
        targets.addAll(target.getCurrentCell().getCellPlayers());
        int i = 0;
        while(i < targets.size()){
            dealDamageMarkToTarget(shooter, damage, mark, targets.get(i));
        }
    }
    protected void dealDamageToAllPlayerOneMoveAway(Player shooter, int damage, int mark){
        ArrayList<Player> targets = new ArrayList<>();
        int direction = 0;
        while(direction < 4){
            targets.addAll(shooter.getCurrentCell().getAdjacentCells()[direction].getCellPlayers());
            direction++;
        }
        int i = 0;
        while(i < targets.size()){
            dealDamageMarkToTarget(shooter, damage, mark, targets.get(i));
        }
    }
    public ArrayList<Player> getTargetAttacked() {
        return targetAttacked;
    }

    public void endShoot(){
        targetBasic.clear();
        targetSecond.clear();
        targetAttacked.clear();
    }

}


