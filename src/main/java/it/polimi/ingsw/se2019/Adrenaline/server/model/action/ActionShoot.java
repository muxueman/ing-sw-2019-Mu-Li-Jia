package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import com.sun.org.apache.regexp.internal.RE;
import it.polimi.ingsw.se2019.Adrenaline.server.model.AmmoColor;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.NotEnoughAmmosException;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.scene.control.SliderBuilder;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import sun.security.krb5.internal.PAData;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 */

public class ActionShoot{

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
//            case "TRACTOR BEAM": targetBasic.addAll()
            case "T.H.O.R": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
//            case "VORTEX CANNON":; break;
//            case "PLASMA GUN": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
//            case "FURANCE": targetBasic.addAll(getTargetsFromRoomYouCanSeeButNotYouAreIn(shooter)) break;
//            case "HEATSEKER": targetBasic.addAll(getTargetsYouCanSee(shooter)); break;
//            case "WHISPER": targetBasic.addAll(getTargetVisibleTwoCellsAway(shooter)); break;
//            case "HELLION": targetBasic.addAll(getTargetOneCellAway(shooter)); break;
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
        }
        return targetBasic;
    }


    protected ArrayList<Player> getTargetsArrayFromName(String names){
        ArrayList<Player> selectedPlayer = new ArrayList<>();
        for(Player p : shooter.getPlayBoard().getAllPlayers()){
            if(names.contains(p.getUserName()))
             selectedPlayer.add(p);
        }
        return selectedPlayer;
    }

    public boolean checkIfInputValid(String names) throws InvalidNameException{
        if(getTargetBasic().size() == 0 || weaponCard.getBasicDamageVision() == 0){
            dealBasicDamageToTarget(targetBasic);
            return true; // dont do anything
        }
        boolean check = true;
        for(Player p : getTargetsArrayFromName(names)){
            if(getTargetBasic().contains(p)) continue;
            else {
                check = false;
                throw new InvalidNameException();
            }
        }
        if(getTargetsArrayFromName(names).size() != weaponCard.getBasicDamageVision()) {
            check = false;
            throw new InvalidNameException();
        }
        if(check){
            targetAttacked.addAll(getTargetsArrayFromName(names));
            dealBasicDamageToTarget(targetAttacked);
        }
        return check;
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
        if(getTargetsArrayFromName(names).size() != weaponCard.getBasicDamageVision()) {
            check = false;
            throw new InvalidNameException();
        }
        if(check){
            dealSideEffectDamageToTarget(getTargetsArrayFromName(names));
        }
        return check;
    }
    // consume ammo for side effect, give available target,

    public String getTargetNameBasic(){
        String targetString = "Targets: ";
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": ;targetString += transferFromPlayerToString(findTargetBasic());
            targetString += "\n" + "type in the name of ONE target"; break;
            case "ELECTROSCYTHE": targetString += transferFromPlayerToString(findTargetBasic());
            targetString += "\n" + "you dont have to choose a target, deal damage to them?"; break;
            case "MACHINE GUN": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "type in the name of ONE or TWO target";break;
        }
        if(getTargetBasic().size() == 0) targetString += "\n" + "nobody to shoot,";
        return targetString;
    }
    public String getTargetNameWithSideEffect(){
        if(targetBasic.size() == 0){
            return "nobody to shoot";
        }
        String secondTargetName = "Second Target:" + "\n";
        targetSecond.addAll(targetBasic);
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": targetSecond.remove(targetAttacked.get(0));
                secondTargetName += transferFromPlayerToString(targetSecond);
            case "ELECTROSCYTHE":  secondTargetName += transferFromPlayerToString(targetSecond);
        }
        return secondTargetName;
    }

    public String transferFromPlayerToString(ArrayList<Player> targetBasic){
        String targetName = "";
        for(Player p : targetBasic) {
            targetName += p.getUsername();
            targetName += "\t";
        }
        return targetName;
    }
    // this is be used to check the num of target is not more than the maximum


    public Player getTargetFromName(String name){
        for(Player p : shooter.getPlayBoard().getAllPlayers()){
            if(p.getUsername() == name) return p;
        }
        return null;
    }

    public boolean checkBasicTargetNum(ArrayList<Player> target, WeaponCard weaponCard){
        boolean shootable = false;
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE": if(target.size() == 1) shootable = true; break;
            case "MACHINE GUN": if(target.size() <= 2) shootable = true; break;
            case "TRACTOR BEAM": if(target.size() == 1) shootable = true; break;
            case "T.H.O.R": if(target.size()  == 1) shootable = true; break;
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
                case "T.H.O.R": if(getTargetsYouCanSee(shooter).contains(target.get(i))) {shootable = true; } break;
                case "VORTEX CANNON": if(getVortexTarget(shooter).contains(target.get(i))) {shootable = true;} break;
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
                case "T.H.O.R":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
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
                    moveTarget(shooter, shooter.getPlayBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), target.get(i).getCurrentCell()));
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
                case "T.H.O.R":
                    dealDamageMarkToTarget(shooter, 2, 0, target.get(i));
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
                    moveTarget(shooter, shooter.getPlayBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), target.get(i).getCurrentCell()));
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
        if(weaponCard.getCardName() == "FLAMETHROWER"){
            dealDamageToEveryoneInCell(shooter, 2,0,target.get(0));
            dealDamageToEveryoneInCell(shooter, 1, 0, target.get(1));

        }
        else if(weaponCard.getCardName() == "SHOCKWAVE"){
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


    public boolean moveTargetToVortexOneStep(Player shooter, Player target, int direction){
        if(shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter
        .getCurrentCell()).contains(target.getCurrentCell().getNextCell(direction))){
            moveTarget(target, direction);
            return true;
        }
        else return false;
    }
    public boolean moveTargetToSquareYouCanSeeMaxi2tep(Player shooter, Player target, int[] direction){
        boolean done = false;
        int i = 0;
        while(i < direction.length){
            moveTarget(target, direction[i]);
            i++;
        }
        if(getTargetsYouCanSee(shooter).contains(target)) {
            done = true;
        }
        else done = false;
        return done;
    }

    public ArrayList<Player> getTargetsYouCanSee(Player shooter){
        ArrayList<Cell> cells = new ArrayList<>();
        cells= shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
        //去重
        return getTargetFromCell(getCleanCellArray(cells));
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

    private ArrayList<Player> getVortexTarget(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
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
        return getTargetFromCell(cellOfTargets);
    }
    protected ArrayList<Player> getTargetVisibleTwoCellsAway(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleTwoAwayCells(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
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
        cellOfTargets = shooter.getPlayBoard().getMap().getAvailableOneWalkCell(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    protected ArrayList<Player> getTargetInYourCell(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets.add(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    protected ArrayList<Player> getTargetCardinal(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        Player shooter1 = shooter;
        int i = 0;
        while(i < 4){
            cellOfTargets.addAll(shooter.getPlayBoard().getMap().getAllCardinalCells(shooter.getCurrentCell(), i));
        }
        return getTargetFromCell(cellOfTargets);
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
            case "TRACTOR BEAM": paid = payAmmoForAtherMode(shooter, AmmoColor.RED); break;
            case "T.H.O.R": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
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
    public boolean payAmmoForThirdSideEffect(Player shooter, WeaponCard weaponCard){
        boolean paid = false;
        switch(weaponCard.getCardName()){
            case "MACHINE GUN": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "T.H.O.R": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
            case "PLASMA GUN": paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE);  break;
            case "ROCKET LAUNCHER": paid = payAmmoForAtherMode(shooter,  AmmoColor.YELLOW); break;
            case "CYBERBLADE":  paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW);; break;
        }
        return paid;
    }
    public void moveATargetToYourCell(Player shooter, Player target){
        target.getCurrentCell().removePlayer(target);
        shooter.getCurrentCell().addPlayer(target);
        target.setCurrentCell(shooter.getCurrentCell());
    }
    public void moveTarget(Player target, int direction){
        //try
        target.getCurrentCell().removePlayer(target);
        target.setCurrentCell(target.getCurrentCell().getNextCell(direction));
        target.getCurrentCell().addPlayer(target);
        // catch cannotMoveException;
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


