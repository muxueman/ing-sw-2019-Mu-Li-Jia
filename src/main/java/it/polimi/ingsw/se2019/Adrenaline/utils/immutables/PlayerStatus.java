package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;
/**
 *Player Status from client-side point of view, contains all data needed for view (client)
 *@author Xueman Mu
 */
import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerStatus implements Status{

    private String username;
    private String playerID;
    private Color playerColor;
    private int[] ammoOwned;
    private Map<WeaponCard, Boolean> weaponsOwned;
    private ArrayList<PowerupCard> powerupsOwned;
    private int myScore;
    //private int currentCell;
    private int actionMode;
    private int favorTokens;
    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;
    private int numKilled; // 被射杀的次数


    public Color getPlayerColor() {
        return playerColor;
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

    public int getActionMode() {
        return actionMode;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public ArrayList<Color> getDamageColorOnTrack() {
        return damageColorOnTrack;
    }

    public ArrayList<Color> getMarkColorOnTrack() {
        return markColorOnTrack;
    }

    public int getNumKilled() {
        return numKilled;
    }

    //constructor from update a player
    public PlayerStatus(Player player){
        username = player.getUsername();
        playerID = player.getPlayerID();
        playerColor = player.getPlayerColor();
        ammoOwned = player.getAmmoOwned();
        weaponsOwned = player.getWeaponsOwned();
        powerupsOwned = player.getPowerupsOwned();
        myScore = player.getMyScore();
        //currentCell = player.getCurrentCell().getCellID();
        actionMode = player.getActionMode();
        favorTokens = player.getFavorTokens();
        damageColorOnTrack = player.getKillShootTrack().getDamageColorOnTrack();
        markColorOnTrack = player.getKillShootTrack().getMarkColorOnTrack();
        numKilled = player.getKillShootTrack().getNumKillShoot();
    }

    public String getPlayerID(){
        return this.playerID;
    }
    public String getUsername(){
        return this.username;
    }



    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + playerColor+ "\n"
                + "Ammo you owned: " + ammoOwned + "\n" + "weapon you have:"
                + weaponsOwned + "\n" + "powerup you have:" + "\n";
    }

    @Override
    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + playerColor + "\n" );
    }
}

