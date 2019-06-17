package it.polimi.ingsw.se2019.Adrenaline.client.view;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;

import java.util.*;


public abstract class View{

    public View() {
    }


    //show the Ranking with scores
    public abstract void showMessage(String message);
    public abstract void showRanking(KillShootTrack track);
    public abstract void reportError(String error);
    //public void showStatus(Status status) {
        // doesn't need to do anything, only used by CLIView
    //}
    public void nextView(boolean wpc) {
        // doesn't need to do anything, only used by GUIView
    }

}