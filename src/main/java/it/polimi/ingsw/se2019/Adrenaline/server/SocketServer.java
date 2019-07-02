package it.polimi.ingsw.se2019.Adrenaline.server;

/**
 * socket server
 * RMI的基础是接口，RMI构架基于一个重要的原理：定义接口和定义接口的具体实现是分开的。
 * @author Xueman Mu
 */

import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.logging.Logger;

public class SocketServer extends Thread {

    private final Server server;
    private ServerSocket serverSocket;

    SocketServer(Server server, int port) {
        this.server = server;
        try {
            serverSocket = new ServerSocket(port);
            //System.out.println("Socket Server HOST: " + serverSocket.getInetAddress());
        } catch (IOException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            Socket newClientConnection;
            try {
                //使用 ServerSocket 监听某一端口，然后等待连接获取 Socket对象
                newClientConnection = serverSocket.accept();
                Logger.getGlobal().info("CLIENT CONNECTION, FROM: " + newClientConnection.getInetAddress() + ", PORT: " + newClientConnection.getPort());
                // Every time there is a connection established, add that to the client connection list
                server.addClient(newClientConnection);
            } catch (IOException e) {
                Logger.getGlobal().warning(e.getMessage());
            }
        }
    }

}