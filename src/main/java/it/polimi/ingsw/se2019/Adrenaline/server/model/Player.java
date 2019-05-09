package it.polimi.ingsw.se2019.Adrenaline.server.model;


import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.AmmotileCard;
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
        switch(ammoColor){
            case RED: ammoOwned[0]++;
            case BLUE: ammoOwned[1]++;
            case YELLOW: ammoOwned[2]++;
        }
    }
    //overload
    public void fillAmmo(AmmoColor ammoColor, int num){
        int index = 0;
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]++; num--; } index = 0;
            case YELLOW: while(num != 0){ammoOwned[1]++; num--; } index = 1;
            case BLUE: while(num != 0){ammoOwned[2]++; num--; } index = 2;
        }
        while(ammoOwned[index] >3) {
            ammoOwned[index]--;
        }
    }
    public void consumeAmmo(AmmoColor ammoColor){
        switch(ammoColor){
            case RED: ammoOwned[0]--;
            case BLUE: ammoOwned[1]--;
            case YELLOW: ammoOwned[2]--;
        }
    }
    //overload
    public void consumeAmmo(AmmoColor ammoColor, int num){
        int index = 0;
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]--; num--; }
            case YELLOW: while(num != 0){ammoOwned[1]--; num--; }
            case BLUE: while(num != 0){ammoOwned[2]--; num--; }
        }
        while(ammoOwned[index] <0) {
            ammoOwned[index]++;
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
        if (damageNum > 2 & damageNum<= 5)
            this.actionMode = 1;
        else if(damageNum > 5 & damageNum <= 10)
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
}