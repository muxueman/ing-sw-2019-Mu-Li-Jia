package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import it.polimi.ingsw.se2019.Adrenaline.client.controller.controllerState.ActionSelectState;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.CLIView;
import it.polimi.ingsw.se2019.Adrenaline.server.model.action.ActionRun;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class TestActionSelectState {
    String s = "run, grab, end round";
    String a ="run,grab,shoot,end round";


    ClientController client = new ClientController(new CLIView(System.out));
    ArrayList<String> previousActions = new ArrayList<>();

    @Test
    public void testActionSelect() {

        //Scanner scanner = new Scanner(System.in);

        String[] message = s.split(",");
        System.out.println(message[0]);
        System.out.println(message[1]);
        System.out.println(message[2]);

        ArrayList<String> selectedActions = new ArrayList<>();
        String[] message1 = a.split(",");
        for (String m : a.split(",")) {
            selectedActions.add(m);
        }
        System.out.println(selectedActions.contains("grab"));


        //
        ActionSelectState a = new ActionSelectState(client,new ArrayList<>());
        System.out.println(a.getPreviousActions());
        System.out.println(a.addChoice());

        a.getPreviousActions().add("run");
        System.out.println(a.getPreviousActions());
        System.out.println(a.addChoice());

        a.getPreviousActions().add("run");
        System.out.println(a.getPreviousActions());
        System.out.println(a.addChoice());

        a.getPreviousActions().add("run");
        System.out.println(a.getPreviousActions());
        System.out.println(a.addChoice());

        a.getPreviousActions().add("run");
        System.out.println(a.getPreviousActions());
        System.out.println(a.addChoice());

    }

    @Test
    public void test(){

    }
}
