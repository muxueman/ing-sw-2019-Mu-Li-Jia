package it.polimi.ingsw.se2019.Adrenaline.network;

/**
 * The remote object is present on the server for the client to call
 * 只有在远程接口中声明的方法才能从远程调用，其他的公共方法只能在本地虚拟机中使用
 * 子接口的每个方法都必须声明抛出java.rmi.RemoteException异常，该异常是使用RMI时可能抛出的大多数异常的父类。
 * 子接口的实现类应该直接或者间接继承java.rmi.server.UnicastRemoteObject类
 * @author Xueman Mu
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote{

    //server provided by server
    GameServerInterface addClient(ClientInterface client) throws RemoteException;
}