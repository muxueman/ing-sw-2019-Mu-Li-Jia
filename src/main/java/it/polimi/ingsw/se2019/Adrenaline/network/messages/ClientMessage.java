package it.polimi.ingsw.se2019.Adrenaline.network.messages;

/**
 * A ClientMessage object is the message sent from the client to the server.
 * @author Xueman Mu
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientMessage implements Serializable {

    // 通过socket发过去的object一定需要implements serializable,rmi不需要,因为RemoteObject 其实已经是Serializable
    // serialVersionUID适用于java序列化机制,反序列化发生版本不一致抛出异常InvalidCastException。
    // use this website to generate your unique ID https://blog.csdn.net/qq_35246620/article/details/77686098, not it's default
    private static final long serialVersionUID = 1L;

    private String textMove;
    private int mainParam;
    private List<Integer> additionalParams;

    // Constructs a message with a move and a main parameter , or a list of additional parameters
    public ClientMessage(String textMove, int mainParam) {
        this.textMove = textMove;
        this.mainParam = mainParam;
        this.additionalParams = new ArrayList<>();
    }
    public ClientMessage(String textMove, int mainParam, List<Integer> additionalParams) {
        this.textMove = textMove;
        this.mainParam = mainParam;
        this.additionalParams = new ArrayList<>(additionalParams);
    }

    public void addParam(int... params) {
        for (int param : params) {
            additionalParams.add(param);
        }
    }

    // get values of the messages
    public String getTextMove() {
        return textMove;
    }
    public int getMainParam() {
        return mainParam;
    }
    public List<Integer> getAdditionalParams() {
        return additionalParams;
    }
}
