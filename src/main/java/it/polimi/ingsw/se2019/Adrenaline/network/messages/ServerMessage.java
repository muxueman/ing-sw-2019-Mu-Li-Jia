package it.polimi.ingsw.se2019.Adrenaline.network.messages;

/**
 * A ServerMessage object is the message sent from the server to the client.
 * @author Xueman Mu
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerMessage implements Serializable {

    // 通过socket发过去的object一定需要implements serializable,rmi不需要,因为RemoteObject 其实已经是Serializable
    // serialVersionUID适用于java序列化机制,反序列化发生版本不一致抛出异常InvalidCastException。
    // use this website to generate your unique ID https://blog.csdn.net/qq_35246620/article/details/77686098, not it's default
    private static final long serialVersionUID = 1L;

    private boolean error;
    private boolean playing;
    private String message;
    private ArrayList<StatusUpdate> statusUpdates;
    private int parameter;
    private String subParameter;

    //Constructs a message with default settings.

    //*************************** the rest of code is about this constructor for different inputs*******************

    public ServerMessage() {
        error = false;
        playing = false;
        message = "";
        statusUpdates = new ArrayList<>();
        parameter = 0;
        subParameter = null;
    }

    public ServerMessage(boolean playing) {
        error = false;
        this.playing = playing;
        message = "";
        statusUpdates = new ArrayList<>();
        subParameter = null;
    }

    public ServerMessage(boolean playing, String message) {
        error = false;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
        subParameter = null;
    }

    public ServerMessage(boolean playing, String message, Integer parameter) {
        this.playing = false;
        this.message = message;
        this.parameter = parameter;
        this.subParameter = null;
    }

    public ServerMessage(boolean playing, String message, String subParameter) {
        this.playing = false;
        this.message = message;
        this.parameter = 0;
        this.subParameter = subParameter;
    }

    public ServerMessage(boolean error, boolean playing, String message) {
        this.error = error;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
    }

    public ServerMessage(boolean playing, StatusUpdate statusUpdate) {
        error = false;
        this.playing = playing;
        message = "";
        statusUpdates = new ArrayList<>();
        statusUpdates.add(statusUpdate);
    }

    public ServerMessage(String message, boolean playing, StatusUpdate statusUpdate) {
        error = false;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
        statusUpdates.add(statusUpdate);
    }

    public ServerMessage(String errorMessage) {
        error = true;
        playing = false;
        message = errorMessage;
        statusUpdates = new ArrayList<>();
    }

    public ServerMessage(boolean playing, List<StatusUpdate> statusUpdates) {
        error = false;
        this.playing = playing;
        message = "";
        this.statusUpdates = new ArrayList<>(statusUpdates);
    }

    public ServerMessage(String errorMessage, List<StatusUpdate> statusUpdates) {
        error = true;
        playing = false;
        message = errorMessage;
        this.statusUpdates = new ArrayList<>(statusUpdates);
    }

    //*********************************** ends of constructor *************************************************

    // check if there is an error
    public boolean isError() {
        return error;
    }

    // check the condition of playing.
    public boolean isPlaying() {
        return playing;
    }

    //get value
    public int getParm(){return parameter;}
    public String getSubParameter(){return subParameter;}
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public int numberOfUpdates() {
        return statusUpdates != null ? statusUpdates.size() : 0;
    }
    public List<StatusUpdate> getStatusUpdates() {
        return statusUpdates;
    }

    // add an arbitrary number of statusUpdates.
    public void addStatusUpdate(StatusUpdate... statusUpdates) {
        for (StatusUpdate statusUpdate : statusUpdates) {
            if (statusUpdate != null) {
                this.statusUpdates.add(statusUpdate);
            }
        }
    }

    // add a list of statusUpdates.
    public void addStatusUpdate(List<StatusUpdate> statusUpdates) {
        for (StatusUpdate statusUpdate : statusUpdates) {
            if (statusUpdate != null) {
                addStatusUpdate(statusUpdate);
            }
        }
    }
}
