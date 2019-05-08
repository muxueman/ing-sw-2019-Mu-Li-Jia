package it.polimi.ingsw.se2019.Adrenaline.server.model;


import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.arrayListCards.AmmotileCard;
public class Player  {
    protected int playerID;
    protected Color playerColor;
    private String nickName;
    private int[] ammoOwned;
    private Map<WeaponCard, Boolean> weaponsOwned;
    private ArrayList<PowerupCard> powerupsOwned;
    protected int myScore;
    protected ArrayList<Integer> score;
    private Cell currentCell;
    private KillShootTrack killShootTrack;
    protected int actionMode;
    protected boolean alive;
    /*mode = 0 three walk/one walk+ pick/shoot
    // mode = 1  two walk+pick
    mode = 1 walk+shoot
    */

    //constructor
    private Player() {
        playerID = (int)(System.currentTimeMillis()%1000); //randomGenerated number
        this.killShootTrack = new KillShootTrack(this);
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        weaponsOwned = new HashMap<>();
        powerupsOwned = new ArrayList<>();
        actionMode = 0;
        myScore = 0;
        score = new ArrayList<>();
        alive = true;
        //初始化所有的玩家,放入allPlayer
    }
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
    public void fillAmmo(AmmoColor ammoColor, int num){
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]++; num--; }
            case YELLOW: while(num != 0){ammoOwned[1]++; num--; }
            case BLUE: while(num != 0){ammoOwned[2]++; num--; }
        }
    }
    public void consumeAmmo(AmmoColor ammoColor){
        switch(ammoColor){
            case RED: ammoOwned[0]--;
            case BLUE: ammoOwned[1]--;
            case YELLOW: ammoOwned[2]--;
        }
    }
    public void consumeAmmo(AmmoColor ammoColor, int num){
        switch (ammoColor){
            case RED: while(num != 0){ammoOwned[0]--; num--; }
            case YELLOW: while(num != 0){ammoOwned[1]--; num--; }
            case BLUE: while(num != 0){ammoOwned[2]--; num--; }
        }
    }
    public void countMyScore(PlayBoard playBoard) {
        int index = 0;
        while(index < playBoard.getAllPlayers().size()){
            myScore += (int)(playBoard.getAllPlayers().get(index).killShootTrack.playerScore.get(playerColor));
            index++;
        }
    }

    public void changeActionMode(){
        int damageNum = this.killShootTrack.getDamageColorOnTrack().size();

        //if(triggerFirenzy) this.actionMode = 3 3选1， 4，2选1
        if (damageNum > 2 & damageNum<= 5)
            this.actionMode = 1;
        else if(damageNum > 5 & damageNum <= 10)
            this.actionMode = 2;
    }

    public KillShootTrack getKillShootTrack(){
        return this.killShootTrack;
    }
}