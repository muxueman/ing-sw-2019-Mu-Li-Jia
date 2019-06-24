package it.polimi.ingsw.se2019.Adrenaline.client.controller;

import org.junit.Test;

public class TestActionSelectState {
    String s = "run, grab, end round";

    @Test
    public void testActionSelect(){
        String[] message = s.split(",");
        System.out.println(message[0]);
        System.out.println(message[1]);
        System.out.println(message[2]);
    }
}
