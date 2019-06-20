package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.network.messages.ServerMessage;

public class ConnectionState extends ControllerState{

    private int selectedConnection; // 1 for RMI, 2 for Socket
    private String host;
    private int port;

    //basic info about connection state
    public ConnectionState(ClientController controller) {
        super(controller, "Choose the connection type:\n" +
                "1. RMI Connection\n" +
                "2. Socket Connection");
        selectedConnection = 0;
        host = null;
        port = 0;
    }

    @Override
    public ControllerState update(String message) {
        int intMessage;
        try {
            intMessage = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            intMessage = -1;
        }
        //第一次选择connection type
        if (selectedConnection == 0 && (intMessage == 1 || intMessage == 2)) {
            selectedConnection = intMessage;
            clientController.sendToView("Insert host ip (\"cancel\" to select the connection type again):");
            return this;
        }
        //取消选择connection type
        else if (selectedConnection != 0 &&  host == null) {
            if (message.equalsIgnoreCase("CANCEL")) {
                selectedConnection = 0;
                clientController.sendToView("Changing connection type, choose:\n" +
                        "1. RMI Connection\n" +
                        "2. Socket Connection");
                return this;
            }
            if (message.equals("")) {
                message = "localhost";
            }
            host = message;
            clientController.sendToView("Insert port: ");
            return this;
        } else if (host != null && port == 0 && intMessage != -1) {
            port = intMessage;
            clientController.sendToView("Connecting to server...");
            if (clientController.connect(selectedConnection, host, port)) {
                clientController.nextView(false);
                return new PlayerSetupState(clientController).initState();
            } else {
                host = null;
                port = 0;
                clientController.reportError("Can't connect to the server. Insert host ip: ");
                return this;
            }
        }
        clientController.reportError("Insert valid inputs, please.");
        return this;
    }


    @Override
    public ControllerState updateStatus(ServerMessage serverMessage) {
        if (serverMessage.isError()) {
            clientController.reportError(serverMessage.getMessage());
        }
        return nextState(serverMessage.isError(), serverMessage.isPlaying());
    }
}
