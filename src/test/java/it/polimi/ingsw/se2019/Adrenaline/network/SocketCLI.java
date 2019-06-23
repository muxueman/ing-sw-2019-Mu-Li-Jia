package it.polimi.ingsw.se2019.Adrenaline.network;

import it.polimi.ingsw.se2019.Adrenaline.client.Client;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.controller.SocketController;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLIView;
import it.polimi.ingsw.se2019.Adrenaline.server.Server;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCLI {
    @Test
    public void connectTest(){
        Server server = new Server();

        ClientController client1 = new ClientController(new CLIView(System.out));
        ClientController client2 = new ClientController(new CLIView(System.out));
        ClientController client3 = new ClientController(new CLIView(System.out));
        ClientController client4 = new ClientController(new CLIView(System.out));

    }

}
