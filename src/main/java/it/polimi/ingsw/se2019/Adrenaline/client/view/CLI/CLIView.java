package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;


import it.polimi.ingsw.se2019.Adrenaline.client.model.ModelUpdate;
import it.polimi.ingsw.se2019.Adrenaline.client.view.View;
import it.polimi.ingsw.se2019.Adrenaline.utils.immutables.Status;


import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 *
 * @author li xuejing
 *
 */

public class CLIView extends View {

    private boolean active;
    private PrintStream out;

    /**
     *
     * Constructs a CLIView method with a PrintStream and a boolean.
     *
     * @param  out PrintStream.
     *
     */
    public CLIView(PrintStream out) {
        active = true;
        this.out = out;
        (new InputHandler()).start();
    }

    /**
     *
     * The InputHandler class that extends a Thread, and
     * controls the input on the cli.
     *
     */

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

    /**
     *
     * The isActive method is used to know if the active
     * variable is true or false.
     *
     * @return an bool
     *
     */

    public boolean isActive() {
        return active;
    }

    /**
     *
     * The showMessage method is used to show a message on the view, either on
     * a CLI or on a GUI, depending on the implementation.
     *
     * @param message the String message to show.
     *
     */
    public void showMessage(String message) {
        print(message);
    }

    /**
     *
     * The reportError method is used to report an error to the user on the view.
     *
     * @param error the String error message to be reported.
     *
     */

    public void reportError(String error) {
        showMessage("\u001b[1;31mError: " + error);
    }
    /**
     *
     * The print method is used to print out a object.
     * @param object object to be printed.
     *
     */

    private void print(Object object){
        out.println("\u001b[1;34m"+ object);
    }
    /**
     *
     * The print method is used to print out a status.
     *
     * @param status status to be printed.
     *
     */

    private void print(Status status){
//        if () {
//            out.println(status.toAnsi().fg(Ansi.Color.DEFAULT));
//        } else out.println(status.toString());
    }

    /**
     *
     * update message
     *
     * @param message
     */

    @Override
    public void update(ModelUpdate message) {
        print(message.getMessage());
        List<Status> statusUpdates = message.getStatusUpdates();
        for (Status status : statusUpdates) {
            if (status != null) {
                print(status);
            }
        }
    }

}
