package it.polimi.ingsw.se2019.Adrenaline.server.model;


import java.util.ArrayList;
import java.util.Map;

import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidGrabException;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerBoardStatus;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.PlayerStatus;
import javafx.scene.SnapshotParametersBuilder;

public class Player extends PlayerStatus {
//    private String userName;
//    private Cell currentCell;
//    private String playerID;
    private PlayerBoard killShootTrack;
    private WeaponCard weaponInUse;

    /*mode = 0 three walk/one walk+ pick/shoot
    // mode = 1  two walk+pick
    mode = 1 walk+shoott
    */

    //constructor
    public Player(String userName) {
        super(userName);
        this.killShootTrack = new PlayerBoard(this);
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setPlayBoard(Board board) { this.board = board; }
    public void setActionMode(int mode){
        actionMode = mode;
    }
    public void setPlayerColor(Color color){
        playerColor = color;
    }

    public void setCurrentCell(Cell cell){
        this.getCurrentCell().removePlayer(this);
        this.currentCell = cell;
        cell.addPlayer(this);
    }
    public void setEnterCellByColor(String color) {
        Color c = Color.RED; // just for initialize;
        switch (color) {
            case "red":
                c = Color.RED;
                break;
            case "yellow":
                c = Color.YELLOW;
                break;
            case "blue":
                c = Color.BLUE;
                break;
        }
        currentCell = board.getMap().getGenerationCellByColor(c);
    }

    public String getPlayerID() {
        return playerID;
    }
    public Color getPlayerColor() {
        return playerColor;
    }


    public int[] getAmmoOwned() {
        return ammoOwned;
    }

    public Board getPlayBoard() { return board; }

    public Map<WeaponCard, Boolean> getWeaponsOwned() {
        return weaponsOwned;
    }

    public ArrayList<PowerupCard> getPowerupsOwned() {
        return powerupsOwned;
    }

    public int getMyScore() {
        return myScore;
    }

    public int getActionMode(){
        return actionMode;
    }


    public void setAlive(boolean alive) { this.alive = alive; }

    public boolean isAlive() {
        return alive;
    }

    public void beAttacked(Player shooter, int damage, int mark){
        this.killShootTrack.beAttacked(shooter, damage, mark);
    }


    /*
        //装载ammo
        public void fillAmmo(AmmotileCard ammotileCard){
            ammoOwned.add(ammotileCard.ammo)//color, num
        }
        //使用武器卡，消耗ammo
        public void consumeAmmo(AmmoColor color, int num){
            weaponsOwned.put(weaponCard, false);
            int ammoCost
            ammoOwned.put();
        }
        public void loadAmmo(AmmoColor color, int num){

        }
        */
    /*
    public Player findPlayerByColor (Color playerColor){
        int i = 0;
        while(getAllPlayer([i].playerColor != playerColor){
            i++;
            if(i == allPlayer.length){
                System.out.println("error: not exist");
                break;
            }
        }
        return allPlayer[i];
    }
    */
    public void fillAmmo(AmmoColor ammoColor){
        int index = 0;
        switch(ammoColor){
            case RED: ammoOwned[0]++; index = 0; break;
            case BLUE: ammoOwned[1]++; index = 1; break;
            case YELLOW: ammoOwned[2]++; index = 2; break;
        }
        while(ammoOwned[index] > 3){
            ammoOwned[index]--;
        }

    }
    //overload
    public void fillAmmo(AmmoColor ammoColor, int num){
        int index = 0;
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]++; num--; } index = 0; break;
            case YELLOW: while(num != 0){ammoOwned[1]++; num--; } index = 1; break;
            case BLUE: while(num != 0){ammoOwned[2]++; num--; } index = 2; break;
        }
        while(ammoOwned[index] >3) {
            ammoOwned[index]--;
        }
    }
    public void consumeAmmo(AmmoColor ammoColor){
        int index = 0;
        switch(ammoColor){
            case RED: ammoOwned[0]--; index = 0; break;
            case BLUE: ammoOwned[1]--; index = 1; break;
            case YELLOW: ammoOwned[2]--; index = 2; break;
        }
        while(ammoOwned[index] <0) {
            ammoOwned[index]++;
        }
    }
    //overload
    public void consumeAmmo(AmmoColor ammoColor, int num){
        int index = 0;
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]--; num--; } index = 0; break;
            case YELLOW: while(num != 0){ammoOwned[1]--; num--; } index = 1; break;
            case BLUE: while(num != 0){ammoOwned[2]--; num--; } index = 2; break;
        }
