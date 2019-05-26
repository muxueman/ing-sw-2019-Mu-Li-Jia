package it.polimi.ingsw.se2019.Adrenaline.server.model;


import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
import it.polimi.ingsw.se2019.Adrenaline.utils.exceptions.InvalidGrabException;

public class Player  {
    protected int playerID;
    protected Color playerColor;
    private String nickName;
    private int[] ammoOwned;
    private Map<WeaponCard, Boolean> weaponsOwned;
    private ArrayList<PowerupCard> powerupsOwned;
    protected int myScore;
    //protected ArrayList<Integer> score;
    private Cell currentCell;
    private KillShootTrack killShootTrack;
    protected int actionMode;
    protected boolean alive;
    protected PlayBoard playBoard;

    /*mode = 0 three walk/one walk+ pick/shoot
    // mode = 1  two walk+pick
    mode = 1 walk+shoott
    */

    //constructor
    public Player() {
        playerID = (int)(System.currentTimeMillis()%1000); //randomGenerated number
        this.killShootTrack = new KillShootTrack(this);
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        weaponsOwned = new HashMap<>();
        powerupsOwned = new ArrayList<>();
        actionMode = 0;
        myScore = 0;
        //score = new ArrayList<>();
        alive = true;
        //初始化所有的玩家,放入allPlayer
    }

    public void setPlayBoard(PlayBoard playBoard) { this.playBoard = playBoard; }
    public void setActionMode(int mode){
        actionMode = mode;
    }
    public void setName(String name){
        this.nickName = name;
    }
    public void setPlayerColor(Color color){
        playerColor = color;
    }
    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }
    public int getPlayerID() {
        return playerID;
    }
    public Color getPlayerColor() {
        return playerColor;
    }

    public String getNickName() {
        return nickName;
    }

    public int[] getAmmoOwned() {
        return ammoOwned;
    }

    public PlayBoard getPlayBoard() { return playBoard; }

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
    public Cell getCurrentCell(){
        return currentCell;
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
        while(ammoOwned[index] <0) {
            ammoOwned[index]++;
        }
    }
    public void addPowerupCard() throws Exception{
        PowerupCard powerupCard = new PowerupCard();
        powerupsOwned.add(powerupCard);
        if(powerupsOwned.size() > 3) throw new InvalidGrabException(); // 最多三张powerup
    }
    public void addWeaponCard() throws Exception{
        WeaponCard weaponCard = new WeaponCard();
        weaponsOwned.put(weaponCard, true);
        if(weaponsOwned.size() > 3) throw new InvalidGrabException();
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

    public KillShootTrack getKillShootTrack(){
        return this.killShootTrack;
    }

    @Override
    public String toString() {
        return "Player: " + nickName + "\n"
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
    public boolean reloadWeapon(WeaponCard weaponCard){
        int[] ammoCost = weaponCard.getBasicammoCost();
        int i = 0;
        while(i<3){
            i++;
            switch (ammoCost[i]){
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
    public boolean payExtraAmmoForSideEffect(WeaponCard weaponCard){
//        ArrayList<Integer> extraAmmoCost = weaponCard.getSpecialAmmoCost();
//        int i = 0;
//        while(){
//            i++;
//            switch (extraAmmoCost.get(i)){
//                case 0: continue;
//                case 1:
//                    if(getAmmoOwned()[1] > 0) {getAmmoOwned()[1]--; break;}
//                    else return false;
//                case 2:
//                    if(getAmmoOwned()[2] > 0) {getAmmoOwned()[2]--; break;}
//                    else return false;
//                case 3:
//                    if(getAmmoOwned()[3] > 0) {getAmmoOwned()[3]--; break;}
//                    else return false;
//            }
//        }
        return true;
    }
}