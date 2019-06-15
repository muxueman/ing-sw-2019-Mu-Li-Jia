package it.polimi.ingsw.se2019.Adrenaline.client.view;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;
import java.util.*;


public abstract class View extends Observable{

    public View() {
    }


    //show the Ranking with scores
    public abstract void showRanking(KillShootTrack track);
    public abstract void reportError(String error);
    public abstract void showMessage(String message);

}