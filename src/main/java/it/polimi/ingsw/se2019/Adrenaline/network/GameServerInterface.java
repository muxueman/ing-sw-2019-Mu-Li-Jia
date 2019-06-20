package it.polimi.ingsw.se2019.Adrenaline.network;

/**
 *
 * @author Xueman Mu
 */

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ClientMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServerInterface extends Remote {

    GameServerInterface update(ClientMessage message, ClientInterface client) throws RemoteException;
}