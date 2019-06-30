package it.polimi.ingsw.se2019.Adrenaline.network.messages;



import java.io.Serializable;

public class ViewMessage implements Serializable {

    private String message;
    private int parm;
    private String parmS;

    public ViewMessage(String message, int parm){
        this.message = message;
        this.parm = parm;
    }
    public ViewMessage(String message, String parm){
        this.message = message;
        this.parmS = parm;
    }

    public int getParm() {
        return parm;
    }

    public String getMessage() {
        return message;
    }

    public String getParmS() {
        return parmS;
    }
}
