package it.polimi.ingsw.se2019.Adrenaline.server.model;


import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Player  {
    protected int playerID;
    protected Color playerColor;
    private String nickName;
    private int[] ammoOwned;
    //private HashMap<WeaponCard, Boolean> weaponsOwned;
    //private ArrayList<PowerupCard, Boolean> powerupsOwned;
    //protected Player allPlayer[];
    private int myScore;
    //private Cell currentCell;
    private KillShootTrack killShootTrack;
    private int actionMode;
    /*mode = 0 three walk/one walk+ pick/shoot
    // mode = 1  two walk+pick
    mode = 1 walk+shoot*/

    //constructor
    private Player(Color color) {
        playerID = (int)(System.currentTimeMillis()%1000); //randomGenerated number
        playerColor = color;
        this.killShootTrack = new KillShootTrack();
        actionMode = 0;
        myScore = 0;
        ammoOwned = new int[] {3,3,3};//RED, BLUE, YELLOW
        //初始化所有的玩家,放入allPlayer
    }

    /*
    //装载ammo
    public void fillAmmo(ammotileCard){
        ammoOwned.add(ammotileCard.ammo, )//color, num
    }
    //使用武器卡，消耗ammo
    public void consumeAmmo(AmmoColor color, int num){
        weaponsOwned.put(weaponCard, false);
        int ammoCost
        ammoOwned.put();
    }
    public void loadAmmo(AmmoColor color, int num){
    }

    //
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
    public void showActionMode(){
        switch(actionMode){
            case 0:
                System.out.println("basic mode 0: three walk/one walk and pick/shoot ");
            case 1:
                System.out.println("basic mode 1: mode 0 upgrade to 'two walk and pick'");
            case 2:
                System.out.println("basic mode 2: mode 1 upgrade to 'one walk and shoot'");
            case 3:
                System.out.println("frenzy mode 1: one walk reload shoot/ four walk/ two walk pick");
            case 4:
                System.out.println("frenzy mode 2: two walk reload shoot/ three walk pick");
        }
    }
    /*
    public void countMyScore() {
        int index = 0;
        while(index < allPlayer.length){
            myScore += (int)(allPlayer[index].killShootTrack.playerScore.get(playerColor));
            index++;
        }
    }
    */
    /*
    public Cell getCurrentCell() {
    }
    */

    public void changeActionMode(){
        int damageNum = this.killShootTrack.getDamageColorOnTrack().size();

        //if(triggerfirenzy) this.actionMode = 3
        if (damageNum > 2 & damageNum<= 5)
            this.actionMode = 1;
        else if(damageNum > 5 & damageNum <= 10)
            this.actionMode = 2;
    }


    public KillShootTrack getKillShootTrack(){
        return this.killShootTrack;
    }
    public void takeAction(int actionType) {

    }
    public int getMyscore(){
        return myScore;
    }

}