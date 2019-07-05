package it.polimi.ingsw.se2019.Adrenaline.server.model.action;
import it.polimi.ingsw.se2019.Adrenaline.server.model.AmmoColor;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidNameException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.InvalidRunException;
import it.polimi.ingsw.se2019.Adrenaline.utils.network.exceptions.NotEnoughAmmosException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        if(weaponCard.getType() == 0) return 0; //only basic mode
        return 1; // shoot + move;
    }

    public List<Player> getTargetBasic(){
        return targetBasic;
    }

    private List<Player> getTargetSecond() {
        return targetSecond;
    }

    private ArrayList<Player> findTargetBasic() {
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
            case "HELLION": targetBasic.addAll(getVortexTarget()); break;
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

    private ArrayList<Player> getTargetsArrayFromName(String names){
        ArrayList<Player> selectedPlayer = new ArrayList<>();
        for(Player p : shooter.getPlayBoard().getAllPlayers()){
            if(names.contains(p.getUserName()))
             selectedPlayer.add(p);
        }
        return selectedPlayer;
    }

    public void checkIfInputValid(String names) throws InvalidNameException{
        if(getTargetBasic().isEmpty() || weaponCard.getBasicDamageVision() == 0){  // case: dont need to choose target or nobody valid
            dealBasicDamageToTarget(targetBasic);
            shooter.getBoard().checkIfAnyPlayerDie();
            return;
        }
        if(weaponCard.getCardName().equals("FLAMETHROWER")) {
            targetAttacked.addAll(getTargetsArrayFromName(names));
            if(checkIfInputValidFlamethrower()){
                dealBasicDamageToTarget(targetAttacked);
                shooter.getBoard().checkIfAnyPlayerDie();
                return;
            }
            else throw new InvalidNameException();
        }
        for(Player p : getTargetsArrayFromName(names)){
            if(!getTargetBasic().contains(p)) {
                throw new InvalidNameException();      // target not in vision
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getBasicDamageVision()) {
            throw new InvalidNameException();      //selected target more than
        }
        if(true){
            targetAttacked.addAll(getTargetsArrayFromName(names));
            dealBasicDamageToTarget(targetAttacked);
            shooter.getBoard().checkIfAnyPlayerDie();
        }
    }
    public boolean checkIfInputValidFlamethrower(){
        switch (targetAttacked.size()){
            case 0: return true;
            case 1: if(targetBasic.contains(targetAttacked.get(0))) return true; else break;
            case 2: if(targetBasic.contains(targetAttacked.get(0)) || targetBasic.get(0).getCurrentCell().getNextCell(
                    shooter.getBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), targetAttacked
                            .get(0).getCurrentCell())) == targetAttacked.get(1).getCurrentCell()||
                    targetBasic.contains(targetAttacked.get(1)) || targetBasic.get(1).getCurrentCell().getNextCell(
                    shooter.getBoard().getMap().getDirectionFromCellToCell(shooter.getCurrentCell(), targetAttacked
                            .get(1).getCurrentCell())) == targetAttacked.get(0).getCurrentCell()) return true;
            default:
            }

        return false;
    }

    public boolean checkIfInputValidSecond(String names) throws InvalidNameException{
        if(targetSecond.isEmpty() || weaponCard.getEffectDamageVison() == 0) {
            dealSideEffectDamageToTarget(targetSecond);
            return true;
        }
        for(Player p : getTargetsArrayFromName(names)){
            if(!getTargetSecond().contains(p)) {
                throw new InvalidNameException();
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getEffectDamageVison()) {
            throw new InvalidNameException();
        }
        if(true){
            dealSideEffectDamageToTarget(getTargetsArrayFromName(names));
            targetAttacked.addAll(getTargetsArrayFromName(names));
        }
        return true;
    }
    public boolean checkIfInputValidThird(String names) throws InvalidNameException{
        if(targetSecond.isEmpty() || weaponCard.getEffectDamageVison() == 0) {
            dealSideEffectDamageToTarget(targetSecond);
            return true;
        }
        for(Player p : getTargetsArrayFromName(names)){
            if(getTargetSecond().contains(p)) {
                throw new InvalidNameException();
            }
        }
        if(getTargetsArrayFromName(names).size() > weaponCard.getEffectDamageVison()) {
            throw new InvalidNameException();
        }
        if(true){
            dealThirdSideEffectDamageToTarget(getTargetsArrayFromName(names));
            targetAttacked.addAll(getTargetsArrayFromName(names));
        }
        return true;
    }

    // consume ammo for side effect, give available target,

    public String getTargetNameBasic(){
        String targetString = "Targets: ";
        switch (weaponCard.getCardName()){
            case "LOCK RIFLE":
            case "T.H.O.R." :
            case "PLASMA GUN":
            case "VORTEX CANNON":
            case "WHISPER":
            case "HEATSEEKER":
            case "HELLION":
            case "RAILGUN":
            case "SHOCKWAVE":
            case "CYBERBLADE":
            case "SLEDGEHAMMER":
            case "ZX-2": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target"; break;
            case "GRENADE LAUNCHER":
            case "SHOTGUN":
            case "ROCKET LAUNCHER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, after shoot you can move the target 1 cell"; break;
            case "ELECTROSCYTHE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "you dont have to choose a target, deal damage to them?"; break;
            case "MACHINE GUN": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE or TWO target";break;
            case "TRACTOR BEAM": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, and direction you want it to move, maxi two steps";break;
            case"FURNACE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, ALL player within the same room will get damage"; break;
            case "POWER GLOVE": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, you will move on its square and give a shot"; break;
            case "FLAMETHROWER": targetString += transferFromPlayerToString(findTargetBasic());
                targetString += "\n" + "type in the name of ONE target, or possibly another target that is in the same " +
                        "direction one cell away from your first target"; break;
                        default: break;
        }
        if(getTargetBasic().isEmpty()) targetString += "\n" + "nobody to shoot,";
        return targetString;
    }
    public String getTargetNameWithSideEffect(){
        if(weaponCard.getType() == 1){
            targetBasic = findTargetBasic();
        }
        if(targetBasic.isEmpty()){
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
                default: break;
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
                default: break;

        }
        return thirdTargetName;
    }

    public Cell getCellFromID(int CellID){
        for(Cell c : shooter.getBoard().getMap().getAllCells()){
            if(c.getCellID() == CellID) return c;
        }
        return null;
    }


    private String transferFromPlayerToString(ArrayList<Player> targetBasic){
        String targetName = "";
        for(Player p : targetBasic) {
            targetName += p.getUsername();
            targetName += "\t";
        }
        return targetName;
    }
    // this is be used to check the num of target is not more than the maximum

// when the player click shoot， check the target is valid or not，
// after click shoot, all the target will add damage / mark on their killshoottrack


//    public void dealBasicDamageToTarget(String name){
//       Player target = getTargetFromName(name);
//       switch (weaponCard.getCardName()){
//           case "LOCK RIFLE": dealDamageMarkToTarget(shooter, 2, 1, target);
//       }
//       targetAttacked.add(target);
//    }
    private void moveTargetOneWalkToVortex(Player target){
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
        if(target.isEmpty()) return;
        boolean shoot = false;
        int i = 0;
        if (weaponCard.getCardName().equalsIgnoreCase("FURANCE")) {
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
                    break;
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
                    default:
            }
            i++;
        }

    }

    public void dealSideEffectDamageToTarget(List<Player> target){
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
                    break;
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
                    default: break;
            }
            i++;
        }
    }

    private void dealThirdSideEffectDamageToTarget(List<Player> target){
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
                    default: break;
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
                case "FLAMETHROWER": dealDamageMarkToTarget(shooter, 2,0, target.get(i));break;
                case "ZX-2": dealDamageMarkToTarget(shooter, 0,1,target.get(i)); break;
                case "SHOTGUN": dealDamageMarkToTarget(shooter,2,0, target.get(i)); break;
                case "POWER GLOVE": ; break;
                case "RAILGUN":  dealDamageMarkToTarget(shooter,2,0, target.get(i)); break;
                case "SLEDGEHAMMER": dealDamageMarkToTarget(shooter, 3, 0, target.get(i)); break;
                default: break;
            }
            i++;
        }
    }
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
        ArrayList<Cell> cells;
        cells= shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
        //去重
        return getTargetFromCell(getCleanCellArray(cells));
    }
    public ArrayList<Player> getTargetsYouCanSeeButNotOnYourSquare(Player shooter){
        ArrayList<Cell> cells;
        cells= shooter.getPlayBoard().getMap().getAllVisibleCells(shooter.getCurrentCell());
        //去重
        cells = getCleanCellArray(cells);
        cells.remove(shooter.getCurrentCell());
        return getTargetFromCell(cells);
    }

    public ArrayList<Cell> getCleanCellArray(List<Cell> cells){
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
        ArrayList<Cell> cellOfTargets;
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
    private ArrayList<Player> getTargetsFromRoomYouCanSeeButNotYouAreIn(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int cellIndex = 0;
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
    private ArrayList<Player> getTargetVisibleTwoCellsAway(Player shooter){
        ArrayList<Cell> cellOfTargets;
        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleTwoAwayCells(shooter.getCurrentCell());
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }
    //get targets from visible cells but at least one cell away
//    private ArrayList<Player> getTargetVisibleOneCellAway(Player shooter){
//        ArrayList<Cell> cellOfTargets;
//        cellOfTargets = shooter.getPlayBoard().getMap().getVisibleCellsWithoutYourCell(shooter.getCurrentCell());
//        return getTargetFromCell(cellOfTargets);
//    }
    //hget targets from one move away cell
    private ArrayList<Player> getTargetOneCellAway(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int i = 0;
        while(i < 4){
            if(shooter.getCurrentCell().getNextCell(i)!= null)
                cellOfTargets.add(shooter.getCurrentCell().getNextCell(i));
            i++;
        }
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }
    private ArrayList<Player> getTargetInYourCell(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        cellOfTargets.add(shooter.getCurrentCell());
        return getTargetFromCell(cellOfTargets);
    }
    private ArrayList<Player> getTargetCardinal(Player shooter){
        ArrayList<Cell> cellOfTargets = new ArrayList<>();
        int i = 0;
        while(i < 4){
            cellOfTargets.addAll(shooter.getPlayBoard().getMap().getAllCardinalCells(shooter.getCurrentCell(), i));
            i++;
        }
        return getTargetFromCell(getCleanCellArray(cellOfTargets));
    }

    private ArrayList<Player> getTargetFromCell(ArrayList<Cell> cellOfTargets){
        ArrayList<Player> targetsInVision = new ArrayList<>();
        for(Cell c : cellOfTargets){
            targetsInVision.addAll(c.getCellPlayers());
        }
        targetsInVision.remove(shooter); // 攻击对象不包括shooter.
        return  targetsInVision;
    }

    private void dealDamageMarkToTarget(Player shooter, int damage, int mark, Player target){
        target.getKillShootTrack().beAttacked(shooter, damage, mark);
    }


    private boolean payAmmoForAtherMode(Player shooter, AmmoColor color){
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
            case "ELECTROSCYTHE": payAmmoForAtherMode(shooter, AmmoColor.RED); paid = payAmmoForAtherMode(shooter, AmmoColor.BLUE); break;
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

            case "FLAMETHROWER": payAmmoForAtherMode(shooter, AmmoColor.YELLOW); paid = payAmmoForAtherMode(shooter, AmmoColor.YELLOW);break;
            case "ZX-2": paid = true; break;
            case "GRENA DE LAUNCHER": paid = payAmmoForAtherMode(shooter, AmmoColor.RED); break;
            case "SHOTGUN":  paid = true; break;
            case "ROCKET LAUNCHER": paid = payAmmoForAtherMode(shooter,  AmmoColor.BLUE); break;
            case "POWER GLOVE": paid = payAmmoForAtherMode(shooter,  AmmoColor.BLUE); break;
            case "RAILGUN":  paid = true; break;
            case "SHOCKWAVE":  paid = payAmmoForAtherMode(shooter,  AmmoColor.YELLOW); break;
            case "CYBERBLADE":  paid = true; break;
            case "SLEDGEHAMMER":  paid = payAmmoForAtherMode(shooter,  AmmoColor.RED); break;
            default: break;
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
            default: break;
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
    }

    public void grenadeMove(String direction) throws InvalidRunException{
        if(direction.equals("stay")) return;
        if(targetAttacked.get(0).getCurrentCell().getNextCell(Integer.valueOf(direction)) == null)
            throw new InvalidRunException();
        targetAttacked.get(0).setCurrentCell(targetAttacked.get(0).getCurrentCell().getNextCell(Integer.valueOf(direction)));
    }

    private void dealDamageToEveryoneInCell(Player shooter, int damage, int mark, Player target){
        ArrayList<Player> targets = new ArrayList<>();
        targets.addAll(target.getCurrentCell().getCellPlayers());
        int i = 0;
        while(i < targets.size()){
            dealDamageMarkToTarget(shooter, damage, mark, targets.get(i));
        }
    }
    private void dealDamageToAllPlayerOneMoveAway(Player shooter, int damage, int mark){
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
    ArrayList<Player> getTargetAttacked() {
        return targetAttacked;
    }

    public void endShoot(){
        targetBasic.clear();
        targetSecond.clear();
        targetAttacked.clear();
    }

}


