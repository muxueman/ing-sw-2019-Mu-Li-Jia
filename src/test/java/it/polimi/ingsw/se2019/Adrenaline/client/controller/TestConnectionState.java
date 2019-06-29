package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.ConnectionState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLIView;
import it.polimi.ingsw.se2019.Adrenaline.client.view.View;

import java.util.Scanner;

public class TestConnectionState {


    View view = new CLIView(System.out);
    ClientController controller = new ClientController(view);
    ConnectionState state = new ConnectionState(controller);


    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    //ControllerState nextState = state.update(input);
    //System.out.(nextState)


}
