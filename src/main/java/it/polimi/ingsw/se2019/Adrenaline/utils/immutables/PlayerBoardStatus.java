package it.polimi.ingsw.se2019.Adrenaline.utils.immutables;

import it.polimi.ingsw.se2019.Adrenaline.server.model.Color;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

public class PlayerBoardStatus implements Status {

    private PlayerStatus playerStatus;
    private ArrayList<Color> damageColorOnTrack;
    private ArrayList<Color> markColorOnTrack;
    private Map<Color, Integer> playerScore;
    private int numKillShoot; // 被射杀的次数
    private int beKilled; // 0 没有被杀， 1 被杀， 2 被超杀

    public PlayerBoardStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
        this.damageColorOnTrack = new ArrayList<Color>();
        this.markColorOnTrack = new ArrayList<Color>();
        playerScore = new HashMap<>();
        numKillShoot = 0;
        beKilled = 0;
    }
    public PlayerBoardStatus(){}

    public int getNumKillShoot() {
        return numKillShoot;
    }

    public ArrayList<Color> getMarkColorOnTrack() {
        return markColorOnTrack;
    }

    public ArrayList<Color> getDamageColorOnTrack() {
        return damageColorOnTrack;
    }

    public int getBeKilled() {
        return beKilled;
    }

    public Map<Color, Integer> getPlayerScore() {
        return playerScore;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    @Override
    public String toString(){
        return "damage on you: " + damageColorOnTrack.toString() + "\n" + "mark on you: " + markColorOnTrack.toString()+ "\n";
    }
    public Ansi toAnsi(){
        return ansi().a("damage on you:" + damageColorOnTrack);
    }


}