//        while(ammoOwned[index] <0) {
//            ammoOwned[index]++;
//        }
    }

    public void addPowerupCard(PowerupCard powerupCard) throws InvalidGrabException{
        powerupsOwned.add(powerupCard);
        if(powerupsOwned.size() > 3) {
            while(powerupsOwned.size() > 3){
                powerupsOwned.remove(powerupsOwned.size()-1);
            }
            throw new InvalidGrabException("powerup"); // 最多三张powerup
        }
    }

    public void addPowerupCard(){
        powerupsOwned.add(getPlayBoard().extractPowerupcard());
    }
    public void addWeaponCard(WeaponCard weaponCard) throws InvalidGrabException{
        weaponsOwned.put(weaponCard, true);
        if(weaponsOwned.size() > 3) {
            while (weaponsOwned.size() > 3){
                weaponsOwned.remove(weaponsOwned.size()-1);
            }
            throw new InvalidGrabException("weapon");
        }
    }

    public void countMyScore(Player diedPlayer){
        myScore += diedPlayer.getKillShootTrack().getPlayerScore().get(this.playerColor);
    }
    public void addToMyScore(int score){
        myScore += score;
    }

    public void changeActionMode(){
        int damageNum = this.killShootTrack.getDamageColorOnTrack().size();
        //if(triggerFirenzy) this.actionMode = 3 3选1， 4，2选1
        if (damageNum > 2 && damageNum<= 5)
            this.actionMode = 1;
        else if(damageNum > 5 && damageNum <= 10)
            this.actionMode = 2;
    }
    public String ammoOwnedToString(){
        return "RED ammo: " + ammoOwned[0] + "\n" +
                "BLUE ammo" + ammoOwned[1] + "\n" +
                "YELLOW ammo" + ammoOwned[2];
    }

    public PlayerBoard getKillShootTrack(){
        return this.killShootTrack;
    }
    public PlayerBoardStatus getKillShootTrackStatus(){
        return (PlayerBoardStatus)this.killShootTrack;
    }
    @Override
    public String toString() {
        return "Player: " + username + "\n"
        + "Color: " + playerColor + "\n"
        + "ActionMode: " + actionMode + "\n"
        +"MyScore: " + myScore;
    }

    public void recover(){
        alive = true;
        killShootTrack.recover();
    }
    public ArrayList<WeaponCard> getAvailableWeapon(){
        ArrayList<WeaponCard>availableWeapons = new ArrayList<>();
        for (WeaponCard key : weaponsOwned.keySet()){
            if(weaponsOwned.get(key) == true) availableWeapons.add(key);
            else continue;
        }
        return availableWeapons;
    }


    public void useWeapon(String weaponName){
        for(WeaponCard weaponCard : weaponsOwned.keySet()){
            if(weaponCard.getCardName() == weaponName){
                weaponsOwned.put(weaponCard, false);
                weaponInUse = weaponCard;
            }
        }
    }
    public void useWeapon(WeaponCard weapon){
        for(WeaponCard weaponCard : weaponsOwned.keySet()){
            if(weapon == weaponCard) {
                weaponsOwned.put(weaponCard, false);
                weaponInUse = weaponCard;
            }
        }
    }

    public WeaponCard getWeaponInUse() {
        return weaponInUse;
    }

    public boolean reloadWeapon(WeaponCard weaponCard){
        int[] ammoCost = weaponCard.getBasicammoCost();
        int i = 0;
        while(i<3){
            i++;
            switch (ammoCost[i-1]){
                case 0: continue;
                case 1:
                    if(getAmmoOwned()[1] > 0) {getAmmoOwned()[1]--; break;}
                    else return false;
                case 2:
                    if(getAmmoOwned()[2] > 0) {getAmmoOwned()[2]--; break;}
                    else return false;
                case 3:
                    if(getAmmoOwned()[3] > 0) {getAmmoOwned()[3]--; break;}
                    else return false;
            }
        }
        weaponsOwned.put(weaponCard, true);
        return true;
    }
    public void dropWeapon(WeaponCard weaponCard){
        weaponsOwned.remove(weaponCard);
    }

    public WeaponCard getWeaponFromName(String weaponName){
        for(WeaponCard w : weaponsOwned.keySet()){
            if(w.getCardName() == weaponName){
                return w;
            }
            else continue;
        }
        return null;
    }
    public PowerupCard getPowerupFromName(String powerupName){
        for(PowerupCard p : powerupsOwned){
            if(p.getCardName() == powerupName) return p;
        }
        return null;
    }

    public PlayerStatus getPlayerStatus(){
        return (PlayerStatus) this;
    }
    public String getUserName() {
        return super.getUsername();
    }
//    public boolean payExtraAmmoForSideEffect(WeaponCard weaponCard){
////        ArrayList<Integer> extraAmmoCost = weaponCard.getSpecialAmmoCost();
////        int i = 0;
////        while(){
////            i++;
////            switch (extr`aAmmoCost.get(i)){
////                case 0: continue;
////                case 1:
////                    if(getAmmoOwned()[1] > 0) {getAmmoOwned()[1]--; break;}
////                    else return false;
////                case 2:
////                    if(getAmmoOwned()[2] > 0) {getAmmoOwned()[2]--; break;}
////                    else return false;
////                case 3:
////                    if(getAmmoOwned()[3] > 0) {getAmmoOwned()[3]--; break;}
////                    else return false;
////            }
////        }
//        return true;
//    }

    /**
     *
     * The getStatus method is used to get the immutable
     * copy of the player.
     *
     * @return a PlayerStatus object that represents a immutable copy of the player.
     *
     */

    public PlayerStatus getStatus() {
        return new PlayerStatus(playerID, username, favorTokens);
    }
}