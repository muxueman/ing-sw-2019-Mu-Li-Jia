package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;

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






    @Override
    protected void notify(String message) {
        super.notify(message);
    }



    public void launchTimer() { guiController.guiLaunchTimer(); }

    public void play(boolean playing) {
        guiController.guiPlay(playing);
    }


    @Override
    public void showMessage(String message) {
        guiController.showMessage(message);

    }

    @Override
    public void reportError(String error) {
        guiController.reportError(error);

    }



    @Override
    public void update(ModelUpdate message) {
        guiController.update(message);
    }

    public void setGuiController(GUIController guiController) {
        this.guiController = guiController;
        guiController.setGuiView(this);
    }

    @Override
    public void nextView(boolean next){
        guiController.nextView(next);
    }

    @Override
    public void setCancelImage(){
        guiController.guiSetCancelImage();
    }


    public GUIController getGuiController() {
        return guiController;
    }

    @Override
    public void value(){
        guiController.guiValue();
    }


}
