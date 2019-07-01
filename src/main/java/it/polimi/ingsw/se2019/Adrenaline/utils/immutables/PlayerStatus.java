package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Board;
import it.polimi.ingsw.se2019.Adrenaline.server.model.Player;
import it.polimi.ingsw.se2019.Adrenaline.server.model.PlayerBoard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.deckCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Cell;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerStatus implements Status{

    private String username;
    private String playerID;
    private Color playerColor;
    private int[] ammoOwned;
    private Map<WeaponCard, Boolean> weaponsOwned;
    private ArrayList<PowerupCard> powerupsOwned;
    private int myScore;
    private int currentCell;
    private int actionMode;
    private int favorTokens;
    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;
    private int numKilled; // 被射杀的次数


    //constructor from update a player
    public PlayerStatus(Player player){
        username = player.getUsername();
        playerID = player.getPlayerID();
        playerColor = player.getPlayerColor();
        ammoOwned = player.getAmmoOwned();
        weaponsOwned = player.getWeaponsOwned();
        powerupsOwned = player.getPowerupsOwned();
        myScore = player.getMyScore();
        currentCell = player.getCurrentCell().getCellID();
        actionMode = player.getActionMode();
        favorTokens = player.getFavorTokens();
        damageColorOnTrack = player.getKillShootTrack().getDamageColorOnTrack();
        markColorOnTrack = player.getKillShootTrack().getMarkColorOnTrack();
        numKilled = player.getKillShootTrack().getNumKillShoot();
    }

    public String getPlayerID(){
        return this.playerID;
    }

    @Override
    public String toString(){
        return "Username: " + username + "\n" + "color: " + playerColor+ "\n"
                + "Your current Cell:" + currentCell + "\n" + "Ammo you owned: " + ammoOwned + "\n" + "weapon you have:"
                + weaponsOwned + "\n" + "powerup you have:" + "\n";
    }

    @Override
    public Ansi toAnsi(){
        return ansi().a("Username: " + username + "\n color: " + playerColor + "\n" );
    }
}

