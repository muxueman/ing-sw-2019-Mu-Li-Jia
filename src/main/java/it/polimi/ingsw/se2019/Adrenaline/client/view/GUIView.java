package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;

import java.util.List;

/**
 *
 * The GUIView Class represent the Gui View and it
 * extends the View Class.
 *
 *
 * @author Li xuejing
 */

public class GUIView extends View{

    private GUIController guiController = null;

    /**
     * Constructs a GuiView passing a guiController.
     *
     * @param guiController a new guiController.
     */
    public GUIView(GUIController guiController) {
        setGuiController(guiController);
    }



    /**
     * The setGuiController method is used to set the gui controller.
     *
     * @param guiController the actual guiController.
     */

    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
        guiController.setGuiView(this);
    }


    protected void notify(String message) {
        super.notify();
    }



    public void launchTimer() { guiController.guiLaunchTimer(); }

    public void play(boolean playing) {
        guiController.guiPlay(playing);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void reportError(String error) {

    }

    @Override
    public void update(ModelUpdate message) {

    }
}
