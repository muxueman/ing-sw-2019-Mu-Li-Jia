package it.polimi.ingsw.se2019.Adrenaline.Model;


import it.polimi.ingsw.se2019.Adrenaline.Model.PlayBoard;
import it.polimi.ingsw.se2019.Adrenaline.Model.powerupCards.PowerupCard;
import it.polimi.ingsw.se2019.Adrenaline.Model.weaponCards.WeaponCard;
import it.polimi.ingsw.se2019.Adrenaline.Model.Cell;
import it.polimi.ingsw.se2019.Adrenaline.Model.KillShootTrack;
import java.util.*;

public class Player  {


    private Player() {
    }

    private Color color;

    private String nickName;

    private ArrayList<WeaponCard> weapons;


    private ArrayList<PowerupCard> powerups;






    private int totalscores;


    private Cell currentRoom;


    private KillShootTrack killShootTrack;


    private int actionMode;


    public void initialPlayer() {
        // TODO implement here

}





    public int readscores() {
        // TODO implement here
        return 0;
    }


    public void addscores(int point) {
        // TODO implement here

    }


    public Cell getCurrentCell() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public KillShootTrack getKillShootTrack() {
        // TODO implement here
        return null;
    }


    public int getActionMode(KillShootTrack track) {
        // TODO implement here
        return 0;
    }


    public void takeAction() {
        // TODO implement here

    }

}