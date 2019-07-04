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

    /**
     *
     * Constructs a message with default settings.
     *
     */
    public ServerMessage() {
        error = false;
        playing = false;
        message = "";
        statusUpdates = new ArrayList<>();
        parameter = 0;
        subParameter = null;
    }
    /**
     *
     * Constructs a message with default settings,
     * except for playing's parameter.
     *
     * @param playing given boolean state of playing.
     *
     */

    public ServerMessage(boolean playing) {
        error = false;
        this.playing = playing;
        message = "";
        statusUpdates = new ArrayList<>();
        subParameter = null;
    }
    /**
     *
     * Constructs a message with default settings,
     * except for playing and message's parameters.
     *
     * @param playing given boolean for playing.
     * @param message given String for message.
     *
     */

    public ServerMessage(boolean playing, String message) {
        error = false;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
        subParameter = null;
    }

    public ServerMessage(boolean playing, String message, Integer parameter) {
        this.playing = playing;
        this.message = message;
        this.parameter = parameter;
        this.subParameter = null;
    }

    public ServerMessage(boolean playing, String message, String subParameter) {
        this.playing = playing;
        this.message = message;
        this.parameter = 0;
        this.subParameter = subParameter;
    }

    /**
     *
     * Constructs a message with
     * playing and message and error's parameters.
     *
     * @param error given boolean for error.
     * @param playing given boolean for playing.
     * @param message given String for message.
     *
     */

    public ServerMessage(boolean error, boolean playing, String message) {
        this.error = error;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
    }
    /**
     *
     * Constructs a message with
     * playing and statusUpdate's parameters.
     *
     * @param playing given boolean for playing.
     * @param statusUpdate given StatusUpdate for statusUpdate.
     *
     */
    public ServerMessage(boolean playing, StatusUpdate statusUpdate) {
        error = false;
        this.playing = playing;
        message = "";
        statusUpdates = new ArrayList<>();
        statusUpdates.add(statusUpdate);
    }


    /**
     *
     * Constructs a message with
     * playing and statusUpdate and message's parameters.
     *
     * @param playing given boolean for playing.
     * @param statusUpdate given StatusUpdate for statusUpdate.
     * @param message given String for message.
     *
     */
    public ServerMessage(String message, boolean playing, StatusUpdate statusUpdate) {
        error = false;
        this.playing = playing;
        this.message = message;
        statusUpdates = new ArrayList<>();
        statusUpdates.add(statusUpdate);
    }
    /**
     *
     * Constructs a message with an error message
     *
     * @param errorMessage given String for message.
     *
     */
    public ServerMessage(String errorMessage) {
        error = true;
        playing = false;
        message = errorMessage;
        statusUpdates = new ArrayList<>();
    }
    /**
     *
     * Constructs a message with playing and a list of statusUpdate's parameters.
     *
     * @param playing given boolean for playing.
     * @param statusUpdates given list of StatusUpdate for statusUpdates
     *
     */
    public ServerMessage(boolean playing, List<StatusUpdate> statusUpdates) {
        error = false;
        this.playing = playing;
        message = "";
        this.statusUpdates = new ArrayList<>(statusUpdates);
    }
    /**
     *
     * Constructs a message with an errorMessage and a list of statusUpdate's parameters.
     *
     * @param errorMessage given String for message.
     * @param statusUpdates given list of StatusUpdate for statusUpdates
     *
     */

    public ServerMessage(String errorMessage, List<StatusUpdate> statusUpdates) {
        error = true;
        playing = false;
        message = errorMessage;
        this.statusUpdates = new ArrayList<>(statusUpdates);
    }

    /**
     *
     * The isError method is used to check if there's an error.
     * @return a boolean as answer.
     *
     */
    public boolean isError() {
        return error;
    }

    /**
     *
     * The isPlaying method is used to check the condition of playing.
     * @return a boolean as answer.
     *
     */
    public boolean isPlaying() {
        return playing;
    }

    //get value
    public int getParm(){return parameter;}
    public String getSubParameter(){return subParameter;}
    /**
     *
     * The setMessage method is used to set the message.
     *
     * @param message text to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * The getMessage method is used to obtain the message.
     *
     * @return the String representing the message.
     *
     */

    public String getMessage() {
        return message;
    }
    public int numberOfUpdates() {
        return statusUpdates != null ? statusUpdates.size() : 0;
    }
    /**
     *
     * The getStatusUpdates method is used to get the list of statusUpdates.
     *
     * @return the list of status updates.
     */
    public List<StatusUpdate> getStatusUpdates() {
        return statusUpdates;
    }

    /**
     *
     * The addStatusUpdate method is used to add an arbitrary number of statusUpdates.
     *
     * @param statusUpdates an arbitrary number of statusUpdates.
     */
    public void addStatusUpdate(StatusUpdate... statusUpdates) {
        for (StatusUpdate statusUpdate : statusUpdates) {
            if (statusUpdate != null) {
                this.statusUpdates.add(statusUpdate);
            }
        }
    }

    /**
     *
     * The addStatusUpdate method is used to add a list of statusUpdates.
     *
     * @param statusUpdates list of statusUpdates.
     */
    public void addStatusUpdate(List<StatusUpdate> statusUpdates) {
        for (StatusUpdate statusUpdate : statusUpdates) {
            if (statusUpdate != null) {
                addStatusUpdate(statusUpdate);
            }
        }
    }
}
