package it.polimi.ingsw.se2019.Adrenaline.server.model;

import org.junit.Test;

import java.util.*;

public class TestColor {

    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));

    @Test
    public synchronized void test() {
        //shuffle the colors
        colors.remove(Color.RED);
        System.out.println(colors);
        Collections.shuffle(colors);
        Color color = colors.get(0);
        colors.remove(0);
        System.out.println(color);
        System.out.println(colors);



    }
}
