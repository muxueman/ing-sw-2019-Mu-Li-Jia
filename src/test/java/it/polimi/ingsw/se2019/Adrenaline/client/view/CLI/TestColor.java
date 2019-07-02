package it.polimi.ingsw.se2019.Adrenaline.client.view.CLI;
import org.junit.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class TestColor {
    @Test
    public void TestColor(){
        System.out.println("\033[30;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[31;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[32;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[33;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[34;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[35;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[36;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[37;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[40;31;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[41;32;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[42;33;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[43;34;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[44;35;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[45;36;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[46;37;4m" + "我滴个颜什" + "\033[0m");
        System.out.println("\033[47;4m" + "我滴个颜什" + "\033[0m");
        System.out.println( ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );
        System.out.println("\u001b[1;31m Hello red world!" + "\u001b[1;32m 32" + "\u001b[1;33m 33" +
                "\u001b[1;34m 34" + "\u001b[1;35m 35" +"\u001b[1;36m 36" +"\u001b[1;37m 37" +
                "\u001b[1;38m 38" + "\u001b[1;39m 39" + "\u001b[1;40m 40" +
                "\u001b[1;41m 41" + "\u001b[1;30m 30" +"\u001b[1;29m 29" +"\u001b[1;28m 28"+ "\n" +
                "\u001b[1;42m 42" + "\u001b[1;43m 43" +"\u001b[1;44m 44" +"\u001b[1;45m 45"+ "\n" +
                "\u001b[1;46m 46" + "\u001b[1;47m 47" +"\u001b[1;48m 48" +"\u001b[1;49m 49"+ "\n"
        );

        //map
        System.out.println("A:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;41m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "B:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;48m   "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;45m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                + "\u001b[1;47m 10" + "\u001b[1;47m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "C:\n" + "\u001b[1;46m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;41m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;48m   "
                + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n" +
                "D:\n" + "\u001b[1;41m 1 " + "\u001b[1;46m 2 " + "\u001b[1;46m 3 " + "\u001b[1;42m 4 "
                + "\u001b[1;48m\n" + "\u001b[1;41m 5 " + "\u001b[1;45m 6 " + "\u001b[1;43m 7 " + "\u001b[1;43m 8 " + "\u001b[1;48m\n" + "\u001b[1;47m 9 "
                + "\u001b[1;47m 10" + "\u001b[1;43m 11" + "\u001b[1;43m 12"+ "\u001b[1;48m\n"
        );
    }
}

