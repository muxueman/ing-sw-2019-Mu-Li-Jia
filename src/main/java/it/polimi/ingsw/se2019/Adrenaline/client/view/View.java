package it.polimi.ingsw.se2019.Adrenaline.client.view;
import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.KillShootTrack;

import java.util.*;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observer;
import it.polimi.ingsw.se2019.Adrenaline.utils.Observable;


public abstract class View extends Observable<String> implements Observer<ModelUpdate> {

    public View() {
    }


    //show the Ranking with scores
    public abstract void showMessage(String message);
//    public abstract void showRanking(KillShootTrack track);
    public abstract void reportError(String error);
    //public void showStatus(Status status) {
        // doesn't need to do anything, only used by CLIView
    //}
    public void nextView(boolean wpc) {
        // doesn't need to do anything, only used by GUIView
    }

    public abstract InitialViewController getGuiController();
}