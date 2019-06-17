package it.polimi.ingsw.se2019.Adrenaline.client;

//represent a client

import javafx.application.Application;

import java.util.Scanner;

public class Client {



    //choose to play with cli or gui
    private static boolean chooseView(String input) {
        if (input.equalsIgnoreCase("cli") || input.equalsIgnoreCase("c")) {
            //new Controller(new CLIView(System.out, colors));
        } else if (input.equalsIgnoreCase("gui") || input.equalsIgnoreCase("g")) {
            Application.launch(ClientGui.class);
        } else {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Adrenaline!");
        System.out.println("Do you want to play on a CLI (Command Line Interface) or on a GUI (Graphic User Interface)?");
        //input is a string containing the user's answer.
        String input = scanner.nextLine();
        while (!chooseView(input)) {
            System.out.println("Please answer with \"cli\" (\"c\") or \"gui\" (\"g\"):");
            input = scanner.nextLine();
        }
    }
}
