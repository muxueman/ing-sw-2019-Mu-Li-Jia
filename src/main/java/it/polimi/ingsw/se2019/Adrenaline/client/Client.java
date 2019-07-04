package it.polimi.ingsw.se2019.Adrenaline.client;

//represent a client

import it.polimi.ingsw.se2019.Adrenaline.client.controller.ClientController;
import it.polimi.ingsw.se2019.Adrenaline.client.view.CLI.CLIView;
import it.polimi.ingsw.se2019.Adrenaline.client.view.GUI.ClientGui;
import javafx.application.Application;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

/**
 *
 * The Client class is the representation of a client.
 * @author Xueman Mu
 *
 */

public class Client {

    //choose to play with cli or gui

    /**
     *
     * The chooseView method is used to ask to the user if he want to play with the CLI
     * or with the GUI.
     * @param input is a string containing the user's answer.
     * @return true if the decision is done, false if not.
     *
     */
    private static boolean chooseView(String input) {
        if (input.equalsIgnoreCase("cli") || input.equalsIgnoreCase("c")) {
            new ClientController(new CLIView(System.out));
        } else if (input.equalsIgnoreCase("gui") || input.equalsIgnoreCase("g")) {
            Application.launch(ClientGui.class);
        } else {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println( ansi().eraseScreen().fg(RED).a("Welcome").fg(YELLOW).a(" To").fg(BLUE).a(" Adrenaline").fg(GREEN).a("!").reset() );
        System.out.println( "\u001b[1;31m Wel" + "\u001b[1;33mcome" + "\u001b[1;35m To" + "\u001b[1;36m Adrena" + "\u001b[1;34mline" + "\u001b[1;38m!");
        System.out.println("\033[34;4m" + "Do you want to play on a CLI (Command Line Interface) or on a GUI (Graphic User Interface)?" + "\033[0m");
        //input is a string containing the user's answer.
        String input = scanner.nextLine();
        while (!chooseView(input)) {
            System.out.println("Please answer with \"cli\" (\"c\") or \"gui\" (\"g\"):");
            input = scanner.nextLine();

        }
    }
}

