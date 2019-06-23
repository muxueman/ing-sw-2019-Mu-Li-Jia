package it.polimi.ingsw.se2019.Adrenaline.client.view;

import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.server.model.map.Map;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;


import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class CLIView extends View {

    private boolean active;
    private PrintStream out;

    public CLIView(PrintStream out) {
        active = true;
        this.out = out;
        (new InputHandler()).start();
    }

    private class InputHandler extends Thread {

        @Override
        public void run() {
            try(Scanner in = new Scanner(System.in)) {
                while (isActive()) {
                    String input = in.nextLine();
                    CLIView.this.notify(input);
                }
            }
        }
    }
    public boolean isActive() {
        return active;
    }


    public void showMessage(String message) {
        print(message);
    }
    public void reportError(String error) {
        showMessage("Error: " + error);
    }

    //@Override
    public void showStatus(Status status) {
        print(status);
    }

    //public abstract void showRanking(Ranking ranking);

    @Override
    public InitialViewController getGuiController() {
        return null;
    }

    //@Override
    public void chooseMap(List<Map> maps) {
        for (int i = 0; i < maps.size(); i++) {
            print("map number " + (i+1));
            print(maps.get(i));
        }
    }

    private void print(Object object){
        out.println(object);
    }


    private void print(Status status){
        out.println(status);
//        if () {
//            out.println(status.toAnsi().fg(Ansi.Color.DEFAULT));
//        } else out.println(status.toString());
    }

    @Override
    public void update(ModelUpdate message) {
        print(message.getMessage());
        Logger.getGlobal().info("update model update");
        List<Status> statusUpdates = message.getStatusUpdates();
        for (Status status : statusUpdates) {
            if (status != null) {
                print(status);
            }
        }
    }







}
