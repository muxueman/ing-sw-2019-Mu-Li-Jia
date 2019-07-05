package it.polimi.ingsw.se2019.Adrenaline.utils.network.messages;

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
    private int additionalParam;
    private String mainParamS;

    /**
     *
     * Constructs a message with a move and a main parameter.
     *
     * @param textMove the move to be forwarded to the server.
     * @param mainParam the main parameter of the move.
     *
     */
    public ClientMessage(String textMove, int mainParam) {
        this.textMove = textMove;
        this.mainParam = mainParam;
        this.additionalParam = 0;
        this.additionalParams = new ArrayList<>();
    }
    /**
     *
     * Constructs a message with a move, a main parameter and some additional parameters.
     *
     * @param textMove the move to be forwarded to the server.
     * @param mainParam the main parameter of the move.
     * @param additionalParam parameters used by the move.
     *
     */
    public ClientMessage(String textMove, int mainParam, int additionalParam){
        this.textMove = textMove;
        this.mainParam = mainParam;
        this.additionalParam = 0;
        this.additionalParam= additionalParam;
    }

    public ClientMessage(String textMove, String mainParamS){
        this.textMove = textMove;
        this.mainParamS = mainParamS;
        this.additionalParam = 0;
    }

    public ClientMessage(String textMove, int mainParam, List<Integer> additionalParams) {
        this.textMove = textMove;
        this.mainParam = mainParam;
        this.additionalParam = 0;
        this.additionalParams = new ArrayList<>(additionalParams);
    }
    /**
     *
     * The addParam method can add parameters to the additional parameters of the move.
     *
     * @param params one or more parameters to be added to the list.
     *
     */
    public void addParam(int... params) {
        for (int param : params) {
            additionalParams.add(param);
        }
    }
    /**
     *
     * The getTextMove is used to obtain the textual representation of the user move.
     *
     * @return the String representing the move.
     *
     */
    public String getTextMove() {
        return textMove;
    }
    /**
     *
     * The getMainParam is used to obtain the main parameter of the user move.
     *
     * @return the integer representing the main parameter of the move.
     *
     */

    public int getMainParam() {
        return mainParam;
    }

    public int getAddParams(){ return additionalParam;}

    /**
     *
     * The getAdditionalParams is used to obtain the additional parameters of the user move.
     *
     * @return the list of integers representing the additional parameters of the move.
     *
     */

    public List<Integer> getAdditionalParams() {
        return additionalParams;
    }
    public String getMainParamS(){return mainParamS;}
}
